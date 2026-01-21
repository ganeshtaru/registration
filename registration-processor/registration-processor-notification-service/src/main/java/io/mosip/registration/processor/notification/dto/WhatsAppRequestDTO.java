package io.mosip.registration.processor.notification.dto;

public class WhatsAppRequestDTO {
        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private String recipient;
        private String message;
}
