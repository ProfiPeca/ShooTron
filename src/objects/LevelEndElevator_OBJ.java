package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class LevelEndElevator_OBJ extends DefaultObject {

    public LevelEndElevator_OBJ() {
        objName = "LevelEndElevator";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/levelEndElevator.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
