package client;

import SB.grpc.lightsGrpc;
import SB.grpc.Lights.APIResponse;
import SB.grpc.Lights.SwitchRequest;
import SB.grpc.Roomba.APIRoomResponse;
import SB.grpc.Roomba.RoombaRequest;
import SB.grpc.lightsGrpc.lightsBlockingStub;
import SB.grpc.roombaGrpc;
import SB.grpc.roombaGrpc.roombaBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client 
{

	public static void main(String[] args) 
	{
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
		
		lightsBlockingStub lightsStub = lightsGrpc.newBlockingStub(channel);
		
		SwitchRequest switchrequest = SwitchRequest.newBuilder().setLightsstatus("1").setLightsroom("1").build();
		
		APIResponse response = lightsStub.switch_(switchrequest);
		
		System.out.println(response.getResponsemessage());
		
		
		
		ManagedChannel channel2 = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		
		roombaBlockingStub roombaStub = roombaGrpc.newBlockingStub(channel2);
		
		RoombaRequest roombarequest = RoombaRequest.newBuilder().setRoombastatus("1").setRoombaroom("1").build();
		
		APIRoomResponse roombaresponse = roombaStub.roomba(roombarequest);
		
		System.out.println(roombaresponse.getResponsemessage());
	}

}
