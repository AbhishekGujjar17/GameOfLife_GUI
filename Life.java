
/**
* A program that simulates the Game of Life
* 
*
* @author  Abhishek Gujjar
* @version 1.0
* @since   2022-23-09 
*/

import java.awt.Color;
import java.util.Random;

public class Life {

    // initialize the variables
    private int numberOfIterations, size, magnification;
    private char pattern;
    private int[][] current;
    private int[][] old;
    private Picture picture;

    // constructor
    public Life(int numberOfIterations, int size, char pattern) {

        // assigning the values
        this.numberOfIterations = numberOfIterations;
        this.size = size;
        this.pattern = pattern;
        magnification = 10;
        current = new int[size][size];
        old = new int[size][size];
        picture = new Picture(size * magnification, size * magnification);
        Random rand = new Random();

        // checking the pattern types entered, and initialize the initial configuration
        // of cells

        // for random pattern
        if (this.pattern == 'R' || this.pattern == 'r') {

            for (int row = 0; row < this.size; row++) {

                for (int col = 0; col < this.size; col++) {

                    current[row][col] = rand.nextInt(2);
                }
            }

        }

        // for Penta-decathlon Oscillator pattern
        else if (this.pattern == 'P' || this.pattern == 'p') {

            for (int row = 0; row < this.size; row++) {

                for (int col = 0; col < this.size; col++) {

                    if ((row == 1 && col == 3) || (row == 1 && col == 8)) {
                        current[col][row] = 1;

                    }

                    else if (row == 2 && (col >= 0 && col < 12) && col != 0 && col != 3 && col != 8 && col != 11) {

                        current[col][row] = 1;
                    }

                    else if ((row == 3 && col == 3)
                            || (row == 3 && col == 8)) {

                        current[col][row] = 1;
                    }

                    else {

                        current[col][row] = 0;
                    }

                }
            }

        }
        // for Simkin glider gun pattern
        else if (this.pattern == 'S' || this.pattern == 's') {

            for (int row = 0; row < this.size; row++) {

                for (int col = 0; col < this.size; col++) {

                    if ((row == 1 && col == 1) || (row == 1 && col == 2) || (row == 1 && col == 8)
                            || (row == 1 && col == 9) || (row == 1 && col == 21)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 2 && col == 1) || (row == 2 && col == 2) || (row == 2 && col == 8)
                            || (row == 2 && col == 9) || (row == 2 && col == 19) || (row == 2 && col == 20)
                            || (row == 2 && col == 21)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 3 && col == 19) || (row == 3 && col == 21)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 4 && col == 5) || (row == 4 && col == 6) || (row == 4 && col == 19)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 5 && col == 5) || (row == 5 && col == 6)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 9 && col == 28) || (row == 9 && col == 29)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 10 && col == 28) || (row == 10 && col == 29)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 12 && col == 32) || (row == 12 && col == 33) || (row == 12 && col == 26)
                            || (row == 12 && col == 25)) {
                        current[col][row] = 1;

                    }

                    else if ((row == 13 && col == 32) || (row == 13 && col == 33) || (row == 13 && col == 26)
                            || (row == 13 && col == 25)) {
                        current[col][row] = 1;

                    }

                    else {

                        current[col][row] = 0;
                    }

                }
            }

        }

    }

    // function that is used to draw individual cell of the grid

    public void drawCell(int row, int col) {

        Color cellColor;

        if (current[row][col] == 0) {

            cellColor = Color.WHITE;
        }

        else {

            cellColor = Color.BLACK;
        }

        for (int offsetX = 0; offsetX < magnification; offsetX++) {

            for (int offsetY = 0; offsetY < magnification; offsetY++) {

                picture.set((row * magnification) + offsetX,
                        (col * magnification) + offsetY, cellColor);
            }
        }
    }

    // function that is used to call draw cell function for every cell in the grid

    public void display() {

        for (int row = 0; row < this.size; row++) {

            for (int col = 0; col < this.size; col++) {

                drawCell(row, col);
                old[row][col] = current[row][col]; // to record current;
            }
        }

        picture.show();
    }

    // function that is used to delay the program for few seconds in between

    public void delay(int t) {

        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // function that is used to count the wrapping neighbors around the edge cell

    public int checkEdgeNeighbors(int r, int c) {
        int wrappingNeighbors = 0;
        if (r == this.size - 1) {
            if (old[0][c] == 1) {
                wrappingNeighbors++;
            }
            if (c - 1 >= 0) {
                if (old[0][c - 1] == 1) {
                    wrappingNeighbors++;
                }
            }
            if (c + 1 <= this.size - 1) {
                if (old[0][c + 1] == 1) {
                    wrappingNeighbors++;
                }
            }
        }
        if (r == 0) {
            if (old[this.size - 1][c] == 1) {
                wrappingNeighbors++;
            }
            if (c - 1 >= 0) {
                if (old[this.size - 1][c - 1] == 1) {
                    wrappingNeighbors++;
                }
            }
            if (c + 1 <= this.size - 1) {
                if (old[this.size - 1][c + 1] == 1) {
                    wrappingNeighbors++;
                }
            }
        }
        if (c == this.size - 1) {
            if (old[r][0] == 1) {
                wrappingNeighbors++;
            }
            if (r - 1 >= 0) {
                if (old[r - 1][0] == 1) {
                    wrappingNeighbors++;
                }
            }
            if (r + 1 <= this.size - 1) {
                if (old[r + 1][0] == 1) {
                    wrappingNeighbors++;
                }
            }
        }
        if (c == 0) {
            if (old[r][this.size - 1] == 1) {
                wrappingNeighbors++;
            }
            if (r - 1 >= 0) {
                if (old[r - 1][this.size - 1] == 1) {
                    wrappingNeighbors++;
                }
            }
            if (r + 1 <= this.size - 1) {
                if (old[r + 1][this.size - 1] == 1) {
                    wrappingNeighbors++;
                }
            }
        }
        return wrappingNeighbors;
    }

    // function that is used to count the neighbors around the cell

    public int countNeighbors(int row, int col) {

        int neighbors = 0;

        if (row == 0 || col == 0 || row == this.size - 1 || col == this.size - 1) {
            neighbors += checkEdgeNeighbors(row, col);
        }

        for (int r = row - 1; r <= row + 1; r++) {

            for (int c = col - 1; c <= col + 1; c++) {

                if (r >= 0 && c >= 0 && r < this.size && c < this.size && old[r][c] == 1
                        && !(r == row && c == col)) {

                    neighbors += 1;
                }
            }
        }

        return neighbors;

    }

    // function that is used to update the current state using the older state and
    // neighbors

    public void update() {

        for (int row = 0; row < this.size; row++) {

            for (int col = 0; col < this.size; col++) {

                if ((old[row][col] == 1) && (countNeighbors(row, col) < 2)) {

                    current[row][col] = 0;

                } else if ((old[row][col] == 1) && (countNeighbors(row, col) == 2 || countNeighbors(row, col) == 3)) {

                    current[row][col] = 1;
                }

                else if ((old[row][col] == 1) && (countNeighbors(row, col) > 3)) {

                    current[row][col] = 0;
                }

                else if ((old[row][col] == 0) && (countNeighbors(row, col) == 3)) {

                    current[row][col] = 1;
                }

            }
        }
    }

    // must provide numberOfIterations, grid size (size) and pattern (p)
    public static void main(String[] args) {

        // accessing the command line arguments
        int numberOfIterations = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        char pattern = args[2].charAt(0);

        Life life = new Life(numberOfIterations, size, pattern); // creating a new Life object

        // run for the iterations entered
        for (int i = 0; i < numberOfIterations; i++) {

            life.display(); // display the cells
            life.delay(200); // delay the program
            life.update(); // update the current state

        }

        life.display(); // display the cells final time

    }

}
