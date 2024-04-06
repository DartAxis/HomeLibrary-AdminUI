package ru.dartinc.service;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import ru.dartinc.dto.SignInRequest;
import ru.dartinc.dto.SignInResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AppAthorization {
    private AppAthorization() {
    }

    public static String getAuthorizationToken(){
        var request=new SignInRequest("Dart_Axis","Qwerty3366");
        Gson gson = new Gson();
        var json = gson.toJson(request);
        try {
            var result = gson.fromJson((sendPOST("http://localhost:8080/auth/sign-in", json)),SignInResponse.class);
            return result.getToken();
        } catch (IOException e){
            System.out.println("Ошибка Авторизации");
            return null;
        }
    }

    private static String sendPOST(String url, String json) throws IOException {
        var result = "";
        var post = new HttpPost(url);
        post.addHeader("content-type", "application/json;");
        var entity = new StringEntity(json, StandardCharsets.UTF_8);
        post.setEntity(entity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                result = new String(response.getEntity().getContent().readAllBytes(),StandardCharsets.UTF_8);
                return result;
            } catch (Exception e){
                System.out.println("Ответ не распарсился");
                return null;
            }
        } catch (IOException e){
            throw new IOException("Error connection");
        }
    }
}
