package com.phillit.qa.basicinputtest.Common.KeyType;

public abstract class KeyType {
    // Screen Orientation & KeyType
    public static final int PORTRAIT = 0;
    public static final int LANDSCAPE = 1;

    public static final int QWERTY_KOREA = 100;
    public static final int QWERTY_ENGLISH = 101;
    public static final int CHUNJIIN = 102;
    public static final int SKY = 103;
    public static final int NARAGUL = 104;
    public static final int DANMOUM = 105;

    abstract public void input(String args);
    abstract public void input(StringBuffer args);
}
