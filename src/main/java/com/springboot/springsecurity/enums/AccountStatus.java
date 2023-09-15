package com.springboot.springsecurity.enums;

import com.springboot.springsecurity.exception.EnumNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum AccountStatus {

    REGISTERED(-1), ACTIVE(1), INACTIVE(0);
    private int displayValue;

    private AccountStatus(int displayValue) {
        this.displayValue = displayValue;
    }

    public int getDisplayValue() {
        return displayValue;
    }

    public static List<Integer> getDisplayValues() {
        return Arrays.stream(values()).map(AccountStatus::getDisplayValue).collect(Collectors.toList());
    }

    public static AccountStatus getByDisplayValue(int displayValue) {
        for (AccountStatus type : values()) {
            if (type.getDisplayValue() == displayValue) {
                return type;
            }
        }
        throw new EnumNotFoundException("AccountStatus", "AccountStatus not found with : " + displayValue);
    }
}
