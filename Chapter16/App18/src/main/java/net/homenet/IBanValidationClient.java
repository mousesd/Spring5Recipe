package net.homenet;

public interface IBanValidationClient {
    IbanValidationResult validate(String iban);
}
