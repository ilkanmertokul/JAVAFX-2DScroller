package Props;

/**PowerUpPropJUMP class has the multiplier of 1, but makes the player can jump BIG.
 * @author ilkan mert okul 1801042649*/
public class PowerUpPropJump extends PowerUpProp {

    private final int multiplier = 1;
    private final boolean bigJump = true;

    private PowerUpProp power;

    public PowerUpPropJump(PowerUpProp power) {
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
