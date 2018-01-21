package com.jdc.theatre.main;

import com.jdc.theatre.logic.TheatreSeating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String input = "6 6\n" +
                "3 5 5 3\n" +
                "4 6 6 4\n" +
                "2 8 8 2\n" +
                "6 6\n" +
                "\n" +
                "Smith 2\n" +
                "Jones 5\n" +
                "Davis 6\n" +
                "Wilson 100\n" +
                "Johnson 3\n" +
                "Williams 4\n" +
                "Brown 8\n" +
                "Miller 12";
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        ArrayList<String> stdin = new ArrayList<String>();
        try {
            while (in.hasNextLine()) {
                String s = in.nextLine();
                if (s.equals("##")) {
                    in.close();
                } else {
                    stdin.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Scanner closed");
        }

        //BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
        TheatreSeating theatreSeating = new TheatreSeating();

        //String[] strArr = input.split("\n");
        //Arrays.stream(strArr).forEach(line -> stdin.add(line));
        theatreSeating.efficientSeating(stdin);
    }
}
