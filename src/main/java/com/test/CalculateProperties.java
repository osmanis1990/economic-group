package com.test;

import com.test.entities.CompanyPartner;
import com.test.entities.Partner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides methods to calculate the total asset value of a partner, including nested assets
 * for company-type partners. It ensures that partners are not processed multiple times by tracking them
 * using their unique document identifiers.
 */
public class CalculateProperties {

    /**
     * Calculates the total value of a partner's assets, including assets from nested partners
     * in the case of a company partner. Ensures partners are not processed multiple times
     * by tracking their documents.
     *
     * @param partner the partner whose total asset value is to be calculated
     * @return the total asset value of the partner, rounded to two decimal places
     */
    public double calculateTotalAssetsValue(Partner partner) {
        Set<String> processedDocuments = new HashSet<>();
        double value = calculateRecursiveValue(partner, processedDocuments);

        return BigDecimal.valueOf(value)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    /**
     * Recursively calculates the total value of a partner's assets, including the values of nested partners
     * for company-type partners, while avoiding duplicate processing of partners based on their documents.
     *
     * @param partner            the partner whose total value is to be calculated
     * @param processedDocuments a set of document identifiers to track which partners have already been processed
     * @return the computed total value for the partner, including nested values where applicable
     */
    private double calculateRecursiveValue(Partner partner, Set<String> processedDocuments) {
        String document = partner.getDocument();
        DocumentUtils.validateDocument(document);

        if (!processedDocuments.add(document)) {
            return 0;
        }

        double totalValue = partner.getTotalValue();
        if (partner instanceof CompanyPartner companyPartner) {
            totalValue += calculateCompanyValue(companyPartner, processedDocuments);
        }

        return totalValue;
    }

    /**
     * Calculates the total value of a company by recursively summing up the values
     * of all partners within the company, while avoiding duplicate calculations
     * based on processed documents.
     *
     * @param companyPartner     the company partner whose total value is to be calculated
     * @param processedDocuments a set of document identifiers to track which partners
     *                           have already been processed
     * @return the total computed value of the company including all its partners
     */
    private double calculateCompanyValue(CompanyPartner companyPartner, Set<String> processedDocuments) {
        double totalValue = 0;
        for (Partner partner : companyPartner.getPartners()) {
            totalValue += calculateRecursiveValue(partner, processedDocuments);
        }

        return totalValue;
    }

}
