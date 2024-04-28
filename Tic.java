import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerPostions = new ArrayList<Integer>();//ArrayList is a dynamic array in Java  <Integer> specifies that this ArrayList will store Integer objects.
    static ArrayList<Integer> cpuPostions = new ArrayList<Integer>();
    
    public static void main(String[] args) {
        char[][] gameboard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameboard);//calling the function game board with argument

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Your Placement (1-9): ");
            int playerPos = scan.nextInt();
            while (playerPostions.contains(playerPos) || cpuPostions.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct position.");
                playerPos = scan.nextInt();
            }
            placePiece(gameboard, playerPos, "Player");

            String result = checkWinner();
            if (!result.equals("")) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();//it is used to play with the user 
            int cpuPos;
            do {
                cpuPos = rand.nextInt(9) + 1;
            } while (playerPostions.contains(cpuPos) || cpuPostions.contains(cpuPos));
            placePiece(gameboard, cpuPos, "cpu");

            printGameBoard(gameboard);

            result = checkWinner();
            if (!result.equals("")) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameboard) {
        for (char[] row : gameboard) {// Iterate over each row of the game board
            // Iterate over each character in the current row
            for (char c : row) {
                System.out.print(c); // Print each character
            }
            System.out.println();//used to make a tic tac toe board
        }
    }

    public static void placePiece(char[][] gameboard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("Player")) {
            symbol = 'X';
            playerPostions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPostions.add(pos);
        }
        switch (pos) {
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(rightCol);
        winning.add(midCol);
        winning.add(leftCol);
        winning.add(cross1);
        winning.add(cross2);
        for (List l : winning) {
            if (playerPostions.containsAll(l)) {
                return "Congratulations you Won!";
            } else if (cpuPostions.containsAll(l)) {
                return "Cpu Won the Game!!";
            } else if (playerPostions.size() + cpuPostions.size() == 9) {
                return "Draw / Cat";
            }
        }
        return "";
    }
}
