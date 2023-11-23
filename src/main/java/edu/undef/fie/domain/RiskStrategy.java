package edu.undef.fie.domain;
import edu.undef.fie.interfaces.StrategyInvest;


public class RiskStrategy implements StrategyInvest {
    @Override
    public void downInvest(Investor investor, CriptoCurrency criptoCurrency) {
        double investment = investor.getCash() * 0.5;
        investor.buyCripto(investment, criptoCurrency);
    }

    @Override
    public void highInvest(Investor investor, CriptoCurrency criptoCurrency) {
        int amountOfCriptosToSell = (int) (investor.getCriptoCurrencies().get(criptoCurrency.getName()) * 0.5);
        investor.sellCripto(criptoCurrency, amountOfCriptosToSell);
    }
}
