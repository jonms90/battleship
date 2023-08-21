package battleship.state;

import battleship.Board;
import battleship.DeploymentCoordinates;
import battleship.Display;
import battleship.Ship;
import battleship.input.InputHandler;

import java.util.LinkedList;

public class Initialization implements GameState{

    private final LinkedList<Ship> _shipsToDeploy;
    private final LinkedList<String> _errors;

    public Initialization(){
        _errors = new LinkedList<>();
        _shipsToDeploy = createShips();
    }

    @Override
    public GameState update(String input, InputHandler handler, Board board) {
        try{
            DeploymentCoordinates coordinates = handler.getDeploymentCoordinates(input);
            Ship nextShip = _shipsToDeploy.getFirst();
            if(nextShip.getLength() != coordinates.getLength()){
                // TODO: handle Error
                _errors.add(String.format("Error! Wrong length of the %s! Try again:%n", nextShip.getName()));
                return null;
            }

            if(!board.isValidDeployment(coordinates)){
                _errors.add(String.format("Error! You placed it too close to another one. Try again:%n")); // Handle proximity
                return null;
            }

            board.placeShip(coordinates);
            _shipsToDeploy.removeFirst();
            return _shipsToDeploy.isEmpty() ? new Playing() : null;
        } catch(IllegalArgumentException ex){
            _errors.add(String.format("Error! Wrong ship location! Try again:%n"));
            return null;
        }
        catch(Exception ex){
            return null;
        }
    }

    @Override
    public void render(Display display, Board board) {
        if(_errors.isEmpty()){
            board.render();
            if(!_shipsToDeploy.isEmpty()){
                display.deployment(_shipsToDeploy.getFirst());
            }
        }
        else{
            display.error(_errors.pop());
        }
    }

    @Override
    public void enter(Display display, Board board) {

    }

    @Override
    public void exit(Display display, Board board) {
        render(display, board);
    }

    private LinkedList<Ship> createShips() {
        LinkedList<Ship> ships = new LinkedList<>();
        ships.add(new Ship("Aircraft Carrier", 5));
        ships.add(new Ship("Battleship", 4));
        ships.add(new Ship("Submarine", 3));
        ships.add(new Ship("Cruiser", 3));
        ships.add(new Ship("Destroyer", 2));
        return ships;
    }
}
