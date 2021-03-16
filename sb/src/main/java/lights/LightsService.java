package lights;

import SB.grpc.Lights.APIResponse;
import SB.grpc.Lights.Empty;
import SB.grpc.Lights.SwitchRequest;
import SB.grpc.lightsGrpc.lightsImplBase;
import io.grpc.stub.StreamObserver;

public class LightsService extends lightsImplBase
{

	@Override
	public void switch_(SwitchRequest request, StreamObserver<APIResponse> responseObserver) 
	{
		String lightsstatus = request.getLightsstatus();
		String lightsroom = request.getLightsroom();
		
		APIResponse.Builder response = APIResponse.newBuilder();
		
		if(lightsstatus.equals(lightsroom))
		{
			response.setResponsecode(0).setResponsemessage("SUCCESS");
		}
		else
		{
			response.setResponsecode(0).setResponsemessage("FAILURE");
		}
		
		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void switchout(Empty request, StreamObserver<APIResponse> responseObserver) 
	{

	}
	
}
