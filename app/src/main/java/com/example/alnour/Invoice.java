package com.example.alnour;

import java.util.ArrayList;

public class Invoice {
    private String customerName ;
    private double price;
    private double total;
    ArrayList<Product> product_invoice ;

    public Invoice(String customerName, double price, double total, ArrayList<Product> product_invoice) {
        this.customerName = customerName;
        this.price = price;
        this.total = total;
        this.product_invoice = product_invoice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<Product> getProduct_invoice() {
        return product_invoice;
    }

    public void setProduct_invoice(ArrayList<Product> product_invoice) {
        this.product_invoice = product_invoice;
    }



}
