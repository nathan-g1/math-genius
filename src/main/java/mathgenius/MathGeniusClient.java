package mathgenius;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MathGeniusClient {
    static int PORT = 50051;
    public static void main(String[] args) {
        // Create a channel to connect to server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext() // Use plaintext communication (no encryption)
                .build();

        // Create a stub for making RPC calls
        MathGeniusServiceGrpc.MathGeniusServiceBlockingStub stub = MathGeniusServiceGrpc.newBlockingStub(channel);

        // Make RPC calls
        double resultAdd = stub.magicAdd(MagicAddRequest.newBuilder().setInput1(3).setInput2(2).build()).getSum();
        System.out.println("Addition result: " + resultAdd);

        channel.shutdown();
    }
}
