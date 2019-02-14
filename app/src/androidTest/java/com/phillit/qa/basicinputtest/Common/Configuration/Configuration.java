package com.phillit.qa.basicinputtest.Common.Configuration;

public abstract class Configuration {
    // Internal Test 단어입력 횟수
    public static final int INTERNAL_TEST_COUNT = 50;

    // 최소배터리 이하로 떨어졌을경우 테스트 일시중지
    public static final int BATTERY_MIN_VALUE =  40;
    // 최대배터리까지 충전되었을 경우 테스트를 재개
    public static final int BATTERY_MAX_VALUE =  90;
}
