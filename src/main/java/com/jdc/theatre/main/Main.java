package com.jdc.theatre.main;

import com.jdc.theatre.logic.TheatreSeating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        ArrayList<String> stdin = new ArrayList<String>();
        try {
            while (in.hasNextLine()) {
                String s = in.nextLine();
                //This is to stop the program from waiting for any further input
                if (s.equals("##")) {
                    in.close();
                } else {
                    stdin.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Scanner closed");
        }
        TheatreSeating theatreSeating = new TheatreSeating();
        theatreSeating.efficientSeating(stdin);
    }
}
