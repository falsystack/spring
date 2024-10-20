package jp.co.falsystack.hexagonal.account.application.port.out;

import jp.co.falsystack.hexagonal.account.domain.Account;

public interface AccountLock {
    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);
}
