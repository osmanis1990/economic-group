package com.test.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyPartner extends Partner {

    private List<Partner> partners;

    public CompanyPartner(String document, double totalValue, List<Partner> partners) {
        super(document, totalValue);
        this.partners = partners;
    }
}
