package jp.co.falsystack.hexagonal.account.application.port.in;

import jakarta.validation.constraints.NotNull;
import jp.co.falsystack.hexagonal.account.domain.Money;
import lombok.Getter;

import static jp.co.falsystack.hexagonal.account.domain.Account.*;

@Getter
public class SendMoneyCommand {

    private final AccountId sourceAccountId;
    private final AccountId targetAccountId;

    @NotNull
    private final Money money;

    public SendMoneyCommand(
            AccountId sourceAccountId,
            AccountId targetAccountId,
            Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
    }
}
