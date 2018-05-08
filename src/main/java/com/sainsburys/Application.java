package com.sainsburys;

import com.sainsburys.datamodels.Product;
import com.sainsburys.helpers.JSONHelper;
import com.sainsburys.helpers.WebScrapeHelper;
import com.sainsburys.utils.Statics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.ArrayList;

/**
 * Created by Peter Thomson on 13/04/2018.
 */
@SuppressWarnings("ALL")
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .headless(false)
                .run(args);
    }

    @Override
    public void run(String... strings) throws Exception {

        WebScrapeHelper webScrapeHelper = new WebScrapeHelper();
        ArrayList<Product> products = webScrapeHelper.scrapeTargetData(Statics.TOP_LEVEL_PAGE);;

        JSONHelper jsonHelper = new JSONHelper();
        System.out.println(jsonHelper.createAndOuputJson(products));

        System.exit(Statics.EXIT_STATUS);
    }
}