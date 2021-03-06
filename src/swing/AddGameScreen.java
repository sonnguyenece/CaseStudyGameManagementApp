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
    private JButton addButton;
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
    public static final int SCREEN_POS_X = 335;
    public static final int SCREEN_POS_Y = 57;

    AddGameScreen(Frame parent, boolean modal) {
        setTitle("ADD GAME");
        getPreferredSize();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocation(SCREEN_POS_X, SCREEN_POS_Y);
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
                gameGenre = genreTextField.getText();
            }
        });
        languagesTextFiled.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameLanguage = languagesTextFiled.getText();
            }
        });
        sizeOnDiskTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameSize = sizeOnDiskTextField.getText();
            }
        });
        iconPathText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameIcon = iconPathText.getText();
            }
        });
        headerImgPath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameHeader = headerImgPath.getText();
            }
        });
        screenshotsPath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameScreenshots = screenshotsPath.getText();
            }
        });
        homepageTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
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

        addButton.addActionListener(new ActionListener() {
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
                        JOptionPane.showMessageDialog(null,
                                "Wrong Score Input!!!");
                        gameScr.setVisible(true);
                    } else {
                        gameScr.setVisible(false);
                        JOptionPane.showMessageDialog(null,
                                "Game ID exist!");
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
