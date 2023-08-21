package battleship;

public class Ship {
    private final int _length;
    private final String _name;

    public Ship(String name, int length){
        _name = name;
        _length = length;
    }

    public int getLength(){
        return _length;
    }

    public String getName() { return _name; }
}
