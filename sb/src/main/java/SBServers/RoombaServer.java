package SBServers;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import roomba.RoombaService;

public class RoombaServer {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Server roomserver = ServerBuilder.forPort(50051).addService(new RoombaService()).build();
		roomserver.start();
		
		System.out.println("Server started at port " + roomserver.getPort());
		roomserver.awaitTermination();

	}

}
