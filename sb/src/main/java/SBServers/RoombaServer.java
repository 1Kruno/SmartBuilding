package SBServers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import SB.grpc.APICleanAllRoomsResponse;
import SB.grpc.APICleanSelectedRoomsResponse;
import SB.grpc.APILightsResponse;
import SB.grpc.CleanAllRooms;
import SB.grpc.CleanSelectedRooms;
import SB.grpc.roombaGrpc.roombaImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


public class RoombaServer extends roombaImplBase
{
	// enum for rooms
	public static int hallwayroomba = 0;
	public static int kitchenroomba = 1;
	public static int wcroomba = 2;
	public static int livingroomroomba = 3;
	public static int bedroom1roomba = 4;
	public static int bedroom2roomba = 5;
	// classification of room names
	public static ArrayList <String> rooms = new ArrayList<>();
	public static String hallway = "hallway";
	public static String kitchen = "kitchen";
	public static String wc = "bathroom";
	public static String livingroom = "living room";
	public static String bedroom1 = "bedroom 1";
	public static String bedroom2 = "bedroom 2";
	// enum for clean percentage
	public static int hallwayclean = 0;
	public static int kitchenclean = 0;
	public static int wcclean = 0;
	public static int livingroomclean = 0;
	public static int bedroom1clean = 0;
	public static int bedroom2clean= 0;
	// current clean percentage
	public static int roomclean = 0;
	// main method
	public static void main(String[] args) 
	{
		// server declared & initialized 
		RoombaServer rs = new RoombaServer();
		Properties rp = rs.getRoombaProperties();
		rs.registerRoombaService(rp);
		int rport = Integer.valueOf(rp.getProperty("sservice_port"));
		
		try
		{
			Server rserver = ServerBuilder.forPort(rport).addService(rs).build().start();
			System.out.println("Roomba Server started, listening on " + rport);
			rserver.awaitTermination();
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
	private Properties getRoombaProperties() 
	{
		// initialize properties
		Properties rp = null;
		// read properties	
		 try (InputStream input = new FileInputStream("src/main/resources/roomba.properties")) 
		 {
			 rp = new Properties();

	        // load a properties file
			 rp.load(input);

	            // get the property value and print it out
	            System.out.println("Roomba properties ...");
	            System.out.println("\t service_type: " + rp.getProperty("sservice_type"));
	            System.out.println("\t service_name: " + rp.getProperty("sservice_name"));
	            System.out.println("\t service_description: " + rp.getProperty("sservice_description"));
		        System.out.println("\t service_port: " + rp.getProperty("sservice_port"));

	     } 
		 catch (IOException ex) 
		 {
			 System.out.println("We have an input/output error: ");
	         ex.printStackTrace();
	     }
		 return rp;
	}
	// service registration
	private  void registerRoombaService(Properties rp) 
	{
		 try 
		 {
	            // Create a JmDNS instance
	            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	            
	            String sservice_type = rp.getProperty("sservice_type");
	            String sservice_name = rp.getProperty("sservice_name");
	           // int service_port = 1234;
	            int sservice_port = Integer.valueOf( rp.getProperty("sservice_port"));

	            String sservice_description_properties = rp.getProperty("sservice_description");
	            
	            // Register a service
	            ServiceInfo serviceInfo = ServiceInfo.create(sservice_type, sservice_name, sservice_port, sservice_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("registrering service with type %s and name %s \n", sservice_type, sservice_name);
	            
	            // Wait a bit
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

	// observer for msgs from client
	public StreamObserver<CleanSelectedRooms> clean(StreamObserver<APICleanSelectedRoomsResponse> responseObserver) 
	{
		// clear current records
		rooms.clear();
		return new StreamObserver<CleanSelectedRooms> () 
		{

			@Override
			public void onNext(CleanSelectedRooms msg) 
			{
				System.out.println("Receiving from client room name: "+ msg.getRoom() + " and room number " + msg.getRoomnumber() + " where array has " + rooms.size());
				// add rooms based on numbers sent to server
				if(msg.getRoomnumber() == 0)
				{
					rooms.add(hallway);
				}
				else if(msg.getRoomnumber() == 1)
				{
					rooms.add(kitchen);
				}
				else if(msg.getRoomnumber() == 2)
				{
					rooms.add(wc);
				}
				else if(msg.getRoomnumber() == 3)
				{
					rooms.add(livingroom);
				}
				else if(msg.getRoomnumber() == 4)
				{
					rooms.add(bedroom1);
				}
				else if(msg.getRoomnumber() == 5)
				{
					rooms.add(bedroom2);
				}
				else
				{
					System.out.println("Request is not recognised.");
				}
				// start cleaning
				cleanRoom();
				// send reply
				APICleanSelectedRoomsResponse reply = APICleanSelectedRoomsResponse.newBuilder().setRoom(msg.getRoom()).setRoomnumber(msg.getRoomnumber()).setRoompercent(roomclean).build();
			}

			@Override
			public void onError(Throwable t) 
			{
				t.printStackTrace();
			}

			@Override
			public void onCompleted() 
			{
				System.out.println("Receiving rooms to be cleaned completed");
				//completed
				responseObserver.onCompleted();
			}
			
		};
	}
	// method for cleaning all rooms
	public StreamObserver<CleanAllRooms> cleanAll(StreamObserver<APICleanAllRoomsResponse> responseObserver) 
	{
		// same logic as method above
		rooms.clear();
		return new StreamObserver<CleanAllRooms> () 
		{

			@Override
			public void onNext(CleanAllRooms msg) 
			{
				System.out.println("Receiving from client room name: "+ msg.getRoom() + " and room number " + msg.getRoomnumber() + " where array has " + rooms.size());
				
				if(msg.getRoomnumber() == 0)
				{
					rooms.add(hallway);
				}
				else if(msg.getRoomnumber() == 1)
				{
					rooms.add(kitchen);
				}
				else if(msg.getRoomnumber() == 2)
				{
					rooms.add(wc);
				}
				else if(msg.getRoomnumber() == 3)
				{
					rooms.add(livingroom);
				}
				else if(msg.getRoomnumber() == 4)
				{
					rooms.add(bedroom1);
				}
				else if(msg.getRoomnumber() == 5)
				{
					rooms.add(bedroom2);
				}
				else
				{
					System.out.println("Request is not recognised.");
				}
				// start cleaning
				cleanRoom();
				
			}

			@Override
			public void onError(Throwable t) 
			{
				t.printStackTrace();
			}

			@Override
			public void onCompleted() 
			{
				System.out.println("Receiving rooms to be cleaned completed");
				// reply for client
				APICleanAllRoomsResponse reply = APICleanAllRoomsResponse.newBuilder().setResponsemessage("All rooms have been cleaned.").build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}
			
		};
	}
	// method for cleaning
	public void cleanRoom()
	{
		// check if there are scheduled jobs
		if(rooms.size() < 1)
		{
			System.out.println("There are no scheduled cleaning jobs");
		}
		else
		{
			// check the 1st value in array, clean and remove from array
			String room2clean = rooms.get(0).toString();
			if(room2clean.equals(hallway))
			{
				if(hallwayclean < 100)
				{
					hallwayclean = hallwayclean + 20;
					System.out.println("Hallway is now " + hallwayclean + "% cleaned.");
					roomclean = hallwayclean;
					cleanRoom();
				}
				else
				{
					System.out.println("Hallway is " + hallwayclean + "% clean.");
					roomclean = hallwayclean;
					rooms.remove(0);
					cleanRoom();
				}
			}
			else if(room2clean.equals(kitchen))
			{
				if(kitchenclean < 100)
				{
					kitchenclean = kitchenclean + 20;
					System.out.println("Kitchen is now " + kitchenclean + "% cleaned.");
					roomclean = kitchenclean;
					cleanRoom();
				}
				else
				{
					System.out.println("Kitchen is " + kitchenclean + "% clean.");
					roomclean = kitchenclean;
					rooms.remove(0);
					cleanRoom();
				}
			}
			else if(room2clean.equals(wc))
			{
				if(wcclean < 100)
				{
					wcclean = wcclean + 20;
					System.out.println("WC is now " + wcclean + "% cleaned.");
					roomclean = wcclean;
					cleanRoom();
				}
				else
				{
					System.out.println("Bathroom is " + wcclean + "% clean.");
					roomclean = wcclean;
					rooms.remove(0);
					cleanRoom();
				}
			}
			else if(room2clean.equals(livingroom))
			{
				if(livingroomclean < 100)
				{
					livingroomclean = livingroomclean + 20;
					System.out.println("LR is now " + livingroomclean + "% cleaned.");
					roomclean = livingroomclean;
					cleanRoom();
				}
				else
				{
					System.out.println("Living room is " + livingroomclean + "% clean.");
					roomclean = livingroomclean;
					rooms.remove(0);
					cleanRoom();
				}
			}
			else if(room2clean.equals(bedroom1))
			{
				if(bedroom1clean < 100)
				{
					bedroom1clean = bedroom1clean + 20;
					System.out.println("LBR1 is now " + bedroom1clean + "% cleaned.");
					roomclean = bedroom1clean;
					cleanRoom();
				}
				else
				{
					System.out.println("Bedroom 1 is " + bedroom1clean + "% clean.");
					roomclean = bedroom1clean;
					rooms.remove(0);
					cleanRoom();
				}
			}
			else if(room2clean.equals(bedroom2))
			{
				if(bedroom2clean < 100)
				{
					bedroom2clean = bedroom2clean + 20;
					System.out.println("BR2 is now " + bedroom2clean + "% cleaned.");
					roomclean = bedroom2clean;
					cleanRoom();
				}
				else
				{
					System.out.println("Bedroom 2 is " + bedroom2clean + "% clean.");
					roomclean = bedroom2clean;
					rooms.remove(0);
					cleanRoom();
				}
			}
			
		}
	}

}
