package com.example.myapplication;


import android.widget.TextView;

public class Creature {
    private int hunger;
    private int tiredness;
    private int boredom;
    private int happiness;


    private TextView hungerTextView;
    private TextView tirednessTextView;
    private TextView boredomTextView;
    private TextView happinessTextView;
    private TextView angerTextView;

    private int anger;



    public void setHungerTextView(TextView textView) {
        this.hungerTextView = textView;
    }

    public void setTirednessTextView(TextView textView) {
        this.tirednessTextView = textView;
    }

    public void setBoredomTextView(TextView textView) {
        this.boredomTextView = textView;
    }

    public void setHappinessTextView(TextView textView) {
        this.happinessTextView = textView;
    }

    public void setAngerTextView(TextView textView) {
        this.angerTextView = textView;
    }


    public void setHunger(int hunger) {
        this.hunger = hunger;
        if (hungerTextView != null) {
            hungerTextView.setText("Голод: " + hunger);
        }
    }

    public void setTiredness(int tiredness) {
        this.tiredness = tiredness;
        if (tirednessTextView != null) {
            tirednessTextView.setText("Бодрость: " + tiredness);
        }
    }

    public void setBoredom(int boredom) {
        this.boredom = boredom;
        if (boredomTextView != null) {
            boredomTextView.setText("Скука: " + boredom);
        }
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
        if (happinessTextView != null) {
            happinessTextView.setText("Счастье: " + happiness);
        }
    }

    public void setAnger(int anger) {
        this.anger = anger;
        if (angerTextView != null) {
            angerTextView.setText("Злость: " + anger);
        }
    }

    public Creature() {
        hunger = 0;
        tiredness = 0;
        boredom = 0;
        happiness = 100;
    }

    public void feed() {
        hunger -= 10;
        if (hunger < 0) hunger = 0;
        happiness += 5;
        if (happiness > 100) happiness = 100;
    }

    public void sleep() {
        tiredness -= 10;
        if (tiredness < 0) tiredness = 0;
        happiness += 10;
        if (happiness > 100) happiness = 100;
    }

    public void play() {
        boredom -= 10;
        if (boredom < 0) boredom = 0;
        happiness += 15;
        if (happiness > 100) happiness = 100;
    }

    public void increaseHunger() {
        hunger += 5;
    }

    public void increaseTiredness() {
        tiredness += 5;
    }

    public void increaseBoredom() {
        boredom += 5;
    }

    public int getHunger() {
        return hunger;
    }

    public int getTiredness() {
        return tiredness;
    }

    public int getBoredom() {
        return boredom;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getAnger() {
        return anger;
    }


}