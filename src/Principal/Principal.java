package Principal;

import Modelos.Exchange;
import Modelos.Resultado;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import excepcion.ErrorEnResultado;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        int cantidadResultados = 0;
        double montoIntercambio;

        Scanner lectura = new Scanner(System.in);
        List<String> resultados = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        System.out.println("**********************************");
        System.out.println("*      CONVERTIDOR DE MONEDA     *");
        System.out.println("**********************************");
        System.out.println("*           Códigos              *");
        System.out.println("*  ARS <--Peso    Argentina      *");
        System.out.println("*  BRL <--Real    Brazil         *");
        System.out.println("*  CLP <--Peso    Chile          *");
        System.out.println("*  PYG <--Guaraní Paraguay       *");
        System.out.println("*  UYU <--Peso    Uruguay        *");
        System.out.println("*  USD <--Dollar  Estados Unidos *");
        System.out.println("*  SALIR <-- salir               *");
        System.out.println("**********************************");

        while (true){
            System.out.println("Escriba el código de ORIGEN o Salir");
            var base = lectura.nextLine();
            if (base.equalsIgnoreCase("SALIR")){
                break;
            }

            System.out.println("Escriba el Monto a CONVERTIR o Salir");
            var montoBase = lectura.nextLine();
            if (montoBase.equalsIgnoreCase("SALIR")){
                break;
            }

            System.out.println("Escriba el código de INTERCAMBIO o Salir");
            var cambio = lectura.nextLine();
            if (cambio.equalsIgnoreCase("SALIR")){
                break;
            }


            String direccion = "https://v6.exchangerate-api.com/v6/ca50e60632206d99d58846b2/pair/"+
                    base.toUpperCase()+"/"+cambio.toUpperCase();
            System.out.println(direccion);


            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println("Json CRUDO: "+json);

                if (json.contains("<html>")){
                    throw new ErrorEnResultado("Datos incorrectos");
                }

                cantidadResultados++;
                System.out.println("Intercambios: " + cantidadResultados);

                Exchange intercambio = gson.fromJson(json, Exchange.class);
                //System.out.println(intercambio);

                montoIntercambio = Double.parseDouble(montoBase) * intercambio.conversion_rate();
                //System.out.println(montoIntercambio);

                Resultado miresultado = new Resultado(intercambio);
                String registro = cantidadResultados + ". " + montoBase + " " + miresultado + " " + montoIntercambio;
                System.out.println(registro);

                resultados.add(registro);

            }catch (ErrorEnResultado e){
                System.out.println(e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("Ocurrió un error: ");
                System.out.println(e.getMessage());
            }


        }
        System.out.println(resultados);


    }
}
