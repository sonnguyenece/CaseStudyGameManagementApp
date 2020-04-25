package code;

import com.sun.source.tree.TryTree;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class Game implements Comparable<Game>, Serializable {
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
    //    private String[]screenShot;
    private String gameLanguage;
    private String gameGenre;
    private int playTime;
    private String gamePrice;
    private String gameSize;
    private String gameScore;
    private boolean isSteamGame;
    private boolean isValidateName;
    private boolean isValidateID;
    private boolean isRunning;
    private boolean isGameScore;
    private static Pattern pattern;
    private static final String STEAM_GAME_ID = "[0-9]+";
    private static final String NON_STEAM_GAME_ID = "^[0-9a-zA-z].*";
    private static final String GAME_NAME_REGEX = "^[0-9a-zA-z].*";
    private static final String GAME_IMAGE_PATH_REGEX = "^[0-9a-zA-z].*";


    public Game() {
        initParameter();
    }


    public Game(String name, Boolean isSteamGame, String gameID,
                String iconPath, String score) {
        setName(name);
        this.lastPlayed = "Never Play Before";
        this.iconPath = iconPath;
        setGameID(gameID, isSteamGame);
        this.isSteamGame = isSteamGame;
        this.gameScore = score;
        this.headerImage = "image/imgNotFoundHeader.png";
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
    }

    public Game(String name, String developer, String iconPath, String screenShotPath,
                String description, String gameID, String headerImage,
                String gameLanguage, String gameGenre, String gameScore,
                String homepage, boolean isSteamGame) {
        initParameter();
        setName(name);
        try {
            if (developer == null || developer.equals(" ")) developer = "Not Update";
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Developer no input!");
        }
        this.developer = developer;
        this.iconPath = iconPath;

        this.headerImage = headerImage;
        this.description = description;
        setGameID(gameID, isSteamGame);

        if (gameScore == null || gameScore.equals(" ")) {
            this.gameScore = "No Score";
        } else setGameScore(gameScore);

        try {
            if (homepage.equals(null) || homepage.equals(" ")) homepage = "";
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Homepage no input!");
        }

        this.gameHomepage = homepage;
        this.isSteamGame = isSteamGame;
        this.gameLanguage = gameLanguage;
        this.setScreenShot(screenShotPath);
    }

//        this.gameLanguage.add(gameLanguage);

    public String getGameLanguage() {
        return gameLanguage;
    }

    public String getStringLanguage() {
        return this.gameLanguage;
    }

    public String getStringScreenshots() {
        String temp = "";
        for (int i = 0; i < getScreenShot().size(); i++) {
            temp += getScreenShot().get(i) + ",";
        }
        return temp;
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
                this.screenShot.add((image.trim()));
            }
        }
    }

    public ArrayList<String> getScreenShot() {
        return screenShot;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGenre() {
        return this.gameGenre;
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

    public boolean isGameScore() {
        return isGameScore;
    }

    public void setGameScore(boolean gameScore) {
        isGameScore = gameScore;
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
            try {
                float score = Float.parseFloat(gameScore);

                if (score <= 10 && score > 0) {
                    this.gameScore = gameScore;
                    isGameScore = true;
                    return;
                }
            } catch (Exception e) {
                isGameScore = false;
                this.gameScore = "No Score";
            }
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

    public void initParameter() {
        isRunning = false;
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.screenShot.add("image/imgNotFound.png");
        this.headerImage = "image/imgNotFoundHeader.png";
        this.name = "";
        this.gameID = "";
        this.developer = "No Data";
        this.lastPlayed = "No Data";
        this.gameSize = "No Data";
        this.gamePrice = "No Data";
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
            System.out.println("Game.java/Wrong gameID");
            JOptionPane.showMessageDialog(null,"Wrong GameID",
                    "GAME CAN NOT LAUNCH", JOptionPane.WARNING_MESSAGE);
        }
        this.isRunning = true;

    }

    @Override
    public int compareTo(Game game) {
        return this.getName().compareTo(game.getName());
    }

}
