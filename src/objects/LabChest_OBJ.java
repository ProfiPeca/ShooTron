package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LabChest_OBJ extends DefaultObject{
    public LabChest_OBJ() {
        objName = "ClosedChest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chestClosed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
