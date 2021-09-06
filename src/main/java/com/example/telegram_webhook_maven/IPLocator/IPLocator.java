package com.example.telegram_webhook_maven.IPLocator;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "iplocator")

public class IPLocator {
    private String requestUrl = "https://api.ipgeolocation.io/ipgeo?apiKey=";
    @Autowired
    private Environment env;

    public JSONObject readJsonFromUrl(String ip) throws IOException, JSONException {
        String apiKey = env.getProperty("iplocator.apiKey");
        String finalURL = requestUrl + apiKey + "&ip=" + ip;
        try (InputStream is = new URL(finalURL).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String prettyViewJSON(JSONObject jsonObject) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("ip : ")
                .append(jsonObject.getString("ip"))
                .append(System.lineSeparator())

                .append("continent code : ")
                .append(jsonObject.getString("continent_code"))
                .append(System.lineSeparator())

                .append("continent name : ")
                .append(jsonObject.getString("continent_name"))
                .append(System.lineSeparator())

                .append("country name : ")
                .append(jsonObject.getString("country_name"))
                .append(System.lineSeparator())

                .append("country_flag : ")
                .append(jsonObject.getString("country_flag"))
                .append(System.lineSeparator())
                .append("city : ")
                .append(jsonObject.getString("city"))
                .append(System.lineSeparator())

                .append("isp : ")
                .append(jsonObject.getString("isp"))
                .append(System.lineSeparator())

                .append("organization : ")
                .append(jsonObject.getString("organization"))
                .append(System.lineSeparator());
        return sb.toString();
    }
}
