package com.example.demo.Service.ServiceImpl;

import com.example.demo.Entity.AccountEntity;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEntity findByUserAndPass(String userName, String passWord) {
        return accountRepository.findByUserNameAndPassWord(userName, passWord);
    }

    @Override
    public AccountEntity findByUser(String user) {
        return accountRepository.findByUserName(user);
    }

    @Override
    public AccountEntity findById(int id) {
        Optional<AccountEntity> accountOp = accountRepository.findById(id);
        AccountEntity accountEntity = null;
        if (accountOp.isPresent()) {
            accountEntity = accountOp.get();
        } else {
            throw new RuntimeException("Khong tim thay account");
        }
        return accountEntity;
    }

    @Override
    public Page<AccountEntity> finAllAccount(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return accountRepository.findAll(pageable);
    }

    @Override
    public void saveAccount(AccountEntity accountEntity) {
        this.accountRepository.save(accountEntity);
    }

    @Override
    public void deleteAccount(AccountEntity accountEntity) {
        this.accountRepository.delete(accountEntity);
    }
}
