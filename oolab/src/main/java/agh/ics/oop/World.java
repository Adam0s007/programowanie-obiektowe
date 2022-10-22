package agh.ics.oop;

public class World {
    public static void run(Direction[] directions){
        for(Direction direction: directions ) {
            switch(direction) {
                case f:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case r:
                    System.out.println("Zwierzak skreca w prawo");
                    break;
                case b:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case l:
                    System.out.println("Zwierzak skreca w lewo");
                    break;
                default:
                    System.out.println("Nieznany kierunek");
            }
        }
    }

    public static void main(String[] args) {
        if(args.length >= 2){
            Direction[] dir = new Direction[args.length];
            for(int i = 0; i < args.length;i++) {
                try{
                    dir[i] = Direction.valueOf(args[i]);  //nawet jesli arg[i] == "other" to wykona się default w switch w run()
                }catch (IllegalArgumentException e){
                    dir[i] = Direction.other; //aby zlapac wyjatek, jesli podamy niewlasciwy argument, wtedy w run() w switchu wykona sie default
                }
            }
            System.out.println("Start");
            run(dir);
            System.out.println("Stop");
        }
        else{
            System.out.println("Za malo argumentow");
        }

//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//        MapDirection direct = MapDirection.NORTH;
//        System.out.println(direct);
//        System.out.println(direct.toUnitVector());
//        direct = MapDirection.EAST;
//        System.out.println(direct.toUnitVector());
//        direct = MapDirection.WEST;
//        System.out.println(direct.toUnitVector());
//        direct = MapDirection.SOUTH;
//        System.out.println(direct.toUnitVector());
    }
}