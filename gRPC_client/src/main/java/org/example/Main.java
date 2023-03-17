package org.example;

import io.grpc.ManagedChannelBuilder;
import org.bytedeco.opencv.opencv_core.Mat;
import org.example.gRPC.WebCamServiceGrpc;
import org.example.gRPC.WebCamServiceOuterClass;

public class Main {
    public static void main(String[] args) {
        var memoryService = new MemoryService();
        memoryService.start();

        var channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext().maxInboundMessageSize(100000000)
                .build();

        var stub = WebCamServiceGrpc.newBlockingStub(channel);
        var request = WebCamServiceOuterClass.WebCamRequest.newBuilder().build();
        var response = stub.videoStream(request);

        var imshow = new Imshow("WebCam");

        while (response.hasNext()) {
            var data = response.next().getData().toByteArray();
            var rows = response.next().getRows();
            var cols = response.next().getCols();
            var type = response.next().getType();

            var mat = new Mat(rows, cols, type);
            mat.data().put(data);

            imshow.showImage(mat);
        }

        channel.shutdown();
    }
}