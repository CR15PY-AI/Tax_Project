package service;

public class IndividualTaxCalculator implements TaxCalculator {
    @Override
    public double calculate(double income) {

        if (income < 10000) {
            return income * 0.1;
        } else if (income < 50000) {
            return income * 0.2;
        } else {
            return income * 0.3;
        }
    }
}