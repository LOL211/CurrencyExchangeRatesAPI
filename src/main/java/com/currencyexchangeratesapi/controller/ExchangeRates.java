package com.currencyexchangeratesapi.controller;


import com.currencyexchangeratesapi.update.Currencies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/exchange")
public class ExchangeRates {

    @GetMapping("/{start}/{end}")
    public ResponseEntity<String> getRate(@PathVariable("start") String startCurrency, @PathVariable("end") String endCurrency)
    {

        try{
            return ResponseEntity
                    .status(200)
                    .body(Currencies.getRateChange(startCurrency,endCurrency)+"");
        }
        catch(Exception e){
            return ResponseEntity
                    .status(400)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/currencylist")
    public ResponseEntity<String> getCurrencyList()
    {
        return ResponseEntity
                .status(200)
                .body(Currencies.getSymbols());
    }


}
