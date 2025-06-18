package org.homework.adapter.grpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.homework.application.exeption.NotExistException;
import org.homework.application.service.ManageAccountService;
import org.homework.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@GrpcService
public class PaymentGrpcController extends PaymentServiceGrpc.PaymentServiceImplBase {
    @Autowired
    private ManageAccountService manageAccountService;
    @Override
    public void createAccount(GatewayRequestId request,
                              StreamObserver<ResponseAccount> responseObserver) {

        ResponseAccount response;

        try {
            if (request.getId() < 0) {
                throw new NotExistException("Bad userId");
            }
            response = ResponseAccount.newBuilder()
                    .setIsOk(true)
                    .setIdAccount(manageAccountService.createAccount(request.getId()).getId())
                    .build();
        } catch (Exception e) {
            response = ResponseAccount.newBuilder()
                    .setIsOk(false)
                    .build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void topUpAccount(GatewayRequestIdAndMoney request,
                             StreamObserver<ResponseTopUp> responseObserver) {

        ResponseTopUp response;
        try {
            if (request.getMoney() < 0 || request.getId() < 0) {
                throw new NotExistException("Bad request");
            }

            manageAccountService.topUpAccount(request.getId(), request.getMoney());
            response = ResponseTopUp.newBuilder().setIsOk(true).build();
        } catch (Exception e) {
            response = ResponseTopUp.newBuilder().setIsOk(false).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void checkAccount(GatewayRequestId request,
                             StreamObserver<ResponseAccountScore> responseObserver) {

        ResponseAccountScore response;
        try {

            response = ResponseAccountScore.newBuilder()
                    .setIsOk(true)
                    .setScore(manageAccountService.sum(request.getId()))
                    .build();
        } catch (Exception e) {
            response = ResponseAccountScore.newBuilder().setIsOk(false).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}