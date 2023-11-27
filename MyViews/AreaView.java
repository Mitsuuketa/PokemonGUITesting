package MyViews;

import java.util.Arrays;

public class AreaView {
    protected int numRows;
    protected int numCols;
    protected int[] currentPosition;

    public AreaView(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.currentPosition = new int[]{0, 0}; // Initial position at [0, 0]
    }

    public void displayArea() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (Arrays.equals(currentPosition, new int[]{i, j})) {
                    System.out.print("X "); // Marker for current position
                } else {
                    System.out.print("* "); // Placeholder for other tiles
                }
            }
            System.out.println();
        }
    }

    public void moveUp() {
        if (currentPosition[0] > 0) {
            currentPosition[0]--;
        }
    }

    public void moveDown() {
        if (currentPosition[0] < numRows - 1) {
            currentPosition[0]++;
        }
    }

    public void moveLeft() {
        if (currentPosition[1] > 0) {
            currentPosition[1]--;
        }
    }

    public void moveRight() {
        if (currentPosition[1] < numCols - 1) {
            currentPosition[1]++;
        }
    }

}
