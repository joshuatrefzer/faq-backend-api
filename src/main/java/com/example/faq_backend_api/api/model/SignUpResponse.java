package com.example.faq_backend_api.api.model;

public class SignUpResponse {

    private boolean success;
    private String message;

    public SignUpResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

  
    
}
