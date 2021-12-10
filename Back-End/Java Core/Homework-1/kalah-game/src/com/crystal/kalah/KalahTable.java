package com.crystal.kalah;

public class KalahTable {
    // Array representing the regular cups between the stores.
    // kalahCups[1][...] is on player 1's side
    // kalahCups[2][...] is on player 2's side
    private int[][] kalahCups = new int[2][6];
    // Array representing the stores, kalahStores[0] is player 1's, kalahStores[1] is player 2's
    private int[] kalahStores = new int[2];
    // Used to keep track of the remaining picked up stones during the movement of stones.
    private int stonesInHand;
    private int playerTurn;
    private boolean gameRunning;
    private boolean gotExtraTurn;

    InputManager input = new InputManager();

    // setups the table for a game to start, fills all of the cups with 4 stones
    public void setupTable(int amountOfStones) {
        // Arrays.fill(kalahCups, 4);
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 6; j++) kalahCups[i][j] = amountOfStones;
    }

    public void game() {
        // The game default is that player 1 always has the first turn.
        playerTurn = 0;
        // Sets the game to be running.
        gameRunning = true;
        // By default, a player has no extra turns.
        // This turns to true if it is determined a player should receive.
        gotExtraTurn = false;

        // Game starts running, fetches input and moves the stones accordingly
        // until one of the players is determined to be the winner.
        while (gameRunning) {
            // Getting input.
            int selectedCup = input.getInput(playerTurn);
            // Moving the stones.
            grabStones(playerTurn, selectedCup);
            // Displaying the new state of the game after last move.
            printTable();

            // Checking to see if we need to give the player an extra turn.
            if (gotExtraTurn) {
                gotExtraTurn = false;
            }
            // Else just swap turns as usual.
            else {
                if (playerTurn == 0) playerTurn = 1;
                else playerTurn = 0;
            }
        }

        // Game ended, checking who is the winner and displaying it.
        determineWinner();
    }

    // Whenever a player makes a move, he first has to grab stones from a cup.
    public void grabStones(int playerSide, int j) {
        // Stones in hand should be equal to however many stones were in the selected cup.
        stonesInHand = kalahCups[playerSide][j];
        kalahCups[playerSide][j] = 0;

        // We verify which player's turn it is and the position of the selected cup
        // in order to correctly move the stones.

        // Move was made on player 1's side.
        if (playerSide == 0) {
            // We currently are on the last cup on player 1's side, next we should go to the store.
            if (j == 0) {
                addStoneToStore(playerSide, playerSide, stonesInHand);
            }
            // Not on the last cup, we proceed as usual.
            else {
                moveStones(playerSide, playerSide, j - 1, stonesInHand);
            }
        }
        // move was made on player 2's side
        else {
            if (j == 5) {
                addStoneToStore(playerSide, playerSide, stonesInHand);
            }
            else {
                moveStones(playerSide, playerSide, j + 1, stonesInHand);
            }
        }
    }

    // recursively moving the stones
    public void moveStones(int playerTurn, int tableSide, int j, int stones)
    {
        // If we still have any stones in hand, proceed, otherwise there is nothing else to do.
        if (stonesInHand > 0) {
            // take one stone from the hand and add it in the current cup
            kalahCups[tableSide][j]++;
            stonesInHand--;

            // This means a player just placed his last stone from the hand into a previously empty cup on
            // his own side of the table. That means he gets to steal all the other stones in the opposite cup.
            if (stonesInHand == 0 && kalahCups[tableSide][j] == 1 && playerTurn == tableSide) {
                int stolenStones;
                if (tableSide == 0) {
                    stolenStones = kalahCups[1][j];

                    // The amount added to the kalah store is equal to the amount of stones stolen
                    // plus one because we also add the stone we just placed in the empty cup.
                    kalahStores[0] = kalahStores[0] + stolenStones + 1;

                    // Empty the cups we took stones from;
                    kalahCups[0][j] = 0;
                    kalahCups[1][j] = 0;
                }
                else {
                    stolenStones = kalahCups[0][j];

                    // The amount added to the kalah store is equal to the amount of stones stolen
                    // plus one because we also add the stone we just placed in the empty cup.
                    kalahStores[1] = kalahStores[1] + stolenStones + 1;

                    // Empty the cups we took stones from;
                    kalahCups[0][j] = 0;
                    kalahCups[1][j] = 0;
                }
            }

            // move was made on player 1's side
            if (tableSide == 0) {
                // we currently are on the last cup on player 1's side, next we should go to the stone store
                if (j == 0) {
                    addStoneToStore(playerTurn, tableSide, stonesInHand);
                }
                // not on the last cup, we proceed as usual
                else {
                    moveStones(playerTurn, tableSide, j - 1, stonesInHand);
                }
            }
            // move was made on player 2's side
            else {
                // we currently are on the last cup on player 2's side, next we should go to the stone store
                if (j == 5)
                {
                    addStoneToStore(playerTurn, tableSide, stonesInHand);
                }
                else {
                    moveStones(playerTurn, tableSide, j + 1, stonesInHand);
                }
            }
        }
    }

    // When its time to add a stone to the store, it is also time so switch the direction
    // in which the stones are placed, so we handle this here.
    // playerTurn - which player's turn it is
    // playerStore - which player's store are we in
    // It is important to keep track of both because a player cannot place a stone in the other's player store.
    // If this is the case the store is simply skipped.
    public void addStoneToStore(int playerTurn, int playerStore, int stones)
    {
        // If the store we're in is owned by the player who's turn it is, we can place the stone there.
        // Else we skip placing the stone.
        if ((playerTurn == playerStore) && stonesInHand > 0) {
            kalahStores[playerTurn]++;
            stonesInHand--;

            // If after placing a stone in the store, stonesIsHand is 0, that means we placed our
            // last stone in the store, which means we get an extra turn, so we flag it for
            // the turn swapping logic.
            if (stonesInHand == 0) {
                gotExtraTurn = true;
            }
        }



        // It's player's 1 turn.
        if (playerTurn == 0) {
            // We're leaving player 1's store, so that means we start placing stones on player's 2 side of the table.
            if (playerStore == 0) moveStones(playerTurn, 1, 0, stonesInHand);
            // We're leaving player 2's store, so that means we start placing stones on player's 1 side of the table.
            else moveStones(playerTurn, 0, 5, stonesInHand);
        }
        // It's player's 2 turn, so that means we need to continue on player's 1 side of the table.
        else {
            if (playerStore == 0) {
                moveStones(playerTurn, 1, 0, stonesInHand);
            }
            else {
                moveStones(playerTurn, 0, 5, stonesInHand);
            }

        }
    }

    // Determines if the game has ended or not
    public boolean gameEnded() {
        boolean stonesOnPlayerOneSide = false;
        boolean stonesOnPlayerTwoSide = false;

        for(int i = 0; i < 6; i++) {
            if (kalahCups[0][i] > 0) stonesOnPlayerOneSide = true;
            if (kalahCups[1][i] > 0) stonesOnPlayerTwoSide = true;
        }

        if (!stonesOnPlayerOneSide || !stonesOnPlayerTwoSide) return true;
        else return false;
    }

    // Determines who the winner is with the current board state
    public void determineWinner() {
        int playerOneScore;
        int playerTwoScore;

        playerOneScore = kalahStores[0];
        playerTwoScore = kalahStores[1];

        for(int i = 0; i < 6; i++) {
            playerOneScore = playerOneScore + kalahCups[0][i];
            playerTwoScore = playerTwoScore + kalahCups[1][i];
        }

        System.out.println("Game ended.");
        if (playerOneScore > playerTwoScore) {
            System.out.println("Player 1 is the winner.");
        }
        else if (playerOneScore < playerTwoScore) {
            System.out.println("Player 2 is the winner.");
        }
        else {
            System.out.println("It is a tie");
        }
    }

    // Prints the current state of the table.
    public void printTable()
    {
        System.out.print(kalahStores[0] + "   ");
        for(int i = 0; i < 6; i++) {
            System.out.print(kalahCups[0][i] + " ");
        }
        System.out.print("  " + kalahStores[1] + "\n");

        if (kalahStores[0] > 9) System.out.print("     ");
        else System.out.print("    ");

        for(int i = 0; i < 6; i++) {
            System.out.print(kalahCups[1][i] + " ");
        }
        System.out.println();
    }
}
