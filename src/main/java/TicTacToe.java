package main.java;

import main.java.player.ArtificialIntelligentPlayer;
import main.java.player.ManualPlayer;
import main.java.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TicTacToe {

  List<Player> playerList;
  Board board;

  public TicTacToe(String fileName) {

    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      Object[] line = stream.toArray();

      int sizeOfTheBoard = Integer.parseInt(line[0].toString());
      if (sizeOfTheBoard < 3 || sizeOfTheBoard > 10) {
        throw new IOException("Dimension of the board should be between 3 and 10.");
      }
      board = new Board(sizeOfTheBoard);
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

    board.display();

    int playerTime = getRandomPlayerIndex();

    int totalRoundsInGame = board.getTotalSizeOfTheBoard();
    int countRoundsInGame = 0;

    Player currentPlayer;
    Board.Position currentPosition;
    do {
      playerTime = (playerTime + 1) % 3;
      currentPlayer = playerList.get(playerTime);

      currentPosition = currentPlayer.makeMove(board);

      board.matrix[currentPosition.x][currentPosition.y] = currentPlayer.getSymbol();
      board.display();
      countRoundsInGame++;
    } while (!board.isVictory(currentPosition) && !isDraw(totalRoundsInGame, countRoundsInGame));
    System.out.println("Ended Game.");
  }

  private int getRandomPlayerIndex() {
    int min = 0;
    int max = playerList.size() - 1;
    return (int) (Math.random() * ((max - min) + 1)) + min;
  }

  private boolean isDraw(int totalMovesInGame, int countMovesInGame) {
    return countMovesInGame >= totalMovesInGame;
  }

  public static void main(String[] args) {
    TicTacToe ticTacToe = new TicTacToe("/Users/marinatoledo/IdeaProjects/TicTacToe/src/main/resources/Rules.txt");
    ticTacToe.play();
  }
}
