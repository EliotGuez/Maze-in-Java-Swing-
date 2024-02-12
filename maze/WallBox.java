package maze;
import java.awt.Color;

public class WallBox extends MazeBox {
    public WallBox(int x, int y) {
        super(x,y,"W",false, Color.DARK_GRAY);
    }
}