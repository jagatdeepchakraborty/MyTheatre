package com.jdc.theatre.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TheatreSeatingTest {
    TheatreSeating theatreSeatingUnderTest;
    ArrayList<String> inputArr;
    TheatreObject theatreObject;

    @Before
    public void setUp() throws Exception {
        theatreSeatingUnderTest = new TheatreSeating();
        inputArr = readFile("SampleInput.txt");
        theatreObject = theatreSeatingUnderTest.parseInput(inputArr);
    }

    public ArrayList<String> readFile(String fileName) throws Exception {
        String fileContents = null;
        try {
            URI uri = TheatreSeating.class.getClassLoader().getResource(fileName).toURI();
            fileContents = new String(Files.readAllBytes(Paths.get(uri)));
        } catch (Exception e) {
            throw new Exception(fileName + " doesn't exist in the classpath !!!!!");
        }
        ArrayList<String> stdin = new ArrayList<String>();
        String[] strArr = fileContents.replace("\r","").split("\n");
        Arrays.stream(strArr).forEach(line -> stdin.add(line));

        return  stdin;
    }
}
