package com.carrentingsystem.service;

import com.carrentingsystem.entity.Account;
import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account getAccountById(Long id);
    Account saveAccount(Account account);
    void deleteAccount(Long id);
    Account findByAccountName(String name);
}
