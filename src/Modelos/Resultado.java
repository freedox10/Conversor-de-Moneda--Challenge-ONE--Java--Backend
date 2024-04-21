package Modelos;

import excepcion.ErrorEnResultado;

public class Resultado {
    private String resultado;
    private String tipoBase;
    private  String tipoCambio;
    private double tasaDeConversion;

    public Resultado(String resultado, String tipoBase, String tipoCambio, double tasaDeConversion) {
        this.resultado = resultado;
        this.tipoBase = tipoBase;
        this.tipoCambio = tipoCambio;
        this.tasaDeConversion = tasaDeConversion;
    }

    public Resultado(Exchange intercambio) {
        if (intercambio.result().contains("error")){
            throw new ErrorEnResultado("No se logró un resultado satisfactorio "+
                    "porque no ingresó un código incorrecto");
        }
        this.resultado = intercambio.result();
        this.tipoBase = intercambio.base_code();
        this.tipoCambio = intercambio.target_code();
        this.tasaDeConversion = intercambio.conversion_rate();
        //this.fechaDeLanzamiento = Integer.valueOf(miTituloOmdb.year());
        //if (miTituloOmdb.runtime().contains("N/A")){
        //    throw new ErrorEnConversionDeDuracion("No pude convertir la duracion," +
        //            "porque contiene un N/A");
        //}
        //this.duracionEnMinutos = Integer.valueOf(
        //        miTituloOmdb.runtime().substring(0,3).replace(" ",""));
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTipoBase() {
        return tipoBase;
    }

    public void setTipoBase(String tipoBase) {
        this.tipoBase = tipoBase;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public double getTasaDeConversion() {
        return tasaDeConversion;
    }

    public void setTasaDeConversion(double tasaDeConversion) {
        this.tasaDeConversion = tasaDeConversion;
    }

    @Override
    public String toString() {
        return tipoBase + " --> " + tipoCambio;
    }
}
