package fr.socrates.domain.meal;

public class Quantity {

    private  long quantity;

    private Quantity(long quantity) {
        this.quantity = quantity;
    }

    public static Quantity of(long quantity) {

        return new Quantity(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quantity quantity1 = (Quantity) o;

        return quantity == quantity1.quantity;
    }

    @Override
    public int hashCode() {
        return (int) (quantity ^ (quantity >>> 32));
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "quantity=" + quantity +
                '}';
    }
}
