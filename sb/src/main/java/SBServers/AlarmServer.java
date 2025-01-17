package SBServers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import SB.grpc.APIAlarmOffResponse;
import SB.grpc.APIAlarmOnResponse;
import SB.grpc.APILightsResponse;
import SB.grpc.AlarmOffRequest;
import SB.grpc.AlarmOnRequest;
import SB.grpc.SwitchRequest;
import SB.grpc.alarmGrpc.alarmImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class AlarmServer extends alarmImplBase{

	// main method
	public static void main(String[] args) 
	{
		// server declaration and registration
		AlarmServer as = new AlarmServer();
		Properties ap = as.getAlarmProperties();
		as.registerAlarmService(ap);
		int aport = Integer.valueOf(ap.getProperty("aservice_port"));
		
		try
		{
			// server declared & initialized 
			Server aserver = ServerBuilder.forPort(aport).addService(as).build().start();
			System.out.println("Alarm Server started, listening on " + aport);
			aserver.awaitTermination();
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
	private Properties getAlarmProperties() 
	{
		// initialize properties
		Properties ap = null;	
		// read properties
		 try (InputStream input = new FileInputStream("src/main/resources/alarm.properties")) 
		 {
			 ap = new Properties();

	         // load a properties file
			 ap.load(input);

	            // get the property values and print them out
	            System.out.println("Alarm properties ...");
	            System.out.println("\t service_type: " + ap.getProperty("aservice_type"));
	            System.out.println("\t service_name: " + ap.getProperty("aservice_name"));
	            System.out.println("\t service_description: " + ap.getProperty("aservice_description"));
		        System.out.println("\t service_port: " + ap.getProperty("aservice_port"));

	     } 
		 // ahndle any I/O exception
		 catch (IOException ex) 
		 {
			 System.out.println("We have an input/output error: ");
	         ex.printStackTrace();
	     }
		 return ap;
	}
	// service registration
	private void registerAlarmService(Properties ap) 
	{
		try 
		 {
	            // Create a JmDNS instance
	            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
	            // get properties
	            String aservice_type = ap.getProperty("aservice_type");
	            String aservice_name = ap.getProperty("aservice_name");
	            int aservice_port = Integer.valueOf( ap.getProperty("aservice_port"));

	            String aservice_description_properties = ap.getProperty("aservice_description");
	            
	            // Register a service
	            ServiceInfo serviceInfo = ServiceInfo.create(aservice_type, aservice_name, aservice_port, aservice_description_properties);
	            jmdns.registerService(serviceInfo);
	            
	            System.out.printf("registrering service with type %s and name %s \n", aservice_type, aservice_name);
	            
	            // Wait a bit
	            Thread.sleep(1000);
	     } 
		 // handle exceptions
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
	// method to switch on the alarm
	public void alarmon(AlarmOnRequest alarmrequest, StreamObserver<APIAlarmOnResponse> responseObserver)
	{
		System.out.println("Receiving info from the client - Alarm " + alarmrequest.getAlarmonreqmessage());
	}
	// method to switch off the alarm
	public void alarmoff(AlarmOffRequest alarmrequest, StreamObserver<APIAlarmOffResponse> responseObserver)
	{
		System.out.println("Receiving info from the client - Alarm " + alarmrequest.getAlarmoffreqmessage());
	}

}
