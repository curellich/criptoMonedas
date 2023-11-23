package edu.undef.fie;

import edu.undef.fie.domain.ConservativeStrategy;
import edu.undef.fie.domain.CriptoCurrency;
import edu.undef.fie.domain.Investor;
import edu.undef.fie.domain.RiskStrategy;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //Create a new Cripto with initial a price
        CriptoCurrency etherium = new CriptoCurrency("etherium", 15);
        CriptoCurrency ada = new CriptoCurrency("ada", 10);
        CriptoCurrency bitcoin = new CriptoCurrency("bitcoin", 100);

        //Create investors
        Investor jonathan = new Investor("Risk Investor", 1000, new HashMap<>(), new RiskStrategy());
        Investor cristian = new Investor("Conservative Investor", 1000, new HashMap<>(), new ConservativeStrategy());

        //Buy ehtherium
        jonathan.buyCripto(300, etherium);
        jonathan.buyCripto(200, ada);
        etherium.addObserver(jonathan);
        ada.addObserver(jonathan);

        cristian.buyCripto(300, etherium);
        cristian.buyCripto(600, bitcoin);
        etherium.addObserver(cristian);
        bitcoin.addObserver(cristian);


        etherium.generateMovement(0.1);
        etherium.generateMovement(-(0.2));


        cristian.changeStrategy(new RiskStrategy());

        ada.generateMovement(0.3);
        ada.generateMovement(-(0.4));
        ada.generateMovement(0.5);
        etherium.generateMovement(0.6);
        bitcoin.generateMovement(-0.7);



        System.out.println("Jonathan's cripto currencies are: " + jonathan.getCriptoCurrencies().toString());
        System.out.println("Jonathan's cash is: " + jonathan.getCash());

        System.out.println("Cristian's cripto currencies are: " + cristian.getCriptoCurrencies().toString());
        System.out.println("Cristian's cash is: " + cristian.getCash());

        System.out.println("The etherium movements are: " + etherium.getLastMovements().toString());

    }
}