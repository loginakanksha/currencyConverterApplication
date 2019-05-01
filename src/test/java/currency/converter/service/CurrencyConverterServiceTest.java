package currency.converter.service;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyConverterServiceTest {
	
	private CurrencyConverterService service;
	
	@Test
	public void testCurrencyConverterService() throws Exception{
		service = new CurrencyConverterService();
		service.start();
	}

}
