package com.manas.quizapp;

public class ScoreRecordModel {
    String Username;
    String SessionTS;
    String category;
    Integer score;
    Integer quiz_length;
    double correct_percent;

    public ScoreRecordModel(String username, String sessionTS, String category, Integer score, Integer quiz_length, double correct_percent) {
        Username = username;
        SessionTS = sessionTS;
        this.category = category;
        this.score = score;
        this.quiz_length = quiz_length;
        this.correct_percent = correct_percent;
    }

    public ScoreRecordModel() {

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getSessionTS() {
        return SessionTS;
    }

    public void setSessionTS(String sessionTS) {
        SessionTS = sessionTS;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getQuiz_length() {
        return quiz_length;
    }

    public void setQuiz_length(Integer quiz_length) {
        this.quiz_length = quiz_length;
    }

    public double getCorrect_percent() {
        return correct_percent;
    }

    public void setCorrect_percent(double correct_percent) {
        this.correct_percent = correct_percent;
    }

    @Override
    public String toString() {
        return "ScoreRecordModel{" +
                "Username='" + Username + '\'' +
                ", SessionTS='" + SessionTS + '\'' +
                ", category='" + category + '\'' +
                ", score=" + score +
                ", quiz_length=" + quiz_length +
                ", correct_percent=" + correct_percent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreRecordModel)) return false;

        ScoreRecordModel that = (ScoreRecordModel) o;

        if (Double.compare(that.getCorrect_percent(), getCorrect_percent()) != 0)
            return false;
        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null)
            return false;
        if (getSessionTS() != null ? !getSessionTS().equals(that.getSessionTS()) : that.getSessionTS() != null)
            return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getScore() != null ? !getScore().equals(that.getScore()) : that.getScore() != null)
            return false;
        return getQuiz_length() != null ? getQuiz_length().equals(that.getQuiz_length()) : that.getQuiz_length() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getSessionTS() != null ? getSessionTS().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getScore() != null ? getScore().hashCode() : 0);
        result = 31 * result + (getQuiz_length() != null ? getQuiz_length().hashCode() : 0);
        temp = Double.doubleToLongBits(getCorrect_percent());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
