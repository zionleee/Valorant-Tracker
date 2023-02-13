package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MatchTest {
    private Match match1;
    private Match match2;

    @BeforeEach
    public void setUp() {
        //(String date, boolean win, String agent, String map, int k, int d, int a)
        match1 = new Match("11/02/2023", false, "j", "p", 10, 14, 5);
        match2 = new Match("11/03/2023", true, "v", "i", 25, 12, 14);
    }

    @Test
    public void constructorTest() {
        assertEquals("11/02/2023", match1.getDate());
        assertFalse(match1.isWin());
        assertEquals("Jett", match1.getAgent());
        assertEquals("Viper", match2.getAgent());
        assertEquals("Pearl", match1.getMap());
        assertEquals("Icebox", match2.getMap());
        assertEquals("10.0/14.0/5.0", match1.getKda());

    }
}