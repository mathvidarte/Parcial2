package com.example.myapplication;

public class QuestionList {
    private String id;
    private String questions;

    public QuestionList(String id, String questions) {
        this.id = id;
        this.questions = questions;
    }

    public QuestionList () {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
