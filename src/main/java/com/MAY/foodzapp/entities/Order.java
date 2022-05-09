package com.MAY.foodzapp.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "productId"
    )
    private Product product;


}
