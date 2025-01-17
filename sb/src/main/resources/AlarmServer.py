from concurrent import futures
import logging

import grpc

import alarm_pb2
import alarm_pb2_grpc


class alarm(alarm_pb2_grpc.alarmServicer):

    def alarmon(self, request, context):
        return alarm_pb2.APIAlarmOnResponse(alarmonresmessage='Hello, %s!' % request.alarmonreqmessage)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    alarm_pb2_grpc.add_alarmServicer_to_server(alarm(), server)
    server.add_insecure_port('[::]:9070')
    server.start()
    server.wait_for_termination()

if __name__ == '__main__':
    logging.basicConfig()
    serve()