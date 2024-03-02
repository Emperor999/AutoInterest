package com.softbank.auto.job;

import com.softbank.auto.repository.AccountRepository;
import com.softbank.common.entity.Account;
import com.softbank.common.enums.ErrorMessage;
import com.softbank.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InterestRate {

    private final AccountRepository accountRepository;

    private final BigDecimal INTEREST_RATE = BigDecimal.valueOf(0.05);

    @Scheduled(cron = "0 0 15 * * ?")
    public void calculateInterestRate() {
        List<Account> accounts = accountRepository.findAll();

        if(accounts.isEmpty()){
            throw new NotFoundException(ErrorMessage.NO_DATA_IS_FOUND.getErrorMessage());
        }

        log.info("Account Balance has been adjusted");

        accounts.forEach(account -> {
            BigDecimal currentAccountBalance = account.getAccountBalance();
            account.setAccountBalance(currentAccountBalance.add(INTEREST_RATE.multiply(currentAccountBalance)));
            account.setModifiedWhen(LocalDateTime.now());
            accountRepository.save(account);
        });

    }
}
