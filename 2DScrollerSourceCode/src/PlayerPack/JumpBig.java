package PlayerPack;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**JumpBig class that jumps the Shape BIG!
 * @author ilkan mert okul 1801042649*/
public class JumpBig implements JumpBehavior{

    @Override
    public String jump(Node node,int durationMS) {

        TranslateTransition translation = new TranslateTransition(Duration.millis(durationMS * 1.3), node);
        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation.setByY(flightHeight*2);
        translation.setAutoReverse(true);
        translation.setCycleCount(2);
        translation.play();

        return "Big jump happened.";
    }
}
