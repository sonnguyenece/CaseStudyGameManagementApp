package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CRUDList {
    private ArrayList<Game> gameList;
    private ArrayList<Game> gameSearchList;
    private ArrayList<Game> gameFilterList;
    private int quantityGames;

    public CRUDList() {
        gameList = new ArrayList<Game>();
        gameSearchList = new ArrayList<Game>();
        gameFilterList = new ArrayList<Game>();
        quantityGames = 0;
    }

    public ArrayList<Game> getGameSearchList() {
        return gameSearchList;
    }

    public void setGameSearchList(ArrayList<Game> gameSearchList) {
        this.gameSearchList = gameSearchList;
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public ArrayList<Game> getGameFilterList() {
        return gameFilterList;
    }

    public void setGameFilterList(ArrayList<Game> gameFilterList) {
        this.gameFilterList = gameFilterList;
    }

    public int getQuantityGames() {
        return quantityGames;
    }

    public void setQuantityGames(int quantityGames) {
        this.quantityGames = quantityGames;
    }

    public boolean addGame(Game game) {
        for (Game g :
                this.gameList) {
            if (game.getGameID().equals(g.getGameID())) {
                return false;
            }
        }
        if (game.isGameScore()) {
            this.gameList.add(game);
            this.quantityGames++;
            return true;
        }
        return false;
    }

    public boolean checkBeforeSave(Game game, int gameEditIndex) {
        for (int i = 0; i < this.gameList.size(); i++) {
            if (i == gameEditIndex) continue;
            if (game.getGameID().equals(gameList.get(i).getGameID())) {
                return false;
            }
        }
        if (game.isGameScore()) {
            this.gameList.add(game);
            this.quantityGames++;
            return true;
        }
        return false;
    }

    public void editGame(int index) {

    }

    public void editSearchGame(int index) {
        delGameInList(index);
    }

    public void deleteGame(int index) {
        gameList.remove(index);
        quantityGames--;
    }

    public void deleteSearchGame(int index) {
        delGameInList(index);
        gameSearchList.remove(index);
        quantityGames--;
    }

    public void delGameInList(int index) {
        Game gameTemp = gameSearchList.get(index);
        for (int i = 0; i < gameList.size(); i++) {
            if (gameTemp.equals(gameList.get(i))) {
                gameList.remove(i);
            }
        }
    }

    public void search(String searchText) {
        gameSearchList.clear();
        for (Game game : this.gameList) {
            if (game.getName().toUpperCase().contains(searchText)
                    || game.getName().toLowerCase().contains(searchText)
                    || game.getName().contains(searchText)) {
                gameSearchList.add(game);
            }
        }
    }

    public void sortByName() {
        Collections.sort(gameList);
    }

    public void sortByRevertName() {
        Collections.sort(gameList, new Comparator<Game>() {
            @Override
            public int compare(Game game, Game g) {
                return -game.getName().compareTo(g.getName());
            }
        });
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
                return -game.getGameScore().compareTo(g.getGameScore());
            }
        });
    }

    public void filterToSearch() {
        gameSearchList = gameFilterList;
    }


    public void exportToFile() {

    }
}
