package swing;

import code.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddGameScreen extends JDialog {
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
    private JTextField iconPathText;
    private JTextField headerImgPath;
    private JTextField screenshotsPath;
    private JTextField homepageTextField;
    private JTextField descriptionTextField;
    private JTextField developerTextField;

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
    private String gameDeveloper;
    private Game game;
    private boolean isSave;
    private final ButtonGroup radioGroup = new ButtonGroup();
    private Screen screen;

    AddGameScreen(Frame parent, boolean modal) {
        setTitle("ADD GAME");
        setSize(1000, 700);
        setLocation(280, 50);
        this.setContentPane(mainPanel);
        this.pack();
        initInitialVar();
        screen = (Screen) parent;

        radioGroup.add(yesRadioButton);
        radioGroup.add(noRadioButton);
        nameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameName = nameTextField.getText();
//                game.setName(nameTextField.getText());
            }
        });
        gameIDTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setGameID(gameIDTextField.getText());
                gameID = gameIDTextField.getText();
            }
        });
        yesRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
//                isSteamGame = true;
                game.setSteamGame(true);
            }
        });
        noRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
//                isSteamGame = false;
                game.setSteamGame(false);
            }
        });
        scoreTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setGameScore(scoreTextField.getText());
                gameScore = scoreTextField.getText();
            }
        });
        genreTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setGameGenre(genreTextField.getText());
                gameGenre = genreTextField.getText();
            }
        });
        languagesTextFiled.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setGameLanguage(languagesTextFiled.getText());
                gameLanguage = languagesTextFiled.getText();
            }
        });
        sizeOnDiskTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setGameSize(sizeOnDiskTextField.getText());
                gameSize = sizeOnDiskTextField.getText();
            }
        });
        iconPathText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setIconPath(iconPathText.getText());
                gameIcon = iconPathText.getText();
            }
        });
        headerImgPath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setHeaderImage(headerImgPath.getText());
                gameHeader = headerImgPath.getText();
            }
        });
        screenshotsPath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
//                game.setScreenShot(screenshotsPath.getText());
                gameScreenshots = screenshotsPath.getText();
            }
        });
        homepageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
//                game.setGameHomepage(homepageTextField.getText());
                gameHomepage = homepageTextField.getText();
            }
        });
        descriptionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                gameDescription = descriptionTextField.getText();
            }
        });
        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSave = false;
                dispose();
                screen.setEnabled(true);
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (gameName.equals("") || gameID.equals("")) {
                    JOptionPane.showMessageDialog(null, "Wrong Input!");
                } else {
//                    isSave = true;
                    game = new Game(gameName, gameDeveloper, gameIcon, gameScreenshots,
                            gameDescription, gameID, gameHeader,
                            gameLanguage, gameGenre, gameScore, gameHomepage, isSteamGame);
                    screen.getCrudList().addGame(game);
                    JOptionPane.showMessageDialog(null, "Added");
                    screen.refreshGameList();
                    dispose();
                    screen.setEnabled(true);
                }
            }
        });
        nameTextField.addKeyListener(new KeyAdapter() {
        });
        developerTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameDeveloper = developerTextField.getText();
            }
        });
    }

    private void initInitialVar() {
        gameName = "";
        gameID = "";
        gameScore = "";
        gameGenre = "";
        gameLanguage = "";
        gameSize = "";
        gameIcon = "";
        gameHeader = "";
        gameScreenshots = "";
        gameHomepage = "";
        gameDescription = "";
        game = new Game();
        isSave = false;
        noRadioButton.setSelected(true);
    }


}
