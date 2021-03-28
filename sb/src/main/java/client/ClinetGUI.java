package client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextField;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;

import SB.grpc.APIAllC;
import SB.grpc.APICResponse;
import SB.grpc.APICleanAllRoomsResponse;
import SB.grpc.APICleanSelectedRoomsResponse;
import SB.grpc.APILightsResponse;
import SB.grpc.APISwitchOffAllLights;
import SB.grpc.AllC;
import SB.grpc.CRequest;
import SB.grpc.CleanAllRooms;
import SB.grpc.CleanSelectedRooms;
import SB.grpc.SwitchOffAllLights;
import SB.grpc.SwitchRequest;
import SB.grpc.alarmGrpc;
import SB.grpc.alarmGrpc.alarmBlockingStub;
import SB.grpc.alarmGrpc.alarmStub;

import SB.grpc.lightsGrpc;
import SB.grpc.lightsGrpc.lightsBlockingStub;
import SB.grpc.lightsGrpc.lightsStub;
import SB.grpc.roombaGrpc;
import SB.grpc.roombaGrpc.roombaBlockingStub;
import SB.grpc.roombaGrpc.roombaStub;
import SB.grpc.curtainsGrpc;
import SB.grpc.curtainsGrpc.curtainsBlockingStub;
import SB.grpc.curtainsGrpc.curtainsStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ClinetGUI extends JFrame 
{
	private static lightsBlockingStub blockingStub;
	private static lightsStub asyncStub;
	private ServiceInfo lightsServiceInfo;
	
	private static alarmBlockingStub alarmBlockingStub;
	private static alarmStub alarmAsyncStub;
	private ServiceInfo alarmServiceInfo;
	
	private static curtainsBlockingStub curtainsBlockingStub;
	private static curtainsStub curtainsAsyncStub;
	private ServiceInfo curtainsServiceInfo;
	
	private static roombaBlockingStub roombaBlockingStub;
	private static roombaStub roombaAsyncStub;
	private ServiceInfo roombaServiceInfo;
	
	
	private JFrame frame;
	private JTextArea txtStatus ;
	private JLabel lblStatus;
	public JTextArea txtroombajobs;
	
	public static String currentRoom;
	public static int currentRoomNumber;
	
	boolean isLabelVisible;
	
	public static int hallwayroomba = 0;
	public static int kitchenroomba = 1;
	public static int wcroomba = 2;
	public static int livingroomroomba = 3;
	public static int bedroom1roomba = 4;
	public static int bedroom2roomba = 5;
	
	public static ArrayList <String> roomjobs = new ArrayList<>();
	public static String hallway = "hallway";
	public static String kitchen = "kitchen";
	public static String wc = "bathroom";
	public static String livingroom = "living room";
	public static String bedroom1 = "bedroom 1";
	public static String bedroom2 = "bedroom 2";
	
	public ClinetGUI() 
	{
		
		// lights service discovery
		String lights_service_type = "_lights._tcp.local.";
		discoverLightsService(lights_service_type);
		
		String host = lightsServiceInfo.getHostAddresses()[0];
		int port = lightsServiceInfo.getPort();
		
		// set up communication channels
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		blockingStub = lightsGrpc.newBlockingStub(channel);
		asyncStub = lightsGrpc.newStub(channel);
		
		
		// alarm service discovery
		String alarm_service_type = "_alarm._tcp.local.";
		discoverAlarmService(alarm_service_type);
		
		String host3 = alarmServiceInfo.getHostAddresses()[0];
		int port3 = alarmServiceInfo.getPort();
		
		ManagedChannel channel3 = ManagedChannelBuilder.forAddress(host3, port3).usePlaintext().build();
		
		alarmBlockingStub = alarmGrpc.newBlockingStub(channel);
		alarmAsyncStub = alarmGrpc.newStub(channel);
		
		
		//curtains service discovery
		String curtain_service_type = "_curtains._tcp.local.";
		discoverCurtainService(curtain_service_type);
		
		String host4 = curtainsServiceInfo.getHostAddresses()[0];
		int port4 = curtainsServiceInfo.getPort();
		
		// set up communication channels
		ManagedChannel channel4 = ManagedChannelBuilder.forAddress(host4, port4).usePlaintext().build();
		
		curtainsBlockingStub = curtainsGrpc.newBlockingStub(channel4);
		curtainsAsyncStub = curtainsGrpc.newStub(channel4);
		
		
		
		// roomba service discovery
		String roomba_service_type = "_roomba._tcp.local.";
		discoverRoombaService(roomba_service_type);
		
		String host1 = roombaServiceInfo.getHostAddresses()[0];
		int port1 = roombaServiceInfo.getPort();
		
		// set up communication channels
		ManagedChannel channel1 = ManagedChannelBuilder.forAddress(host1, port1).usePlaintext().build();
		
		roombaBlockingStub = roombaGrpc.newBlockingStub(channel1);
		roombaAsyncStub = roombaGrpc.newStub(channel1);

		// call method for initializing GUI elements
		initialize();
	}

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					// display GUI window
					ClinetGUI window = new ClinetGUI();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	// method containing all GUI elements for the java client
	public void initialize()
	{
		// main GUI frame declaration
		frame = new JFrame();
		// background
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\Kruno\\eclipse-workspace-1\\housemap.png"));
		// set background
		frame.setContentPane(label);
		// title
		frame.setTitle("Smart Building Client - Service Controller");
		// size
		frame.setBounds(100, 100, 1379, 1014);
		// what to do when GUI is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// layout for GUI
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		// setting layout
		frame.getContentPane().setLayout(null);
		// icon for blinds
		ImageIcon blinds = new ImageIcon("C:\\Users\\Kruno\\eclipse-workspace-1\\eye.png");
		// label declaration (same pattern for the rest below)
		JLabel bbr1 = new JLabel("New label");
		// label size and position
		bbr1.setBounds(328, 247, 56, 53);
		// add label to GUI frame
		frame.getContentPane().add(bbr1);
		// add icon to label
		bbr1.setIcon(blinds);
		// set label as invisible
		bbr1.setVisible(false);
		
		JLabel bbr2 = new JLabel("New label");
		bbr2.setBounds(328, 588, 56, 53);
		frame.getContentPane().add(bbr2);
		bbr2.setIcon(blinds);
		bbr2.setVisible(false);
		
		JLabel bh = new JLabel("New label");
		bh.setBounds(328, 775, 56, 53);
		frame.getContentPane().add(bh);
		bh.setIcon(blinds);
		bh.setVisible(false);
		
		JLabel bk = new JLabel("New label");
		bk.setBounds(820, 63, 56, 53);
		frame.getContentPane().add(bk);
		bk.setIcon(blinds);
		bk.setVisible(false);
		
		JLabel blr = new JLabel("New label");
		blr.setBounds(892, 588, 56, 53);
		frame.getContentPane().add(blr);
		blr.setIcon(blinds);
		blr.setVisible(false);
		
		JLabel bwc = new JLabel("New label");
		bwc.setBounds(892, 775, 56, 53);
		frame.getContentPane().add(bwc);
		bwc.setIcon(blinds);
		bwc.setVisible(false);
		// icon for roomba
		ImageIcon roomba = new ImageIcon("C:\\Users\\Kruno\\eclipse-workspace-1\\roomba.png");
		
		JLabel rbr1 = new JLabel("New label");
		rbr1.setBounds(238, 247, 50, 53);
		frame.getContentPane().add(rbr1);
		rbr1.setIcon(roomba);
		rbr1.setVisible(false);
		
		JLabel rbr2 = new JLabel("New label");
		rbr2.setBounds(238, 588, 50, 53);
		frame.getContentPane().add(rbr2);
		rbr2.setIcon(roomba);
		rbr2.setVisible(false);
		
		JLabel rh = new JLabel("New label");
		rh.setBounds(238, 775, 50, 53);
		frame.getContentPane().add(rh);
		rh.setIcon(roomba);
		rh.setVisible(false);
		
		JLabel rwc = new JLabel("New label");
		rwc.setBounds(794, 775, 50, 53);
		frame.getContentPane().add(rwc);
		rwc.setIcon(roomba);
		rwc.setVisible(false);
		
		JLabel rlr = new JLabel("New label");
		rlr.setBounds(794, 588, 50, 53);
		frame.getContentPane().add(rlr);
		rlr.setIcon(roomba);
		rlr.setVisible(false);
		
		JLabel rk = new JLabel("New label");
		rk.setBounds(729, 63, 50, 53);
		frame.getContentPane().add(rk);
		rk.setIcon(roomba);
		rk.setVisible(false);
		// icon for light
		ImageIcon bulb = new ImageIcon("C:\\Users\\Kruno\\eclipse-workspace-1\\bulb.jpg");
		
		JLabel lbr2 = new JLabel("New label");
		lbr2.setBounds(126, 588, 50, 53);
		frame.getContentPane().add(lbr2);
		lbr2.setIcon(bulb);
		lbr2.setVisible(false);
		
		JLabel lh = new JLabel("New label");
		lh.setBounds(126, 775, 50, 53);
		frame.getContentPane().add(lh);
		lh.setIcon(bulb);
		lh.setVisible(false);
		
		JLabel lbr1 = new JLabel("New label");
		lbr1.setBounds(126, 247, 50, 53);
		frame.getContentPane().add(lbr1);
		lbr1.setIcon(bulb);
		lbr1.setVisible(false);
		
		JLabel lwc = new JLabel("New label");
		lwc.setBounds(691, 775, 50, 53);
		frame.getContentPane().add(lwc);
		lwc.setIcon(bulb);
		lwc.setVisible(false);
		
		JLabel llr = new JLabel("New label");
		llr.setBounds(691, 588, 50, 53);
		frame.getContentPane().add(llr);
		llr.setIcon(bulb);
		llr.setVisible(false);
		
		JLabel lk = new JLabel("New label");
		lk.setBounds(621, 63, 50, 53);
		frame.getContentPane().add(lk);
		lk.setIcon(bulb);
		lk.setVisible(false);
		
		// text area for appending roomba jobs
		JTextArea txtroombajobs = new JTextArea();
		txtroombajobs.setBounds(1066, 431, 270, 224);
		frame.getContentPane().add(txtroombajobs);
		// text area for appending other actions
		JTextArea txtStatus = new JTextArea();
		txtStatus.setBounds(1066, 45, 270, 320);
		frame.getContentPane().add(txtStatus);
		// button for swiching off all lights
		JButton btnTurnOffAllLights = new JButton("Turn off all lights");
		btnTurnOffAllLights.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// pass key
						String toall = "toall";
						// create communication for server
						SwitchOffAllLights req = SwitchOffAllLights.newBuilder().setSwitchOffAll(toall).build();
						StreamObserver<APISwitchOffAllLights> responseObserver = new StreamObserver<APISwitchOffAllLights>()
								{
									@Override
									public void onNext(APISwitchOffAllLights value) 
									{
										System.out.println("Getting message from server: " + value);
									}

									@Override
									public void onError(Throwable t) 
									{
										t.printStackTrace();
									}

									@Override
									public void onCompleted() 
									{
										System.out.println("Turning lights off...");
									}
								};
								// send to server and update GUI
								asyncStub.lightswitchall(req, responseObserver);
								txtStatus.append("All lights are now off." + "\n");
								try 
								{
									Thread.sleep(15000);
								} 
								catch (InterruptedException ie) 
								{
									ie.printStackTrace();
								}
								// hide all labels
								lh.setVisible(false);
								lk.setVisible(false);
								llr.setVisible(false);
								lwc.setVisible(false);
								lbr1.setVisible(false);
								lbr2.setVisible(false);
					}
				}
				);
		
		btnTurnOffAllLights.setBounds(53, 911, 207, 37);
		frame.getContentPane().add(btnTurnOffAllLights);
		
		// individual button for turning off the lights in particular room (same patter for the rest)
		JButton btnlightsbr2 = new JButton("Lights");
		btnlightsbr2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// enum for status and room
				int light = 1;
				int room = 5;
				// create request for server
				SwitchRequest req = SwitchRequest.newBuilder().setLightsroom(room).setLightsstatus(light).build();
				APILightsResponse res = blockingStub.lightswitch(req);
				// data returned from server
				String resmsg = String.valueOf(res.getResponsemessage());
				String rescode = String.valueOf(res.getResponsecode());
				// display info in logs and GUI
				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				// update labels
				isLabelVisible = lbr2.isVisible();
				if(isLabelVisible == false)
				{
					lbr2.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					lbr2.setVisible(false);
				}
			}
		});
		btnlightsbr2.setBounds(113, 650, 89, 23);
		frame.getContentPane().add(btnlightsbr2);
		
		JButton btnroombabr2 = new JButton("Vacuum");
		btnroombabr2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// add a job to the list of jobs
				addRommbaJob(bedroom2);
				// update labels
				isLabelVisible = rbr2.isVisible();
				if(isLabelVisible == false)
				{
					rbr2.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					rbr2.setVisible(false);
				}
			}
		});
		btnroombabr2.setBounds(212, 650, 89, 23);
		frame.getContentPane().add(btnroombabr2);
		
		JButton btnblindsbr2 = new JButton("Blinds");
		btnblindsbr2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// enum for blinds status and room
				int blind = 1;
				int room = 5;
				// create request for server
				CRequest req = CRequest.newBuilder().setCroom(room).setCstatus(blind).build();
				APICResponse res = curtainsBlockingStub.cswitch(req);
				// data returned from server
				String resmsg = String.valueOf(res.getCresponsemessage());
				String rescode = String.valueOf(res.getCresponsecode());
				// display info in logs and GUI
				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				// update labels
				isLabelVisible = bbr2.isVisible();
				if(isLabelVisible == false)
				{
					bbr2.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					bbr2.setVisible(false);
				}
			}
		});
		btnblindsbr2.setBounds(311, 650, 89, 23);
		frame.getContentPane().add(btnblindsbr2);
		
		JButton btnlightsbr1 = new JButton("Light");
		btnlightsbr1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int light = 1;
				int room = 4;

				SwitchRequest req = SwitchRequest.newBuilder().setLightsroom(room).setLightsstatus(light).build();
				APILightsResponse res = blockingStub.lightswitch(req);
				
				String resmsg = String.valueOf(res.getResponsemessage());
				String rescode = String.valueOf(res.getResponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = lbr1.isVisible();
				if(isLabelVisible == false)
				{
					lbr1.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					lbr1.setVisible(false);
				}
			}
		});
		btnlightsbr1.setBounds(113, 307, 89, 23);
		frame.getContentPane().add(btnlightsbr1);
		
		JButton btnroombabr1 = new JButton("Vacuum");
		btnroombabr1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRommbaJob(bedroom1);
				
				isLabelVisible = rbr1.isVisible();
				if(isLabelVisible == false)
				{
					rbr1.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					rbr1.setVisible(false);
				}
			}
		});
		btnroombabr1.setBounds(212, 307, 89, 23);
		frame.getContentPane().add(btnroombabr1);
		
		JButton btnblindsbr1 = new JButton("Blinds");
		btnblindsbr1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int blind = 1;
				int room = 4;

				CRequest req = CRequest.newBuilder().setCroom(room).setCstatus(blind).build();
				APICResponse res = curtainsBlockingStub.cswitch(req);
				
				String resmsg = String.valueOf(res.getCresponsemessage());
				String rescode = String.valueOf(res.getCresponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = bbr1.isVisible();
				if(isLabelVisible == false)
				{
					bbr1.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					bbr1.setVisible(false);
				}
			}
		});
		btnblindsbr1.setBounds(311, 307, 89, 23);
		frame.getContentPane().add(btnblindsbr1);
		
		JButton btnlightshall = new JButton("Lights");
		btnlightshall.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int light = 1;
				int room = 0;

				SwitchRequest req = SwitchRequest.newBuilder().setLightsroom(room).setLightsstatus(light).build();
				APILightsResponse res = blockingStub.lightswitch(req);
				
				String resmsg = String.valueOf(res.getResponsemessage());
				String rescode = String.valueOf(res.getResponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = lh.isVisible();
				if(isLabelVisible == false)
				{
					lh.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					lh.setVisible(false);
				}
			}
		});
		btnlightshall.setBounds(113, 834, 89, 23);
		frame.getContentPane().add(btnlightshall);
		
		JButton btnroombahall = new JButton("Vacuum");
		btnroombahall.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRommbaJob(hallway);
				
				isLabelVisible = rh.isVisible();
				if(isLabelVisible == false)
				{
					rh.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					rh.setVisible(false);
				}
			}
		});
		btnroombahall.setBounds(212, 834, 89, 23);
		frame.getContentPane().add(btnroombahall);
		
		JButton btnblindshall = new JButton("Blinds");
		btnblindshall.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int blind = 1;
				int room = 0;

				CRequest req = CRequest.newBuilder().setCroom(room).setCstatus(blind).build();
				APICResponse res = curtainsBlockingStub.cswitch(req);
				
				String resmsg = String.valueOf(res.getCresponsemessage());
				String rescode = String.valueOf(res.getCresponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = bh.isVisible();
				if(isLabelVisible == false)
				{
					bh.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					bh.setVisible(false);
				}
			}
		});
		btnblindshall.setBounds(311, 834, 89, 23);
		frame.getContentPane().add(btnblindshall);
		
		JButton btnlightskit = new JButton("Lights");
		btnlightskit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int light = 1;
				int room = 1;

				SwitchRequest req = SwitchRequest.newBuilder().setLightsroom(room).setLightsstatus(light).build();
				APILightsResponse res = blockingStub.lightswitch(req);
				
				String resmsg = String.valueOf(res.getResponsemessage());
				String rescode = String.valueOf(res.getResponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = lk.isVisible();
				if(isLabelVisible == false)
				{
					lk.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					lk.setVisible(false);
				}
			}
		});
		btnlightskit.setBounds(607, 119, 89, 23);
		frame.getContentPane().add(btnlightskit);
		
		JButton btnroombakit = new JButton("Vacuum");
		btnroombakit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRommbaJob(kitchen);
				
				isLabelVisible = rk.isVisible();
				if(isLabelVisible == false)
				{
					rk.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					rk.setVisible(false);
				}
			}
		});
		btnroombakit.setBounds(706, 119, 89, 23);
		frame.getContentPane().add(btnroombakit);
		
		JButton btnblindskit = new JButton("Blinds");
		btnblindskit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int blind = 1;
				int room = 1;

				CRequest req = CRequest.newBuilder().setCroom(room).setCstatus(blind).build();
				APICResponse res = curtainsBlockingStub.cswitch(req);
				
				String resmsg = String.valueOf(res.getCresponsemessage());
				String rescode = String.valueOf(res.getCresponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = bk.isVisible();
				if(isLabelVisible == false)
				{
					bk.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					bk.setVisible(false);
				}
			}
		});
		btnblindskit.setBounds(805, 119, 89, 23);
		frame.getContentPane().add(btnblindskit);
		
		JButton btnlightslr = new JButton("Lights");
		btnlightslr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int light = 1;
				int room = 3;

				SwitchRequest req = SwitchRequest.newBuilder().setLightsroom(room).setLightsstatus(light).build();
				APILightsResponse res = blockingStub.lightswitch(req);
				
				String resmsg = String.valueOf(res.getResponsemessage());
				String rescode = String.valueOf(res.getResponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = llr.isVisible();
				if(isLabelVisible == false)
				{
					llr.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					llr.setVisible(false);
				}
			}
		});
		btnlightslr.setBounds(677, 650, 89, 23);
		frame.getContentPane().add(btnlightslr);
		
		JButton btnroombalr = new JButton("Vacuum");
		btnroombalr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRommbaJob(livingroom);
				
				isLabelVisible = rlr.isVisible();
				if(isLabelVisible == false)
				{
					rlr.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					rlr.setVisible(false);
				}
			}
		});
		btnroombalr.setBounds(776, 650, 89, 23);
		frame.getContentPane().add(btnroombalr);
		
		JButton btnblindslr = new JButton("Blinds");
		btnblindslr.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int blind = 1;
				int room = 3;

				CRequest req = CRequest.newBuilder().setCroom(room).setCstatus(blind).build();
				APICResponse res = curtainsBlockingStub.cswitch(req);
				
				String resmsg = String.valueOf(res.getCresponsemessage());
				String rescode = String.valueOf(res.getCresponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = blr.isVisible();
				if(isLabelVisible == false)
				{
					blr.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					blr.setVisible(false);
				}
			}
		});
		btnblindslr.setBounds(875, 650, 89, 23);
		frame.getContentPane().add(btnblindslr);
		
		JButton btnlightswc = new JButton("Lights");
		btnlightswc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int light = 1;
				int room = 2;

				SwitchRequest req = SwitchRequest.newBuilder().setLightsroom(room).setLightsstatus(light).build();
				APILightsResponse res = blockingStub.lightswitch(req);
				
				String resmsg = String.valueOf(res.getResponsemessage());
				String rescode = String.valueOf(res.getResponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				isLabelVisible = lwc.isVisible();
				if(isLabelVisible == false)
				{
					lwc.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					lwc.setVisible(false);
				}
			}
		});
		btnlightswc.setBounds(677, 834, 89, 23);
		frame.getContentPane().add(btnlightswc);
		
		JButton btnroombawc = new JButton("Vacuum");
		btnroombawc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRommbaJob(wc);
				
				isLabelVisible = rwc.isVisible();
				if(isLabelVisible == false)
				{
					rwc.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					rwc.setVisible(false);
				}
			}
		});
		btnroombawc.setBounds(776, 834, 89, 23);
		frame.getContentPane().add(btnroombawc);
		
		JButton btnblindswc = new JButton("Blinds");
		btnblindswc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int blind = 1;
				int room = 2;

				CRequest req = CRequest.newBuilder().setCroom(room).setCstatus(blind).build();
				APICResponse res = curtainsBlockingStub.cswitch(req);
				
				String resmsg = String.valueOf(res.getCresponsemessage());
				String rescode = String.valueOf(res.getCresponsecode());

				System.out.println("Server: " + resmsg + " Status: " + rescode);
				txtStatus.append(resmsg + "\n");
				
				lbr2.setVisible(false);
				
				isLabelVisible = bwc.isVisible();
				if(isLabelVisible == false)
				{
					bwc.setVisible(true);
				}
				else if(isLabelVisible == true)
				{
					bwc.setVisible(false);
				}
			}
		});
		btnblindswc.setBounds(875, 834, 89, 23);
		frame.getContentPane().add(btnblindswc);
		
		JLabel lblLogs = new JLabel("Status logs");
		lblLogs.setBounds(1066, 20, 83, 14);
		frame.getContentPane().add(lblLogs);
		
		JLabel lblroombastatus = new JLabel("Roomba scheduled work");
		lblroombastatus.setBounds(1066, 406, 221, 14);
		frame.getContentPane().add(lblroombastatus);
		
		JButton btnroombastart = new JButton("Start vacuuming");
		btnroombastart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// checks whether there are scheduled jobs
				if(roomjobs.size() > 0)
				{
					// for each job on the list append GUI
					for (String job : roomjobs) 
					{ 
						txtroombajobs.append("The " + job + " is cleaned. \n");
					}
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException ex) 
					{
						ex.printStackTrace();
				}
				// start clean method
				clean();
				// update labels
				rh.setVisible(false);
				rk.setVisible(false);
				rlr.setVisible(false);
				rwc.setVisible(false);
				rbr1.setVisible(false);
				rbr2.setVisible(false);
				// empty array
				roomjobs.clear();
				}
				else
				{
					// if there are no scheduled jobs, inform user
					JOptionPane.showMessageDialog(null, "Please add cleaning jobs first.");
				}
			}
		}
		);
		btnroombastart.setBounds(1066, 666, 251, 23);
		frame.getContentPane().add(btnroombastart);
		
		JButton btnroombaallrooms = new JButton("Vacuum entire house");
		btnroombaallrooms.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// add all rooms to the job list
				roomjobs.add(hallway);
				roomjobs.add(kitchen);
				roomjobs.add(livingroom);
				roomjobs.add(wc);
				roomjobs.add(bedroom1);
				roomjobs.add(bedroom2);
				// for each job on the list append GUI
				for (String job : roomjobs) 
				{ 
					txtroombajobs.append("The " + job + " is cleaned. \n");
				}
				try
				{
					Thread.sleep(1000);
				}
					catch (InterruptedException ex) 
				{
						ex.printStackTrace();
				}
				// start clean method
				cleanAll();
				// update labels
				rh.setVisible(false);
				rk.setVisible(false);
				rlr.setVisible(false);
				rwc.setVisible(false);
				rbr1.setVisible(false);
				rbr2.setVisible(false);
				// empty array
				roomjobs.clear();
			}
		});
		btnroombaallrooms.setBounds(281, 911, 207, 37);
		frame.getContentPane().add(btnroombaallrooms);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// close GUI
				System.exit(0);
			}
		});
		
		btnExit.setBounds(1149, 911, 168, 37);
		frame.getContentPane().add(btnExit);
		
		JButton btnshutallblinds = new JButton("Shut all blinds");
		btnshutallblinds.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// pass key
				String toall = "toall";
				// create communication for server
				AllC req = AllC.newBuilder().setCAllrequest(toall).build();
				StreamObserver<APIAllC> responseObserver = new StreamObserver<APIAllC>()
						{
							@Override
							public void onNext(APIAllC value) 
							{
								System.out.println("Getting message from server: " + value);
							}

							@Override
							public void onError(Throwable t) 
							{
								t.printStackTrace();
							}

							@Override
							public void onCompleted() 
							{
								System.out.println("Shutting blinds...");
							}
						};
						// send to server and append reply
						curtainsAsyncStub.cswitchall(req, responseObserver);
						txtStatus.append("All blinds are now shut." + "\n");
						try 
						{
							Thread.sleep(15000);
						} 
						catch (InterruptedException ie) 
						{
							ie.printStackTrace();
						}
						// update labels
						bh.setVisible(true);
						bk.setVisible(true);
						blr.setVisible(true);
						bwc.setVisible(true);
						bbr1.setVisible(true);
						bbr2.setVisible(true);
			}
		}
		);
		btnshutallblinds.setBounds(512, 911, 207, 37);
		frame.getContentPane().add(btnshutallblinds);
		
		JButton btnAlarm = new JButton("Turn Alarm On");
		btnAlarm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// create runtime event
				Runtime rt = Runtime.getRuntime();
				try {
					// declare file path
					String path = "C:\\Users\\Kruno\\eclipse-workspace-1\\sb\\src\\main\\resources\\";
					// create & execute process from python file
					Process pr = rt.exec("python " + path + "AlarmClient.py");
					// append GUI
					txtStatus.append("Alarm has been activated." + "\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAlarm.setBounds(1066, 750, 251, 29);
		frame.getContentPane().add(btnAlarm);
		
		JButton btnTurnAlarmOff = new JButton("Turn Alarm Off");
		btnTurnAlarmOff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// create runtime event
				Runtime rt = Runtime.getRuntime();
				try {
					// declare file path
					String path = "C:\\Users\\Kruno\\eclipse-workspace-1\\sb\\src\\main\\resources\\";
					// create & execute process from python file
					Process pr = rt.exec("python " + path + "AlarmClient2.py");
					// append GUI
					txtStatus.append("Alarm has been deactivated." + "\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTurnAlarmOff.setBounds(1066, 787, 251, 29);
		frame.getContentPane().add(btnTurnAlarmOff);
		
		JLabel lblsec = new JLabel("Security");
		lblsec.setBounds(1066, 725, 134, 14);
		frame.getContentPane().add(lblsec);
	
	}
	
	
	// method for adding roomba job on the list
	public void addRommbaJob(String addedRoom)
	{
		// if there are no jobs, add job
		if(roomjobs.size() < 1)
		{
			roomjobs.add(addedRoom);
		}
		else
		{
			// checks whether the newly added job is already on the list. If it is, remove it. If not, add it
			for(int i=0;i<roomjobs.size();i++)
			{
				String roomname = roomjobs.get(i).toString();
				if(i == roomjobs.size()-1)
				{
					if(roomname.equals(addedRoom))
					{
						System.out.println("Already on list");
						System.out.println("Removed " + roomjobs.get(i).toString());
						roomjobs.remove(i);
						break;
					}
					else
					{
						roomjobs.add(addedRoom);
						break;
					}
				}
				else if(roomname.equals(addedRoom))
				{
					System.out.println("Already on list");
					System.out.println("Removed " + roomjobs.get(i).toString());
					roomjobs.remove(i);
					break;
				}
			}
		}
	}
	// method for jmDNS service discovery - lights
	private void discoverLightsService(String service_type) 
	{
		try 
		{
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(service_type, new ServiceListener()

			{
				@Override
				public void serviceResolved(ServiceEvent event) 
				{
					System.out.println("Lights Service resolved: " + event.getInfo());
					// get info from event
					lightsServiceInfo = event.getInfo();
					// service properties info
					int port = lightsServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + lightsServiceInfo.getNiceTextString());
					System.out.println("\t host: " + lightsServiceInfo.getHostAddresses()[0]);
				}
				@Override
				public void serviceRemoved(ServiceEvent event) 
				{
					System.out.println("Lights Service removed: " + event.getInfo());
				}
				@Override
				public void serviceAdded(ServiceEvent event) 
				{
					System.out.println("Lights Service added: " + event.getInfo());
				}
			});
			// Wait a bit
			Thread.sleep(2000);
			jmdns.close();
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	// method for jmDNS service discovery - alarm
	private void discoverAlarmService(String service_type) 
	{
		try 
		{
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(service_type, new ServiceListener()

			{
				@Override
				public void serviceResolved(ServiceEvent event) 
				{
					System.out.println("Alarm Service resolved: " + event.getInfo());
					// get info from event
					alarmServiceInfo = event.getInfo();
					// service properties info
					int port3 = alarmServiceInfo.getPort();
					
					System.out.println("resolving " + service_type + " with properties ...");
					System.out.println("\t port: " + port3);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + alarmServiceInfo.getNiceTextString());
					System.out.println("\t host: " + alarmServiceInfo.getHostAddresses()[0]);
				}
				@Override
				public void serviceRemoved(ServiceEvent event) 
				{
					System.out.println("Alarm Service removed: " + event.getInfo());
				}
				@Override
				public void serviceAdded(ServiceEvent event) 
				{
					System.out.println("Alarm Service added: " + event.getInfo());
				}
			});
			// Wait a bit
			Thread.sleep(2000);
			jmdns.close();
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	// method for jmDNS service discovery - blinds
	private void discoverCurtainService(String cservice_type) 
	{
		try 
		{
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(cservice_type, new ServiceListener()

			{
				@Override
				public void serviceResolved(ServiceEvent event) 
				{
					System.out.println("Curtains Service resolved: " + event.getInfo());
					// get info from event
					curtainsServiceInfo = event.getInfo();
					// service properties info
					int port4 = curtainsServiceInfo.getPort();
					
					System.out.println("resolving " + cservice_type + " with properties ...");
					System.out.println("\t port: " + port4);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + curtainsServiceInfo.getNiceTextString());
					System.out.println("\t host: " + curtainsServiceInfo.getHostAddresses()[0]);
				}
				@Override
				public void serviceRemoved(ServiceEvent event) 
				{
					System.out.println("Roomba Service removed: " + event.getInfo());
				}
				@Override
				public void serviceAdded(ServiceEvent event) 
				{
					System.out.println("Roomba Service added: " + event.getInfo());
				}
			});
			// Wait a bit
			Thread.sleep(2000);
			jmdns.close();
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	// method for jmDNS service discovery - roomba
	private void discoverRoombaService(String sservice_type) 
	{
		try 
		{
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(sservice_type, new ServiceListener()

			{
				@Override
				public void serviceResolved(ServiceEvent event) 
				{
					System.out.println("Roomba Service resolved: " + event.getInfo());
					// get info from event
					roombaServiceInfo = event.getInfo();
					// service properties info
					int port1 = roombaServiceInfo.getPort();
					
					System.out.println("resolving " + sservice_type + " with properties ...");
					System.out.println("\t port: " + port1);
					System.out.println("\t type:"+ event.getType());
					System.out.println("\t name: " + event.getName());
					System.out.println("\t description/properties: " + roombaServiceInfo.getNiceTextString());
					System.out.println("\t host: " + roombaServiceInfo.getHostAddresses()[0]);
				}
				@Override
				public void serviceRemoved(ServiceEvent event) 
				{
					System.out.println("Roomba Service removed: " + event.getInfo());
				}
				@Override
				public void serviceAdded(ServiceEvent event) 
				{
					System.out.println("Roomba Service added: " + event.getInfo());
				}
			});
			// Wait a bit
			Thread.sleep(2000);
			jmdns.close();
		} 
		catch (UnknownHostException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	// method for cleaning
	public static void clean()
	{
		// create communication for server
		StreamObserver<APICleanSelectedRoomsResponse> cleanResponseObserver = new StreamObserver<APICleanSelectedRoomsResponse>()
				{
					int roomcount = 0;
					@Override
					public void onNext(APICleanSelectedRoomsResponse value) 
					{
						System.out.println("Receiving room " + value.getRoom() + " indexed by number " + value.getRoomnumber() + " which is cleaned " + value.getRoompercent() + "%");
						roomcount++;
					}

					@Override
					public void onError(Throwable t) 
					{
						System.out.println("Throwable error occured:");
						t.printStackTrace();
					}

					@Override
					public void onCompleted() 
					{
						System.out.println("The stream is completed.");
					}
				};
		// async operations for cleaning
		StreamObserver<CleanSelectedRooms> cleanRequestObserver = roombaAsyncStub.clean(cleanResponseObserver);
		try 
		{
			// while there are scheduled jobs
			while(roomjobs.size() > 0)
			{
				int i = 0;
					currentRoom = roomjobs.get(i).toString();
					currentRoomNumber = 0;
					// room detection
					if(currentRoom.equals(hallway))
					{
						// assign values and send to server. Once done, remove from the list (same for all below)
						currentRoomNumber = hallwayroomba;
						cleanRequestObserver.onNext(CleanSelectedRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(kitchen))
					{
						currentRoomNumber = kitchenroomba;
						cleanRequestObserver.onNext(CleanSelectedRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(wc))
					{
						currentRoomNumber = wcroomba;
						cleanRequestObserver.onNext(CleanSelectedRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(livingroom))
					{
						currentRoomNumber = livingroomroomba;
						cleanRequestObserver.onNext(CleanSelectedRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));
	
					}
					else if(currentRoom.equals(bedroom1))
					{
						currentRoomNumber = bedroom1roomba;
						cleanRequestObserver.onNext(CleanSelectedRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(bedroom2))
					{
						currentRoomNumber = bedroom2roomba;
						cleanRequestObserver.onNext(CleanSelectedRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else
					{
						System.out.println("Selected room was not found.");
					}
			}
			cleanRequestObserver.onCompleted();
		} 
		catch (RuntimeException e) 
		{
			e.printStackTrace();
		} 
		try 
		{
			Thread.sleep(15000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	// method for cleaning all rooms
	public static void cleanAll()
	{
		// create communication with server
		StreamObserver<APICleanAllRoomsResponse> cleanResponseObserver = new StreamObserver<APICleanAllRoomsResponse>()
				{
					int roomcount = 0;
					@Override
					public void onNext(APICleanAllRoomsResponse value) 
					{
						System.out.println(value.getResponsemessage());
					}

					@Override
					public void onError(Throwable t) 
					{
						System.out.println("Throwable error occured:");
						t.printStackTrace();
					}

					@Override
					public void onCompleted() 
					{
						System.out.println("Completed");
					}
				};
		// server method
		StreamObserver<CleanAllRooms> cleanRequestObserver = roombaAsyncStub.cleanAll(cleanResponseObserver);
		try 
		{
			// same logic as the method above
			while(roomjobs.size()>0)
			{
				
			int i = 0;

					currentRoom = roomjobs.get(i).toString();
					currentRoomNumber = 0;
					
					if(currentRoom.equals(hallway))
					{
						currentRoomNumber = hallwayroomba;
						cleanRequestObserver.onNext(CleanAllRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(kitchen))
					{
						currentRoomNumber = kitchenroomba;
						cleanRequestObserver.onNext(CleanAllRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(wc))
					{
						currentRoomNumber = wcroomba;
						cleanRequestObserver.onNext(CleanAllRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(livingroom))
					{
						currentRoomNumber = livingroomroomba;
						cleanRequestObserver.onNext(CleanAllRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));
	
					}
					else if(currentRoom.equals(bedroom1))
					{
						currentRoomNumber = bedroom1roomba;
						cleanRequestObserver.onNext(CleanAllRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else if(currentRoom.equals(bedroom2))
					{
						currentRoomNumber = bedroom2roomba;
						cleanRequestObserver.onNext(CleanAllRooms.newBuilder().setRoom(currentRoom).setRoomnumber(currentRoomNumber).build());
						System.out.println(currentRoom + " was removed from roomjobs list " + roomjobs.remove(i));

					}
					else
					{
						System.out.println("Selected room was not found.");
					}
			}
			cleanRequestObserver.onCompleted();
		} 
		catch (RuntimeException e) 
		{
			e.printStackTrace();
		} 
		try 
		{
			Thread.sleep(15000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
