package com.denizcanbagdatlioglu.easybankapi.application.user.domain.entity;

public class Email {
    private final String content;

    public boolean isValid() {
        String[] tokens = content.split("@");
        if(tokens.length != 2) return false;

        tokens = tokens[1].split("\\.");
        if(tokens.length != 2) return false;

        return true;
    }

    public Email(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Email{content: " + content + "}";
    }
}
