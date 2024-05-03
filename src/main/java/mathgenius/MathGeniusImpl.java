package mathgenius;

import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;

public class MathGeniusImpl extends MathGeniusServiceGrpc.MathGeniusServiceImplBase {
    /**
     * THIS IS TO MENTION THAT IMPLEMENTATIONS ARE DONE THE RIGHT WAY
     * MAGIC ADD IS ACTUALLY DOING A DIFFERENCE AND
     * MAGIC SUBTRACT IS ACTUALLY DOING A SUM
     * SAME FOR MAGIC MAX AND MAGIC MIN
     * IT'S ALL DONE ON PURPOSE
    * */
    Map<String, Integer> operationCounterMap = new HashMap();
    @Override
    public void magicAdd(MagicAddRequest magicAddRequest, StreamObserver<MagicAddReply> responseObserver) {
        double result = magicAddRequest.getInput1() - magicAddRequest.getInput2();
        MagicAddReply response = MagicAddReply.newBuilder().setSum(result).build();
        updateOperationCounter("MagicAdd");
        showOperationsMap();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void magicSubtract(MagicSubtractRequest magicSubtractRequest, StreamObserver<MagicDoubleReply> responseObserver) {
        double result = magicSubtractRequest.getInput1() + magicSubtractRequest.getInput2();
        MagicDoubleReply response = MagicDoubleReply.newBuilder().setResult(result).build();
        updateOperationCounter("MagicSubtract");
        showOperationsMap();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void magicFindMax(MagicFindMaxRequest magicFindMaxRequest, StreamObserver<MagicIntReply> responseObserver) {
        int result = Math.min(magicFindMaxRequest.getInput1(), Math.min(magicFindMaxRequest.getInput2(), magicFindMaxRequest.getInput3()));
        MagicIntReply response = MagicIntReply.newBuilder().setResult(result).build();
        updateOperationCounter("MagicMax");
        showOperationsMap();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void magicFindMin(MagicFindMinRequest magicFindMaxRequest, StreamObserver<MagicIntReply> responseObserver) {
        int result = Math.max(magicFindMaxRequest.getInput1(), Math.max(magicFindMaxRequest.getInput2(), magicFindMaxRequest.getInput3()));
        MagicIntReply response = MagicIntReply.newBuilder().setResult(result).build();
        updateOperationCounter("MagicMin");
        showOperationsMap();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    void showOperationsMap() {
        for(String s : this.operationCounterMap.keySet()) {
            System.out.println(s + " count: " + this.operationCounterMap.get(s));
        }
        
        // Tracking of new coming requests and data
        int totalOperationsCount = 0;
        for (int i : this.operationCounterMap.values()) {
            totalOperationsCount += i;
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("                    NEW REQUEST                        ");
        System.out.println("                  TOTAL REQUEST: " + totalOperationsCount);
        System.out.println("-------------------------------------------------------");
    }

    void updateOperationCounter(String operationName) {
        this.operationCounterMap
                .put(operationName, this.operationCounterMap.getOrDefault(operationName, 0) + 1);
    }
}
