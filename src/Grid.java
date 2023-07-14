import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.*;
import java.util.ArrayList;

public class Grid {
    // Grid Data
    private int cols = 15; // Número de colunas
    private int rows = 15; // Número de linhas
    private int cellSize = 50; // Tamanho de cada célula
    private int width = cols * cellSize; // Largura total do grid
    private int height = rows * cellSize; // Altura total do grid
    private int padding = 10;

    // Arrays of the grid and the blocks to be painted
    private Rectangle[][] gridArray = new Rectangle[cols][rows];
    private Rectangle[][] paintArray = new Rectangle[cols][rows];

    private Rectangle block;
    private Rectangle savedBlock;

    // I/O Data needed to save

    char[] buffer = new char[cols*rows];





    private int x, y;

    public void createGrid() {

        // Desenhar células
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                 x = col * cellSize + padding; // Posição x da célula
                 y = row * cellSize + padding; // Posição y da célula

                block =  new Rectangle(x, y, cellSize, cellSize);
                gridArray[col][row] = block;
                block.setColor(Color.BLACK);
                block.draw();
            }
        }
    }


    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle[][] getBlockArray() {
        return gridArray;
    }

    public Rectangle[][] getPaintArray() {
        return paintArray;
    }

    public void saveDrawing() throws IOException {
        FileWriter writer = new FileWriter("src/SaveSlot.txt");
        BufferedWriter bWriter = new BufferedWriter(writer);

        for (int i = 0; i < paintArray.length ; i++) {
            for (int j = 0; j < paintArray[rows - 1].length ; j++) {
                if(paintArray[i][j] != null) {
                    System.out.println("!null");
                    bWriter.write("1");
                }
                if(paintArray[i][j] == null) {
                    System.out.println("null");
                    bWriter.write("0");
                }
            }
        }
        bWriter.flush();
        bWriter.close();
    }

    public void loadDrawing() throws IOException {
        FileReader reader = new FileReader("src/SaveSlot.txt");
        BufferedReader bReader = new BufferedReader(reader);

        String savedData = bReader.readLine();


        int counter = 0;


        for (int i = 0; i < paintArray.length ; i++) {
            for (int j = 0; j < paintArray[rows - 1].length ; j++) {
                if(Character.getNumericValue(savedData.charAt(counter)) == 1) {
                    System.out.println("AM I IN THIS CONDITION?");
                    x = i * cellSize + padding; // Posição x da célula
                    y = j * cellSize + padding; // Posição y da célula
                    savedBlock = new Rectangle(y, x, cellSize, cellSize);
                    savedBlock.setColor(Color.BLACK);
                    savedBlock.fill();
                    paintArray[i][j] = savedBlock;
                }
                counter++;
            }

        }
        bReader.close();

    // eu quero que cada char seja equivalente a uma posição do array, tem que iterar a mesma quantidade de vezes
    }
}