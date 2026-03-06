package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {

    private String monedaOrigen;
    private String monedaDestino;
    private double montoOriginal;
    private double montoConvertido;
    private String fechaHora;

    public Conversion(String monedaOrigen, String monedaDestino,
                      double montoOriginal, double montoConvertido) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.montoOriginal = montoOriginal;
        this.montoConvertido = montoConvertido;
        this.fechaHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    @Override
    public String toString() {
        return String.format("[%s] %.2f %s => %.2f %s",
                fechaHora, montoOriginal, monedaOrigen,
                montoConvertido, monedaDestino);
    }
}