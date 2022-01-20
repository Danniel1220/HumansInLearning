package com.crystal.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class Customer {
    private final long id;
    private final String name;
    private final int tier;
}
