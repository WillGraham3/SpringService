package ru.ilevda.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import ru.ilevda.interfaces.GetDataFromRest;
import ru.ilevda.model.User;

import java.io.IOException;
import java.util.*;

@Controller
@ConfigurationProperties(prefix = "getfeefromrest")
public class GetFeeFromRest implements GetDataFromRest {

    private String resourceUrl;

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Override
    public void get(List<User> list) {
        for (User user : list) {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl + user.getPhoneNumber() + "/dailyfee", String.class, HttpMethod.GET);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            try {
                root = mapper.readTree(response.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (root.has("fee")) {
                user.setGetFee(true);
                user.setDailyFee(root.get("fee").asText());
            } else if (root.has("exceptions"))
                user.setGetFee(false);

        }
    }
}
