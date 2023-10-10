package com.currencyexchangeratesapi.dataUpdate;

import java.util.Map;

public class Currencies {

    private static Map<String, Double> rates;

    public static void updateRates(Map<String, Double> newRates)
    {
        rates = newRates;
    }

    public static double getRateChange(String start, String end) throws Exception {
        if(!rates.containsKey(start) || !rates.containsKey(end))
            throw new Exception("Currency doesn't exist");

       double startRate = rates.get(start);
       double endRate = rates.get(end);

       return endRate/startRate;
    }



}
