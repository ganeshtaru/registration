package io.mosip.registration.processor.notification.dto;

public class WhatsAppResponseDTO {
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getRecipient() {
            return recipient;
        }

        public void setRecipient(String recipient) {
            this.recipient = recipient;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public boolean isFromMe() {
            return fromMe;
        }

        public void setFromMe(boolean fromMe) {
            this.fromMe = fromMe;
        }

        private boolean success;
        private String recipient;
        private String name;
        private String message;
        private String messageId;
        private long timestamp;
        private boolean fromMe;

    }
