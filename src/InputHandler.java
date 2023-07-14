import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.IOException;

public class InputHandler implements KeyboardHandler {

    private final Keyboard keyboard;
    private final KeyboardEvent[] events;
    private final Rectangle cursor;
    private final Grid grid;

    private final Cursor cursorObject;

    public InputHandler(Rectangle cursor, Cursor cursorObject, Grid grid) {
        this.cursor = cursor;
        this.cursorObject = cursorObject;
        this.grid = grid;
        keyboard = new Keyboard(this);
        events = new KeyboardEvent[7];
        createEvents();


    }

    private void createEvents() {
        for (int i = 0; i < events.length; i++) {
            events[i] = new KeyboardEvent();
        }

        events[0].setKey(KeyboardEvent.KEY_DOWN);
        events[1].setKey(KeyboardEvent.KEY_RIGHT);
        events[2].setKey(KeyboardEvent.KEY_LEFT);
        events[3].setKey(KeyboardEvent.KEY_UP);
        events[4].setKey(KeyboardEvent.KEY_SPACE);
        events[5].setKey(KeyboardEvent.KEY_S);
        events[6].setKey(KeyboardEvent.KEY_L);

        for (KeyboardEvent event : events) {
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_UP:
                if(cursorObject.getPosY() > 0) {
                    cursor.translate(0, -50);
                    cursorObject.setPosY(cursorObject.getPosY() - 1);
                    cursor.delete();
                    cursor.fill();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if(cursorObject.getPosY() < cursorObject.getGrid().getCols() - 1) {
                    cursor.translate(0, 50);
                    cursorObject.setPosY(cursorObject.getPosY() + 1);
                    cursor.delete();
                    cursor.fill();
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if(cursorObject.getPosX() > 0) {
                    cursor.translate(-50, 0);
                    cursorObject.setPosX(cursorObject.getPosX() - 1);
                    cursor.delete();
                    cursor.fill();
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if(cursorObject.getPosX() < cursorObject.getGrid().getRows() - 1) {
                    cursor.translate(50, 0);
                    cursorObject.setPosX(cursorObject.getPosX() + 1);
                    cursor.delete();
                    cursor.fill();
                }
                break;
            case KeyboardEvent.KEY_SPACE:
                cursorObject.drawBlock();
                break;
            case KeyboardEvent.KEY_S:
                try {
                    grid.saveDrawing();
                    System.out.println("YO IS IT DRAWING?");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case KeyboardEvent.KEY_L:
                try {
                    grid.loadDrawing();
                    System.out.println("YO IS IT TRYING TO LOAD?");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
