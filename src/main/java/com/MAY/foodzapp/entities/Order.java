package com.MAY.foodzapp.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_order")
@ToString(exclude = "product")
public class Order{

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long orderId;

    private Long quantity;
    private String shipAddress;
    private String totalPrice;
    private LocalDateTime orderDate;



    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "profile_id",
            referencedColumnName = "profileId"
    )
    private Profile profile;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<Product> orderedProducts;


}
