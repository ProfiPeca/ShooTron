package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class KeyCard_OBJ extends DefaultObject {

    public KeyCard_OBJ() {
        objName = "KeyCard";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/keyCard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
