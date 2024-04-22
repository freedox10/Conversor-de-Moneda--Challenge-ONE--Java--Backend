package Modelos;


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

    public double getTasaDeConversion() {
        return tasaDeConversion;
    }

    public Resultado(Exchange intercambio) {
        //if (intercambio.result().contains("error")){
        //    throw new ErrorEnResultado("No se logró un resultado satisfactorio "+
        //            "porque ingresó un código incorrecto");
        //}
        this.resultado = intercambio.result();
        this.tipoBase = intercambio.base_code();
        this.tipoCambio = intercambio.target_code();
        this.tasaDeConversion = intercambio.conversion_rate();

    }

    @Override
    public String toString() {
        return tipoBase + " --> " + tipoCambio;
    }
}
