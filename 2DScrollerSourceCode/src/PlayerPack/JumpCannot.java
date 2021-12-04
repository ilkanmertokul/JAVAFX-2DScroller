package PlayerPack;

import javafx.scene.Node;

/**JumpCannot class that informs the user it cannot jump!
 * @author ilkan mert okul 1801042649*/
public class JumpCannot implements JumpBehavior{
    @Override
    public String jump(Node node, int durationMS) {
        return "Cannot jump.. Player is in AIR!!";
    }
}
