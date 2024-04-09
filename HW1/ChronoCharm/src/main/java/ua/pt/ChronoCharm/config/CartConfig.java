package ua.pt.ChronoCharm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.pt.ChronoCharm.data.Cart;

@Configuration
public class CartConfig {

    @Bean
    public Cart cart() {
        return new Cart();
    }

}
