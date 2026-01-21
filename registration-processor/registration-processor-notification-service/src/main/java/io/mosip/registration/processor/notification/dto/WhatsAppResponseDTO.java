package io.mosip.registration.processor.notification.dto;

public class WhatsAppResponseDTO {

    private int statusCode;
    private String response;

    public WhatsAppResponseDTO(int statusCode, String response) {
        this.statusCode = statusCode;
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponse() {
        return response;
    }
}
