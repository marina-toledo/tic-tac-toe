package main.java.player;

import main.java.Board;

public class ArtificialIntelligentPlayer extends Player {

  public ArtificialIntelligentPlayer(char symbol) {
    super(symbol);
  }

  @Override
  public Board.Position choosePosition(Board board) {
    System.out.println("Time of the AI player " + symbol);
    Board.Position position;

    do {
      int x = generateRandomNumberBetweenRange(0, board.getDimension() - 1);
      int y = generateRandomNumberBetweenRange(0, board.getDimension() - 1);
      position = new Board.Position(x, y);
    }
    while (board.isOutOfBoard(position) || board.isEmpty(position));

    return position;
  }

  private int generateRandomNumberBetweenRange(int min, int max) {
    return (int) (Math.random() * ((max - min) + 1)) + min;
  }
}
