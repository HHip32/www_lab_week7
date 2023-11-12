package vn.edu.iuh.fit.backends.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.backends.pks.ProductPricePK;

import java.time.LocalDateTime;
@Getter@Setter@NoArgsConstructor
@Entity
@Table(name = "product_price")
@IdClass(ProductPricePK.class)
public class ProductPrice {
    @Id
    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;
    @Id
    @Column(name = "price_date_time")
    private LocalDateTime price_date_time;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "note")
    private String note;



    public ProductPrice(Product product, LocalDateTime price_date_time, double price, String note) {
        this.product = product;
        this.price_date_time = price_date_time;
        this.price = price;
        this.note = note;
    }


}
