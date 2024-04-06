package ru.dartinc.service;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import ru.dartinc.dto.BookOutDTO;
import ru.dartinc.forms.MainForm;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpUtils {
    public static boolean sendBookOutDtoToServer(BookOutDTO book){
        Gson gson = new Gson();
        var json = gson.toJson(book);
        try {
            var result = sendPOST("http://localhost:8080/rest/api/webversion1.0/book", json);
            System.out.println(result);
            if(result.contains("error")){
                return false;
            }
            return true;
        } catch (IOException e){
          return false;
        }
    }

    private static String sendPOST(String url,String json) throws IOException {
        var result = "";
        var post = new HttpPost(url);
        post.addHeader("content-type", "application/json;");
        post.addHeader("authorization","Bearer "+ MainForm.getInstance().getToken());
        var entity = new StringEntity(json, StandardCharsets.UTF_8);
        post.setEntity(entity);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpClient.execute(post)) {
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
