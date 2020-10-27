package com.manas.quizapp;



public class QuizQuestionsModel {
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String option5;
    public String option6;
    public String questionStatement;
    public String correctOptionNumber;
    public String pictureUrl;
    public String questionCategory;
    public Double userRating;

    public QuizQuestionsModel() {
    }

    public QuizQuestionsModel(String option1, String option2, String option3, String option4, String option5, String option6, String questionStatement, String correctOptionNumber, String pictureUrl, String questionCategory, Double userRating) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.option6 = option6;
        this.questionStatement = questionStatement;
        this.correctOptionNumber = correctOptionNumber;
        this.pictureUrl = pictureUrl;
        this.questionCategory = questionCategory;
        this.userRating = userRating;
    }


    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public void setQuestionStatement(String questionStatement) {
        this.questionStatement = questionStatement;
    }

    public String getCorrectOptionNumber() {
        return correctOptionNumber;
    }

    public void setCorrectOptionNumber(String correctOptionNumber) {
        this.correctOptionNumber = correctOptionNumber;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }


}
