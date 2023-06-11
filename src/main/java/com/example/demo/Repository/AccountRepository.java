package com.example.demo.Repository;

import com.example.demo.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity findByUserNameAndPassWord(String userName, String passWord);
    AccountEntity findByUserName(String user);
}
