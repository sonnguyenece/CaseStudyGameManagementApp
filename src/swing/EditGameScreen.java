package swing;

import code.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditGameScreen extends JDialog {
    public static final int SCREEN_POS_X = 335;
    public static final int SCREEN_POS_Y = 57;
    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField gameIDTextField;
    private JTextField scoreTextField;
    private JTextField genreTextField;
    private JButton saveButton;
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
    private JButton cancelButton;

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
    public static Game game;
    public static boolean isSaved;
    private final ButtonGroup radioGroup = new ButtonGroup();
    private Screen screen;
    private Game editGame;
    private int gameIndex;

    EditGameScreen(Frame parent, boolean modal, Game editGame, int gameEditIndex) {
        setTitle("EDIT GAME");
        setLocation(SCREEN_POS_X, SCREEN_POS_Y);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        screen = (Screen) parent;
        if (screen.isSearching()) {
            for (int i = 0; i < screen.getCrudList().getGameList().size(); i++) {
                if (screen.getCrudList().getGameList().get(i).equals(editGame)) {
                    gameEditIndex = i;
                    break;
                }
            }
        }

        initInitialVar(gameEditIndex);

        EditGameScreen gameScr = this;
        if (editGame != null) {
            this.editGame = editGame;
        }
        nameTextField.setText(this.editGame.getName());
        gameIDTextField.setText(this.editGame.getGameID());
        screen.setEnabled(false);
        displayExistGameInfo();
        eventListenerHandle(gameScr);
    }

    private void eventListenerHandle(EditGameScreen gameScr) {
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
                yesRadioButton.setActionCommand("Yes");
                isSteamGame = true;
//                game.setSteamGame(false);
            }
        });
        noRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
//                game.setSteamGame(false);
                isSteamGame = false;
                yesRadioButton.setActionCommand("No");
            }
        });
        scoreTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

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

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSaved = false;
                dispose();
                screen.setEnabled(true);

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String steamGameSelected = yesRadioButton.getActionCommand();
                if (nameTextField.getText().equals("")
                        || gameIDTextField.getText().equals("")
                        || steamGameSelected.equals("not selected")) {
                    gameScr.setVisible(false);
                    JOptionPane.showMessageDialog(null,
                            "Wrong Input!", "Warning",
                            JOptionPane.PLAIN_MESSAGE);
                    gameScr.setVisible(true);
                } else {
                    isSaved = true;
                    game = new Game(nameTextField.getText(), developerTextField.getText(),
                            iconPathText.getText(), screenshotsPath.getText(),
                            descriptionTextField.getText(), gameIDTextField.getText(),
                            headerImgPath.getText(), languagesTextFiled.getText(),
                            genreTextField.getText(), scoreTextField.getText(),
                            homepageTextField.getText(), isSteamGame);
                    boolean gameEdited = screen.getCrudList().checkBeforeSave(game, gameIndex);
                    if (gameEdited) {
                        screen.getCrudList().deleteGame(gameIndex);
                        screen.getCrudList().sortByName();
                        gameScr.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Saved");
                        gameScr.setVisible(true);
                        screen.getSearchTextField().setText("");
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

    private void displayExistGameInfo() {
        scoreTextField.setText(this.editGame.getGameScore());
        sizeOnDiskTextField.setText(this.editGame.getGameSize());
        iconPathText.setText(this.editGame.getIconPath());
        headerImgPath.setText(this.editGame.getHeaderImage());
        developerTextField.setText(this.editGame.getDeveloper());
        homepageTextField.setText(this.editGame.getGameHomepage());
        descriptionTextField.setText(this.editGame.getDescription());
        languagesTextFiled.setText(this.editGame.getStringLanguage());
        genreTextField.setText(this.editGame.getGenre());
        screenshotsPath.setText(this.editGame.getStringScreenshots());
        radioGroup.add(yesRadioButton);
        radioGroup.add(noRadioButton);
        if (this.editGame.isSteamGame()) {
            yesRadioButton.setSelected(true);
            noRadioButton.setSelected(false);
            isSteamGame = true;
        } else {
            yesRadioButton.setSelected(false);
            noRadioButton.setSelected(true);
            isSteamGame = false;
        }
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void initInitialVar(int gameEditIndex) {
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
        isSaved = false;
        this.gameIndex = gameEditIndex;
    }

}
