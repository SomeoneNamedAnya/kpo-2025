package org.example.auxiliary_tools;

import lombok.Getter;

//Я просто в шоке... 1:39 ночи, и я пишу класс Pair, которого нет в Java...
@Getter
public class Pair {

    float firstValue;
    String secondValue;
    public Pair(float firstValue, String secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
    public static int compare(Pair first, Pair second) {
        return -Float.compare(first.firstValue, second.firstValue);

    }

}
