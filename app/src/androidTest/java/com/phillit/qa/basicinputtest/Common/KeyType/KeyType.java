package com.phillit.qa.basicinputtest.Common.KeyType;

public abstract class KeyType {
    // Screen Orientation & KeyType
    public static final int QWERTY_PORTRAIT = 0;
    public static final int QWERTY_LANDSCAPE = 1;

    abstract public void input(String args);
}