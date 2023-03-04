package com.assignment.technical.test.api.models;

import java.util.StringJoiner;

public class FailureResponse {

    private Integer cod;
    private String message;

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FailureResponse.class.getSimpleName() + "[", "]")
                .add("code=" + cod)
                .add("message='" + message + "'")
                .toString();
    }
}
