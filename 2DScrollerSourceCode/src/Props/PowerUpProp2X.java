package Props;

/**PowerUpProp2X class has the multiplier of 2.
 * @author ilkan mert okul 1801042649*/
public class PowerUpProp2X extends PowerUpProp {

    private final int multiplier = 2;
    private final boolean bigJump = false;

    private PowerUpProp power;

    public PowerUpProp2X(PowerUpProp power) {
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
