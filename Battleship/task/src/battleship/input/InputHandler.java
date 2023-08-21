package battleship.input;

import battleship.Coordinate;
import battleship.DeploymentCoordinates;

public class InputHandler {
    public DeploymentCoordinates getDeploymentCoordinates(String input) {
        var inputs = input.split(" ");
        if(inputs.length != 2){
            throw new IllegalArgumentException("Not valid input for coordinates.");
        }

        // The following can throw if input is invalid.
        Coordinate start = new Coordinate(inputs[0].substring(0, 1).charAt(0), Integer.parseInt(inputs[0].substring(1)));
        Coordinate end = new Coordinate(inputs[1].substring(0, 1).charAt(0), Integer.parseInt(inputs[1].substring(1)));

        return new DeploymentCoordinates(start, end);
    }

    public Coordinate getTarget(String input) {
        if(input.length() != 2){
            throw new IllegalArgumentException("Not valid input for target.");
        }
        return new Coordinate(input.charAt(0), Integer.parseInt(input.substring(1)));
    }
}
