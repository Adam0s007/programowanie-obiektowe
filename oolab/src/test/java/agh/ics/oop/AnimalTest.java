package agh.ics.oop;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {


    @Test
    void isAt() {
//        IWorldMap map0 = new RectangularMap(4, 4);
//        Vector2d position0 = new Vector2d(2, 2);

        Animal tomek = new Animal();
        assertTrue(tomek.isAt(new Vector2d(2,2)));
        tomek.move(MoveDirection.BACKWARD);
        assertTrue(tomek.isAt(new Vector2d(2,1)));
        tomek.move(MoveDirection.BACKWARD);
        assertTrue(tomek.isAt(new Vector2d(2,0)));
        tomek.move(MoveDirection.BACKWARD); // SPRAWDZENIE CZY WYJEDZIE POZA MAPE!
        assertFalse(tomek.isAt(new Vector2d(2,-1)));
        tomek.move(MoveDirection.FORWARD);
        tomek.move(MoveDirection.FORWARD);
        tomek.move(MoveDirection.FORWARD);
        tomek.move(MoveDirection.FORWARD); // 4 pozycje do przodu od (2,0)
        assertTrue(tomek.isAt(new Vector2d(2,4)));
        tomek.move(MoveDirection.FORWARD);
        assertFalse(tomek.isAt(new Vector2d(2,5)));
        tomek.move(MoveDirection.LEFT);
    }
    @Test
    void getDirection(){
        //getDirection()
        Animal tomek = new Animal();
        assertEquals(MapDirection.NORTH, tomek.getDirection());
        tomek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, tomek.getDirection());
        tomek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, tomek.getDirection());
        tomek.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.WEST, tomek.getDirection());
        tomek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, tomek.getDirection());
        tomek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, tomek.getDirection());
    }

    @Test
    void move() {
        Animal grzesiek = new Animal();
        assertEquals("(2,2) N", grzesiek.extendedToString());//pozycja defaultowa!
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,1) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,0) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.BACKWARD); // SPRAWDZENIE CZY WYJEDZIE POZA MAPE!
        assertEquals("(2,0) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(2,1) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(2,2) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,1) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.RIGHT);  //ZMIENIONA ORIENTACJA NA PRAWO! (kierunek wschodni)
        assertEquals("(2,1) E",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);  //PRZEMIESZCZANIE SIE PRZY NOWEJ ORIENTACJI
        assertEquals("(3,1) E",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,1) E",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(3,1) E",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,1) E",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE CZY WYJEDZIE POZA MAPE!
        assertEquals("(4,1) E",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.LEFT); //ZMIENIONA ORIENTACJA NA LEWO! (ponownie jedzie w kierunku polnocnym)
        assertEquals("(4,1) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,2) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,3) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(4,2) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,3) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,4) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(4,4) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD); // PONOWNE SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(4,4) N",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.LEFT); // ZMIENIONA ORIENTACJA! Teraz w kierunku zachodnim
        assertEquals("(4,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(3,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(2,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(1,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(1,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(0,4) W",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.LEFT); // PONOWNIE ZMIENIONA ORIENTACJA! Teraz w kierunku poludniowym
        assertEquals("(0,4) S",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,3) S",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,2) S",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(0,3) S",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,2) S",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,1) S",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,0) S",grzesiek.extendedToString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(0,0) S",grzesiek.extendedToString());
    }

}