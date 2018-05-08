package com.sainsburys.helpers;

import com.sainsburys.datamodels.JSONProducts;
import com.sainsburys.datamodels.Product;
import com.google.gson.*;
import java.util.ArrayList;

public class JSONHelper {

    public String createAndOuputJson(ArrayList<Product> products) {

        JSONProducts results = new JSONProducts();
        results.setResults(products);

        Gson gsonBuilder = new GsonBuilder().disableHtmlEscaping().create();

        return gsonBuilder.toJson(results);
    }
}
