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
    private ButtonGroup radioGroup;
    private Screen screen;
    private String steamGameSelected;

    AddGameScreen(Frame parent, boolean modal) {
        setTitle("ADD GAME");
        getPreferredSize();
        setSize(800, 700);
        setLocation(280, 50);
        this.setContentPane(mainPanel);
        this.pack();
        initInitialVar();
        screen = (Screen) parent;
        radioGroup = new ButtonGroup();
        radioGroup.add(yesRadioButton);
        radioGroup.add(noRadioButton);
        AddGameScreen gameScr = this;
        yesRadioButton.setActionCommand("not selected");
        screen.setEnabled(false);
        /*----------------------Action Listener----------------------*/
        nameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameName = nameTextField.getText();
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
                isSteamGame = true;
                yesRadioButton.setActionCommand("Yes");
            }
        });
        noRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                isSteamGame = false;
                yesRadioButton.setActionCommand("No");
            }
        });
        scoreTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (!scoreTextField.getText().equals(""))
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
                steamGameSelected = yesRadioButton.getActionCommand();
                if (gameName.equals("") || gameID.equals("") ||
                        steamGameSelected.equals("not selected")) {
                    gameScr.setVisible(false);
                    JOptionPane.showMessageDialog(null,
                            "Wrong Input!", "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                    gameScr.setVisible(true);
                } else {
                    game = new Game(gameName, gameDeveloper, gameIcon,
                            gameScreenshots, gameDescription, gameID,
                            gameHeader, gameLanguage, gameGenre,
                            gameScore, gameHomepage, isSteamGame);
                    boolean gameAdded = screen.getCrudList().addGame(game);
                    if (gameAdded) {
                        screen.getCrudList().sortByName();
                        gameScr.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Added");
                        gameScr.setVisible(true);
                        screen.refreshGameList();
                        dispose();
                        screen.setEnabled(true);
                    } else if (!game.isGameScore()) {
                        gameScr.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Score Input!!!");
                        System.out.println("Wrong score input!");
                        gameScr.setVisible(true);
                    } else {
                        gameScr.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Game ID exist!");
                        gameScr.setVisible(true);
                    }
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
        gameScore = "No Score";
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
    }
}
