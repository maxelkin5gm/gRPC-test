package org.example.gRPC;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;

import org.example.services.VideoService;

public class WebCamServiceImpl extends WebCamServiceGrpc.WebCamServiceImplBase {

    @Override
    public void videoStream(WebCamServiceOuterClass.WebCamRequest request,
                            StreamObserver<WebCamServiceOuterClass.WebCamResponse> responseObserver) {

        while (!VideoService.interrupted()) {
            var image = VideoService.getImage();
            var response = WebCamServiceOuterClass.WebCamResponse
                    .newBuilder()
                    .setData(ByteString.copyFrom(image.getBuffer()))
                    .setRows(image.getRows())
                    .setCols(image.getCols())
                    .setType(image.getType())
                    .build();

            responseObserver.onNext(response);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        responseObserver.onCompleted();
    }
}
