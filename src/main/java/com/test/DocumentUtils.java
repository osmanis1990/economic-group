package com.test;

/**
 * Utility class for performing operations related to identification documents such as CPF and CNPJ.
 * This class provides methods for validating, determining types, and checking specific properties
 * of these documents.
 */
public class DocumentUtils {

    private DocumentUtils() {
    }

    /**
     * Validates whether the provided document string is a valid CPF or CNPJ.
     * If the document is invalid, throws an exception with an error message.
     *
     * @param document the document string to be validated. It can be a CPF or CNPJ.
     * @throws IllegalArgumentException if the document is invalid.
     */
    public static void validateDocument(String document) {
        if (!isValidDocument(document)) {
            throw new IllegalArgumentException("The document \"" + document + "\" does not comply with the required format.");
        }
    }

    /**
     * Validates whether the provided document string is a valid CPF or CNPJ.
     * A valid CPF must meet the CPF validation criteria.
     * A valid CNPJ must meet the CNPJ validation criteria.
     *
     * @param document the document string to be validated. It can be a CPF or CNPJ.
     * @return true if the document is a valid CPF or CNPJ; false otherwise.
     */
    public static boolean isValidDocument(String document) {
        return isValidCpf(document) || isValidCnpj(document);
    }

    /**
     * Checks if the given CPF value is valid.
     * <p>
     * A valid CPF must contain 11 digits and cannot be a sequence of the same digit (e.g., 11111111111).
     * The method calculates the check digits to verify the authenticity of the CPF.
     *
     * @param cpf {@link String} The CPF to be validated.
     * @return {@link Boolean} Returns true if the CPF is valid, false otherwise.
     * @throws IllegalArgumentException if the CPF contains invalid characters.
     */
    public static boolean isValidCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int[] digits = convertToDigitsArray(cpf);
            int firstCheck = calculateCpfCheckDigit(digits, 9);
            int secondCheck = calculateCpfCheckDigit(digits, 10);

            return digits[9] == firstCheck && digits[10] == secondCheck;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the given CNPJ value is valid.
     * <p>
     * A valid CNPJ must contain 14 digits and cannot be a sequence of the same digit (e.g., 11111111111111).
     * The method calculates the check digits to verify the authenticity of the CNPJ.
     *
     * @param cnpj {@link String} The CNPJ to be validated.
     * @return {@link Boolean} Returns true if the CNPJ is valid, false otherwise.
     * @throws IllegalArgumentException if the CNPJ contains invalid characters.
     */
    public static boolean isValidCnpj(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        try {
            int[] digits = convertToDigitsArray(cnpj);
            int firstCheck = calculateCnpjCheckDigit(digits, 12);
            int secondCheck = calculateCnpjCheckDigit(digits, 13);

            return digits[12] == firstCheck && digits[13] == secondCheck;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Calculates the check digit for a given CPF based on the provided digits.
     * <p>
     * The check digit is calculated using a specific algorithm that involves multiplying the digits
     * by weights in decreasing order and applying a modulus operation.
     *
     * @param digits {@link int[]} An array of integers representing the CPF digits.
     * @param length {@link int} The number of digits used for the calculation (9 or 10).
     * @return {@link int} The calculated check digit (0-9).
     */
    private static int calculateCpfCheckDigit(int[] digits, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += digits[i] * (length + 1 - i);
        }

        int remainder = sum % 11;

        return remainder < 2
               ? 0
               : 11 - remainder;
    }

    /**
     * Calculates the check digit for a given CNPJ based on the provided digits.
     * The check digit is determined using a specific algorithm that involves
     * multiplying the digits by predefined weights and applying a modulus operation.
     *
     * @param digits an array of integers representing the CNPJ digits.
     * @param length the number of digits used for the calculation (12 or 13).
     * @return the calculated check digit (0-9).
     */
    private static int calculateCnpjCheckDigit(int[] digits, int length) {
        int sum = 0;
        int[] weights = length == 12
                        ? new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}
                        : new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < length; i++) {
            sum += digits[i] * weights[i];
        }

        int remainder = sum % 11;

        return remainder < 2
               ? 0
               : 11 - remainder;
    }

    /**
     * Converts a given string of numeric characters into an array of integers,
     * where each element in the array represents a single digit from the string.
     *
     * @param input the input string containing numeric characters to be converted
     * @return an array of integers representing the digits of the input string
     */
    private static int[] convertToDigitsArray(String input) {
        return input.chars()
                .map(c -> c - '0')
                .toArray();
    }

}
