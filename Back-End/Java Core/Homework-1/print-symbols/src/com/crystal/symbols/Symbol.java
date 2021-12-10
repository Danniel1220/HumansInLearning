package com.crystal.symbols;

public class Symbol {
    private int[][] symbolArray;

    Symbol (int[][] symbolArray) {
        this.symbolArray = symbolArray;
    }

    public void printSymbol() {
        for(int i = 0; i < symbolArray.length; i++) {
            for(int j = 0; j < symbolArray[0].length; j++) {
                if(symbolArray[i][j] == 1) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void printSymbolLine(int line) {
        for(int i = 0; i < symbolArray[0].length; i++) {
            if(symbolArray[line][i] == 1) System.out.print("*");
            else System.out.print(" ");
        }
    }
}
