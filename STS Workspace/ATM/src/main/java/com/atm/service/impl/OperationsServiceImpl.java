package com.atm.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.atm.exception.DenominationNotAvailableException;
import com.atm.model.User;
import com.atm.model.Withdrawal;
import com.atm.service.OperationsService;

@Scope("prototype")
@Service
public class OperationsServiceImpl implements OperationsService {
    private static final Logger LOG = LoggerFactory.getLogger(OperationsServiceImpl.class);
    protected List<User> userData = new ArrayList<User>(
    		Arrays.asList(new User("123456789", 800, 800, 200),new User("987654321", 1230, 1230, 150) ));
   
    
    @Autowired
    public OperationsServiceImpl( ) {
    	
       
    }

    private final static ArrayList<Integer> denominations = new ArrayList<>(Arrays.asList(50, 20, 10,5));
    double amountWithBank = new Double(1500);

    @Override
    public List<Integer> getDenominations() {
        return denominations;
    }
   

    @Override
    public Withdrawal withdraw(double amountReq, com.atm.model.User user ) throws DenominationNotAvailableException {
       
    	User userReuested = null;
		 for(int i=0; i < userData.size() ; i++)
		 {
			 if(userData.get(i).getName().equalsIgnoreCase(user.getName()))
				 userReuested = userData.get(i);
		 }
		 LOG.info("User Balance is ::"+userReuested.getBalance());
		 LOG.info("Amount Requested by User is ::"+amountReq);
        List<Integer> denomination = getDenominations();
        if(amountWithBank < amountReq)
        {
        	throw new DenominationNotAvailableException("Funds not available :"+userReuested.getName());
        }
        if(amountReq > userReuested.getOverDraftLimit())
        {
        	 throw new DenominationNotAvailableException("Incorrect amount requested by :"+userReuested.getName());
        }
        if (userReuested.getBalance() >= amountReq && isSuitableDenominationsAvailable(amountReq, denomination)) {
            return performWithdrawalTransaction(amountReq, userReuested.getBalance(), denomination);
        }
        throw new DenominationNotAvailableException("Incorrect amount requested by :"+userReuested.getName());
    }

    private boolean isSuitableDenominationsAvailable(double amount, List<Integer> denomination) throws DenominationNotAvailableException {
        for (Integer currentDenomination : denomination) {
            if (amount % currentDenomination == 0) {
                return true;
            }
        }
        return false;
    }

    private Withdrawal performWithdrawalTransaction(double amountReq, double userBalace, List<Integer> denomination) {
        List<Integer> requiredBanknotes = new ArrayList<>();
        int nominalCounter = 0;
        double orgRequestAmount = amountReq;
        double withdrawnAmount = 0; 
        while (amountReq >= 1) {
            int currValue = denomination.get(nominalCounter);
            if (currValue <= amountReq) {
                requiredBanknotes.add(currValue);
                amountReq -= currValue;
                userBalace -= currValue;
            } else {
                nominalCounter++;
            }
        }
        
        	withdrawnAmount  = userBalace - amountReq;
        	Withdrawal withdrawalDto = new Withdrawal();
        	withdrawalDto.setAmount(orgRequestAmount);
        	withdrawalDto.setBanknotes(requiredBanknotes);
        	withdrawalDto.setBalanceAmount(userBalace);
        	amountWithBank -= orgRequestAmount;
        LOG.info("Remainder of amount: {}, Required banknotes: {}", userBalace, requiredBanknotes);
        return withdrawalDto;
    }


	@Override
	public double showBalance(String userName) {
		User user = null;
		 for(int i=0; i < userData.size() ; i++)
		 {
			 if(userData.get(i).getName().equalsIgnoreCase(userName))
				 user = userData.get(i);
		 }
		 LOG.info("User Balance is ::"+user.getBalance());
		return user.getBalance();
	}
}
