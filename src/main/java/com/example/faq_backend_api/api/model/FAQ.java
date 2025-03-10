package com.example.faq_backend_api.api.model;


public class FAQ {

    private int id;
    private String question;
    private String solution;
    private String videoLink;

    public FAQ(int id, String question, String solution, String videoLink) {
        this.id = id;
        this.question = question;
        this.solution = solution;
        this.videoLink = videoLink;
    }


//#region getter & setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSolution() {
        return solution;
    }
    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getVideoLink() {
        return videoLink;
    }
    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

//#endregion

    
}
