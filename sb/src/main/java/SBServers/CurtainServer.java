package SBServers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import SB.grpc.APIAllC;
import SB.grpc.APICResponse;
import SB.grpc.AllC;
import SB.grpc.CRequest;
import SB.grpc.curtainsGrpc.curtainsImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class CurtainServer extends curtainsImplBase
{
	// 0 is off, 1 is on
			public static int hallwayblinds = 0; // 0
			public static int kitchenblinds = 0;// 1
			public static int wcblinds = 0;// 2
			public static int livingroomblinds = 0;// 3
			public static int bedroom1blinds = 0;// 4
			public static int bedroom2blinds = 0;// 5
			
	public static void main(String[] args) 
	{
		// server declaration and registration
		CurtainServer cs = new CurtainServer();
		Properties cp = cs.getCurtainProperties();
		cs.registerCurtainService(cp);
		int cport = Integer.valueOf(cp.getProperty("cservice_port"));
		
		try
		{
			// server declared & initialized 
			Server cserver = ServerBuilder.forPort(cport).addService(cs).build().start();
			System.out.println("Curtain Server started, listening on " + cport);
			cserver.awaitTermination();
		}
		catch (IOException e) 
		{
			System.out.println("We have an input/output error: " + e.getMessage());
			e.printStackTrace();
		} 
		catch (InterruptedException e) 
		{
			System.out.println("Server connection was interrupted: " + e.getMessage());
			e.printStackTrace();
		}

	}
	// get properties from the properties file
	private Properties getCurtainProperties() 
	{
		// initialize properties
		Properties cp = null;	
		// read properties
		 try (InputStream input = new FileInputStream("src/main/resources/curtains.properties")) 
		 {
			 cp = new Properties();

	            // load a properties file
			 cp.load(input);

			 	// get the property values and print them out
	            System.out.println("Curtain properties ...");
	            System.out.println("\t service_type: " + cp.getProperty("cservice_type"));
	            System.out.println("\t service_name: " + cp.getProperty("cservice_name"));
	            System.out.println("\t service_description: " + cp.getProperty("cservice_description"));
		        System.out.println("\t service_port: " + cp.getProperty("cservice_port"));

	     } 
		 catch (IOException ex) 
		 {
			 System.out.println("We have an input/output error: ");
	         ex.printStackTrace();
	     }
		 return cp;
	}
	// service registration
	private  void registerCurtainService(Properties cp) 
	{
		 try 
		 {
	            // Create a JmDNS instance
	            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	         // get properties
	            String cservice_type = cp.getProperty("cservice_type");
	            String cservice_name = cp.getProperty("cservice_name");

	            int cservice_port = Integer.valueOf( cp.getProperty("cservice_port"));

	            String cservice_description_properties = cp.getProperty("cservice_description");
	            
	            // Register a service
	            ServiceInfo serviceInfo = ServiceInfo.create(cservice_type, cservice_name, cservice_port, cservice_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("registrering service with type %s and name %s \n", cservice_type, cservice_name);
	            
	            // Wait a bit
	            Thread.sleep(1000);

	            // Unregister all services
	            //jmdns.unregisterAllServices();

	     } 
		 catch (IOException e) 
		 {
			 	System.out.println("We have an input/output error: " + e.getMessage());
	     } 
		 catch (InterruptedException e) 
		 {
			 	System.out.println("Server connection was interrupted: ");
				e.printStackTrace();
		 }
	}
	// method for opening & closing blinds
	public void cswitch(CRequest cswitch, StreamObserver<APICResponse> responseObserver)
	{
		
		System.out.println("hallwayblinds " + hallwayblinds);
		System.out.println("kitchenblinds " + kitchenblinds);
		System.out.println("wcblinds" + wcblinds);
		System.out.println("livingroomblinds " + livingroomblinds);
		System.out.println("bedroom1blinds " + bedroom1blinds);
		System.out.println("bedroom2blinds " + bedroom2blinds);
		
		System.out.println("Receiving info from the client - Blinds status " + cswitch.getCstatus() + " , " + cswitch.getCroom());
		
		int value = 0; // opened
		int value2 = 1; // closed
		String msg = ""; // msg part 1
		String turned = ""; // msg part 2
		// find room, check blinds. If closed, open and vice versa
		if(cswitch.getCroom() == 0)
		{
			if(hallwayblinds == cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in thehallway are ";
					turned = "shut.";
					hallwayblinds = value2;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the hallway are ";
					turned = "opened.";
					hallwayblinds = value;
				}
			}
			else if(hallwayblinds != cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the hallway are ";
					turned = "opened.";
					hallwayblinds = value;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the hallway are ";
					turned = "shut.";
					hallwayblinds = value2;
				}
			}
		}
		else if(cswitch.getCroom() == 1)
		{
			if(kitchenblinds == cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the kitchen are ";
					turned = "shut.";
					kitchenblinds = value2;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the kitchen are ";
					turned = "opened.";
					kitchenblinds = value;
				}
			}
			else if(kitchenblinds != cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the kitchen are ";
					turned = "opened.";
					kitchenblinds = value;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the kitchen are ";
					turned = "shut.";
					kitchenblinds = value2;
				}
			}
		}
		else if(cswitch.getCroom() == 2)
		{
			if(wcblinds == cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the bathroom are ";
					turned = "shut.";
					wcblinds = value2;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the bathroom are ";
					turned = "opened.";
					wcblinds = value;
				}
			}
			else if(wcblinds != cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the bathroom are ";
					turned = "opened.";
					wcblinds = value;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the bathroom are ";
					turned = "shut.";
					wcblinds = value2;
				}
			}
		}
		else if(cswitch.getCroom() == 3)
		{
			if(livingroomblinds == cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the living room are ";
					turned = "shut.";
					livingroomblinds = value2;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the living room are ";
					turned = "opened.";
					livingroomblinds = value;
				}
			}
			else if(livingroomblinds != cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the living room are ";
					turned = "opened.";
					livingroomblinds = value;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the living room are ";
					turned = "shut.";
					livingroomblinds = value2;
				}
			}
		}
		else if(cswitch.getCroom() == 4)
		{
			if(bedroom1blinds == cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 1 are ";
					turned = "shut.";
					bedroom1blinds = value2;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 1 are ";
					turned = "opened.";
					bedroom1blinds = value;
				}
			}
			else if(bedroom1blinds != cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 1 are ";
					turned = "opened.";
					bedroom1blinds = value;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 1 are ";
					turned = "shut.";
					bedroom1blinds = value2;
				}
			}
		}
		else if(cswitch.getCroom() == 5)
		{
			if(bedroom2blinds == cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 2 are ";
					turned = "shut.";
					bedroom2blinds = value2;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 2 are ";
					turned = "opened.";
					bedroom2blinds = value;
				}
			}
			else if(bedroom2blinds != cswitch.getCstatus())
			{
				if(value == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 2 are ";
					turned = "opened.";
					bedroom2blinds = value;
				}
				else if(value2 == cswitch.getCstatus())
				{
					msg = "Blinds in the bedroomroom 2 are ";
					turned = "shut.";
					bedroom2blinds = value2;
				}
			}
		}
		else 
		{
			System.out.println("No such room");
		}
		// send reply
		APICResponse reply = APICResponse.newBuilder().setCresponsemessage(msg + turned).setCresponsecode("SUCCESS").build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
		
		System.out.println("hallwayblinds " + hallwayblinds);
		System.out.println("kitchenblinds " + kitchenblinds);
		System.out.println("wcblinds " + wcblinds);
		System.out.println("livingroomblinds " + livingroomblinds);
		System.out.println("bedroom1blinds " + bedroom1blinds);
		System.out.println("bedroom2blinds " + bedroom2blinds);
	}
	// close all blinds
	public void cswitchall(AllC request, StreamObserver<APIAllC> responseObserver)
	{
		if(request.getCAllrequest().contentEquals("toall"))
		{
			System.out.println("Server received request to shut all blinds: ");
			System.out.println(request.getCAllrequest());
		
			String place = "";
			String hallway = "hallway";
			String kitchen = "kitchen";
			String bathroom = "bathroom";
			String livingroom = "living room";
			String bedroom1 = "bedroom 1";
			String bedroom2 = "bedroom 2";
			
			hallwayblinds = 0; 
			kitchenblinds = 0;
			wcblinds = 0;
			livingroomblinds = 0;
			bedroom1blinds = 0;
			bedroom2blinds = 0;
			
			ArrayList <String> rooms = new ArrayList<>();
			rooms.add(hallway);
			rooms.add(kitchen);
			rooms.add(bathroom);
			rooms.add(livingroom);
			rooms.add(bedroom1);
			rooms.add(bedroom2);
			
			for(int i=0; i<rooms.size();i++)
			{
				place = rooms.get(i);
				APIAllC reply = APIAllC.newBuilder().setCAllresponse("Blinds in the" + place + " are shut.").build();
				responseObserver.onNext(reply);
			}
				responseObserver.onCompleted();
		}
		else
		{
			System.out.println("The request is not valid");
		}
	}

}
