package battleship;

import java.util.ArrayList;

public class Ship {
    private final int _length;
    private final String _name;
    private ArrayList<Coordinate> _coordinates;

    public Ship(String name, int length){
        _name = name;
        _length = length;
        _coordinates = new ArrayList<>(length);
    }

    public int getLength(){
        return _length;
    }

    public String getName() { return _name; }

    public ArrayList<Coordinate> getCoordinates(){
        return _coordinates;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(obj.getClass() != this.getClass()){
            return false;
        }

        final Ship other = (Ship)obj;
        return this._name.equals(other._name) && this._length == other._length; // TODO: Assumes ship names are unique.
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates){
        _coordinates = coordinates;
    }
}
