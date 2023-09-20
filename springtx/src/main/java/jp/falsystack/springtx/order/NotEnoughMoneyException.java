package jp.falsystack.springtx.order;

public class NotEnoughMoneyException extends Exception{

  public NotEnoughMoneyException(String message) {
    super(message);
  }
}
