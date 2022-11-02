package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {


    @Test
    void isAt() {
        Animal grzesiek = new Animal();
        assertTrue(grzesiek.isAt(new Vector2d(2,2)));
        grzesiek.move(MoveDirection.BACKWARD);
        assertTrue(grzesiek.isAt(new Vector2d(2,1)));
        grzesiek.move(MoveDirection.BACKWARD);
        assertTrue(grzesiek.isAt(new Vector2d(2,0)));
        grzesiek.move(MoveDirection.BACKWARD); // SPRAWDZENIE CZY WYJEDZIE POZA MAPE!
        assertFalse(grzesiek.isAt(new Vector2d(2,-1)));
        grzesiek.move(MoveDirection.FORWARD);
        grzesiek.move(MoveDirection.FORWARD);
        grzesiek.move(MoveDirection.FORWARD);
        grzesiek.move(MoveDirection.FORWARD); // 4 pozycje do przodu od (2,0)
        assertTrue(grzesiek.isAt(new Vector2d(2,4)));
        grzesiek.move(MoveDirection.FORWARD);
        assertFalse(grzesiek.isAt(new Vector2d(2,5)));
        grzesiek.move(MoveDirection.LEFT);

        //getDirection()
        assertEquals(MapDirection.WEST, grzesiek.getDirection());
        grzesiek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, grzesiek.getDirection());
        grzesiek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, grzesiek.getDirection());
        grzesiek.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, grzesiek.getDirection());
        grzesiek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, grzesiek.getDirection());
        grzesiek.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, grzesiek.getDirection());



    }

    @Test
    void move() {
        Animal grzesiek = new Animal();
        assertEquals("(2,2) Północ", grzesiek.toString());//pozycja defaultowa!
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,1) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,0) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.BACKWARD); // SPRAWDZENIE CZY WYJEDZIE POZA MAPE!
        assertEquals("(2,0) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(2,1) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(2,2) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,1) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.RIGHT);  //ZMIENIONA ORIENTACJA NA PRAWO! (kierunek wschodni)
        assertEquals("(2,1) Wschód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);  //PRZEMIESZCZANIE SIE PRZY NOWEJ ORIENTACJI
        assertEquals("(3,1) Wschód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,1) Wschód",grzesiek.toString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(3,1) Wschód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,1) Wschód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE CZY WYJEDZIE POZA MAPE!
        assertEquals("(4,1) Wschód",grzesiek.toString());
        grzesiek.move(MoveDirection.LEFT); //ZMIENIONA ORIENTACJA NA LEWO! (ponownie jedzie w kierunku polnocnym)
        assertEquals("(4,1) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,2) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,3) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(4,2) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,3) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(4,4) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(4,4) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD); // PONOWNE SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(4,4) Północ",grzesiek.toString());
        grzesiek.move(MoveDirection.LEFT); // ZMIENIONA ORIENTACJA! Teraz w kierunku zachodnim
        assertEquals("(4,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(3,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(2,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(1,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(2,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(1,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(0,4) Zachód",grzesiek.toString());
        grzesiek.move(MoveDirection.LEFT); // PONOWNIE ZMIENIONA ORIENTACJA! Teraz w kierunku poludniowym
        assertEquals("(0,4) Południe",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,3) Południe",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,2) Południe",grzesiek.toString());
        grzesiek.move(MoveDirection.BACKWARD);
        assertEquals("(0,3) Południe",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,2) Południe",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,1) Południe",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD);
        assertEquals("(0,0) Południe",grzesiek.toString());
        grzesiek.move(MoveDirection.FORWARD); // SPRAWDZENIE PRZY WYJEDZIE POZA MAPE!
        assertEquals("(0,0) Południe",grzesiek.toString());
    }

}