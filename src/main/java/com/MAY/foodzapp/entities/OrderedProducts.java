package com.MAY.foodzapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides(
        @AttributeOverride(
                name = "products",
                column = @Column(name = "ordered_products")
        )
)
public class OrderedProducts {

    private List<Product> products;

}
