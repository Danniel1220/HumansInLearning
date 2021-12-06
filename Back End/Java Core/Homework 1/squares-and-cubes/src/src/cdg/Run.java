package src.cdg;

public class Run {
    public static void main(String[] args) {
        for(int i = 0; i <= 10; i++) {
            int square = i * i;
            int cube = square * i;
            System.out.println(i + " " + square + " " + cube);
        }
    }
}
