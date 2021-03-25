package client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextField;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;

import SB.grpc.APILightsResponse;
import SB.grpc.APISwitchOffAllLights;
import SB.grpc.SwitchOffAllLights;
import SB.grpc.SwitchRequest;
import SB.grpc.lightsGrpc;
import SB.grpc.lightsGrpc.lightsBlockingStub;
import SB.grpc.lightsGrpc.lightsStub;
import SB.grpc.roombaGrpc;
import SB.grpc.roombaGrpc.roombaBlockingStub;
import SB.grpc.roombaGrpc.roombaStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ClinetGUI extends JFrame 
{
	private static lightsBlockingStub blockingStub;
	private static lightsStub asyncStub;
	private ServiceInfo lightsServiceInfo;
	
	private static roombaBlockingStub roombaBlockingStub;
	private static roombaStub roombaAsyncStub;
	private ServiceInfo roombaServiceInfo;
	
	private JFrame frame;
	private JTextField txtLights;
	private JTextField txtRoomba;
	private JTextArea txtStatus ;
	private JLabel lblStatus;
	
	public ClinetGUI() 
	{
		try
		{
		// lights
		String lights_service_type = "_lights._tcp.local.";
		discoverLightsService(lights_service_type);
		
		String host = lightsServiceInfo.getHostAddresses()[0];
		int port = lightsServiceInfo.getPort();
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		
		blockingStub = lightsGrpc.newBlockingStub(channel);
		asyncStub = lightsGrpc.newStub(channel);
		}
		finally
		{
		
		// roomba
		String roomba_service_type = "_roomba._tcp.local.";
		discoverRoombaService(roomba_service_type);
		
		String host1 = roombaServiceInfo.getHostAddresses()[0];
		int port1 = roombaServiceInfo.getPort();
		
		ManagedChannel channel1 = ManagedChannelBuilder.forAddress(host1, port1).usePlaintext().build();
		
		roombaBlockingStub = roombaGrpc.newBlockingStub(channel1);
		roombaAsyncStub = roombaGrpc.newStub(channel1);
		initialize();
		}
		
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
	
	public void initialize()
	{
		frame = new JFrame();
		frame.setTitle("Smart Building Client - Service Controller");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		
		frame.getContentPane().setLayout(null);
		
		JLabel lbllights1 = new JLabel("Lights");
		lbllights1.setBounds(35, 56, 46, 14);
		frame.getContentPane().add(lbllights1);
		
		txtLights = new JTextField();
		txtLights.setBounds(91, 22, 86, 20);
		frame.getContentPane().add(txtLights);
		txtLights.setColumns(10);
		
		txtRoomba = new JTextField();
		txtRoomba.setBounds(91, 53, 86, 20);
		frame.getContentPane().add(txtRoomba);
		txtRoomba.setColumns(10);
		
		JLabel lblStatus = new JLabel("sts:");
		lblStatus.setBounds(91, 165, 135, 14);
		frame.getContentPane().add(lblStatus);
		
		JLabel lblRoomba1 = new JLabel("Roomba");
		lblRoomba1.setBounds(35, 165, 46, 14);
		frame.getContentPane().add(lblRoomba1);
		
		JButton btnLights = new JButton("Lights button");
		btnLights.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int num1 = Integer.parseInt(txtLights.getText());
						int num2 = Integer.parseInt(txtRoomba.getText());
						//String num1 = String.valueOf(num11);
						//String num2 = String.valueOf(num22);
						
						SwitchRequest req = SwitchRequest.newBuilder().setLightsroom(num1).setLightsstatus(num2).build();
						APILightsResponse res = blockingStub.lightswitch(req);
						
						String resmsg = String.valueOf(res.getResponsemessage());
						String rescode = String.valueOf(res.getResponsecode());
						
						//lblStatus.setText(lblStatus.getText() + "more text");
						
						//txtStatus.append("reply:"+ res.getResponsecode() + " mes:"+ res.getResponsemessage() + "\n");
						System.out.println("Server: " + resmsg + " Status: " + rescode);
						
					}
				}
				);
		btnLights.setBounds(187, 21, 89, 23);
		frame.getContentPane().add(btnLights);
		
		JButton btnRoomba = new JButton("Roomba button");
		btnRoomba.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					lblStatus.setText(lblStatus.getText() + "more text");
				}
			}
			);
		btnRoomba.setBounds(35, 131, 89, 23);
		frame.getContentPane().add(btnRoomba);
		
		JTextArea txtStatus = new JTextArea();
		txtStatus.setBounds(303, 25, 102, 210);
		frame.getContentPane().add(txtStatus);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(35, 25, 46, 14);
		frame.getContentPane().add(lblRoom);
		
		JButton btnTurnOffAllLights = new JButton("Turn off all lights");
		btnTurnOffAllLights.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String toall = "toall";
						
						SwitchOffAllLights req = SwitchOffAllLights.newBuilder().setSwitchOffAll(toall).build();
						//APISwitchOffAllLights res = asyncStub.lightswitchall(req);
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
										System.out.println("ALL LIGHTS ARE NOW OFF.");
									}
								};
								asyncStub.lightswitchall(req, responseObserver);
								try 
								{
									Thread.sleep(15000);
								} 
								catch (InterruptedException ie) 
								{
									ie.printStackTrace();
								}
					}
				}
				);
		
		btnTurnOffAllLights.setBounds(35, 212, 156, 23);
		frame.getContentPane().add(btnTurnOffAllLights);
		
		
	}
	
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

					lightsServiceInfo = event.getInfo();

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

					roombaServiceInfo = event.getInfo();

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
}
