package SBServers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import SB.grpc.APILightsResponse;
import SB.grpc.APISwitchOffAllLights;
import SB.grpc.SwitchOffAllLights;
import SB.grpc.SwitchRequest;
import SB.grpc.lightsGrpc.lightsImplBase;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


public class LightsServer extends lightsImplBase
{
	// enumeration
	// 0 is off, 1 is on
	public static int hallwaylight = 0; // 0
	public static int kitchenlight = 0;// 1
	public static int wclight = 0;// 2
	public static int livingroomlight = 0;// 3
	public static int bedroom1light = 0;// 4
	public static int bedroom2light = 0;// 5
	
	public static void main(String[] args)
	{
		// server declaration and registration
		LightsServer ls = new LightsServer();
		Properties lp = ls.getProperties();
		ls.registerService(lp);
		int lport = Integer.valueOf(lp.getProperty("service_port"));
		
		try 
		{
			// server declared & initialized 
			Server server = ServerBuilder.forPort(lport).addService(ls).build().start();
			System.out.println("Lights Server started, listening on " + lport);
			server.awaitTermination();
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
	private Properties getProperties() 
	{
		// initialize properties
		Properties lp = null;		
		// read properties
		 try (InputStream input = new FileInputStream("src/main/resources/lights.properties")) 
		 {
	            lp = new Properties();

	            // load a properties file
	            lp.load(input);

	            // get the property values and print them out
	            System.out.println("Lights properties ...");
	            System.out.println("\t service_type: " + lp.getProperty("service_type"));
	            System.out.println("\t service_name: " + lp.getProperty("service_name"));
	            System.out.println("\t service_description: " + lp.getProperty("service_description"));
		        System.out.println("\t service_port: " + lp.getProperty("service_port"));

	     } 
		 catch (IOException ex) 
		 {
	         ex.printStackTrace();
	         System.out.println("We have an input/output error: " + ex.getMessage());
	     }
		 return lp;
	}
	// service registration
	private void registerService(Properties lp) 
	{
		
		 try 
		 {
			 	// Create a JmDNS instance
	            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	            // get properties
	            String service_type = lp.getProperty("service_type");
	            String service_name = lp.getProperty("service_name");
	            int service_port = Integer.valueOf( lp.getProperty("service_port"));
	            // Register a service
	            String service_description_properties = lp.getProperty("service_description");
	            
	            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port, service_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("Registrering service with type %s and name %s \n", service_type, service_name);
	            
	            Thread.sleep(1000);
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
	// method for light switch
	public void lightswitch(SwitchRequest switchrequest, StreamObserver<APILightsResponse> responseObserver)
	{
		// status of all rooms on each call
		System.out.println("hallwaylight " + hallwaylight);
		System.out.println("kitchenlight " + kitchenlight);
		System.out.println("wclight " + wclight);
		System.out.println("livingroomlight " + livingroomlight);
		System.out.println("bedroom1light " + bedroom1light);
		System.out.println("bedroom2light " + bedroom2light);
		
		System.out.println("Receiving info from the client - Lights status " + switchrequest.getLightsstatus() + " , " + switchrequest.getLightsroom());
		
		int value = 0; // light is off
		int value2 = 1; // light is on
		String msg = "";
		String turned = "";
		// check the status of the light in selected room. If on, swicth off and vice versa
		if(switchrequest.getLightsroom() == 0)
		{
			if(hallwaylight == switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the hallway is ";
					turned = "turned on.";
					hallwaylight = value2;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the hallway is ";
					turned = "turned off.";
					hallwaylight = value;
				}
			}
			else if(hallwaylight != switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the hallway is ";
					turned = "turned off.";
					hallwaylight = value;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the hallway is ";
					turned = "turned on.";
					hallwaylight = value2;
				}
			}
		}
		else if(switchrequest.getLightsroom() == 1)
		{
			if(kitchenlight == switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the kitchen is ";
					turned = "turned on.";
					kitchenlight = value2;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the kitchen is ";
					turned = "turned off.";
					kitchenlight = value;
				}
			}
			else if(kitchenlight != switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the kitchen is ";
					turned = "turned off.";
					kitchenlight = value;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the kitchen is ";
					turned = "turned on.";
					kitchenlight = value2;
				}
			}
		}
		else if(switchrequest.getLightsroom() == 2)
		{
			if(wclight == switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the bathroom is ";
					turned = "turned on.";
					wclight = value2;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the bathroom is ";
					turned = "turned off.";
					wclight = value;
				}
			}
			else if(wclight != switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the bathroom is ";
					turned = "turned off.";
					wclight = value;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the bathroom is ";
					turned = "turned on.";
					wclight = value2;
				}
			}
		}
		else if(switchrequest.getLightsroom() == 3)
		{
			if(livingroomlight == switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the living room is ";
					turned = "turned on.";
					livingroomlight = value2;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the living room is ";
					turned = "turned off.";
					livingroomlight = value;
				}
			}
			else if(livingroomlight != switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the living room is ";
					turned = "turned off.";
					livingroomlight = value;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the living room is ";
					turned = "turned on.";
					livingroomlight = value2;
				}
			}
		}
		else if(switchrequest.getLightsroom() == 4)
		{
			if(bedroom1light == switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 1 is ";
					turned = "turned on.";
					bedroom1light = value2;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 1 is ";
					turned = "turned off.";
					bedroom1light = value;
				}
			}
			else if(bedroom1light != switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 1 is ";
					turned = "turned off.";
					bedroom1light = value;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 1 is ";
					turned = "turned on.";
					bedroom1light = value2;
				}
			}
		}
		else if(switchrequest.getLightsroom() == 5)
		{
			if(bedroom2light == switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 2 is ";
					turned = "turned on.";
					bedroom2light = value2;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 2 is ";
					turned = "turned off.";
					bedroom2light = value;
				}
			}
			else if(bedroom2light != switchrequest.getLightsstatus())
			{
				if(value == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 2 is ";
					turned = "turned off.";
					bedroom2light = value;
				}
				else if(value2 == switchrequest.getLightsstatus())
				{
					msg = "The light in the bedroomroom 2 is ";
					turned = "turned on.";
					bedroom2light = value2;
				}
			}
		}
		else 
		{
			System.out.println("No such room");
		}
		// send reply back
		APILightsResponse reply = APILightsResponse.newBuilder().setResponsemessage(msg + turned).setResponsecode("SUCCESS").build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
		
		System.out.println("hallwaylight " + hallwaylight);
		System.out.println("kitchenlight " + kitchenlight);
		System.out.println("wclight " + wclight);
		System.out.println("livingroomlight " + livingroomlight);
		System.out.println("bedroom1light " + bedroom1light);
		System.out.println("bedroom2light " + bedroom2light);
	}
	// method for switching off all lights
	public void lightswitchall(SwitchOffAllLights request, StreamObserver<APISwitchOffAllLights> responseObserver)
	{
		// check for pass key
		if(request.getSwitchOffAll().contentEquals("toall"))
		{
			System.out.println("Server received request to switch off all lights: ");
			System.out.println(request.getSwitchOffAll());
			// get rooms
			String place = "";
			String hallway = "hallway";
			String kitchen = "kitchen";
			String bathroom = "bathroom";
			String livingroom = "living room";
			String bedroom1 = "bedroom 1";
			String bedroom2 = "bedroom 2";
			// set them all off
			hallwaylight = 0; 
			kitchenlight = 0;
			wclight = 0;
			livingroomlight = 0;
			bedroom1light = 0;
			bedroom2light = 0;
			// add to list
			ArrayList <String> rooms = new ArrayList<>();
			rooms.add(hallway);
			rooms.add(kitchen);
			rooms.add(bathroom);
			rooms.add(livingroom);
			rooms.add(bedroom1);
			rooms.add(bedroom2);
			// for each send reply
			for(int i=0; i<rooms.size();i++)
			{
				place = rooms.get(i);
				APISwitchOffAllLights reply = APISwitchOffAllLights.newBuilder().setSwitchOffAllResponse("The light in the " + place + " is off.").build();
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
