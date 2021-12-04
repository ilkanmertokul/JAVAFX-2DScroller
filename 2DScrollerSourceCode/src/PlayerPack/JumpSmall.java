package PlayerPack;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**JumpSmall class that jumps the Shape SMALL!
 * @author ilkan mert okul 1801042649*/
public class JumpSmall implements JumpBehavior {

    @Override
    public String jump(Node node,int durationMS) {
        TranslateTransition translation = new TranslateTransition(Duration.millis(durationMS), node);
        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation.setByY(flightHeight);
        translation.setAutoReverse(true);
        translation.setCycleCount(2);
        translation.play();

        return "Small jump happened.";
    }
}
