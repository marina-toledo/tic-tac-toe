package main.java;

import main.java.player.ArtificialIntelligentPlayer;
import main.java.player.ManualPlayer;
import main.java.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TicTacToe {

  List<Player> playerList;
  char[][] board;

  public TicTacToe(String fileName) {

    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      Object[] line = stream.toArray();

      int sizeOfTheBoard = Integer.parseInt(line[0].toString());
      if (sizeOfTheBoard < 3 || sizeOfTheBoard > 10) {
        throw new IOException("Dimension of the board should be between 3 and 10.");
      }
      board = new char[sizeOfTheBoard][sizeOfTheBoard];
      playerList = new ArrayList<>();
      playerList.add(new ArtificialIntelligentPlayer(line[1].toString().charAt(0)));
      playerList.add(new ArtificialIntelligentPlayer(line[2].toString().charAt(0)));
      playerList.add(new ManualPlayer(line[3].toString().charAt(0)));
      //todo validate all symbols are different

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void play() {

    displayBoard();

    Scanner sc = new Scanner(System.in);
    int playerTime = generateRandomNumberBetweenRange(0, playerList.size() - 1);
    System.out.println("Random:" + playerTime);
    int totalRoundsInGame = board.length * board.length;
    int countRoundsInGame = 0;
    int xRound;
    int yRound;
    Player currentPlayer;
    do {
      playerTime = (playerTime + 1) % 3;
      currentPlayer = playerList.get(playerTime);

      do {
        if (currentPlayer instanceof ArtificialIntelligentPlayer) {
          System.out.println("Time of the AI player " + playerTime);
          xRound = generateRandomNumberBetweenRange(0, board.length - 1);
          yRound = generateRandomNumberBetweenRange(0, board.length - 1);
        } else {
          System.out.println("Type the next move with two integers, the first being the row and the second the column. Like : 0 1");
          System.out.println("Time of the manual player " + playerTime);
          xRound = sc.nextInt();
          yRound = sc.nextInt();
        }
      }
      while (isOutOfBoard(xRound) || isOutOfBoard(yRound) || board[xRound][yRound] != '\u0000');//todo validate it is a valid move(no one chose it before and it is within board)

      board[xRound][yRound] = currentPlayer.getSymbol();
      displayBoard();
      countRoundsInGame++;
    } while (!isVictory(xRound, yRound, currentPlayer.getSymbol()) && !isDraw(totalRoundsInGame, countRoundsInGame));
    System.out.println("Ended Game.");
  }

  private int generateRandomNumberBetweenRange(int min, int max) {
    return (int) (Math.random() * ((max - min) + 1)) + min;
  }

  private boolean isOutOfBoard(int position) {
    return position < 0 || position >= board.length;
  }

  private void displayBoard() {
//    Arrays.stream(board).forEach(); todo java 8!
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        System.out.print(board[i][j]);
        if (j < board.length - 1) {
          System.out.print("|");
        }
      }
      System.out.println();
    }

    System.out.println("...");
  }

  private boolean isVictory(int x, int y, char symbol) {
    return isVictoryRow(x, symbol) || isVictoryColumn(y, symbol) || isVictoryDiagonal(x, y, symbol);
  }

  private boolean isVictoryRow(int x, char symbol) {
    for (int column = 0; column < board.length; column++) {
      if (board[x][column] != symbol) {
        return false;
      }
    }
    System.out.println("Victory Row " + x + " and symbol " + symbol);
    return true;
  }

  private boolean isVictoryColumn(int y, char symbol) {
    for (int row = 0; row < board.length; row++) {
      if (board[row][y] != symbol) {
        return false;
      }
    }
    System.out.println("Victory Column " + y + " and symbol " + symbol);
    return true;
  }

  private boolean isVictoryDiagonal(int x, int y, char symbol) {
    return isVictoryFirstDiagonal(x, y, symbol) || isVictorySecondDiagonal(x, y, symbol);
  }

  private boolean isVictoryFirstDiagonal(int x, int y, char symbol) {
    if (x != y) {
      return false;
    }

    for (int i = 0; i < board.length; i++) {
      if (board[i][i] != symbol) {
        return false;
      }
    }
    System.out.println("Victory First Diagonal with symbol " + symbol);
    return true;
  }

  private boolean isVictorySecondDiagonal(int x, int y, char symbol) {
    if (y != board.length - x - 1) {
      return false;
    }

    for (int i = 0; i < board.length; i++) {
      if (board[i][board.length - i - 1] != symbol) {
        return false;
      }
    }
    System.out.println("Victory Second Diagonal with symbol " + symbol);
    return true;
  }

  private boolean isDraw(int totalMovesInGame, int countMovesInGame) {
    return countMovesInGame >= totalMovesInGame;
  }

  public static void main(String[] args) {
    TicTacToe ticTacToe = new TicTacToe("/Users/marinatoledo/IdeaProjects/TicTacToe/src/main/resources/Rules.txt");
    ticTacToe.play();
  }
}
