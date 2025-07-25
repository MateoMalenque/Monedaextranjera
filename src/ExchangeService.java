import java.net.http.*;
import java.net.URI;
import java.io.IOException;
import com.google.gson.*;

public class ExchangeService {
    private static final String API_KEY = "d4741de1e96dfc2a157efe32";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static double obtenerTasa(String monedaBase, String monedaDestino) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/latest/" + monedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error al obtener tasas. Código HTTP: " + response.statusCode());
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject conversionRates = json.getAsJsonObject("conversion_rates");

        if (!conversionRates.has(monedaDestino)) {
            throw new IllegalArgumentException("Moneda no válida: " + monedaDestino);
        }

        return conversionRates.get(monedaDestino).getAsDouble();
    }
}
