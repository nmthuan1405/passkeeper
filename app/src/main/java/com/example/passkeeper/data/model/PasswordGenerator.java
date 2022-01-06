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

    public PasswordGenerator(Integer mLength, Boolean mHaveUpper, Boolean mHaveLower, Boolean mHaveNumber, Boolean mHaveSpecial, Integer mMinNumber, Integer mMinSpecial) {
        this.mLength = mLength;
        this.mHaveUpper = mHaveUpper;
        this.mHaveLower = mHaveLower;
        this.mHaveNumber = mHaveNumber;
        this.mHaveSpecial = mHaveSpecial;
        this.mMinNumber = mMinNumber;
        this.mMinSpecial = mMinSpecial;
    }

    public void setmLength(Integer mLength) {
        this.mLength = mLength;
    }

    public void setmHaveUpper(Boolean mHaveUpper) {
        this.mHaveUpper = mHaveUpper;
    }

    public void setmHaveLower(Boolean mHaveLower) {
        this.mHaveLower = mHaveLower;
    }

    public void setmHaveNumber(Boolean mHaveNumber) {
        this.mHaveNumber = mHaveNumber;
    }

    public void setmHaveSpecial(Boolean mHaveSpecial) {
        this.mHaveSpecial = mHaveSpecial;
    }

    public void setmMinNumber(Integer mMinNumber) {
        this.mMinNumber = mMinNumber;
    }

    public void setmMinSpecial(Integer mMinSpecial) {
        this.mMinSpecial = mMinSpecial;
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
        String shuffled = "";
        for (String character : characters) {
            shuffled += character;
        }

        return shuffled;
    }

}
