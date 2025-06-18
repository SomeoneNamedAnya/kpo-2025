package org.homework.application.service;

import jakarta.transaction.Transactional;
import org.homework.application.exeption.NotEnoughException;
import org.homework.application.exeption.NotExistException;
import org.homework.application.factory.AccountFactory;
import org.homework.application.factory.TransactionalInfoFactory;
import org.homework.domain.Account;
import org.homework.domain.TransactionType;
import org.homework.infrostruccture.repository.AccountRepository;
import org.homework.infrostruccture.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ManageAccountService {
    @Autowired
    private AccountFactory accountFactory;
    @Autowired
    private TransactionalInfoFactory transactionalInfoFactory;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(int userId) {
        Optional<Account> exist = accountRepository.findByUserId(userId);
        return exist.orElseGet(() -> accountRepository.save(accountFactory.create(userId)));
    }
    @Transactional
    public void topUpAccount(int userId, float x) throws NotExistException {
        Optional<Account> exist = accountRepository.findByUserId(userId);
        if (exist.isEmpty()) {
            throw new NotExistException("Don't exist payment account");
        } else {
            transactionRepository.save(transactionalInfoFactory.create(
                    exist.get().getId(),
                    x,
                    TransactionType.TOP_UP));
        }
    }
    @Transactional
    public float sum(int userId) throws NotExistException {
        Optional<Account> exist = accountRepository.findByUserId(userId);
        if (exist.isEmpty()) {
            throw new NotExistException("Don't exist payment account");
        } else {
            Optional<Float> getSum = transactionRepository.getCurrentSum(exist.get().getId());
            return getSum.orElse(0F);
        }

    }

    @Transactional
    public void withdrawAccount(int userId, float x) throws NotExistException, NotEnoughException {
        Optional<Account> exist = accountRepository.findByUserId(userId);
        if (exist.isEmpty()) {
            throw new NotExistException("Don't exist payment account");
        } else {
            transactionRepository.lockByAccount(exist.get().getId());
            float balance = transactionRepository.getCurrentSum(exist.get().getId()).orElse(0F);
            if (balance >= x) {
               transactionRepository.save(transactionalInfoFactory.create(
                       exist.get().getId(),
                       -x,
                       TransactionType.WITHDRAW));
            } else {
                throw new NotEnoughException("Don't enough money");
            }
        }
    }

}
