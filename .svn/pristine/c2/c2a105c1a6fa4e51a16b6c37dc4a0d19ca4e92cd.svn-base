package de.MaxWai.Interpreter;

public class Coordinates {

    String parsedLongitude = "E";
    String parsedLatitude = "N";

    public Coordinates (String coordinates) {
        int firstComma = coordinates.indexOf(',');
        double longitude = Double.parseDouble(coordinates.substring(0, firstComma));
        double latitude = Double.parseDouble(coordinates.substring(firstComma + 1, coordinates.indexOf(',', firstComma + 1)));
        if (longitude < 0) {
            parsedLongitude = "W";
            longitude *= -1;
        }
        if (latitude < 0) {
            parsedLatitude = "S";
            latitude *= -1;
        }
        parsedLongitude += String.format("%03d", (int) longitude) + ".";
        parsedLatitude += String.format("%03d", (int) latitude) + ".";

        longitude = (longitude - (int) longitude) * 60;
        latitude = (latitude - (int) latitude) * 60;

        parsedLongitude += String.format("%02d", (int) longitude) + ".";
        parsedLatitude += String.format("%02d", (int) latitude) + ".";

        longitude = (longitude - (int) longitude) * 60;
        latitude = (latitude - (int) latitude) * 60;

        parsedLongitude += String.format("%02d", (int) longitude) + ".";
        parsedLatitude += String.format("%02d", (int) latitude) + ".";

        longitude = (longitude - (int) longitude) * 1000;
        latitude = (latitude - (int) latitude) * 1000;

        parsedLongitude += String.format("%03d", (int) Math.round(longitude));
        parsedLatitude += String.format("%03d", (int) Math.round(latitude));
    }

    public String returnParsedCoordinates(String separator) {
        return parsedLatitude + separator + parsedLongitude;
    }
}
