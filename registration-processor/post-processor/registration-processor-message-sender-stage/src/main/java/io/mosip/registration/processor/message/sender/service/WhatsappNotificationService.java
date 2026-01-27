package io.mosip.registration.processor.message.sender.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.mosip.registration.processor.message.sender.dto.WhatsappNotificationRequestDto;
import io.mosip.registration.processor.message.sender.dto.WhatsappNotificationResponseDto;

@Service
public class WhatsappNotificationService {

    @Value("${cit.whatsapp.base-url}")
    private String baseUrl;

    @Value("${cit.whatsapp.session-id}")
    private String sessionId;

    @Value("${cit.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WhatsappNotificationResponseDto sendWhatsappMessage(
            WhatsappNotificationRequestDto requestDto) {

        String url = baseUrl + "/whatsapp/" + sessionId + "/message";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(MediaType.parseMediaTypes("application/json"));
        headers.set("x-api-key", apiKey);

        HttpEntity<WhatsappNotificationRequestDto> entity =
                new HttpEntity<>(requestDto, headers);

        ResponseEntity<WhatsappNotificationResponseDto> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        WhatsappNotificationResponseDto.class
                );

        return response.getBody();
    }
}
