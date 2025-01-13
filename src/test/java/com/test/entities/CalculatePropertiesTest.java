package com.test.entities;

import com.test.entities.utils.BasePartnerGroupGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatePropertiesTest {

    @Test
    void testCalculateTotalAssetsValue_companyWithPartners() {
        // Given
        double sum = Arrays.stream(BasePartnerGroupGenerator.TOTALS).sum();
        double roundingValue = BigDecimal.valueOf(sum)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        CompanyPartner companyPartner = new CompanyPartner("41720647000175", BasePartnerGroupGenerator.TOTALS[6], BasePartnerGroupGenerator.generatePartners());

        // When
        double totalAssetsValue = new CalculateProperties().calculateTotalAssetsValue(companyPartner);

        // Then
        assertEquals(roundingValue, totalAssetsValue, 0.01);
    }

}