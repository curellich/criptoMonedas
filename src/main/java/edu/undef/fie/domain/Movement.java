package edu.undef.fie.domain;

public class Movement {
    private double percentage;

    public Movement() {
    }

    public Movement(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "percentage=" + percentage +
                '}';
    }
}
