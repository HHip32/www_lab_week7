package vn.edu.iuh.fit.backends.pks;

import lombok.Getter;
import lombok.Setter;
import vn.edu.iuh.fit.backends.models.Order;
import vn.edu.iuh.fit.backends.models.Product;

import java.io.Serializable;

@Setter @Getter
public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}
