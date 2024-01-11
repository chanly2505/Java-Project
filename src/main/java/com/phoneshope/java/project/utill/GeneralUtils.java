package com.phoneshope.java.project.utill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

public class GeneralUtils {




    public  static List<Integer> toIntegerList(List<String> list){
        return  list.stream()
                .map(s -> s.length()).toList();
    }
    public  static List<Integer> getEvenNumber(List<Integer> list){
        return list.stream().filter(x -> x%2 == 0 ).toList();
    }
}