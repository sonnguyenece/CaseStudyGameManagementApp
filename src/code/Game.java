package code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Game implements Comparable<Game> {
    private String name;
    private String developer;
    private String iconPath;
    private String screenShotPath;
    private String gameLocation;
    private String lastPlayed;
    private String description;
    private String gameID;
    private String gameHomepage;
    private String headerImage;
    private ArrayList<String> screenShot = new ArrayList();
    private ArrayList<String> gameLanguage;
    private ArrayList<String> genre;
    private int playTime;
    private float gamePrice;
    private float gameSize;
    private String gameScore;
    private boolean isSteamGame;
    private boolean isValidateName;
    private boolean isValidateID;
    private boolean isRunning;

    private static Pattern pattern;
    private static final String STEAMGAMEID = "[0-9]+";
    private static final String NONSTEAMGAMEID = "^[0-9a-zA-z].*";
    private static final String GAMENAME_REGEX = "^[0-9a-zA-z].*";
    private static final String GAMEIMAGEPATH_REGEX = "^[0-9a-zA-z].*";

    public Game() {
        this.gameLanguage = new ArrayList();
        this.genre = new ArrayList();
//        this.gameImage = new ArrayList();
        isRunning = false;
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.headerImage="image/imgNotFoundHeader.png";
    }

    public Game(String name, Boolean isSteamGame, String gameID, String iconPath, String score) {
        setName(name);
        this.iconPath = iconPath;
        setGameID(gameID, isSteamGame);
        this.isSteamGame = isSteamGame;
        this.gameScore = score;
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.headerImage="image/imgNotFoundHeader.png";
    }

    public Game(String name, String developer, String iconPath, String screenShotPath, String description,
                String gameID, String gameImage, int playTime, String lastPlayed, String gameLanguage,
                String gameGenre, String gameScore, String gameHomepage, boolean isSteamGame) {
        setName(name);
        this.developer = developer;
        this.iconPath = iconPath;
        this.screenShotPath = screenShotPath;
        this.playTime = playTime;
        this.lastPlayed = lastPlayed;
        this.description = description;
        setGameID(gameID, isSteamGame);
        this.gamePrice = gamePrice;
//        this.gameLocation = gameLocationPath;
        this.gameScore = gameScore;
        this.gameHomepage = gameHomepage;
        this.isSteamGame = isSteamGame;
        setGameLanguage(gameLanguage);
        setGameGenre(gameGenre);
        setScreenShot(gameImage);
        this.headerImage="image/imgNotFoundHeader.png";
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

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {

        this.headerImage = headerImage;
    }

    public void setScreenShot(String gameImagePath) {
        this.screenShot.clear();
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        if (gameImagePath.contains(",")) {
            String[] tempgameImage = gameImagePath.trim().split(",");
            for (String image : tempgameImage) {
                this.screenShot.add(0, (image.trim()));
            }
        }
    }

    public ArrayList<String> getScreenShot() {
        return screenShot;
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
        isValidateName = Pattern.matches(GAMENAME_REGEX, name);
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

//    public void setScreenShotPath(String screenShotPath) {
//        this.screenShotPath = screenShotPath;
//    }

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

    public void setGameID(String gameID, boolean isSteamGame) {
        this.gameID = gameID;
        this.isSteamGame = isSteamGame;
        if (this.isSteamGame) {
            isValidateID = Pattern.matches(STEAMGAMEID, gameID);
        } else {
            isValidateID = Pattern.matches(NONSTEAMGAMEID, gameID);
        }
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

    public String getGameScore() {
        return gameScore;
    }

    public void setGameScore(String gameScore) {
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

    public void playGame() {
        try {
            if (this.isSteamGame) {
                if (this.isValidateID && this.isValidateName) playSteamGame();
                else this.isRunning = false;
            } else {
                if (this.isValidateID && this.isValidateName) playNonSteamGame();
                else this.isRunning = false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

//    public void setGameImage(ArrayList<String> gameImage) {
//        this.gameImage = gameImage;
//    }

    public void setGameLanguage(ArrayList<String> gameLanguage) {
        this.gameLanguage = gameLanguage;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public boolean isSteamGame() {
        return isSteamGame;
    }

    public void setSteamGame(boolean steamGame) {
        isSteamGame = steamGame;
    }

    public boolean isValidateName() {
        return isValidateName;
    }

    public void setValidateName(boolean validateName) {
        isValidateName = validateName;
    }

    public boolean isValidateID() {
        return isValidateID;
    }

    public void setValidateID(boolean validateID) {
        isValidateID = validateID;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
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
        this.isRunning = true;
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
        this.isRunning = true;

    }

    @Override
    public int compareTo(Game game) {
        return this.getName().compareTo(game.getName());
    }


//    public static void main(String[] args) throws Exception {
//        Game halfLife = new Game("Half Life 2", true, "220","image/Game Datas/HalfLife2/image/icon.png");
//        halfLife.playGame();
//        System.out.println(halfLife.isRunning);
//
//        Game supertux2 = new Game("supertux 2", false, "supertux2","image/Game Datas/Supertux2/image/icon.jpg");
//        supertux2.playGame();
//        System.out.println(supertux2.isRunning);

//    }
}
