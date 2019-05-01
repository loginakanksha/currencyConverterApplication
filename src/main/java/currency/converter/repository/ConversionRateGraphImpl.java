package currency.converter.repository;

import currency.converter.exception.CurrencyConverterException;
import currency.converter.exception.ErrorCode;
import currency.converter.model.Currency;
import currency.converter.model.CurrencyEdge;
import org.apache.log4j.Logger;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ConversionRateGraphImpl extends DefaultDirectedGraph<Currency, CurrencyEdge> implements ConversionRateGraph{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5822198298107275807L;
	private static final Logger LOGGER = Logger.getLogger(ConversionRateGraphImpl.class);

    public ConversionRateGraphImpl() {
        super(CurrencyEdge.class);
    }

    @Override
    public void addConversionRate(Currency from, Currency to, double conversionFactor){
        //adding vertex into graph
        this.addVertex(from);
        this.addVertex(to);

        //adding both forward and backward edge to graph
        this.addEdgeInternal(from, to, conversionFactor);
    }

    @Override
    public double getConversionRate(Currency from, Currency to) throws CurrencyConverterException {
        CurrencyEdge edge = this.getEdge(from, to);
        if(edge == null){
            throw new CurrencyConverterException(ErrorCode.CC_NO_CONVERSION_RATE);
        }
        return edge.getConversionFactor();
    }

    private void addEdgeInternal(Currency f, Currency t, double conversionFactor){

        LOGGER.debug("Adding edge "+f.getCurrencyName()+" -> "+t.getCurrencyName()+" : " +conversionFactor);

        //adding forward edge into graph
        CurrencyEdge e = this.addEdge(f, t);

        //e could be null only if the edge is already added
        if(e != null){
            LOGGER.info("Edge Added Successfully : "+f.getCurrencyName()+" -> "+t.getCurrencyName()+" : " +conversionFactor);

            e.setConversionFactor(conversionFactor);

            //adding backward edge into graph
            e = this.addEdge(t, f);

            // e could be null only if source and destination currency are same
            if(e != null) {
                LOGGER.info("Edge Added Successfully : "+f.getCurrencyName()+" -> "+t.getCurrencyName()+" : " +conversionFactor);
                e.setConversionFactor(1.0 / conversionFactor);
            }
        }
    }

    public void normalizeGraph(){
        Set<Currency> nodes = this.vertexSet();
        for(Currency node : nodes){
            Set<CurrencyEdge> incoming = this.incomingEdgesOf(node);
            Set<CurrencyEdge> outgoing = this.outgoingEdgesOf(node);

            for(CurrencyEdge e1 : incoming){

                //connecting each incoming node to outgoing nodes
                for(CurrencyEdge e2 : outgoing){
                    if(! this.containsEdge(e1.getSource(), e2.getTarget())){
                        this.addEdgeInternal(e1.getSource(), e2.getTarget(), e1.getConversionFactor() * e2.getConversionFactor());
                    }
                }
            }
        }
    }

}
