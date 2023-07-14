public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.createGrid();
        Cursor cursor = new Cursor(grid);
        cursor.drawCursor();
        new InputHandler(cursor.getCursor(), cursor, grid);
    }
}
