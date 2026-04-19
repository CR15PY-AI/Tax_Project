package model;

public class TaxForm {

    private int id;
    private int userId;
    private double income;
    private double tax;
    private String status;
    private String taxType;

    public TaxForm(int id, int userId, double income, double tax, String status,  String taxType) {
        this.id = id;
        this.userId = userId;
        this.income = income;
        this.tax = tax;
        this.status = status;
        this.taxType = taxType;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public double getIncome() { return income; }
    public double getTax() { return tax; }
    public String getStatus() { return status; }
    public String getTaxType() { return taxType; }

    public void setIncome(double income) { this.income = income; }
    public void setTax(double tax) { this.tax = tax; }
    public void setStatus(String status) { this.status = status; }
    public void setTaxType(String taxType) { this.taxType = taxType; }

    public String toString() {
        return id + " | " + income + " | " + tax + " | " + status + " | " + taxType;
    }
}