package Props;

import javafx.scene.paint.Color;

/**Obstacle is a collider rectangle. that has a RANDOM COLOR.
 * @author ilkan mert okul 1801042649*/
public class Obstacle extends ColliderProps {
    public Obstacle(int x, int y, int width, int height) {
        super(x, y, width, height, Color.color(Math.random(),Math.random(),Math.random()));
    }
}
