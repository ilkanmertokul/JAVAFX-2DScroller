package Props;

/**PowerUpProp5X class has the multiplier of 5.
 * @author ilkan mert okul 1801042649*/
public class PowerUpProp5X extends PowerUpProp {

    private final int multiplier = 5;
    private final boolean bigJump = false;

    private PowerUpProp power;

    public PowerUpProp5X(PowerUpProp power) {
        this.power = power;
    }

    @Override
    public int getMultiplier() {
        return multiplier * power.getMultiplier();
    }

    @Override
    public boolean canBigJump() {
        return bigJump || power.canBigJump();
    }
}
