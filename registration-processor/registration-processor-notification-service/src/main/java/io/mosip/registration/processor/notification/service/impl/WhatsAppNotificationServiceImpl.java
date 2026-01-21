package io.mosip.registration.processor.notification.service.impl;

import io.mosip.registration.processor.notification.dto.WhatsAppRequestDTO;
import io.mosip.registration.processor.notification.dto.WhatsAppResponseDTO;
import io.mosip.registration.processor.notification.service.WhatsAppNotificationService;
import org.codehaus.jackson.map.ObjectMapper;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class WhatsAppNotificationServiceImpl
        implements WhatsAppNotificationService {

    @Value("${base-url}")
    private String baseUrl;

    @Value("${session-id}")
    private String sessionId;

    @Value("${x-api-key}")
    private String apiKey;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public WhatsAppResponseDTO sendWhatsApp(WhatsAppRequestDTO requestDTO) {

        try {
            String finalUrl = baseUrl.replace(
                    "{{wa_sessionId}}", sessionId);

            JSONObject body = new JSONObject();
            body.put("recipient", requestDTO.getRecipient());
            body.put("message", requestDTO.getMessage());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(finalUrl))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("x-api-key", apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return new ObjectMapper()
                    .readValue(response.body(), WhatsAppResponseDTO.class);

        } catch (Exception e) {
            throw new RuntimeException("WhatsApp API call failed", e);
        }
    }
}
