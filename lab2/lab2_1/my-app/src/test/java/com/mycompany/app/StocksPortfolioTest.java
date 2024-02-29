package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest 
{
  @InjectMocks
  StocksPortfolio portfolio;

  @Mock
  IStockmarketService stockmarketService;

  @Test
  void testTotalValue()
  {
    Mockito.when(stockmarketService.lookUpPrice("MSFT")).thenReturn(407.72);
    Mockito.when(stockmarketService.lookUpPrice("AAPL")).thenReturn(181.42);
    Mockito.when(stockmarketService.lookUpPrice("NVDA")).thenReturn(776.63);

    portfolio.addStock(new Stock("MSFT", 5));
    portfolio.addStock(new Stock("AAPL", 5));
    portfolio.addStock(new Stock("NVDA", 5));

    assertEquals(6828.85, portfolio.totalValue());

    assertThat(portfolio.totalValue(), equalTo(6828.85) );
  }
}
