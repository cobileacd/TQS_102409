package pt.ua.BusTicket.unitTests;

import pt.ua.BusTicket.service.CurrencyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.Spy;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {

    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        currencyService = Mockito.spy(new CurrencyService());
    }

    @Test
    void whenAskedForInvalidExchangeRate_thenReturn0() {
        double rate = currencyService.GetExchangeRateFromDollar("XYZ");
        assertEquals(rate, 0);
    }

    @Test
    void whenAskedForValidExchangeRate_thenReturnRate() {
        double rate = currencyService.GetExchangeRateFromDollar("EUR");
        assertTrue(rate > 0);
    }

    @Test
    void whenAskedForNotCachedExchangeRate_thenDontReturnRateFromCache() {
        double rate = currencyService.GetExchangeRateFromDollar("EUR");
        Mockito.verify(currencyService, Mockito.never()).GetCodeFromCache("EUR");
        assertTrue(rate > 0);
    }

    @Test
    void whenAskedForExpiredCachedExchangeRate_thenDontReturnRateFromCache() throws Exception {
        double rate = currencyService.GetExchangeRateFromDollar("EUR");
        rate = currencyService.GetExchangeRateFromDollar("EUR"); // should fetch from cache
        Mockito.verify(currencyService, Mockito.times(1)).GetCodeFromCache("EUR");

        Thread.sleep(6000);

        rate = currencyService.GetExchangeRateFromDollar("EUR");

        Mockito.verify(currencyService, Mockito.times(1)).GetCodeFromCache("EUR");
        assertTrue(rate > 0);
    }

    @Test
    void whenAskedForCachedExchangeRate_thenReturnRateFromCache() throws Exception {
        double rate = currencyService.GetExchangeRateFromDollar("EUR");
        rate = currencyService.GetExchangeRateFromDollar("EUR"); // should fetch from cache
        Mockito.verify(currencyService, Mockito.times(1)).GetCodeFromCache("EUR");
        assertTrue(rate > 0);
    }
}