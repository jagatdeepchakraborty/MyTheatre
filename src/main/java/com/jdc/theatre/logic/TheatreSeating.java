package com.jdc.theatre.logic;

import com.jdc.theatre.exception.ProcessingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreSeating {

    public String efficientSeating(List<String> stdin) {
        TheatreObject theatreObject = new TheatreObject();
        String returnMessage = null;
        try {
            theatreObject = parseInput(stdin);
            if (theatreObject.getLayout().size() > 0 && theatreObject.getRequests().size() > 0) {
                return assignSeats(theatreObject);
            } else {
                returnMessage = "Either layout or request info is missing";
                System.out.println(returnMessage);
                return returnMessage;
            }
        } catch (Exception e) {
            returnMessage = e.getMessage();
            System.out.println(e.getMessage());
        }
        return returnMessage;
    }

    public String assignSeats(TheatreObject theatreObject) {
        boolean found = false;
        String[] rowSplit = null;
        String position;
        String returnMessage = "";
        String message = "";
        for (String request : theatreObject.getRequests()) {
            found = false;
            rowSplit = null;
            for (Map<String, Integer> layoutRow : theatreObject.getLayout()) {
                for (Map.Entry<String, Integer> seat : layoutRow.entrySet()) {
                    int availability = seat.getValue();
                    position = seat.getKey();
                    if (Integer.parseInt(request.split(" ")[1]) <= availability) {
                        rowSplit = position.split(":");
                        message = request.split(" ")[0] + " Row " +
                                (Integer.parseInt(rowSplit[0]) + 1) + " Section " + (Integer.parseInt(rowSplit[1]) + 1);
                        returnMessage = returnMessage + message + "\n";
                        System.out.println(message);
                        seat.setValue(availability - Integer.parseInt(request.split(" ")[1]));
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (!found) {
                returnMessage = returnMessage + showReason(theatreObject.getLayout(), request) + "\n";
            }
        }
        return returnMessage != null ? returnMessage : "Not Found";
        /*theatreObject.getRequests().forEach(request -> {
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
        });*/
    }

    public String showReason(ArrayList<Map<String, Integer>> layout, String request) {
        String requestor = request.split(" ")[0];
        String returnMessage = null;
        int seatsRequested = Integer.parseInt(request.split(" ")[1]);
        int totalAvailable = layout.stream().mapToInt(stringIntegerMap -> {
            return stringIntegerMap.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
        }).sum();
        if (totalAvailable >= seatsRequested) {
            returnMessage = requestor + " Call to split party";
            System.out.println(returnMessage);
        } else {
            returnMessage = requestor + " Sorry, we can't handle your party.";
            System.out.println(returnMessage);
        }
        return returnMessage;
    }

    public TheatreObject parseInput(List<String> stdin) throws ProcessingException {
        ArrayList<Map<String, Integer>> layout = new ArrayList<>();
        ArrayList<String> requests = new ArrayList<>();
        String[] row = null;
        Map<String, Integer> layoutRow = null;
        int lineBreak = 0;
        try {
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
        } catch (NumberFormatException nfe) {
            throw new ProcessingException(nfe, "Layout data can only have numbers");
        }
        if (lineBreak > 0) {
            for (int i = lineBreak + 1; i < stdin.size(); i++) {
                requests.add(stdin.get(i));
                if(stdin.get(i).split(" ").length != 2){
                    throw new ProcessingException("Invalid request format, the right format is 'NAME NUMBER_OF_SEATS'");
                }
            }
        }

        TheatreObject theatreObject = new TheatreObject();
        theatreObject.setLayout(layout);
        theatreObject.setRequests(requests);
        return theatreObject;
    }
}
