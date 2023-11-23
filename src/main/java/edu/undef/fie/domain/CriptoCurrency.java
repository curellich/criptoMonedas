package edu.undef.fie.domain;

import edu.undef.fie.interfaces.CriptoCurrencyObserver;

import java.util.ArrayList;
import java.util.List;

public class CriptoCurrency {
    private static double price;
    private final String name;
    private final List<Movement> movements = new ArrayList<>();
    private final List<CriptoCurrencyObserver> observers = new ArrayList<>();

    public CriptoCurrency(String name, double price) {
        this.name = name;
        setPrice(price);
    }

    public double getPrice() {
        return price;
    }

    public static void setPrice(double price) {
        CriptoCurrency.price = price;
    }

    public String getName() {
        return name;
    }

    public void generateMovement(double percentage) {
        setPrice(price + (price * percentage));
        this.movements.add(new Movement(percentage));
        notifyObservers();
    }

    public void addObserver(CriptoCurrencyObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (CriptoCurrencyObserver observer : observers) {
            observer.update(this);
        }
    }

    public List<Movement> getLastMovements() {
        int amountMovements = Math.min(this.movements.size(), 8);
        return movements.subList(movements.size() - amountMovements, movements.size());
    }
}
