package io.mosip.registration.processor.notification.service;

import io.mosip.registration.processor.notification.dto.WhatsAppRequestDTO;

public interface NotificationRestClient {

    void sendWhatsApp(WhatsAppRequestDTO requestDTO);
}
