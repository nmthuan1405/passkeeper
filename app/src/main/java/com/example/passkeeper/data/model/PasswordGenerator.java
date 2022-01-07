package com.example.passkeeper.data.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    private Integer mLength;
    private Boolean mHaveUpper;
    private Boolean mHaveLower;
    private Boolean mHaveNumber;
    private Boolean mHaveSpecial;
    private Integer mMinNumber;
    private Integer mMinSpecial;

    public PasswordGenerator() {
        this.mLength = 25;
        this.mHaveUpper = true;
        this.mHaveLower = true;
        this.mHaveNumber = true;
        this.mHaveSpecial = true;
        this.mMinNumber = 5;
        this.mMinSpecial = 5;
    }

    public PasswordGenerator(Integer length, Boolean haveUpper, Boolean haveLower, Boolean haveNumber, Boolean haveSpecial, Integer minNumber, Integer minSpecial) {
        this.mLength = length;
        this.mHaveUpper = haveUpper;
        this.mHaveLower = haveLower;
        this.mHaveNumber = haveNumber;
        this.mHaveSpecial = haveSpecial;
        this.mMinNumber = minNumber;
        this.mMinSpecial = minSpecial;
    }

    public void setLength(Integer length) {
        this.mLength = length;
    }

    public void setHaveUpper(Boolean haveUpper) {
        this.mHaveUpper = haveUpper;
    }

    public void setHaveLower(Boolean haveLower) {
        this.mHaveLower = haveLower;
    }

    public void setHaveNumber(Boolean haveNumber) {
        this.mHaveNumber = haveNumber;
    }

    public void setHaveSpecial(Boolean haveSpecial) {
        this.mHaveSpecial = haveSpecial;
    }

    public void setMinNumber(Integer minNumber) {
        this.mMinNumber = minNumber;
    }

    public void setMinSpecial(Integer minSpecial) {
        this.mMinSpecial = minSpecial;
    }

    public String GeneratePassword()
    {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String special = "!@#$%^&*()_-+=<>?/{}~|";
        String allowed = "";
        StringBuilder password = new StringBuilder(mLength);
        Random random = new Random();

        if(mHaveUpper)
        {
            allowed += upper;
            password.append(upper.charAt(random.nextInt(upper.length() - 1)));
        }
        if(mHaveLower)
        {
            allowed += lower;
            password.append(lower.charAt(random.nextInt(lower.length() - 1)));
        }
        if(mHaveNumber)
        {
            allowed += number;
            for(int i = 0; i < mMinNumber; i++)
                password.append(number.charAt(random.nextInt(number.length() - 1)));
        }
        if(mHaveSpecial)
        {
            allowed += special;
            for(int i = 0; i < mMinSpecial; i++)
                password.append(special.charAt(random.nextInt(special.length() - 1)));
        }
        for(int i = password.length(); i < mLength; i++)
            password.append(allowed.charAt(random.nextInt(allowed.length() - 1)));

        List<String> characters = Arrays.asList(password.toString().split(""));
        Collections.shuffle(characters);
        StringBuilder shuffled = new StringBuilder();
        for (String character : characters) {
            shuffled.append(character);
        }

        return shuffled.toString();
    }

}
