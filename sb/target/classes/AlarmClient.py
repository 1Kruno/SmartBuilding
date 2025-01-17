from __future__ import print_function

import logging

import grpc

import alarm_pb2
import alarm_pb2_grpc

def run():
    # NOTE(gRPC Python Team): .close() is possible on a channel and should be
    # used in circumstances in which the with statement does not fit the needs
    # of the code.
    	

    with grpc.insecure_channel('localhost:9070') as channel:
        stub = alarm_pb2_grpc.alarmStub(channel)
        response = stub.alarmon(alarm_pb2.AlarmOnRequest(alarmonreqmessage='your alarm has been activated.'))

    print("Alarm info received: " + response.alarmonresmessage)


if __name__ == '__main__':
    logging.basicConfig()
    run()