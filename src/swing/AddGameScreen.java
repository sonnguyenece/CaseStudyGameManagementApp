package swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGameScreen extends JFrame {
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField gameIDTextField;
    private JTextField scoreTextField;
    private JTextField genreTextField;
    private JButton OKButton;
    private JButton CANCELButton;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JTextField languagesTextFiled;
    private JTextField sizeOnDiskTextField;
    private JTextField gameScoreTextField;
    private JTextField iconPathText;
    private JTextField headerImgPath;
    private JTextField screenshotsPath;
    private JTextField homepageTextField;
    private JTextField descriptionTextField;

    private String gameName;
    private String gameID;
    private boolean isSteamGame;
    private String gameScore;
    private String gameGenre;
    private String gameLanguage;
    private String gameSize;
    private String gameIcon;
    private String gameHeader;
    private String gameScreenshots;
    private String gameHomepage;
    private String gameDescription;

    AddGameScreen() {
        super("ADD GAME");
        setSize(1000, 700);
        setLocation(280, 50);
        setVisible(true);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        initInitialVar();
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameID() {
        return gameID;
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

    public String getGameScore() {
        return gameScore;
    }

    public void setGameScore(String gameScore) {
        this.gameScore = gameScore;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGameLanguage() {
        return gameLanguage;
    }

    public void setGameLanguage(String gameLanguage) {
        this.gameLanguage = gameLanguage;
    }

    public String getGameSize() {
        return gameSize;
    }

    public void setGameSize(String gameSize) {
        this.gameSize = gameSize;
    }

    public String getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

    public String getGameHeader() {
        return gameHeader;
    }

    public void setGameHeader(String gameHeader) {
        this.gameHeader = gameHeader;
    }

    public String getGameScreenshots() {
        return gameScreenshots;
    }

    public void setGameScreenshots(String gameScreenshots) {
        this.gameScreenshots = gameScreenshots;
    }

    public String getGameHomepage() {
        return gameHomepage;
    }

    public void setGameHomepage(String gameHomepage) {
        this.gameHomepage = gameHomepage;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    private void initInitialVar() {
        gameName = "";
        gameID = "";
        isSteamGame = false;
        gameScore = "";
        gameGenre = "";
        gameLanguage = "";
        gameSize = "";
        gameIcon = "";
        gameHeader = "";
        gameScreenshots = "";
        gameHomepage = "";
        gameDescription = "";
    }


}
