package ru.dartinc.service;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestGetWhithAuthorization {
    public static void main(String[] args) {
        var token = AppAthorization.getAuthorizationToken();

        try {
            var result = sendGet("http://localhost:8080/rest/api/webversion1.0/book/2/info",token);
            System.out.println(result);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private static String sendGet(String url, String token) throws IOException {
        var result = "";
        var getRequest = new HttpGet(url);
        getRequest.addHeader("content-type", "application/json;");
        getRequest.addHeader("authorization","Bearer "+token);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                result = EntityUtils.toString(response.getEntity());
                return result;
            } catch (ParseException e){
                System.out.println("Ответ не распарсился");
                return "Parse Error";
            }
        } catch (IOException e){
            throw new IOException("Error connection");
        }
    }

}
