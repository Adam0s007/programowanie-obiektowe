package agh.ics.oop;

public class World {
    public static void run(Direction[] directions){
        for(Direction direction: directions ) {
            switch(direction) {
                case f:
                    System.out.println("zwierzak idzie do przodu");
                    break;
                case b:
                    System.out.println("zwierzak idzie do tyłu");
                    break;
                case r:
                    System.out.println("zwierzak skręca w prawo");
                    break;
                case l:
                    System.out.println("zwierzak skręca w lewo");
                    break;
                default:
                    System.out.println("");
            }
        }
    }

    public static void main(String[] args) {
        if(args.length >= 2){
            Direction[] dir = new Direction[args.length];
            for(int i = 0; i < args.length;i++) {
                dir[i] = Direction.valueOf(args[i]);
            }
            System.out.println("Start");
            run(dir);
            System.out.println("Stop");
        }
        else{
            System.out.println("Za malo argumentów");
        }
    }
}