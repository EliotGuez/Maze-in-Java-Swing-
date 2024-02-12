package maze;
import java.awt.Color;

public class EmptyBox extends MazeBox {
    public EmptyBox(int x, int y) {
        super(x,y,"E", true, Color.GRAY);
    }
}
