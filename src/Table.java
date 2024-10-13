public class Table {

    private int[][] table;

    public Table(int size){
        this.table = new int[size][size];
    }

    public int[][] getTable() {
        return table;
    }


    public void setTable(int[][] table) {
        this.table = table;
    }

}
