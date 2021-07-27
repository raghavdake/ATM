package com.atm.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm.exception.DenominationNotAvailableException;
import com.atm.model.User;
import com.atm.model.Withdrawal;
import com.atm.service.OperationsService;


@RestController
@RequestMapping("/api")
public class OperationsController {

    private static final Logger LOG = LoggerFactory.getLogger(OperationsController.class);

    private OperationsService operationsService;
    


    @Autowired
    public OperationsController(OperationsService operationsService  ) {
        this.operationsService = operationsService;
      
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
    	String user=principal.getName();
    	System.out.println(user);
       return principal.getName();
    }

    @RequestMapping(value = "/showBalance", method = RequestMethod.GET)
    public double getAccountBalance() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        double balance =operationsService.showBalance(userName);
        LOG.info("User Balance Is: {}", balance);
        return  balance;
    }

    @RequestMapping(value = "/withdraw/{withdrawlAmt}", method = RequestMethod.GET)
    public Withdrawal withdraw(@PathVariable("withdrawlAmt") double withdrawalAmt) throws DenominationNotAvailableException {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        System.out.println("Logged in User is"+ auth.getName());
        LOG.info("Logged in User is", auth.getName());
        LOG.info("Amount: {}", withdrawalAmt);
        Withdrawal withdrawalDto = operationsService.withdraw(withdrawalAmt, new User(userName,0d,0d,0d));
        
        return withdrawalDto;
    }
    
    @ExceptionHandler({DenominationNotAvailableException.class})
    public String handleException() {
        return "Check the entered amount for withdrawl";
    }

}
