package jp.co.falsystack.hexagonal.domain;

import lombok.Value;

import java.time.LocalDateTime;

public class Account {
    private AccountId id;
    private Money baselineBalance;
    private ActivityWindow activityWindow;

    public Money calculatedBalance() {
        return Money.add(
                this.baselineBalance,
                this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        var withdrawal = new Activity(
                this.id,
                this.id,
                targetAccountId,
                LocalDateTime.now(),
                money
        );
        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(
                this.calculatedBalance(),
                money.negate()).isPositive();
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {
        var deposit = new Activity(
                this.id,
                sourceAccountId,
                this.id,
                LocalDateTime.now(),
                money);

        this.activityWindow.addActivity(deposit);
        return true;
    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
