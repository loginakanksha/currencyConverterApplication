package currency.converter.repository;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.junit.Before;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.context.annotation.Configuration;

import currency.converter.model.Currency;

@Configuration
@PrepareForTest({DefaultDirectedGraph.class})
public class ConversionRateGraphImplTest {
	
	private ConversionRateGraphImpl conversionRateGraphImpl;
	private Currency from;
	private Currency to;
	private static final double CONVERSION_FACTOR = 0.72;
	

	@Before
	public void setup(){
		conversionRateGraphImpl = new ConversionRateGraphImpl();
		from = new Currency("USD", 2);
		to = new Currency("GBP", 2);
	}
	
	@Test
	public void testConversionRateGraphImpl(){
		conversionRateGraphImpl.addConversionRate(from, to, CONVERSION_FACTOR);
		conversionRateGraphImpl.normalizeGraph();
	}

}
