package com.test;

import com.test.entities.CompanyPartner;

public class Main {

    public static void main(String[] args) {
        CompanyPartner companyPartner = new CompanyPartner("41720647000175",
                BasePartnerGroupGenerator.TOTALS[6], BasePartnerGroupGenerator.generatePartners());
        double totalAssetsValue = new CalculateProperties().calculateTotalAssetsValue(companyPartner);

        System.out.println("Valor total dos bens: " + totalAssetsValue);
    }

}