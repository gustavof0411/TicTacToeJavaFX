public class Table {

    private int[][] table;
    private int playerWon = 0;

    public Table(int size) {
        this.table = new int[size][size];
    }

    public void verifyWin(int row, int column) {
        if (row == 0 && column == 0) {

        }
    }

    public void verifyMainDiagonal() {
        // Checks if all the elements in the main diagonal are equal
        int iterator = 1;
        int firstElementDiagonal = getTable()[0][0]; // Index table[0][0]
        while (iterator < getTable().length) {
            if (getTable()[iterator][iterator] != firstElementDiagonal) {
                break;
            } else {
                iterator++;
                if (iterator == getTable().length) {
                    playerWon = firstElementDiagonal;
                }
            }
        }

    }

    public void verifySecondaryDiagonal() {
        // Checks if all the elements in the secondary diagonal are equal
        int iteratorColumn = 0;
        int iteratorRow = getTable().length - 1;
        int firstElementDiagonal = getTable()[iteratorColumn][iteratorRow]; // Index table[0][n]
        while (iteratorColumn < getTable().length) {
            if (getTable()[iteratorRow][iteratorColumn] != firstElementDiagonal) {
                break;
            } else {
                iteratorColumn++;
                iteratorRow--;
                if (iteratorColumn == getTable().length) {
                    playerWon = firstElementDiagonal;
                }
            }
        }

    }

    public void verifyHorizontal(int row) {
        // Checks if all the row's elements are equal
        int iterator = 1;
        int firstElementRow = getTable()[row][0]; // Index table[n][0]
        while (iterator < getTable().length) {
            if (getTable()[row][iterator] != firstElementRow) {
                break;
            } else {
                iterator++;
                if (iterator == getTable().length) {
                    playerWon = firstElementRow;
                }
            }
        }
    }

    public void verifyVertical(int column) {
        // Checks if all the column's elements are equal
        int iterator = 1;
        int firstElementColumn = getTable()[0][column]; // Index table[0][n]
        while (iterator < getTable().length) {
            if (getTable()[iterator][column] != firstElementColumn) {
                break;
            } else {
                iterator++;
                if (iterator == getTable().length) {
                    playerWon = firstElementColumn;
                }
            }
        }
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    public int getPlayerWon() {
        return playerWon;
    }

}
