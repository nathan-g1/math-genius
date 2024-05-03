package mathgenius;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MathGeniusServer {

    static int PORT = 50051;
    private static final Logger logger = Logger.getLogger(MathGeniusServer.class.getName());
    private Server server;

    private void start() throws IOException {
        // The port the server should run
        server = Grpc.newServerBuilderForPort(PORT, InsecureServerCredentials.create())
                .addService(new MathGeniusImpl())
                .build()
                .start();
        logger.info("Server started, listening on port: " + PORT);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    MathGeniusServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final MathGeniusServer server = new MathGeniusServer();
        server.start();
        server.blockUntilShutdown();
    }
}
