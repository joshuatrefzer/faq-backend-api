package com.example.faq_backend_api.api.model;


public class DeleteResponse {
    private boolean success;
    private String message;

    public DeleteResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
