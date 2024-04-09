package ua.pt.ChronoCharm.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public double totalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getWatch().getPrice() * item.getQuantity();
        }
        return total;
    }

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Cart items(List<CartItem> items) {
        setItems(items);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart cart = (Cart) o;
        return Objects.equals(items, cart.items);
    }

    @Override
    public String toString() {
        return "{" +
            " items='" + getItems() + "'" +
            "}";
    }

}