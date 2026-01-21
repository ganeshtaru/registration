package io.mosip.registration.processor.notification.service.impl;

import io.mosip.registration.processor.notification.dto.WhatsAppRequestDTO;
import io.mosip.registration.processor.notification.service.NotificationRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationRestClientImpl implements NotificationRestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${cit.whatsapp.base-url}")
    private String baseUrl;

    @Value("${cit.whatsapp.session-id}")
    private String sessionId;

    @Value("${cit.api-key}")
    private String apiKey;

    @Override
    public void sendWhatsApp(WhatsAppRequestDTO requestDTO) {

        String url = baseUrl + "/whatsapp/" + sessionId + "/message";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-api-key", apiKey);

        HttpEntity<WhatsAppRequestDTO> entity =
                new HttpEntity<>(requestDTO, headers);

        restTemplate.postForEntity(url, entity, String.class);
    }
}
