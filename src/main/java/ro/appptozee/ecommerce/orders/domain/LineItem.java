package ro.appptozee.ecommerce.orders.domain;

import java.util.Objects;

public class LineItem {
    private long productId;
    private int quantity;

    public LineItem(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId == lineItem.productId;
    }
}
