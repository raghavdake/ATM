package com.atm.model;

import java.util.List;

public class Withdrawal {

    private double amount;
    private double balanceAmount;
    private List<Integer> banknotes;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Integer> getBanknotes() {
        return banknotes;
    }

    public void setBanknotes(List<Integer> banknotes) {
        this.banknotes = banknotes;
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "Amount='" + amount + '\'' +
                 "Balance Amount in Account='" + balanceAmount + '\'' +
                ", Currency Tendered in multiples of =" + banknotes +
                '}';
    }

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
