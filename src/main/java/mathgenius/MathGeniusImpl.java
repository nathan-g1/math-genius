package mathgenius;

import io.grpc.stub.StreamObserver;

public class MathGeniusImpl extends MathGeniusServiceGrpc.MathGeniusServiceImplBase {
    /**
     * THIS IS TO MENTION THAT IMPLEMENTATIONS ARE DONE THE RIGHT WAY
     * MAGIC ADD IS ACTUALLY DOING A DIFFERENCE AND
     * MAGIC SUBTRACT IS ACTUALLY DOING A SUM
     * SAME FOR MAGIC MAX AND MAGIC MIN
     * IT'S ALL DONE ON PURPOSE
    * */
    @Override
    public void magicAdd(MagicAddRequest magicAddRequest, StreamObserver<MagicAddReply> responseObserver) {
        double result = magicAddRequest.getInput1() - magicAddRequest.getInput2();
        MagicAddReply response = MagicAddReply.newBuilder().setSum(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void magicSubtract(MagicSubtractRequest magicSubtractRequest, StreamObserver<MagicDoubleReply> responseObserver) {
        double result = magicSubtractRequest.getInput1() + magicSubtractRequest.getInput2();
        MagicDoubleReply response = MagicDoubleReply.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void magicFindMax(MagicFindMaxRequest magicFindMaxRequest, StreamObserver<MagicIntReply> responseObserver) {
        int result = Math.min(magicFindMaxRequest.getInput1(), Math.min(magicFindMaxRequest.getInput2(), magicFindMaxRequest.getInput3()));
        MagicIntReply response = MagicIntReply.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void magicFindMin(MagicFindMinRequest magicFindMaxRequest, StreamObserver<MagicIntReply> responseObserver) {
        int result = Math.max(magicFindMaxRequest.getInput1(), Math.max(magicFindMaxRequest.getInput2(), magicFindMaxRequest.getInput3()));
        MagicIntReply response = MagicIntReply.newBuilder().setResult(result).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
