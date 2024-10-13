public class Table {

    private int[][] table;

    public Table(int size){
        this.table = new int[size][size];
        for (int i = 0; i<this.table.length; i++){
            for (int j = 0; j<this.table.length; j++){
                this.table[i][j] = 0;
            }
        }
    }

    public int[][] getTable() {
        return table;
    }


    public void setTable(int[][] table) {
        this.table = table;
    }

}
