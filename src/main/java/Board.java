package main.java;

public class Board {

  protected char[][] matrix;

  public Board(int dimension) {
    this.matrix = new char[dimension][dimension];
  }

  public int getDimension() {
    return matrix.length;
  }

  public int getTotalSizeOfTheBoard() {
    return matrix.length * matrix.length;
  }

  public boolean isOutOfBoard(Position position) {
    return (position.x < 0 || position.x >= matrix.length) || (position.y < 0 || position.y >= matrix.length);
  }

  public boolean isEmpty(Position position) {
    return matrix[position.x][position.y] != '\u0000';
  }

  public void display() {
//    Arrays.stream(board).forEach(); todo java 8!
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        System.out.print(matrix[i][j]);
        if (j < matrix.length - 1) {
          System.out.print("|");
        }
      }
      System.out.println();
    }

    System.out.println("...");
  }

  public boolean isVictory(Position position) {
    int x = position.x;
    int y = position.y;
    char symbol = matrix[x][y];
    return isVictoryRow(x, symbol) || isVictoryColumn(y, symbol) || isVictoryDiagonal(x, y, symbol);
  }

  private boolean isVictoryRow(int x, char symbol) {
    for (int column = 0; column < matrix.length; column++) {
      if (matrix[x][column] != symbol) {
        return false;
      }
    }
    System.out.println("Victory Row " + x + " and symbol " + symbol);
    return true;
  }

  private boolean isVictoryColumn(int y, char symbol) {
    for (int row = 0; row < matrix.length; row++) {
      if (matrix[row][y] != symbol) {
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

    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i][i] != symbol) {
        return false;
      }
    }
    System.out.println("Victory First Diagonal with symbol " + symbol);
    return true;
  }

  private boolean isVictorySecondDiagonal(int x, int y, char symbol) {
    if (y != matrix.length - x - 1) {
      return false;
    }

    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i][matrix.length - i - 1] != symbol) {
        return false;
      }
    }
    System.out.println("Victory Second Diagonal with symbol " + symbol);
    return true;
  }

  public static class Position {
    int x;
    int y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
