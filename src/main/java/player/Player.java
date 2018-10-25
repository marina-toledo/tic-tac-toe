package main.java.player;

import main.java.Board;

public abstract class Player {

  protected char symbol;

  public Player(char symbol) {
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }

  public abstract Board.Position makeMove(Board board);
}
