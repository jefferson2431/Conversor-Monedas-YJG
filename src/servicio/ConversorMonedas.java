package servicio;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorMonedas {

    private static final String API_KEY = "4b2885444932b3a5852a1681"; //
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    public double obtenerTasa(String monedaOrigen, String monedaDestino)
            throws Exception {

        String url = URL_BASE + API_KEY + "/pair/" + monedaOrigen + "/" + monedaDestino;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud,
                HttpResponse.BodyHandlers.ofString());

        JsonElement elemento = JsonParser.parseString(respuesta.body());
        JsonObject objectRoot = elemento.getAsJsonObject();

        String resultado = objectRoot.get("result").getAsString();
        if (!resultado.equals("success")) {
            throw new Exception("Error al consultar la API: " + respuesta.body());
        }

        return objectRoot.get("conversion_rate").getAsDouble();
    }

    public double convertir(String monedaOrigen, String monedaDestino, double monto)
            throws Exception {
        double tasa = obtenerTasa(monedaOrigen, monedaDestino);
        return monto * tasa;
    }
}