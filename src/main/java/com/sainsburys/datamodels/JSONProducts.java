package com.sainsburys.datamodels;

import java.util.ArrayList;

public class JSONProducts {

    private ArrayList<Product> results = new ArrayList<Product>();
    private double total;

    public ArrayList<Product> getResults() {
        return results;
    }

    public void setResults(ArrayList<Product> results) {
        this.results = results;
        if (results.size() > 0) {
            for (int i = 0; i < results.size(); i++) {
                total = total + results.get(i).getUnitPrice();
            }
        }
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
