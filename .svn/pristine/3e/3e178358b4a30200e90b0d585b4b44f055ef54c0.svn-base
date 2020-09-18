package de.MaxWai.Interpreter;

public class Point {

    private Coordinates coordinates;
    private String freeText;

    public Point(String freeText, String coordinates) {
        this.freeText = freeText;
        this.coordinates = new Coordinates(coordinates);
    }

    public String getParsedData(String pointName) {
        return coordinates.returnParsedCoordinates(":") + ":" + pointName + ":" + freeText;
    }

    @Override
    public String toString() {
        return getParsedData("temp");
    }
}
