package com.atm.service;

 

import java.util.List;

import com.atm.exception.DenominationNotAvailableException;
import com.atm.model.User;
import com.atm.model.Withdrawal;

public interface OperationsService {


    Withdrawal withdraw(double amount , User user) throws DenominationNotAvailableException;

	List<Integer> getDenominations();

	double showBalance(String userName);
}
