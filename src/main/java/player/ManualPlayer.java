package main.java.player;

import main.java.Board;

import java.util.Scanner;

public class ManualPlayer extends Player {

  private Scanner sc = new Scanner(System.in);

  public ManualPlayer(char symbol) {
    super(symbol);
  }

  @Override
  public Board.Position makeMove(Board board) {

    System.out.println("Type the next move with two integers, the first being the row and the second the column. Like : 0 1");
    System.out.println("Time of the manual player " + symbol);

    Board.Position position;
    boolean isValidMove = false;
    do {
      int x = sc.nextInt();
      int y = sc.nextInt();
      position = new Board.Position(x, y);

      isValidMove = !board.isOutOfBoard(position) && !board.isEmpty(position);
      if (!isValidMove) {
        System.out.println("Invalid Move. Please try typing another position.");
      }
    }
    while (!isValidMove);

    return position;
  }

}
