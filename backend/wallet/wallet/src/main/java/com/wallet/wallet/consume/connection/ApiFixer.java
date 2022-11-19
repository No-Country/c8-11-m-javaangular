package com.wallet.wallet.consume.connection;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiFixer {
    
    @Value("${api.key.fixer}")
    private String API_KEY;

    @Value("${api.url.fixer}")
    private String DEFAULT_URL;

    @Value("${api.header-field.fixer}")
    private String HEADER_FIELD_KEY;

    public String getConnection(String path) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DEFAULT_URL + path))
                .header(HEADER_FIELD_KEY, API_KEY)
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }
}
