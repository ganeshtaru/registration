package io.mosip.registration.processor.notification.service;
import io.mosip.registration.processor.notification.dto.WhatsAppRequestDTO;
import io.mosip.registration.processor.notification.dto.WhatsAppResponseDTO;
public interface WhatsAppNotificationService {
    WhatsAppResponseDTO sendWhatsApp(WhatsAppRequestDTO requestDTO);
}
