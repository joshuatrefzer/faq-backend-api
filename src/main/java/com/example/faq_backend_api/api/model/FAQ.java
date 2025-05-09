package com.example.faq_backend_api.api.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "faqs")
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 500)
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Column(length = 1000)
    private String link;

    @ManyToMany
    @JoinTable(
      name = "faq_tag", 
      joinColumns = @JoinColumn(name = "faq_id"), 
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public FAQ() {}

    public FAQ(String question, String answer, String link) {
        this.question = question;
        this.answer = answer;
        this.link = link;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }
}
