package main.java.player;

public abstract class Player {

  protected char symbol;

  public Player(char symbol) {
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }

  abstract void makeMove(int x, int y);
}
