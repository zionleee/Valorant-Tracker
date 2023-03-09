package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private Match match1;
    private Match match2;
    private Match match3 = new Match("11/03/2023", true, "b", "a", 25, 12, 14);
    private Match match4 = new Match("11/03/2023", true, "v", "f", 25, 12, 14);
    private Match match5 = new Match("11/03/2023", true, "o", "h", 25, 12, 14);
    private Match match6 = new Match("11/03/2023", true, "h", "i", 25, 12, 14);
    private Match match7 = new Match("11/03/2023", true, "a", "l", 25, 12, 14);
    private Match match8 = new Match("11/03/2023", true, "kj", "p", 25, 12, 14);
    private Match match9 = new Match("11/03/2023", true, "cyp", "s", 25, 12, 14);
    private Match match10 = new Match("11/03/2023", true, "sa", "i", 25, 12, 14);
    private Match match11 = new Match("11/03/2023", true, "c", "i", 25, 12, 14);
    private Match match12 = new Match("11/03/2023", true, "so", "i", 25, 12, 14);
    private Match match13 = new Match("11/03/2023", true, "k", "i", 25, 12, 14);
    private Match match14 = new Match("11/03/2023", true, "f", "i", 25, 12, 14);
    private Match match15 = new Match("11/03/2023", true, "br", "i", 25, 12, 14);
    private Match match16 = new Match("11/03/2023", true, "sk", "i", 25, 12, 14);
    private Match match17 = new Match("11/03/2023", true, "phx", "i", 25, 12, 14);
    private Match match18 = new Match("11/03/2023", true, "j", "i", 25, 12, 14);
    private Match match19 = new Match("11/03/2023", true, "r", "i", 25, 12, 14);
    private Match match20 = new Match("11/03/2023", true, "ra", "i", 25, 12, 14);

    private Match match21 = new Match("11/03/2023", true, "z", "z", 25, 12, 14);

    @BeforeEach
    public void setUp() {
        //(String date, boolean win, String agent, String map, int k, int d, int a)
        match1 = new Match("11/02/2023", false, "y", "p", 10, 14, 5);
        match2 = new Match("11/03/2023", true, "n", "i", 25, 12, 14);
    }

    @Test
    public void constructorTest() {
        assertEquals("11/02/2023", match1.getDate());
        assertFalse(match1.isWin());
        assertEquals("Yoru", match1.getAgent());
        assertEquals("Neon", match2.getAgent());
        assertEquals("Pearl", match1.getMap());
        assertEquals("Icebox", match2.getMap());
        assertEquals("10.0/14.0/5.0", match1.getKda());
    }

    @Test
    public void mapTypeTest() {
        assertEquals("Ascent", match3.getMap());
        assertEquals("Fracture", match4.getMap());
        assertEquals("Haven", match5.getMap());
        assertEquals("Icebox", match6.getMap());
        assertEquals("Lotus", match7.getMap());
        assertEquals("Pearl", match8.getMap());
        assertEquals("Split", match9.getMap());

        assertEquals("Split", match9.mapType());
        assertTrue(match9.mapType() == "Split");

        assertEquals("Unrecognized Valorant Map", match21.mapType());
        assertEquals("Unrecognized Valorant Map", match21.getMap());
    }

    @Test
    public void agentTypeTest() {
        assertEquals("Brimstone", match3.getAgent());
        assertEquals("Viper", match4.getAgent());
        assertEquals("Omen", match5.getAgent());
        assertEquals("Harbor", match6.getAgent());
        assertEquals("Astra", match7.getAgent());
        assertEquals("Killjoy", match8.getAgent());
        assertEquals("Cypher", match9.getAgent());
        assertEquals("Sage", match10.getAgent());
        assertEquals("Chamber", match11.getAgent());
        assertEquals("Sova", match12.getAgent());
        assertEquals("KAY/O", match13.getAgent());
        assertEquals("Fade", match14.getAgent());
        assertEquals("Breach", match15.getAgent());
        assertEquals("Skye", match16.getAgent());
        assertEquals("Phoenix", match17.getAgent());
        assertEquals("Jett", match18.getAgent());
        assertEquals("Reyna", match19.getAgent());
        assertEquals("Raze", match20.getAgent());

        assertEquals("Raze", match20.agentType());
        assertTrue(match20.agentType() == "Raze");

        assertEquals("Unrecognized Valorant Agent", match21.agentType());
        assertEquals("Unrecognized Valorant Agent", match21.getAgent());

    }

    @Test
    public void testToJson() {
        JSONObject obj = match3.toJson();
        assertEquals(obj.getString("date"), "11/03/2023");
    }
}