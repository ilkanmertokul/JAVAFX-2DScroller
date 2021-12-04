package Props;

/**PowerUpProp10X class has the multiplier of 10.
 * @author ilkan mert okul 1801042649*/
public class PowerUpProp10X extends PowerUpProp {

    private final int multiplier = 10;
    private final boolean bigJump = false;

    private PowerUpProp power;

    public PowerUpProp10X(PowerUpProp power) {
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
