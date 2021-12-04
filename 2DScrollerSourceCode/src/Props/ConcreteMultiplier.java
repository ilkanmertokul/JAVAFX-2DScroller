package Props;

/**Concrete multiplier that has 1 as default.
 * @author ilkan mert okul, 1801042649*/
public class ConcreteMultiplier extends PowerUpProp{
    @Override
    public int getMultiplier() {
        return 1;
    }

    @Override
    public boolean canBigJump() {
        return false;
    }
}
