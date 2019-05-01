package currency.converter.repository;

import currency.converter.model.Currency;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository{
    private static final Logger LOGGER = Logger.getLogger(CurrencyRepositoryImpl.class);

    private Map<String, Currency> currencies = new HashMap<>();

    @Override
    public Currency getOrInsertCurrency(String name){
        if(currencies.containsKey(name)){
            LOGGER.warn("Currency Already Added : "+name);
            return currencies.get(name);
        }
        Currency currency = new Currency(name);
        currencies.put(name, currency);
        return currency;
    }

    @Override
    public Currency getCurrency(String name) {
        return currencies.get(name);
    }

    @Override
    public boolean hasCurrency(String name) {
        return currencies.containsKey(name);
    }

}
