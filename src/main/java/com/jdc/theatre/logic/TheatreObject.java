package com.jdc.theatre.logic;

import java.util.ArrayList;
import java.util.Map;

public class TheatreObject {
    private ArrayList<Map<String, Integer>> layout;
    private ArrayList<String> requests;

    public ArrayList<Map<String, Integer>> getLayout() {
        return layout;
    }

    public void setLayout(ArrayList<Map<String, Integer>> layout) {
        this.layout = layout;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }
}
