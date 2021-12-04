package Props;

import javafx.scene.paint.Color;

/**a PowerUp is a collider rectangle that gives a player multiplier or jump bonus.*/
public class PowerUp extends ColliderProps{

    /**powerUpType that it holds the type of the power like 2X, 5X ,10X, BIGJUMP*/
    private PowerUpProp.PowerUpType powerUpType;

    public PowerUp(int x, int y, int width, int height, Color color, PowerUpProp.PowerUpType type){
        super(x, y, width, height, color);
        powerUpType = type;
    }

    /**Returns the type of the power.
     * @return PowerUpType the type of the power.*/
    public PowerUpProp.PowerUpType getPowerUpType(){
        return powerUpType;
    }
}
