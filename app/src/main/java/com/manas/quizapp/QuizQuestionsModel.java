package com.manas.quizapp;



public class QuizQuestionsModel {
    String QuestionStatement;
    String Option1;
    String Option2;
    String Option3;
    String Option4;
    String Option5;
    String Option6;
    Integer isPicture;
    String correctOptionNumber;

    public QuizQuestionsModel(String questionStatement, String option1, String option2, String option3, String option4, String option5, String option6, Integer isPicture, String correctOptionNumber) {
        QuestionStatement = questionStatement;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        Option5 = option5;
        Option6 = option6;
        this.isPicture = isPicture;
        this.correctOptionNumber = correctOptionNumber;
    }

    public QuizQuestionsModel() {

    }


    public String getQuestionStatement() {
        return QuestionStatement;
    }

    public void setQuestionStatement(String questionStatement) {
        QuestionStatement = questionStatement;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public String getOption2() {
        return Option2;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public String getOption4() {
        return Option4;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }

    public String getOption5() {
        return Option5;
    }

    public void setOption5(String option5) {
        Option5 = option5;
    }

    public String getOption6() {
        return Option6;
    }

    public void setOption6(String option6) {
        Option6 = option6;
    }

    public Integer getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(Integer isPicture) {
        this.isPicture = isPicture;
    }

    public String getCorrectOptionNumber() {
        return correctOptionNumber;
    }

    public void setCorrectOptionNumber(String correctOptionNumber) {
        this.correctOptionNumber = correctOptionNumber;
    }



}
