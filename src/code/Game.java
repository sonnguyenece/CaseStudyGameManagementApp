package code;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private String gamePrice;
    private String gameSize;
    private String gameScore;
    private boolean isSteamGame;
    private boolean isValidateName;
    private boolean isValidateID;
    private boolean isRunning;

    private static Pattern pattern;
    private static final String STEAM_GAME_ID = "[0-9]+";
    private static final String NON_STEAM_GAME_ID = "^[0-9a-zA-z].*";
    private static final String GAME_NAME_REGEX = "^[0-9a-zA-z].*";
    private static final String GAME_IMAGE_PATH_REGEX = "^[0-9a-zA-z].*";

    public Game() {
        this.gameLanguage = new ArrayList();
        this.genre = new ArrayList();
        this.lastPlayed = "No Data";
        isRunning = false;
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.headerImage = "image/imgNotFoundHeader.png";
    }

    public Game(String name, Boolean isSteamGame, String gameID, String iconPath, String score) {
        setName(name);
        this.lastPlayed = "Never Play Before";
        this.iconPath = iconPath;
        setGameID(gameID, isSteamGame);
        this.isSteamGame = isSteamGame;
        this.gameScore = score;
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.headerImage = "image/imgNotFoundHeader.png";
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
        this.headerImage = "image/imgNotFoundHeader.png";
    }

    public void setGameLanguage(String gameLanguage) {
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

    public void setGameGenre(String gameGenre) {
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
        isValidateName = Pattern.matches(GAME_NAME_REGEX, name);
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

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getLastPlayed() {
        return this.lastPlayed;
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
            isValidateID = Pattern.matches(STEAM_GAME_ID, gameID);
        } else {
            isValidateID = Pattern.matches(NON_STEAM_GAME_ID, gameID);
        }
    }

    public String getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(String gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public String getGameSize() {
        return gameSize;
    }

    public void setGameSize(String gameSize) {
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

    public void setGameID(String gameID) {
        this.gameID = gameID;
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

    public void playGame() {
        try {
            if (this.isSteamGame) {
                if (this.isValidateID && this.isValidateName) playSteamGame();
                else this.isRunning = false;
            } else {
                if (this.isValidateID && this.isValidateName) playNonSteamGame();
                else this.isRunning = false;
            }
            toStringLastPlay();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void toStringLastPlay() {
        long currentTimeMs = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultDate = new Date(currentTimeMs);
        this.lastPlayed = sdf.format(resultDate);
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

}
