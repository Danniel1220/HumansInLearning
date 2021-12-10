package com.crystal.vending;

public class InputValidator {
    public boolean checkCoins(int[] coinsArr) {
        if (coinsArr == null || coinsArr.length == 0) return false;
        else return true;
    }

    public boolean checkPositive(float value) {
        if (value > 0) return true;
        else return false;
    }

    public boolean checkInputMoney (float inputMoney, float price) {
        if (inputMoney >= price) return true;
        else return false;
    }
}
