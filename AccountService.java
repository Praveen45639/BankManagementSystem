package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Account;

public interface AccountService {
	
	public Account createAccount(Account account);
	public Account getAccountDetailsByAccountNumber(Long accountNumber);
	public List<Account> getAllAccountDetails();
	public Account depositAmount(Long accountNumber,double amount);
	public Account withdrawAmount(Long accountNumber,double amount);
	public void closeAccount(Long accountNumber);

}
