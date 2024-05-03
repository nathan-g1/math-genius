package mathgenius;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Random;

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

        // Make 1000 random RPC requests for the 4 methods
        Random random = new Random();
        int totalRequests = 1000;

        for (int i = 0; i < totalRequests; i++) {
            // Generate random operation (0 for add, 1 for subtract, 2 for min, 3 for max)
            int operation = random.nextInt(4);
            double param1 = random.nextDouble() * 100;
            double param2 = random.nextDouble() * 100;
            int param3 = random.nextInt(100);
            int param4 = random.nextInt(100);
            int param5 = random.nextInt(100);

            switch (operation) {
                case 0:
                    double resultAdd = stub.magicAdd(MagicAddRequest.newBuilder().setInput1(param1).setInput2(param2).build())
                            .getSum();
                    System.out.println("Addition result: " + param1 + " + " + param2 + " = " + resultAdd);
                    break;
                case 1:
                    double resultSubtract = stub.magicSubtract(MagicSubtractRequest.newBuilder().setInput1(param1)
                            .setInput2(param2).build()).getResult();
                    System.out.println("Subtraction result: " + param1 + " - " + param2 + " = " + resultSubtract);
                    break;
                case 2:
                    int max = stub.magicFindMax(MagicFindMaxRequest.newBuilder()
                                    .setInput1(param3)
                                    .setInput2(param4)
                                    .setInput3(param5)
                            .build()).getResult();
                    System.out.println("Max result: Max(" + param3 + ", " + param4 + ", " + param5 + ") = " + max);
                    break;
                case 3:
                    int min = stub.magicFindMin(MagicFindMinRequest.newBuilder()
                            .setInput1(param3)
                            .setInput2(param4)
                            .setInput3(param5)
                            .build()).getResult();
                    System.out.println("Min result: Min(" + param3 + ", " + param4 + ", " + param5 + ") = " + min);
                    break;
            }
        }
        channel.shutdown();
    }
}
