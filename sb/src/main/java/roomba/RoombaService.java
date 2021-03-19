package roomba;

import SB.grpc.Roomba.APIRoomResponse;
import SB.grpc.Roomba.REmpty;
import SB.grpc.Roomba.RoombaRequest;
import SB.grpc.roombaGrpc.roombaImplBase;
import io.grpc.stub.StreamObserver;

public class RoombaService extends roombaImplBase
{
	public void roomba(RoombaRequest request, StreamObserver<APIRoomResponse> responseObserver) 
	{
		String roombastatus = request.getRoombastatus();
		String roombaroom = request.getRoombaroom();
		
		APIRoomResponse.Builder response = APIRoomResponse.newBuilder();
		
		if(roombastatus.equals(roombaroom))
		{
			response.setResponsecode(0).setResponsemessage("ROOMBA SUCCESS");
		}
		else
		{
			response.setResponsecode(0).setResponsemessage("ROOMBA FAILURE");
		}
		
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void roombaout(REmpty request, StreamObserver<APIRoomResponse> responseObserver) 
	{

	}
}
