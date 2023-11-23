package edu.undef.fie.domain;

import edu.undef.fie.interfaces.CriptoCurrencyObserver;
import edu.undef.fie.interfaces.StrategyInvest;

import java.util.List;
import java.util.Map;

public class Investor implements CriptoCurrencyObserver {
    private String name;
    private double cash;

    private Map<String, Integer> criptoCurrencies;
    private StrategyInvest strategyInvest;

    public Investor(String name, double cash, Map<String, Integer> criptoCurrencies, StrategyInvest strategyInvest) {
        this.name = name;
        this.cash = cash;
        this.criptoCurrencies = criptoCurrencies;
        this.strategyInvest = strategyInvest;
    }

    public Map<String, Integer> getCriptoCurrencies() {
        return criptoCurrencies;
    }

    public double getCash() {
        return cash;
    }

    @Override
    public void update(CriptoCurrency currency) {
        List<Movement> movements = currency.getLastMovements();
        double sumOfPercentages = 0;

        for (var movement : movements) {
            sumOfPercentages += movement.getPercentage();
        }

        invest(sumOfPercentages >= 0, currency);


    }

    public void buyCripto(double cash, CriptoCurrency criptoCurrency) {
        if (this.cash < cash) {
            throw new IllegalArgumentException("You don't have enough cash to buy this amount");
        } else {
            int amountOfCriptosToBuy = (int) (cash / criptoCurrency.getPrice());
            this.cash -= cash;

            if (this.criptoCurrencies.containsKey(criptoCurrency.getName())) {
                this.criptoCurrencies.put(criptoCurrency.getName(), this.criptoCurrencies.get(criptoCurrency.getName()) + amountOfCriptosToBuy);
            } else {
                this.criptoCurrencies.put(criptoCurrency.getName(), amountOfCriptosToBuy);
            }
        }
    }

    public void sellCripto(CriptoCurrency criptoCurrency, int amountOfCriptosToSell) {
        //Check the amount of criptos that the investor has
        if (this.criptoCurrencies.get(criptoCurrency.getName()) < amountOfCriptosToSell) {
            throw new IllegalArgumentException("You don't have enough criptos to sell this amount");
        }

        //Check if the investor has the cripto
        if (!this.criptoCurrencies.containsKey(criptoCurrency.getName())) {
            throw new IllegalArgumentException("You don't have this cripto");
        }

        //Sell the cripto

        this.cash += criptoCurrency.getPrice() * amountOfCriptosToSell;
        this.criptoCurrencies.put(criptoCurrency.getName(), this.criptoCurrencies.get(criptoCurrency.getName()) - amountOfCriptosToSell);
    }

    private void invest(boolean isHigh, CriptoCurrency criptoCurrency){
        if(isHigh){
            this.strategyInvest.highInvest(this, criptoCurrency);
        }else{
            this.strategyInvest.downInvest(this, criptoCurrency);
        }
    }

    public void changeStrategy(StrategyInvest strategyInvest){
        this.strategyInvest = strategyInvest;
    }



}
