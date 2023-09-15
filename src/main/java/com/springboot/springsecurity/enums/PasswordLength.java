package com.springboot.springsecurity.enums;

import com.springboot.springsecurity.exception.EnumNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PasswordLength {
    EIGHT(8), NINE(9), TEN(10), ELEVEN(11), TWELEVE(12);
    private int displayValue;

    private PasswordLength(int displayValue) {
        this.displayValue = displayValue;
    }

    public int getDisplayValue() {
        return displayValue;
    }

    public static List<Integer> getDisplayValues() {
        return Arrays.stream(values()).map(PasswordLength::getDisplayValue).collect(Collectors.toList());
    }

    public static PasswordLength getByDisplayValue(int displayValue) {
        for (PasswordLength type : values()) {
            if (type.getDisplayValue() == displayValue) {
                return type;
            }
        }
        throw new EnumNotFoundException("PasswordLength", "PasswordLength not found with : " + displayValue);
    }
}
