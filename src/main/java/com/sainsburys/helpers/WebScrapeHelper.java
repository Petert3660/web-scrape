package com.sainsburys.helpers;

import com.sainsburys.datamodels.Product;
import com.sainsburys.utils.Statics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class WebScrapeHelper {

    public ArrayList<Product> scrapeTargetData(String url) throws IOException {

        ArrayList<Product> allProducts = new ArrayList<Product>();

        BufferedReader in = getBufferedReader(url);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("productInfo")) {
                String input = "";
                do {
                    input = in.readLine();
                    analyseForUrlInformation(allProducts, input);
                    input = input.trim();
                } while (!input.equals("</div>"));
            }
        }
        in.close();

        return allProducts;
    }

    public void analyseForUrlInformation(ArrayList<Product> allProducts, String input) throws IOException {

        if (input.contains("<a href=")) {
            input = input.replace("<a href=\"", Statics.EMPTY_STRING)
                    .replace("\" >", Statics.EMPTY_STRING)
                    .replace(Statics.RELATIVE_BASE_URL, Statics.EMPTY_STRING)
                    .trim();

            allProducts.add(getProductDetails(Statics.BASE_PRODUCT_URL + input));
        }
    }

    public Product getProductDetails(String revisedUrl) throws IOException {

        Product product = new Product();

        BufferedReader in = getBufferedReader(revisedUrl);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("productSummary")) {
                String input = "";
                do {
                    input = in.readLine();
                    analyseForProductData(product, input, in);
                } while (!input.contains("</html>"));
            }
        }
        in.close();

        return product;
    }

    public void analyseForProductData(Product product, String input, BufferedReader in) throws IOException {

        if (input.contains("<h1>")) {
            input = input.replaceAll(Statics.HTML_TAG_REGEX, Statics.EMPTY_STRING).trim();
            product.setTitle(input);
        }
        if (input.contains("£") && input.contains("pricePerUnit")) {
            input = input.replaceAll(Statics.HTML_TAG_REGEX, Statics.EMPTY_STRING)
                    .replace("/unit", Statics.EMPTY_STRING)
                    .replace("£", Statics.EMPTY_STRING)
                    .trim();
            product.setUnitPrice(Double.parseDouble(input));
        }
        if (input.contains("<h3") && input.contains("Description")) {
            do {
                input = in.readLine();
                if (input.contains("<p>") && input.contains("</p>")) {
                    input = input.replaceAll(Statics.HTML_TAG_REGEX, Statics.EMPTY_STRING).trim();
                    product.setDescription(input);
                    break;
                }
            } while (true);
        }
        if (input.contains("kJ") && (input.contains("Energy") || input.contains("Energy kJ"))) {
            input = input.replaceAll(Statics.HTML_TAG_REGEX, Statics.EMPTY_STRING)
                    .replace("Energy", Statics.EMPTY_STRING)
                    .replace("kJ", Statics.EMPTY_STRING)
                    .replace("-", Statics.EMPTY_STRING)
                    .trim();
            StringBuffer sb = new StringBuffer();
            sb.append(input.charAt(0)).append(input.charAt(1)).append(input.charAt(2));
            product.setCalories(sb.toString() + "kJ");
        }
    }

    private BufferedReader getBufferedReader(String url) throws IOException {
        URL stream = new URL(url);
        return new BufferedReader(
                new InputStreamReader(stream.openStream()));
    }
}
