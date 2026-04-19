package service;

public class OsOOTaxCalculator implements TaxCalculator {

    @Override
    public double calculate(double income) {
        return income * 0.25;
    }
}
