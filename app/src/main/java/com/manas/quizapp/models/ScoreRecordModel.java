package com.manas.quizapp.models;

import org.jetbrains.annotations.NotNull;

public class ScoreRecordModel {
    String username;
    String sessionTS;
    String category;
    Integer score;
    Integer quizLength;
    double correctPercent;

    public ScoreRecordModel(String username, String sessionTS, String category, Integer score, Integer quizLength, double correctPercent) {
        this.username = username;
        this.sessionTS = sessionTS;
        this.category = category;
        this.score = score;
        this.quizLength = quizLength;
        this.correctPercent = correctPercent;
    }

    public ScoreRecordModel() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionTS() {
        return sessionTS;
    }

    public void setSessionTS(String sessionTS) {
        this.sessionTS = sessionTS;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getQuizLength() {
        return String.valueOf(quizLength);
    }

    public void setQuizLength(Integer quizLength) {
        this.quizLength = quizLength;
    }

    public double getCorrectPercent() {
        return correctPercent;
    }

    public void setCorrectPercent(Double correctPercent) {
        this.correctPercent = correctPercent;
    }

    @NotNull
    @Override
    public String toString() {
        return "ScoreRecordModel{" +
                "Username='" + username + '\'' +
                ", SessionTS='" + sessionTS + '\'' +
                ", category='" + category + '\'' +
                ", score=" + score +
                ", quiz_length=" + quizLength +
                ", correct_percent=" + correctPercent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreRecordModel)) return false;

        ScoreRecordModel that = (ScoreRecordModel) o;

        if (Double.compare(that.getCorrectPercent(), getCorrectPercent()) != 0)
            return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getSessionTS() != null ? !getSessionTS().equals(that.getSessionTS()) : that.getSessionTS() != null)
            return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getScore() != null ? !getScore().equals(that.getScore()) : that.getScore() != null)
            return false;
        return getQuizLength() != null ? getQuizLength().equals(that.getQuizLength()) : that.getQuizLength() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getSessionTS() != null ? getSessionTS().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getScore() != null ? getScore().hashCode() : 0);
        result = 31 * result + (getQuizLength() != null ? getQuizLength().hashCode() : 0);
        temp = Double.doubleToLongBits(getCorrectPercent());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
