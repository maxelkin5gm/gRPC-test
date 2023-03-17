package org.example.gRPC;

import io.grpc.stub.StreamObserver;

public class MemoryServiceImpl extends MemoryServiceGrpc.MemoryServiceImplBase {

    @Override
    public void memory(MemoryServiceOuterClass.MemoryRequest request, StreamObserver<MemoryServiceOuterClass.MemoryResponse> responseObserver) {
        long usedBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        var response = MemoryServiceOuterClass.MemoryResponse.newBuilder().setMemory(usedBytes).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

