package com.jdc.theatre.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TheatreSeating {

    public void efficientSeating(ArrayList<String> stdin) {
        TheatreObject theatreObject = new TheatreObject();
        theatreObject = parseInput(stdin);
        assignSeats(theatreObject);
    }

    public void assignSeats(TheatreObject theatreObject) {
        theatreObject.getRequests().forEach(request -> {
            boolean found = false;
            String[] rowSplit = null;
            for (Map<String, Integer> layoutRow : theatreObject.getLayout()) {
                for (Map.Entry<String, Integer> seat : layoutRow.entrySet()) {
                    int availability = seat.getValue();
                    String position = seat.getKey();
                    if (Integer.parseInt(request.split(" ")[1]) <= availability) {
                        rowSplit = position.split(":");
                        System.out.println(request.split(" ")[0] + " Row " + (Integer.parseInt(rowSplit[0]) + 1) + " Section " + (Integer.parseInt(rowSplit[1]) + 1));
                        seat.setValue(availability - Integer.parseInt(request.split(" ")[1]));
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (!found) showReason(theatreObject.getLayout(), request);
        });
    }

    public void showReason(ArrayList<Map<String, Integer>> layout, String request) {
        String requestor = request.split(" ")[0];
        int seatsRequested = Integer.parseInt(request.split(" ")[1]);
        int totalAvailable = layout.stream().mapToInt(stringIntegerMap -> {
            return stringIntegerMap.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
        }).sum();
        if (totalAvailable > seatsRequested) {
            System.out.println(requestor + " Call to split party");
        } else {
            System.out.println(requestor + " Sorry, we can't handle your party.");
        }
    }

    public TheatreObject parseInput(ArrayList<String> stdin) {
        ArrayList<Map<String, Integer>> layout = new ArrayList<>();
        ArrayList<String> requests = new ArrayList<>();
        String[] row = null;
        Map<String, Integer> layoutRow = null;
        int lineBreak = 0;
        for (String line : stdin) {
            if (line.trim().length() == 0) {
                lineBreak = stdin.indexOf(line);
                break;
            }
            row = line.split(" ");
            layoutRow = new HashMap<>();
            for (int i = 0; i < row.length; i++) {
                layoutRow.put(stdin.indexOf(line) + ":" + i, Integer.parseInt(row[i]));
            }
            layout.add(layoutRow);
        }
        for (int i = lineBreak + 1; i < stdin.size(); i++) {
            requests.add(stdin.get(i));
        }
        TheatreObject theatreObject = new TheatreObject();
        theatreObject.setLayout(layout);
        theatreObject.setRequests(requests);
        return theatreObject;
    }
}
