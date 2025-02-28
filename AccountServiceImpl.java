package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository repo;
	
	@Override
	public Account createAccount(Account account) {
		Account account_saved=repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> account=repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("account not exist");
		}
			Account account_saved=account.get();
		   return account_saved;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> listOfAccounts=repo.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, double amount) {
		Optional<Account> account=repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("account is not present");
		}
		Account accountPresent=account.get();
		Double total_Balance=accountPresent.getAccountBalance()+amount;
		accountPresent.setAccountBalance(total_Balance);
		repo.save(accountPresent);
		return accountPresent;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, double amount) {
		Optional<Account> account=repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("account is not present");
		}
		Account accountPresent=account.get();
		Double total_Balance=accountPresent.getAccountBalance()-amount;
		accountPresent.setAccountBalance(total_Balance);
		repo.save(accountPresent);
		return accountPresent;
		
	}

	@Override
	public void closeAccount(Long accountNumber) {
		getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
	}

}
