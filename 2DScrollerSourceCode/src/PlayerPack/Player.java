package PlayerPack;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;

/**Player class that game uses to play.
 * @author ilkan mert okul, 1801042649*/
public class Player extends Rectangle implements Runnable {

    /**This is the half of the jumping time. It is fixed and does not affect
     * jump height.*/
    private final int durationMS = 500;

    /**Jump behavior makes the jumps.*/
    JumpBehavior jumpType;

    /**If this player is not on ground we cannot jump, or in other words,
     * player are in JumpCANNOT behavior.*/
    private boolean isOnGround = true;

    /**After player has the powerup of JUMPING, player can change jump modes with key"TAB".
     * This is for that.*/
    private boolean jumpMode = false;

    public Player(int x, int y, int width, int height, Color color){
        super(width,height,color);
        this.setTranslateX(x);
        this.setTranslateY(y);
        jumpType = new JumpSmall();
    }

    /**This simply inverses the jumpMode
     * @return new jump mode boolean..*/
    public boolean setJumpmode(){
        if(jumpMode) jumpType = new JumpSmall();
        else jumpType = new JumpBig();
        return jumpMode = !jumpMode;
    }

    /**getJumpMode method returns jumpMode variable
     * @return jumpMode as boolean.*/
    public boolean getJumpmode(){
        return jumpMode;
    }

    /**jump method makes the player jump with proper jumpBehavior.
     * @return String to inform user about the jump.*/
    public String jump(){
        if(!this.isOnGround()) return "Cannot jump.. player is in the air.";
        new Thread(this).start();
        return jumpType.jump(this, durationMS);
    }

    /**isOnGround method returns isOnGround variable
     * @return isOnGround as boolean.*/
    public boolean isOnGround() {
        return isOnGround;
    }

    /**Thread to seek if the player is on ground. While it is not on the ground,
     * jumpBehavior changes to JumpCannot instance.*/
    @Override
    public void run() {
        isOnGround = false;
        jumpType = new JumpCannot();
        try {
            Thread.sleep(durationMS*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOnGround = true;
        if(jumpMode) jumpType = new JumpBig();
        else jumpType = new JumpSmall();
    }
}
