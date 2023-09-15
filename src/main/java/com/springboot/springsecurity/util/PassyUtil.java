package com.springboot.springsecurity.util;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class PassyUtil {

    public static String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(1);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(1);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(1);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return "error in genrating the random password";
            }

            public String getCharacters() {
                return "!@#$";
            }
        };
        CharacterRule splCharRule = new CharacterRule((org.passay.CharacterData) specialChars);
        splCharRule.setNumberOfCharacters(1);


//	    CharacterData passwordLength = new CharacterData() {
//	    	public String getErrorCode() {
//	    		return "error in genrating the random password";
//	    	}
//	    	
//	    	public String getCharacters() {
//	    		return "'8''9''10''11''12'";
//	    	}
//	    };


//	    CharacterRule passwordLengthRule = new CharacterRule((org.passay.CharacterData) specialChars);
//	    splCharRule.setNumberOfCharacters(1);

        int min = 8; // Minimum length of the password
        int max = 12; // Maximum length of the password

        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);


        String password = gen.generatePassword(random_int, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }

//	public static ArrayList<String> pl() {
//    	ArrayList<String> pl = new ArrayList<String>();
//        pl.add("8");
//        pl.add("9");
//        pl.add("10");
//        pl.add("11");
//        pl.add("12");
//
//    	return pl;
//    }

//	public String eight = "EIGHT";

//	public static EnglishCharacterData[] PasswordLength();

//	public static EnglishCharacterData valueOf(String eight);
}
