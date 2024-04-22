package Calculos;

import Modelos.Resultado;

public class CalculadoraDeCambio {
    private double montoIntercambio;

    public double getMontoIntercambio() {
        return montoIntercambio;
    }

    public void calculaIntercambio(Double mBase, Resultado algo){
        //System.out.println(algo.getTasaDeConversion());
        //System.out.println(mBase);
        this.montoIntercambio = mBase * algo.getTasaDeConversion();
        //System.out.println(montoIntercambio);

    }

}
