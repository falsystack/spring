package jp.co.falsystack.hexagonal.account.application.service;

import jp.co.falsystack.hexagonal.account.application.port.in.SendMoneyCommand;
import jp.co.falsystack.hexagonal.account.application.port.in.SendMoneyUsecase;
import jp.co.falsystack.hexagonal.account.application.port.out.AccountLock;
import jp.co.falsystack.hexagonal.account.application.port.out.LoadAccountPort;
import jp.co.falsystack.hexagonal.account.application.port.out.UpdateAccountStatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUsecase {

    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        return false;
    }

}
