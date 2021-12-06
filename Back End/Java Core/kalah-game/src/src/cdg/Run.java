package src.cdg;

public class Run {
    public static void main(String[] args) {
        KalahTable table = new KalahTable();
        table.setupTable(6);
        table.printTable();
        table.game();
    }
}
