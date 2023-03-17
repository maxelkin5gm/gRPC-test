package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.gRPC.MemoryServiceImpl;
import org.example.gRPC.WebCamServiceImpl;
import org.example.services.VideoService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var videoService = new VideoService();
        videoService.start();

        Server server = ServerBuilder.forPort(8080)
                .addService(new MemoryServiceImpl())
                .addService(new WebCamServiceImpl())
                .build();

        server.start();
        System.out.println("Server started");

        server.awaitTermination();
    }
}