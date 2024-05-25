package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LabDoorOpen_OBJ extends DefaultObject{

    public LabDoorOpen_OBJ() {
        objName = "OpenDoor";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/doorOpen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
