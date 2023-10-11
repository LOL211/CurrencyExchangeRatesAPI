package com.currencyexchangeratesapi.update.responseTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRequest {
    private Map<String, Double> rates;
}
