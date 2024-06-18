package br.com.alura.servico;

import br.com.alura.modelo.ConversorMonetario;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaAPI{
    public ConversorMonetario consultaMoeda(String moedaInicial, String moedaFinal) {
        String key = "abfa0694537466a6cda8da43";
        String url = "https://v6.exchangerate-api.com/v6/" + key + "/pair/" + moedaInicial + "/" + moedaFinal;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            /*
            Imprimindo o código do status e o corpo da resposta para teste
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            */

            //Verificando se a resposta foi bem-sucedida
            if (response.statusCode() != 200){
                throw new RuntimeException("Falha na requisição para a API com o código de status: " +response.statusCode());
            }

            //Convertendo para JSON o corpo da resposta
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

            //Extraindo os campos necessários
            String baseCode = jsonObject.get("base_code").getAsString();
            String targetCode = jsonObject.get("target_code").getAsString();
            double conversionRate = jsonObject.get("conversion_rate").getAsDouble();

            return new ConversorMonetario(baseCode, targetCode, String.valueOf(conversionRate));
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("A moeda informada não existe", e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Erro ao analisar a resposta da API. Verifique o formato do JSON.", e);
        }
    }
}