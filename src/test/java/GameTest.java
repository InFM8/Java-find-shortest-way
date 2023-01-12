
import com.assignment.nl22w.game.impl.GameImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class GameTest {

    @Test
    public void maps() throws IOException {
        findExit(4, "map1.txt");
        findExit(13, "map2.txt");
        findExit(0, "map3.txt");
        findExit(7, "map4.txt");
        findExit(2, "map5.txt");
        findExit(10, "map6.txt");
        findExit(0, "map7.txt");
        findExit(90, "map8.txt");
        findExit(0, "wrong-map.txt");
        findExit(0, "map9.txt");
        findExit(0, "map10.txt");
        findExit(257, "map11.txt");
        findExit(0, "map12.txt");
        findExit(0, "map13.txt");
        findExit(0, "map14.txt");
    }

    public void findExit(int expectedOutput, String map) throws IOException {
        ClassPathResource map1 = new ClassPathResource(map);
        GameImpl gameImpl = new GameImpl();
        int actualResult = gameImpl.escapeFromTheWoods(map1);

        Assertions.assertEquals(expectedOutput, actualResult);
    }
}
