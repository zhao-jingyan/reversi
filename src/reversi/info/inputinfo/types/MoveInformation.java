package reversi.info.inputinfo.types;

import reversi.info.inputinfo.InfoType;
import reversi.info.inputinfo.InputInformation;

public class MoveInformation extends InputInformation {
    private final int[] coordinates;
    private final String rawInput;

    private MoveInformation(int[] coordinates, String rawInput) {
        this.coordinates = coordinates;
        this.rawInput = rawInput;
    }

    public static MoveInformation create(String input) {
        int[] coords = parseCoordinates(input);
        return new MoveInformation(coords, input);
    }

    private static int[] parseCoordinates(String input) {
        String[] parts = input.split(" ");
        int[] coords = new int[2];
        coords[0] = Integer.parseInt(parts[0]);
        coords[1] = Integer.parseInt(parts[1]);
        return coords;
    }

    @Override
    public InfoType getInfoType() {
        return InfoType.COORDINATES;
    }

    @Override
    public int[] getInfo() {
        return coordinates;
    }

    public String getRawInput() {
        return rawInput;
    }
}