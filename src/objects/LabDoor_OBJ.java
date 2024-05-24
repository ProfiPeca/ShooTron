package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LabDoor_OBJ extends DefaultObject {
    public LabDoor_OBJ() {
        objName = "ClosedDoor";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doorClosed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
