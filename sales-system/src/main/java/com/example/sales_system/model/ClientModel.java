package com.example.sales_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    private Long id;
    private String name;
    private String lastName;
    private String mobile;
}
