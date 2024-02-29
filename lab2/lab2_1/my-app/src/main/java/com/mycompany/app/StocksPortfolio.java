package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio
{
  private IStockmarketService stockMarketService;
  private List<Stock> stocks;

  public StocksPortfolio(IStockmarketService service)
  {
    this.stockMarketService = service;
    this.stocks = new ArrayList<>();
  }

  public void addStock(Stock stock)
  {
    stocks.add(stock);
  }

  public double totalValue()
  {
    double total = 0.0;

    for(Stock s: stocks)
      total += stockMarketService.lookUpPrice(s.getLabel()) *  s.getQuantity();

    return total;
  }
}

