package currency.converter.repository;

import currency.converter.model.Currency;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository {
    Currency getCurrency(String name);
    Currency getOrInsertCurrency(String name);
    boolean hasCurrency(String name);
}
