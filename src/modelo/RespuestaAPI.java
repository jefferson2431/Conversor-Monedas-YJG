package modelo;

import com.google.gson.annotations.SerializedName;

public class RespuestaAPI {

    @SerializedName("result")
    private String resultado;

    @SerializedName("base_code")
    private String monedaOrigen;

    @SerializedName("target_code")
    private String monedaDestino;

    @SerializedName("conversion_rate")
    private double tasaDeCambio;

    public String getResultado() { return resultado; }
    public String getMonedaOrigen() { return monedaOrigen; }
    public String getMonedaDestino() { return monedaDestino; }
    public double getTasaDeCambio() { return tasaDeCambio; }
}