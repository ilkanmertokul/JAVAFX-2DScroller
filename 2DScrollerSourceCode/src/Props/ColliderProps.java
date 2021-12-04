package Props;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**ColliderProps class is the base class for every single Obstacle and Powerup.
 * Because they are collideable and they are rectangle.
 * And they have common methods like moveLeft() and set/getCollided, because player can collide with this
 * only ONCE.
 * @author ilkan mert okul, 1801042649*/
public class ColliderProps extends Rectangle {

    /**isColided becomes true once it gets collided with player.*/
    private boolean isColided = false;

    public ColliderProps(int x, int y, int width, int height, Color color){
        super(width,height,color);
        this.setTranslateX(x);
        this.setTranslateY(y);
    }

    /**moveLeft moves the object to the end of left screen once invoked.*/
    public void moveLeft(){
        TranslateTransition translation = new TranslateTransition(Duration.millis(2000), this);
        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation.setByX(-800);
        translation.play();
    }

    /**sets isColided boolean to true.*/
    public void setIsColided(){
        isColided = true;
    }

    /** getIsColided returns boolean that means this Object is collided before or not.
     * @return boolean isColided */
    public boolean getIsColided(){
        return isColided;
    }
}
