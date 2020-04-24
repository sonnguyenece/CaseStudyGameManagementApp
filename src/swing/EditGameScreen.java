package swing;

import code.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditGameScreen extends JDialog {
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
    public static Game game;
    public static boolean isSaved;
    private final ButtonGroup radioGroup = new ButtonGroup();
    private Screen screen;
    public Game editGame;

    EditGameScreen(Frame parent, boolean modal, Game editGame) {
        setTitle("ADD GAME");
//        getPreferredSize();
        setSize(800, 700);
        setLocation(280, 50);
        this.setContentPane(mainPanel);
        this.pack();
        initInitialVar();
        screen = (Screen) parent;
        radioGroup.add(yesRadioButton);
        radioGroup.add(noRadioButton);
        EditGameScreen gameScr = this;
        if (editGame != null) {
            this.editGame = editGame;
        }
        nameTextField.setText(this.editGame.getName());
        gameIDTextField.setText(this.editGame.getGameID());

        if (this.editGame.isSteamGame()) {
            yesRadioButton.setSelected(true);
//            isSteamGame = true;
        } else {
            yesRadioButton.setSelected(true);
        }
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
                game.setSteamGame(false);
            }
        });
        noRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                game.setSteamGame(false);
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

        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSaved = false;
                dispose();
            }
        });

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameTextField.equals("") || gameIDTextField.equals("")) {
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
                    gameScr.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Edited");
                    gameScr.setVisible(true);
                    dispose();
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
        isSaved = false;
    }


}
