package io.mosip.registration.processor.notification.service.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.mosip.registration.processor.notification.dto.WhatsAppRequestDTO;
import io.mosip.registration.processor.notification.dto.WhatsAppResponseDTO;
import io.mosip.registration.processor.notification.service.WhatsAppNotificationService;

@Service
public class WhatsAppNotificationServiceImpl implements WhatsAppNotificationService {

    @Value("${cit.api-key}")
    private String apiKey;

    @Value("${cit.whatsapp.base-url}")
    private String baseUrl;

    @Value("${cit.whatsapp.session-id}")
    private String sessionId;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public WhatsAppResponseDTO sendWhatsApp(WhatsAppRequestDTO requestDTO) {

        try {
            String url = baseUrl + "/whatsapp/" + sessionId + "/message";

            String payload = String.format(
                    "{\"recipient\":\"%s\",\"message\":\"%s\"}",
                    requestDTO.getRecipient(),
                    requestDTO.getMessage()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("x-api-key", apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return new WhatsAppResponseDTO(response.statusCode(), response.body());

        } catch (Exception e) {
            return new WhatsAppResponseDTO(500, e.getMessage());
        }
    }
}
