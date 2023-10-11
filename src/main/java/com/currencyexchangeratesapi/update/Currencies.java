package com.currencyexchangeratesapi.update;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class Currencies {

    private static Map<String, Double> rates;
    private static Map<String, String> currencySymbols;

    public static void updateRates(Map<String, Double> newRates)
    {
        rates = newRates;
    }

    public static void updateSymbols(Map<String, String> newSymbols)
    {
        currencySymbols = newSymbols;
    }

    public static String getSymbols()
    {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(currencySymbols.keySet());
        return mapper.valueToTree(currencySymbols).toString();
    }

    public static double getRateChange(String start, String end) throws Exception {
        if(!rates.containsKey(start) || !rates.containsKey(end))
            throw new Exception("Currency doesn't exist");

       double startRate = rates.get(start);
       double endRate = rates.get(end);

       return endRate/startRate;
    }




}
