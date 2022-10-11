package agh.ics.oop;

public class World {
    public static void run(Direction[] directions){
        for(Direction direction: directions ) {
            switch(direction) {
                case f -> System.out.println("zwierzak idzie do przodu");
                case b -> System.out.println("zwierzak idzie do tyłu");
                case r -> System.out.println("zwierzak skręca w prawo");
                case l -> System.out.println("zwierzak skręca w lewo");
                default -> System.out.println("");
            }
        }
        System.out.println("Stop");
    }


    public static void main(String[] args) {

        if(args.length >= 2){
            System.out.println("Start");
            Direction[] d = new Direction[args.length];
            for(int i = 0; i < args.length;i++) {
                d[i] = Direction.valueOf(args[i]);
            }
            run(d);
        }
        else{
            System.out.println("Za malo argumentów");
        }
    }
}