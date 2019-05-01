package currency.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import currency.converter.service.CurrencyConverterService;

@SpringBootApplication
@EnableConfigurationProperties
public class CurrencyConverterApplication implements CommandLineRunner {

	@Autowired
	private CurrencyConverterService service;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {
		service.start();
	}

}
