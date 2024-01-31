package com.learning.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shopping {

    private Integer id;

    private String name;

    private Double price;

    private String status;

    private String type;
}
