package org.example;

import io.grpc.ManagedChannelBuilder;
import org.example.gRPC.MemoryServiceGrpc;
import org.example.gRPC.MemoryServiceOuterClass;

public class MemoryService extends Thread {
    @Override
    public void run() {
        var channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        while (true) {
            var memoryStub = MemoryServiceGrpc.newBlockingStub(channel);
            var responseMemory = memoryStub.memory(MemoryServiceOuterClass.MemoryRequest.newBuilder().build());
            System.out.println("Server used " + responseMemory.getMemory() + " bytes");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
