package com.atm.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.atm.exception.DenominationNotAvailableException;
import com.atm.model.User;
import com.atm.model.Withdrawal;

public class OperationsServiceTest {
 
	OperationsService operationsService = mock(OperationsService.class);
	
	@Test
	public void test_ShowBalance() {
		
		when(operationsService.showBalance("test")).thenReturn(0d);
		
	}
	
	@Test
	public void test_getDenominations() {
		 
		when(operationsService.getDenominations( )).thenReturn(new ArrayList<>(Arrays.asList(1, 1, 1,5)));
		
	}
	
	@Test
	public void test_Withdraw() {
		User testUser = new User("1111", 100, 100, 20);
		Withdrawal testwithdrawal = new Withdrawal();
		testwithdrawal.setAmount(20d);
		testwithdrawal.setBalanceAmount(10d);
		testwithdrawal.setBanknotes(new ArrayList<>(Arrays.asList(1, 1, 1,5)));
		 
		try {
			when(operationsService.withdraw(20d, testUser)).thenReturn(testwithdrawal);
		} catch (DenominationNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
