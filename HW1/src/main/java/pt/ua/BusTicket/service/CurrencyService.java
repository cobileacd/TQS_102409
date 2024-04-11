package pt.ua.BusTicket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import org.json.JSONObject;

import java.net.URL;
import java.net.HttpURLConnection;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Scanner;

@Service
public class CurrencyService {

    public class CachedRate {
        public double rate;
        public long ts;

        public CachedRate(double rate, long ts) {
            this.rate = rate;
            this.ts = ts;
        }
    }

    private static List<String> currencyCodes = Arrays.asList(
        "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND",	"BOB",	"BRL",	
        "BSD",	"BTN",	"BWP",	"BYN",	"BZD",	"CAD",	"CDF",	"CHF",	"CLP",	"CNY",	"COP",	"CRC",	"CUP",	"CVE",	"CZK",	"DJF",	"DKK",	"DOP",
        "DZD",	"EGP",	"ERN",	"ETB",	"EUR",	"FJD",	"FKP",	"FOK",	"GBP",	"GEL",	"GGP",	"GHS",	"GIP",	"GMD",	"GNF",	"GTQ",	"GYD",	"HKD",	
        "HNL",	"HRK",	"HTG",	"HUF",	"IDR",	"ILS",	"IMP",	"INR",	"IQD",	"IRR",	"ISK",	"JEP",	"JMD",	"JOD",	"JPY",	"KES",	"KGS",	"KHR",	
        "KID",	"KMF",	"KRW",	"KWD",	"KYD",	"KZT",	"LAK",	"LBP",	"LKR",	"LRD",	"LSL",	"LYD",	"MAD",	"MDL",	"MGA",	"MKD",	"MMK",	"MNT",	
        "MOP",	"MRU",	"MUR",	"MVR",	"MWK",	"MXN",	"MYR",	"MZN",	"NAD",	"NGN",	"NIO",	"NOK",	"NPR",	"NZD",	"OMR",	"PAB",	"PEN",	"PGK",	
        "PHP",	"PKR",	"PLN",	"PYG",	"QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SOS", "SRD", "SSP", 
        "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VES", "VND", "VUV", 
        "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL" 
    );

    private static Logger logger = LoggerFactory.getLogger(CurrencyService.class);
    private int TTL = 5000; // milliseconds
    private HashMap<String, CachedRate> exchangeRateCache;

    public CurrencyService() {
        this.exchangeRateCache = new HashMap<>();
    }

    public List<String> getCurrencyCodes() {
        return currencyCodes;
    }

    public double GetExchangeRateFromDollar(String code) {
        if (!currencyCodes.contains(code)) { return 0; }

        long currentTime = System.currentTimeMillis();
        if (exchangeRateCache.containsKey(code) && currentTime - exchangeRateCache.get(code).ts < TTL) {
            logger.debug("Cache hit {}.", code);
            return GetCodeFromCache(code);
        } else {
            int maxRetries = 10;
            for (int retry = 0; retry < maxRetries; retry++) {
                try {
                    URL url = new URL("https://open.er-api.com/v6/latest/USD");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.connect();

                    int responsecode = conn.getResponseCode();
                    if (responsecode != 200) {
                        throw new RuntimeException("HttpResponseCode: " + responsecode);
                    } else {
                        String inline = "";
                        Scanner scanner = new Scanner(url.openStream());
                        while (scanner.hasNext()) {
                            inline += scanner.nextLine();
                        }
                        scanner.close();

                        JSONObject data_obj = new JSONObject(inline);
                        JSONObject obj = (JSONObject) data_obj.get("rates");

                        double rate = ((java.math.BigDecimal) obj.get(code)).doubleValue();
                        this.exchangeRateCache.put(code, new CachedRate(rate, System.currentTimeMillis()));
                        return rate;
                    }
                } catch (Exception e) {
                    logger.warn("Error occurred while fetching exchange rate. Retrying... Attempt: {}", retry + 1);
                }
            }
        }

        logger.error("All retry attempts failed. Returning 0.");
        return 0;
    }

    public double GetCodeFromCache(String code) {
        return exchangeRateCache.get(code).rate;
    }

}