package org.homework.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestToCloud {
    String format = "png";
    int width = 1000;
    int height = 1000;
    String fontFamily = "sans-serif";
    int fontScale = 100;
    String scale = "linear";
    String text;

    public RequestToCloud(String text) {
        this.text = "<" + text + ">";
    }
}
