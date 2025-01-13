package com.test.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonPartner extends Partner {

    public PersonPartner(String document, double totalValue) {
        super(document, totalValue);
    }
}
