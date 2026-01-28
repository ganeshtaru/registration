package io.mosip.registration.processor.stages.service;

import io.mosip.registration.processor.stages.dto.WhatsAppRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class WhatsAppNotificationService {

    @Value("${cit.whatsapp.base-url}")
    private String baseUrl;

    @Value("${cit.whatsapp.session-id}")
    private String sessionId;

    @Value("${cit.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> sendMessage(String phone, String message) {

        String url = baseUrl + "/whatsapp/" + sessionId + "/message";

        WhatsAppRequestDto request = new WhatsAppRequestDto();
        request.setRecipient(phone);
        request.setMessage(message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("x-api-key", apiKey);

        HttpEntity<WhatsAppRequestDto> entity = new HttpEntity<>(request, headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
