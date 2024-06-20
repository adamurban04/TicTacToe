package com.mycompany.revisioncs4013;

import java.util.Scanner;

public class Exercise7_9 {
    public static void main(String[] args) {
        StringBuilder map = new StringBuilder("""
        -------------------
        |     |     |     |
        -------------------
        |     |     |     |
        -------------------
        |     |     |     |
        -------------------""");    //19 characters on a single line 
        
        System.out.println(map);
        
        
        
        boolean win = false;
        char playerTurn = 'X';
        char[][] markedArray = new char[3][3];   //For keeping the marked positions
        
        while (win == false){
            int[] playerMove = playerInput(playerTurn);   //Index 0: row, Index 1: column
            int at = 23 + 40*playerMove[0] + 6*playerMove[1]; //calculated charAt in map
            
            //take care of invalid input
            while(markedArray[playerMove[0]][playerMove[1]] !='\u0000'){
                System.out.println("Please enter a cell that is not already taken");
                playerMove = playerInput(playerTurn);
                at = 23 + 40*playerMove[0] + 6*playerMove[1];
            }
            markedArray[playerMove[0]][playerMove[1]] = playerTurn; 
            
            
            
            updateMap(map, at, playerTurn);
            
            
            if (playerTurn == 'X'){ //opponent's turn
                playerTurn = 'O';
            }
            else {
                playerTurn = 'X';
            }
            
            win = checkWin(markedArray);
        }

            
        
    }
    
    public static void updateMap(StringBuilder m, int a, char p){
        m.setCharAt(a, p);
        System.out.println(m);
    }
    
    public static int[] playerInput(char p){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int position[] = new int[2];
        
        System.out.printf("Enter a row (0, 1, or 2) for player %c: ", p);
        position[0] = myObj.nextInt();
        while (position[0] > 2 || position[0] < 0){
            System.out.print("Please enter a valid value: ");
            position[0] = myObj.nextInt();
        }
        System.out.printf("Enter a column (0, 1, or 2) for player %c: ", p);
        position[1] = myObj.nextInt();
        while (position[1] > 2 || position[1] < 0){
            System.out.print("Please enter a valid value: ");
            position[1] = myObj.nextInt();
        }
        
        
        return position;
    }
    
    public static boolean checkWin(char[][] mArray){
        //check rows
        //System.out.println("Checking");
        //System.out.println(mArray[0][0]);
        //System.out.println(mArray[1][0]);
        //System.out.println(mArray[2][0]);
        for (int i = 0; i < 3; i++){    
            if (mArray[i][0] !='\u0000' && mArray[i][0] == mArray[i][1] && mArray[i][1] == mArray[i][2]){
                System.out.println(mArray[i][0] + " won");
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (mArray[0][i] !='\u0000' && mArray[0][i] == mArray[1][i] && mArray[1][i] == mArray[2][i]) {
                System.out.println(mArray[0][i] + " won");
                return true;
            }
        }
        // Check diagonals
        if (mArray[0][0] !='\u0000' && mArray[0][0] == mArray[1][1] && mArray[1][1] == mArray[2][2]) {
            System.out.println(mArray[0][0] + " won");
            return true;
        }
        else if (mArray[0][2] !='\u0000' && mArray[0][2] == mArray[1][1] && mArray[1][1] == mArray[2][0]) {
            System.out.println(mArray[0][2] + " won");
            return true;
        }
        
        // Check for draw (if all values are filled)
        boolean noDraw = false;
        for (int i = 0; i < 3; i++){    
            for (int j = 0; j < 3; j++){ 
                if (mArray[i][j] =='\u0000'){
                    noDraw = true;
                }
            }
        }
        if (noDraw == false){
            System.out.println("It is a draw");
            return true;
        }
        
        else {
            return false;
        }
    }
}
