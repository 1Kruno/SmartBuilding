package SBServers;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lights.LightsService;

public class LightsServer 
{

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Server server = ServerBuilder.forPort(9090).addService(new LightsService()).build();
		server.start();
		
		System.out.println("Server started at port " + server.getPort());
		server.awaitTermination();
	}

}
