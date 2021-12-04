package PlayerPack;

import javafx.scene.Node;

/**JumpBehavior class to interface all jump modes.
 * @author ilkan mert okul, 1801042649*/
public interface JumpBehavior {

    /**This variable is a reference for jump modes, concrete classes
     * do not have to use this.*/
    int flightHeight = -150;

    /**jump method that performs the jump of the given javafx node.
     * @param node The shape to perform jump operation.
     * @param durationMS The duration of the jump's flight (should not include the landing)
     * @return String to return information about jump.*/
    String jump(Node node,int durationMS);

}
