package agh.ics.oop;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST; //zgodnie z kierunkiem wskazowek zegara zapisane!

    public String toString(){
        switch(this) {
            case NORTH: return "Północ";
            case SOUTH: return "Południe";
            case WEST: return "Zachód";
            case EAST: return "Wschód";
        }
        return "Nieznany kierunek";
    }
    public MapDirection next(){
        MapDirection[] arr = MapDirection.values();
        int nextIndex = (this.ordinal() + 1)%arr.length;
        return arr[nextIndex];
    }
    public MapDirection previous(){
        MapDirection[] arr = MapDirection.values();
        int nextIndex;
        if(this.ordinal() == 0){
            nextIndex = arr.length -1;
        }else{
            nextIndex = (this.ordinal() - 1);
        }
        return arr[nextIndex];
    }
    public Vector2d toUnitVector(){
        switch(this){
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case EAST: return new Vector2d(1,0);
        }
        return null;
    }

}
