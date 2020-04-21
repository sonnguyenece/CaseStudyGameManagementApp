package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CRUDList {
    private ArrayList<Game> gameList;
    private int quantityGames;

    public CRUDList() {
        gameList = new ArrayList<Game>();
        quantityGames = 0;
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public void setGameList(List<Game> gameList) {
    }

    public int getQuantityGames() {
        return quantityGames;
    }

    public void setQuantityGames(int quantityGames) {
        this.quantityGames = quantityGames;
    }

    public void addGame(Game game) {
        this.gameList.add(game);
       this.quantityGames++;
    }

    public void editGame(int index) {
    }

    public void deleteGame(int index) {
        gameList.remove(index);
        quantityGames--;
    }

    public void search() {

    }

    public void sortByName() {
        Collections.sort(gameList);
    }

    public void sortFromLowestScore() {
        Collections.sort(gameList, new Comparator<Game>() {
            @Override
            public int compare(Game game, Game g) {
                return game.getGameScore().compareTo(g.getGameScore());
            }
        });
    }
    public void sortFromHighestScore() {
        Collections.sort(gameList, new Comparator<Game>() {
            @Override
            public int compare(Game game, Game g) {
                return - game.getGameScore().compareTo(g.getGameScore());
            }
        });
    }
}
