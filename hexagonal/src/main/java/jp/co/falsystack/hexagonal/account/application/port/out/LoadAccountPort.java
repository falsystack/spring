package jp.co.falsystack.hexagonal.account.application.port.out;

import jp.co.falsystack.hexagonal.account.domain.Account;

import java.time.LocalDateTime;

import static jp.co.falsystack.hexagonal.account.domain.Account.*;

public interface LoadAccountPort {
    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
