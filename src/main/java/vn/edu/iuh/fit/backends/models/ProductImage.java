package vn.edu.iuh.fit.backends.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long image_id;
    @Column(name = "path", length = 250, nullable = false)
    private String path;
    @Column(name = "alternative", length = 250)
    private String alternative;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public ProductImage(String path, String alternative) {
        this.path = path;
        this.alternative = alternative;
    }

    public ProductImage(String path, Product product) {
        this.path = path;
        this.product = product;
    }
}
