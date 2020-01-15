package com.khodadadzade.contact.restclient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GithubRestClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${integration.github.base.url}")
    String githubApiBaseUrl;

    public Optional<String> getPublicRepoByUserName(String userName) {
        if (userName == null || userName.equalsIgnoreCase(""))
            return null;
        String reposUri = new StringBuffer(githubApiBaseUrl).append("/users").append("/").append(userName).append("/repos").toString();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(reposUri, String.class);
            String body = response.getBody();
            StringBuffer repoName = new StringBuffer();
            try {
                JSONArray jsonArray = new JSONArray(body);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    repoName.append(jsonObject.get("name"));
                    if (!(jsonArray.length() - 1 == i))
                        repoName.append(", ");
                }
                return Optional.ofNullable(repoName.toString());
            } catch (JSONException e) {
                e.printStackTrace();
                return Optional.ofNullable(null);
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return Optional.ofNullable("There were some errors in getting repos from github with this username");
        }

    }

}
