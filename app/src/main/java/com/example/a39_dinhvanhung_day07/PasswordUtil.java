package com.example.a39_dinhvanhung_day07;

import java.util.regex.Pattern;

public class PasswordUtil {
    public boolean hasSpace(String password){
        if(password.length()==password.trim().length())
            return true;
        else
            return false;
    }
    public boolean hasLength(String password){
        if(password.length()>=6)
            return true;
        else
            return false;
    }
    public boolean hasSymbol(String password){
        return !password.matches("[A-Za-z0-9]*");
    }
}
