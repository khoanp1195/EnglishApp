package com.example.tracnghiem2;

public class Question {
    public int ID;
    public String Question;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;
    public String image;
    private String traloi="";
    public int ChoiceId = -1; //hỗ trợ checkId của radiogroup



    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public Question(int ID, String question, String answerA, String answerB, String answerC, String answerD, String answer, String traloi,String image) {
        this.ID = ID;
        Question = question;
        AnswerA = answerA;
        AnswerB = answerB;
        AnswerC = answerC;
        AnswerD = answerD;
        Answer = answer;
        this.image = image;
        this.traloi = traloi;
    }

    public Question() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String answerA) {
        AnswerA = answerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String answerB) {
        AnswerB = answerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String answerC) {
        AnswerC = answerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String answerD) {
        AnswerD = answerD;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}

 //
