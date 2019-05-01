package currency.converter.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import currency.converter.exception.CurrencyConverterException;
import currency.converter.exception.ErrorCode;
import currency.converter.model.Currency;

@Service
public class CurrencyConverterService{
	private static final Logger LOGGER = Logger.getLogger(CurrencyConverterService.class);

	private Scanner scanner = new Scanner(System.in);

	@Autowired
	private ConversionRateService conversionRateService;

	@Autowired
	private CurrencyService currencyService;

	public void start() throws Exception {
		LOGGER.debug("Currency Converter Service :: starting service");
		printBanner();
		printGuide();
		while (true) {
			InputDto input = getNextInput();
			if (input != null) {
				takeAction(input);
			}
		}
	}

	private void printBanner(){
		System.out.println("****************** Currency Converter *******************");
	}

	private void printGuide(){
		System.out.println("Info : ");
		System.out.println("1. Press Ctrl+C to Exit from Application");
		System.out.println("2. Accepted format : <ccy1> <amount1> in <ccy2>\n\n");
	}

    private void takeAction(InputDto input){
        Currency from = currencyService.getCurrency(input.from);
        if(from == null){
            System.out.println("Invalid <ccy1> !!!");
            return;
        }
        Currency to = currencyService.getCurrency(input.to);
        if(to == null){
            System.out.println("Invalid <ccy2> !!!");
            return;
        }
        try {
            double conversionRate = conversionRateService.getConversionRate(from, to);
            System.out.println(String.format("%s %."+from.getDecimalPlaces()+"f = %s %." + to.getDecimalPlaces() + "f", input.from, input.amount, input.to, input.amount * conversionRate));
        }catch (CurrencyConverterException e){
            if(e.getErrorCode() == ErrorCode.CC_NO_CONVERSION_RATE.getErrorCode()){
                System.out.println("Unable to find rate for "+input.from+"/"+input.to);
            }
        }
    }

	private Pattern inputPattern = Pattern.compile("([A-Za-z]{3})\\s+(\\d+(.\\d+)?)\\s+in\\s+([A-Za-z]{3})");
	private InputDto getNextInput(){
		System.out.print("%>");
		String line = scanner.nextLine();
		Matcher matcher = inputPattern.matcher(line);
		if(matcher.find()){
			return new InputDto( StringUtils.upperCase(matcher.group(1)), StringUtils.upperCase(matcher.group(4)), Double.parseDouble(matcher.group(2)));
		}else{
			System.out.println("Invalid Input! Usages : <ccy1> <amount1> in <ccy2>");
		}
		return null;
	}

	private static class InputDto{
		private String from;
		private String to;
		private double amount;

		public InputDto(String from, String to, double amount) {
			this.from = from;
			this.to = to;
			this.amount = amount;
		}
	}
	
}
