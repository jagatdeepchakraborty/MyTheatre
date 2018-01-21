package com.jdc.theatre.logic;

import com.jdc.theatre.exception.ProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TheatreSeatingTest {
    TheatreSeating theatreSeatingUnderTest;
    ArrayList<String> inputArr;
    TheatreObject theatreObject;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        theatreSeatingUnderTest = new TheatreSeating();
        inputArr = readFile("GoodInput1.txt");
        theatreObject = theatreSeatingUnderTest.parseInput(inputArr);
    }

    @Test
    public void testParseInputNoRequest() throws ProcessingException {
        TheatreObject theatreObject = theatreSeatingUnderTest.parseInput(inputArr.subList(0, 5));
        Assert.assertEquals(5, theatreObject.getLayout().size());
        Assert.assertEquals(0, theatreObject.getRequests().size());
    }

    @Test
    public void testParseInputBadLayoutData() throws ProcessingException {
        expectedException.expect(ProcessingException.class);
        expectedException.expectMessage("Layout data can only have numbers");
        TheatreObject theatreObject = theatreSeatingUnderTest.parseInput(inputArr.subList(6, 7));
        Assert.assertEquals(5, theatreObject.getLayout().size());
        Assert.assertEquals(0, theatreObject.getRequests().size());
    }
    @Test
    public void testParseInputBadRequestData() throws Exception {
        inputArr = readFile("BadRequest.txt");
        expectedException.expect(ProcessingException.class);
        expectedException.expectMessage("Invalid request format, the right format is 'NAME NUMBER_OF_SEATS'");
        TheatreObject theatreObject = theatreSeatingUnderTest.parseInput(inputArr);
    }
    @Test
    public void testShowReasonCantHandle() {
        Assert.assertEquals("William Sorry, we can't handle your party.", theatreSeatingUnderTest.showReason(theatreObject.getLayout(), "William 200"));
    }

    @Test
    public void testShowReasonSplitParty() {
        Assert.assertEquals("Jake Call to split party", theatreSeatingUnderTest.showReason(theatreObject.getLayout(), "Jake 20"));
    }

    @Test
    public void testEfficientSeatingNoRequests() {
        Assert.assertEquals("Either layout or request info is missing", theatreSeatingUnderTest.efficientSeating(inputArr.subList(0, 5)));
    }

    @Test
    public void testEfficientSeatingException() {
        Assert.assertEquals("Layout data can only have numbers", theatreSeatingUnderTest.efficientSeating(inputArr.subList(6, 7)));
    }

    @Test
    public void testEfficientSeatingGoodRequest1() {
        String expected = "Smith Row 1 Section 1\n" +
                "Jones Row 1 Section 2\n" +
                "Davis Row 3 Section 2\n" +
                "Wilson Sorry, we can't handle your party.\n" +
                "Johnson Row 1 Section 1\n" +
                "Williams Row 2 Section 2\n" +
                "Brown Row 4 Section 2\n" +
                "Miller Call to split party\n";
        Assert.assertEquals(expected, theatreSeatingUnderTest.efficientSeating(inputArr));
    }

    @Test
    public void testEfficientSeatingGoodRequest2() throws Exception {
        String expected = "Q Row 1 Section 1\n" +
                "W Sorry, we can't handle your party.\n" +
                "E Call to split party\n" +
                "R Row 1 Section 4\n" +
                "T Row 1 Section 1\n" +
                "Y Row 1 Section 2\n";
        inputArr = readFile("GoodInput2.txt");
        Assert.assertEquals(expected, theatreSeatingUnderTest.efficientSeating(inputArr));
    }

    @Test
    public void testEfficientSeatingGoodRequest3() throws Exception {
        String expected = "D Call to split party\n" +
                "H Sorry, we can't handle your party.\n" +
                "I Row 1 Section 1\n" +
                "G Row 1 Section 1\n" +
                "Y Row 1 Section 2\n";
        inputArr = readFile("GoodInput3.txt");
        Assert.assertEquals(expected, theatreSeatingUnderTest.efficientSeating(inputArr));
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
        String[] strArr = fileContents.replace("\r", "").split("\n");
        Arrays.stream(strArr).forEach(line -> stdin.add(line));

        return stdin;
    }
}
