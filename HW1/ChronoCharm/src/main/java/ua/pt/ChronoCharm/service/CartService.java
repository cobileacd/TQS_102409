package ua.pt.ChronoCharm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pt.ChronoCharm.data.Cart;
import ua.pt.ChronoCharm.data.CartItem;
import ua.pt.ChronoCharm.data.Watch;

import java.util.Optional;

@Service
public class CartService {

    private final Cart cart;
    private final WatchManagerService watchManagerService;

    @Autowired
    public CartService(Cart cart, WatchManagerService watchManagerService) {
        this.cart = cart;
        this.watchManagerService = watchManagerService;
    }

    public void addToCart(Long watchId) {
        Optional<Watch> watchOptional = watchManagerService.getWatchDetails(watchId);
        if (watchOptional.isPresent()) {
            Watch watch = watchOptional.get();
            for (CartItem item : cart.getItems()) {
                if (item.getWatch().getWatchId().equals(watchId)) {
                    item.setQuantity(item.getQuantity() + 1);
                    return;
                }
            }

            CartItem cartItem = new CartItem(watch, 1); 
            cart.addItem(cartItem);
        } else {
            /* TODO */
        }
    }

    public Cart getCart() {
        return cart;
    }

}
