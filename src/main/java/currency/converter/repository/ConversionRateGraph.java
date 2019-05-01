package currency.converter.repository;

import currency.converter.exception.CurrencyConverterException;
import currency.converter.model.Currency;

public interface ConversionRateGraph {
    void addConversionRate(Currency from, Currency to, double conversionFactor);
    double getConversionRate(Currency from, Currency to) throws CurrencyConverterException;
}
