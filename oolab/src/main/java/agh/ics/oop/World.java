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


    }
}