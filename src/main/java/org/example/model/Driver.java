package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Driver {
    private String name;
    private int age;
    private String gender;

    private Vehicle vehicle;

}
