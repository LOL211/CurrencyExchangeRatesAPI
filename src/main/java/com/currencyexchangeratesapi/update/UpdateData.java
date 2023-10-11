package com.currencyexchangeratesapi.update;


import com.currencyexchangeratesapi.update.responseTypes.CurrencyRequest;
import com.currencyexchangeratesapi.update.responseTypes.CurrencySymbolRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UpdateData {


    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void updateRates() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://data.fixer.io/api/latest?access_key=ef42d7d5feeaecce52661faf3bcb42e2")
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ObjectMapper mapper = new ObjectMapper();
            CurrencyRequest currencyRequest = mapper.readValue(Objects.requireNonNull(response.body()).string(), CurrencyRequest.class);

            Currencies.updateRates(currencyRequest.getRates());

        } catch (IOException e) {

            System.out.println("Error " + e.getMessage());
            throw e;
        }
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.DAYS)
    public void run() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://data.fixer.io/api/symbols?access_key=ef42d7d5feeaecce52661faf3bcb42e2")
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ObjectMapper mapper = new ObjectMapper();
            String res = response.body().string();
            CurrencySymbolRequest currencyRequest = mapper.readValue(res, CurrencySymbolRequest.class);
            System.out.println(currencyRequest.getSymbols());
            Currencies.updateSymbols(currencyRequest.getSymbols());

        } catch (IOException e) {

            System.out.println("Error- " + e.getMessage());

        }
    }

}


