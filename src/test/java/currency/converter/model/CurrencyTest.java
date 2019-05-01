package currency.converter.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyTest {
	
	private Currency currency;
	private Currency currency2;
	
	private static final String CURRENCY_NAME = "USD";
	private static final int DECIMAL_PLACES = 2;
	private static final String TO_STRING_TEST="Currency{currencyName='USD'}";
	
	@Before
	public void setup(){
		currency = new Currency(CURRENCY_NAME);
		currency2= new Currency(CURRENCY_NAME, DECIMAL_PLACES);
		currency.setDecimalPlaces(DECIMAL_PLACES);
	}
	
	@Test
	public void testCurrency(){
		assertEquals(CURRENCY_NAME, currency.getCurrencyName());
		assertEquals(DECIMAL_PLACES, currency.getDecimalPlaces());
		assertEquals(currency, currency2);
		assertEquals(TO_STRING_TEST, currency.toString());
	}

}
