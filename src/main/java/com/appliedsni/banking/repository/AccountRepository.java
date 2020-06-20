package com.appliedsni.banking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appliedsni.banking.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{



}