package ua.pt.ChronoCharm.data;
import java.util.Objects;

public class CartItem {
    private Watch watch;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Watch watch, int quantity) {
        this.watch = watch;
        this.quantity = quantity;
    }

    public Watch getWatch() {
        return this.watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem watch(Watch watch) {
        setWatch(watch);
        return this;
    }

    public CartItem quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CartItem)) {
            return false;
        }
        CartItem cartItem = (CartItem) o;
        return Objects.equals(watch, cartItem.watch) && quantity == cartItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(watch, quantity);
    }

    @Override
    public String toString() {
        return "{" +
            " watch='" + getWatch() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

}