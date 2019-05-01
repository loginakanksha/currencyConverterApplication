package currency.converter.model;

import java.util.Objects;

public class Currency {
	private String currencyName;
	private int decimalPlaces = 2;

	public Currency(String currencyName){
		this.currencyName = currencyName;
	}

	public Currency(String currencyName, int decimalPlaces) {
		this.currencyName = currencyName;
		this.decimalPlaces = decimalPlaces;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Currency currency = (Currency) o;
		return getCurrencyName().equals(currency.getCurrencyName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCurrencyName());
	}

	@Override
	public String toString() {
		return "Currency{" +
				"currencyName='" + currencyName + '\'' +
				'}';
	}
}
