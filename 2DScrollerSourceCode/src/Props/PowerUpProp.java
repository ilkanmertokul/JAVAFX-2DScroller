package Props;

/**This is the powerUpProp that both collider and game uses.
 * @author ilkan mert okul, 1801042649*/
public abstract class PowerUpProp {

    /**ENUM, we have 4 of them right now. Can be extendable*/
    public enum PowerUpType{
        TWOX, FIVEX, TENX, BIGJUMP
    }

    /**getMultiplier method that returns the multiplier
     * @return integer value of multiplier*/
    public abstract int getMultiplier();

    /**canBigJump method that returns the ability of big jump.
     * @return boolean value of can big jump*/
    public abstract boolean canBigJump();
}
