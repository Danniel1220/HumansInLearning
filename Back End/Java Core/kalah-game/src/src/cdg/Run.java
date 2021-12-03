package src.cdg;

public class Run {
    public static void main(String[] args) {
        KalahTable table = new KalahTable();
        table.setupTable();
        table.printTable();
        table.game();
    }
}
