package code.usa.tester;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.testng.annotations.DataProvider;

import code.usa.WebPages.ElementVisibility;
import code.usa.WebPages.FlightResultPage;
import code.usa.utils.Page_window;

public class Rates_flights {

    // Converts the flight price from String to Long, using Java 8's Function interface
    public Long numericalPrice(String price) {
        Function<String, Long> convertToLong = p -> Long.parseLong(p.replaceAll("[^\\d.]", ""));
        return convertToLong.apply(price);
    }

    // Provides random integers for flight price validations using Java 8 streams
    @DataProvider
    public String[][] dp() throws Exception {
        FlightResultPage pageObj = Page_window.obj;
        pageObj.clearAllFilters();
        
        int depCount = pageObj.getDepartureFlightCount();
        int retCount = pageObj.getReturnFlightCount();
        
        Random rand = new Random();
        
        return IntStream.range(0, 3).mapToObj(i -> new String[]{
                String.valueOf(depCount > 10 ? rand.nextInt(11) : rand.nextInt(depCount)),
                String.valueOf(retCount > 10 ? rand.nextInt(11) : rand.nextInt(retCount))
        }).toArray(String[][]::new);
    }
}
