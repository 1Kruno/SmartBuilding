# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import alarm_pb2 as alarm__pb2


class alarmStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.alarmon = channel.unary_unary(
                '/alarm/alarmon',
                request_serializer=alarm__pb2.AlarmOnRequest.SerializeToString,
                response_deserializer=alarm__pb2.APIAlarmOnResponse.FromString,
                )
        self.alarmoff = channel.unary_unary(
                '/alarm/alarmoff',
                request_serializer=alarm__pb2.AlarmOffRequest.SerializeToString,
                response_deserializer=alarm__pb2.APIAlarmOffResponse.FromString,
                )


class alarmServicer(object):
    """Missing associated documentation comment in .proto file."""

    def alarmon(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def alarmoff(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_alarmServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'alarmon': grpc.unary_unary_rpc_method_handler(
                    servicer.alarmon,
                    request_deserializer=alarm__pb2.AlarmOnRequest.FromString,
                    response_serializer=alarm__pb2.APIAlarmOnResponse.SerializeToString,
            ),
            'alarmoff': grpc.unary_unary_rpc_method_handler(
                    servicer.alarmoff,
                    request_deserializer=alarm__pb2.AlarmOffRequest.FromString,
                    response_serializer=alarm__pb2.APIAlarmOffResponse.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'alarm', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class alarm(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def alarmon(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/alarm/alarmon',
            alarm__pb2.AlarmOnRequest.SerializeToString,
            alarm__pb2.APIAlarmOnResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def alarmoff(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/alarm/alarmoff',
            alarm__pb2.AlarmOffRequest.SerializeToString,
            alarm__pb2.APIAlarmOffResponse.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
