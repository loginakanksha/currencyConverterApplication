/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency.converter.model;

import org.jgrapht.graph.DefaultEdge;

public class CurrencyEdge extends DefaultEdge{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5553792755126545755L;
	private double conversionFactor;

    public Currency getSource() {
        return (Currency)super.getSource();
    }

    public Currency getTarget() {
        return (Currency)super.getTarget();
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
}

