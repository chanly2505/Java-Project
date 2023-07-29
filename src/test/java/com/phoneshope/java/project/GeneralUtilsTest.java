package com.phoneshope.java.project;

import com.phoneshope.java.project.utill.GeneralUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralUtilsTest {
    @Test
    public  void testtoIntegerList(){
        //Given
        List<String> name = List.of("Dara" ,"Chan", "Ly");
        //When
        List<Integer> list = GeneralUtils.toIntegerList(name);
        //Then

        assertEquals (3,list.size());
        assertEquals(4, list.get(0));
    }

    @Test
    public  void testGetEvenNumber(){
        //Given
        List<Integer> list = List.of(4,5,3,20,6);
        //When
        GeneralUtils.getEvenNumber(list);
        //Then
        assertEquals(5 ,list.size());
        assertEquals(4, list.get(0));
    }
}