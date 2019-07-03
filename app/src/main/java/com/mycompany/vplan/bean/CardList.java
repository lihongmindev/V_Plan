package com.mycompany.vplan.bean;

public class CardList {
    private int difficultyLevel;
    private String subject;
    private boolean collect;
    private String title;
    private boolean complete;

    public CardList(int difficultyLevel,String subject,boolean collect,String title,boolean complete){
        this.difficultyLevel = difficultyLevel;
        this.subject = subject;
        this.collect = collect;
        this.title = title;
        this.complete = complete;
    }
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompelete() {
        return complete;
    }

    public void setCompelete(boolean compelete) {
        this.complete = compelete;
    }



}
