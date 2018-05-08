package helpers;

import com.sainsburys.datamodels.Product;
import com.sainsburys.helpers.WebScrapeHelper;
import org.junit.Test;

import java.io.BufferedReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WebScrapeHelperTest {

    BufferedReader bufferedReader = mock(BufferedReader.class);

    @Test
    public void testAnalyseForProductDataSetTitle() throws Exception {

        String line = "<h1>This is the product title</h1>";
        Product product = new Product();

        WebScrapeHelper webScrapeHelper = new WebScrapeHelper();
        webScrapeHelper.analyseForProductData(product, line, bufferedReader);

        assertThat(product.getTitle(), is("This is the product title"));
    }

    @Test
    public void testAnalyseForProductDataSetPrice() throws Exception {

        String line = "£2.00<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>";
        Product product = new Product();

        WebScrapeHelper webScrapeHelper = new WebScrapeHelper();
        webScrapeHelper.analyseForProductData(product, line, bufferedReader);

        assertThat(product.getUnitPrice(), is(2.00));
    }

    @Test
    public void testAnalyseForProductDataSetEnergy() throws Exception {

        String line = "<th scope=\"row\" class=\"rowHeader\" rowspan=\"2\">Energy</th><td class=\"tableRow1\">140kJ</td><td class=\"tableRow1\">-</td>";
        Product product = new Product();

        WebScrapeHelper webScrapeHelper = new WebScrapeHelper();
        webScrapeHelper.analyseForProductData(product, line, bufferedReader);

        assertThat(product.getCalories(), is("140kJ"));
    }

    @Test
    public void testAnalyseForProductDataSetDescription() throws Exception {

        String originalLine = "<h3 class=\"productDataItemHeader\">Description</h3>";
        String line = "<p>by Jove these are raspberries</p>";
        Product product = new Product();

        when(bufferedReader.readLine()).thenReturn(line);

        WebScrapeHelper webScrapeHelper = new WebScrapeHelper();
        webScrapeHelper.analyseForProductData(product, originalLine, bufferedReader);

        assertThat(product.getDescription(), is("by Jove these are raspberries"));
    }
}
