package com.test.entities.utils;

import com.test.entities.CompanyPartner;
import com.test.entities.Partner;
import com.test.entities.PersonPartner;

import java.util.List;

/**
 * The {@code BasePartnerGroupGenerator} class is a utility class that provides methods
 * for generating predefined collections of partner entities. Partner entities represent
 * individual and company partners, defined by their respective subclasses.
 */
public class BasePartnerGroupGenerator {

    public static final double[] TOTALS = {489678.98, 879546.25, 145789.12, 478578.25, 145528.12, 999457, 556587};

    private BasePartnerGroupGenerator() {
    }

    /**
     * Generates a list of predefined {@code Partner} instances including both individual and company partners.
     *
     * @return A {@code List} containing multiple {@code Partner} objects. The list includes individual partners
     * of type {@code PersonPartner} and a company partner of type {@code CompanyPartner}, which aggregates
     * some of the individual partners.
     */
    public static List<Partner> generatePartners() {
        Partner partner1 = new PersonPartner("42156492859", TOTALS[0]);
        Partner partner2 = new PersonPartner("02712943961", TOTALS[1]);
        Partner partner3 = new PersonPartner("31464238049", TOTALS[2]);
        Partner partner4 = new PersonPartner("98089811868", TOTALS[3]);
        Partner partner5 = new PersonPartner("21960671804", TOTALS[4]);
        CompanyPartner subCompanyPartner1 = new CompanyPartner("20955843000159", TOTALS[5], List.of(partner1, partner2, partner3));

        return List.of(partner3, partner4, partner5, subCompanyPartner1);
    }

}
