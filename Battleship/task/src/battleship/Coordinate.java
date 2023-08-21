package battleship;

import java.util.LinkedList;

public class Coordinate {
    private final int _row;
    private final int _column;

    public Coordinate(int row, int column){
        if(row < 1 || row > 10){
            throw new IllegalArgumentException("Invalid row value: " + row);
        }

        if(column < 1 || column > 10){
            throw new IllegalArgumentException("Invalid column value: " + column);
        }

        _row = row;
        _column = column;
    }

    public Coordinate(char row, int column){
        this(Character.getNumericValue(row) - 9, column); // -9 => A = 1
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(obj.getClass() != this.getClass()){
            return false;
        }

        final Coordinate other = (Coordinate)obj;
        return this._row == other._row && this._column == other._column;
    }

    public int getColumn() {
        return _column;
    }

    public int getRow() {
        return _row;
    }

    public LinkedList<Coordinate> getNeighbours() { // TODO: Should this check diagonals as well?
        LinkedList<Coordinate> neighbours = new LinkedList<>();
        if(_row > 1){
            neighbours.add(new Coordinate(_row - 1, _column)); // North
        }
        if(_row < 10){
            neighbours.add(new Coordinate(_row + 1, _column)); // South
        }
        if(_column > 1){
            neighbours.add(new Coordinate(_row, _column - 1)); // West
        }
        if(_column < 10){
            neighbours.add(new Coordinate(_row, _column + 1)); // East
        }
        return neighbours;
    }
}
