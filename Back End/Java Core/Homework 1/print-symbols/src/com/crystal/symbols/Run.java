package com.crystal.symbols;

public class Run {
    public static void main(String[] args) {
        Symbol square = new Symbol(new int[][]{
                {1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1}
        });

        Symbol oval = new Symbol(new int[][]{
                {0,0,1,1,1,0,0},
                {0,1,0,0,0,1,0},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {1,0,0,0,0,0,1},
                {0,1,0,0,0,1,0},
                {0,0,1,1,1,0,0}
        });

        Symbol arrow = new Symbol(new int[][]{
                {0,0,1,0,0},
                {0,1,1,1,0},
                {1,1,1,1,1},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
                {0,0,1,0,0},
        });

        Symbol diamond = new Symbol(new int[][]{
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,1,0,1,0,0,0},
                {0,0,1,0,0,0,1,0,0},
                {0,1,0,0,0,0,0,1,0},
                {1,0,0,0,0,0,0,0,1},
                {0,1,0,0,0,0,0,1,0},
                {0,0,1,0,0,0,1,0,0},
                {0,0,0,1,0,1,0,0,0},
                {0,0,0,0,1,0,0,0,0},
        });

        for (int i = 0; i < 9; i++) {
            square.printSymbolLine(i);
            System.out.print("    ");
            oval.printSymbolLine(i);
            System.out.print("    ");
            arrow.printSymbolLine(i);
            System.out.print("    ");
            diamond.printSymbolLine(i);
            System.out.println();
        }
    }
}