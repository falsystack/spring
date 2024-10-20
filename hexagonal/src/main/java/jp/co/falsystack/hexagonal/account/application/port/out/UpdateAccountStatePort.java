package jp.co.falsystack.hexagonal.account.application.port.out;

import jp.co.falsystack.hexagonal.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);

}
