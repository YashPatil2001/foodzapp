package com.MAY.foodzapp.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderModel {

    private Long quantity;
    private String shipAddress;
    private String totalPrice;
    private LocalDateTime orderDate;
    private Long userId;
    private Long productId;
}
