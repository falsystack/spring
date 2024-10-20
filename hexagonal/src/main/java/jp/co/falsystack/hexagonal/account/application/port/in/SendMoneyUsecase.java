package jp.co.falsystack.hexagonal.account.application.port.in;

public interface SendMoneyUsecase {
    boolean sendMoney(SendMoneyCommand command);
}
