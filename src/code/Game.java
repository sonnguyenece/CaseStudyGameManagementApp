package code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {
    private String name;
    private String developer;
    private String iconPath;
    private String screenShotPath;
    private ArrayList<String> gameImage;
    private int playTime;
    private String lastPlayed;
    private String description;
    private String gameID;
    private ArrayList<String> gameLanguage;
    private float gamePrice;
    private ArrayList<String> genre;
    private String gameLocation;
    private float gameSize;
    private float gameScore;
    private String gameHomepage;
    private Boolean isSteamGame;

    private static Pattern pattern;
    private static final String GAMEID_REGEX = "[0-9]+";

    public Game() {
        this.gameLanguage = new ArrayList();
        this.genre = new ArrayList();
        this.gameImage = new ArrayList();
    }

    public Game(String gameLanguage) {
        this.gameLanguage = new ArrayList();
        setGameLanguage(gameLanguage);
    }

    public Game(String name, Boolean isSteamGame, String gameID) {
        this.name = name;
//        this.iconPath = iconPath;
        setGameID(gameID);
        this.isSteamGame = isSteamGame;
    }

    public Game(String name, String developer, String iconPath, String screenShotPath,
                String description, String gameID, String gameImage, int playTime, String lastPlayed,
                String gameLanguage, float gamePrice, String gameGenre,
                float gameScore, String gameHomepage, Boolean isSteamGame) {
        this.name = name;
        this.developer = developer;
        this.iconPath = iconPath;
        this.screenShotPath = screenShotPath;
        this.playTime = playTime;
        this.lastPlayed = lastPlayed;
        this.description = description;
        setGameID(gameID);
        this.gamePrice = gamePrice;
//        this.gameLocation = gameLocationPath;
        this.gameScore = gameScore;
        this.gameHomepage = gameHomepage;
        this.isSteamGame = isSteamGame;
        setGameLanguage(gameLanguage);
        setGameGenre(gameGenre);
        setGameImage(gameImage);
    }

    private void setGameLanguage(String gameLanguage) {
        if (gameLanguage.contains(",")) {
            String[] tempGameLanguage = gameLanguage.trim().split(",");
            for (String language : tempGameLanguage) {
                this.gameLanguage.add(language.trim());
            }
        } else if (!gameLanguage.contains(" ")) {
            this.gameLanguage.add(gameLanguage);
        }
    }

    public ArrayList<String> getGameLanguage() {
        return gameLanguage;
    }

    private void setGameImage(String gameImage) {
        if (gameImage.contains(",")) {
            String[] tempgameImage = gameImage.trim().split(",");
            for (String image : tempgameImage) {
                this.gameImage.add(image.trim());
            }
        } else if (!gameImage.contains(" ")) {
            this.gameImage.add(gameImage);
        }
    }

    public ArrayList<String> getGameImage() {
        return gameImage;
    }

    private void setGameGenre(String gameGenre) {
        if (gameGenre.contains(",")) {
            String[] tempGameGenre = gameGenre.split(",");
            for (String genre : tempGameGenre) {
                this.genre.add(genre.trim());
            }
        } else if (!gameGenre.contains(" ")) {
            this.genre.add(gameGenre);
        }
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getScreenShotPath() {
        return screenShotPath;
    }

    public void setScreenShotPath(String screenShotPath) {
        this.screenShotPath = screenShotPath;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
        if (!isValidateGameID(gameID)) System.out.println("Wrong ID");
    }

    public boolean isValidateGameID(String gameID) {
        return (Pattern.matches(GAMEID_REGEX, gameID));
    }

    public float getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(float gamePrice) {
        this.gamePrice = gamePrice;
    }


    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public float getGameSize() {
        return gameSize;
    }

    public void setGameSize(float gameSize) {
        this.gameSize = gameSize;
    }

    public float getGameScore() {
        return gameScore;
    }

    public void setGameScore(float gameScore) {
        this.gameScore = gameScore;
    }

    public String getGameHomepage() {
        return gameHomepage;
    }

    public void setGameHomepage(String gameHomepage) {
        this.gameHomepage = gameHomepage;
    }

    public Boolean getSteamGame() {
        return isSteamGame;
    }

    public void setSteamGame(Boolean steamGame) {
        isSteamGame = steamGame;
    }

    public void playGame() throws Exception {
        if (this.isSteamGame) playSteamGame();
        else playNonSteamGame();
    }

    public void playSteamGame() throws Exception {
        Runtime runTime = Runtime.getRuntime();
        Process process = runTime.exec("steam steam://rungameid/" + this.gameID);
        //thread.sleep giup tranh tinh trang game chua kip chay jvm da chay xong
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void playNonSteamGame() throws Exception {
        Runtime runTime = Runtime.getRuntime();
        try {
            Process process = runTime.exec(this.gameID);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Wrong gameID");
        }

    }

    public static void main(String[] args) throws Exception {
//        Game halfLife = new Game("Half Life2", true, "220");
//        halfLife.playGame();
//        Game supertux2 = new Game("supertux2", "supertux2", false);
//        supertux2.playGame();
//        Game mother = new Game("English ,Dutch   ,        Vietnamese        ");
//        System.out.println(mother.getGameLanguage());
    }
}
