package com.example.demo.Service;

import com.example.demo.Entity.AccountEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
    AccountEntity findByUserAndPass(String user, String pass);
    AccountEntity findByUser(String user);
    AccountEntity findById(int id);
    Page<AccountEntity> finAllAccount(int pageNumber,int pageSize);
    void saveAccount(AccountEntity accountEntity);
    void deleteAccount(AccountEntity accountEntity);

}
