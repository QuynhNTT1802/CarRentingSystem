package com.carrentingsystem.entity;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewId implements Serializable {
    private Long customer;
    private Long car;
}
