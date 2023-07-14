import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cursor {
    private Rectangle cursor;
    private Grid grid;
    private Rectangle paintedBlock;


    private int posX = 0;

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private int posY = 0;


    public Cursor(Grid grid) {
        this.grid = grid;
    }


    public void drawCursor() {
        cursor = new Rectangle(10, 10, grid.getCellSize(), grid.getCellSize());
        cursor.setColor(Color.GREEN);
        cursor.fill();
    }

    public Rectangle getCursor() {
        return cursor;
    }

    public int drawBlock() {
        if (grid.getPaintArray()[posY][posX] == null) {
            paintedBlock = new Rectangle(cursor.getX(), cursor.getY(), grid.getCellSize(), grid.getCellSize());
            paintedBlock.setColor(Color.BLACK);
            paintedBlock.fill();
            grid.getPaintArray()[posY][posX] = paintedBlock;
            return 1;
        }
        if(grid.getPaintArray()[posY][posX] != null) {
            grid.getPaintArray()[posY][posX].delete();
            grid.getPaintArray()[posY][posX] = null;
        }
        return -1;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Grid getGrid() {
        return grid;
    }
}

