package swing;

import code.CRUDList;
import code.Game;
import code.HomeImage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Screen extends JFrame {
    public static final int PLAYBUTTON_WIDTH = 180;
    public static final int PLAYBUTTON_HEIGHT = 50;
    private JPanel mainPanel;
    private JPanel homeSpecifyPanel;
    private JButton homeButton;
    private JLabel searchIcon;
    private JButton searchButton;
    private JTextField searchTextField;
    private JButton addButton;
    private JLabel quantityGames;
    private JList gameSmallList;
    private JList gameSearchList;
    private JPanel headerImg;
    private JLabel header;
    private JLabel description;
    private JButton playButton;
    private JPanel specifyJpanel;
    private JLabel lastPlayed;
    private JLabel playTime;
    private JLabel gameScore;
    private JLabel developer;
    private JLabel homepage;
    private JLabel screenshot1;
    private JLabel screenshot2;
    private JLabel screenshot3;
    private JLabel screenshot4;
    private JPanel headerPanel;
    private JPanel infoPanel;
    private JPanel timePlayPanel;
    private JLabel screenshotLabel;
    private JScrollPane specifyJScrollPane;
    private Object specifyJScrollPaneTemp;
    private DefaultListModel defaultListGameModel;
    private DefaultListModel defaultListGameSearchModel;
    public CRUDList crudList;
    private Game selectedGame;
    private boolean isPressHomeButton;
    private HomeImage homeImage;


    public Screen() {
        super("GAME MANAGEMENT");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        specifyJpanel.setVisible(false);
        isPressHomeButton = false;
        defaultListGameModel = new DefaultListModel<>();
        gameSmallList.setModel(defaultListGameModel);
        gameSmallList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int gameIndex = gameSmallList.getSelectedIndex();
                if (gameIndex >= 0) {
                    isPressHomeButton = false;
                    specifyJpanel.setVisible(true);
                    specifyJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

                    selectedGame = crudList.getGameList().get(gameIndex);

                    headerPanel.setPreferredSize(new Dimension(1100, 400));
                    header.setIcon(new ImageIcon(selectedGame.getHeaderImage()));
                    infoPanel.setPreferredSize(new Dimension(10, 10));
                    timePlayPanel.setPreferredSize(new Dimension(50, 50));
                    playButton.setPreferredSize(new Dimension(PLAYBUTTON_WIDTH, PLAYBUTTON_HEIGHT));
                    playButton.setIcon(new ImageIcon("image/playIcon.png"));
                    lastPlayed.setText("Last Played : " + selectedGame.getLastPlayed() + "        ");
                    lastPlayed.setFont(new Font("Arial", Font.ITALIC, 20));
                    playTime.setText("Play Time :" + " Hours            ");
                    playTime.setFont(new Font("Arial", Font.ITALIC, 20));
                    gameScore.setText("             Game Score : " + selectedGame.getGameScore());
                    gameScore.setFont(new Font("Arial", Font.PLAIN, 20));
                    developer.setText("Developer: " + selectedGame.getDeveloper() + "       ");
                    developer.setFont(new Font("Arial", Font.PLAIN, 20));
                    homepage.setText("Homepage: " + selectedGame.getGameHomepage());
                    homepage.setFont(new Font("Arial", Font.PLAIN, 20));

                    description.setText(selectedGame.getDescription());
                    description.setFont(new Font("Consolas", Font.ITALIC, 20));
                    description.setForeground(Color.WHITE);

                    screenshotLabel.setText("SCREENSHOTS");
                    screenshotLabel.setFont(new Font("Arial", Font.BOLD, 25));
                    screenshotLabel.setForeground(Color.ORANGE);

                    screenshot1.setIcon(new ImageIcon(selectedGame.getScreenShot().get(0)));
                    screenshot2.setIcon(new ImageIcon(selectedGame.getScreenShot().get(1)));
                    screenshot3.setIcon(new ImageIcon(selectedGame.getScreenShot().get(2)));
                    screenshot4.setIcon(new ImageIcon(selectedGame.getScreenShot().get(3)));
                }

            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPressHomeButton = true;
                homeImage = new HomeImage();
                header.setIcon(new ImageIcon(homeImage.selectionImage()));

                specifyJpanel.setVisible(true);

                specifyJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                searchTextField.setText("");
                refreshGameList();
            }
        });

        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String searchText = searchTextField.getText();
                crudList.search(searchText);
                refreshGameList();
            }
        });


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGame.playGame();
                selectedGame.toStringLastPlay();

            }
        });

    }

    private void createUIComponents() {
        searchIcon = new JLabel(new ImageIcon("image/searchIcon.png"));
        searchButton = new JButton(new ImageIcon("image/filter.png"));
        homeButton = new JButton(new ImageIcon("image/homeIcon.png"));
        addButton = new JButton(new ImageIcon("image/addIcon.png"));
    }

    public CRUDList getCrudList() {
        return crudList;
    }

    public void setCrudList(CRUDList crudList) {
        this.crudList = crudList;
    }

    public boolean isPressHomeButton() {
        return isPressHomeButton;
    }

    public void setPressHomeButton(boolean pressHomeButton) {
        isPressHomeButton = pressHomeButton;
    }

    public void refreshGameList() {
        defaultListGameModel.removeAllElements();
        defaultListGameModel.clear();
        if (searchTextField.getText().equals("")) {
            for (Game g : crudList.getGameList()) {
                defaultListGameModel.addElement(new ImgsNText(g.getName(), new ImageIcon(g.getIconPath())));
            }
        } else {
            for (Game g : crudList.getGameSearchList()) {
                defaultListGameModel.addElement(new ImgsNText(g.getName(), new ImageIcon(g.getIconPath())));
            }
        }
        gameSmallList.setCellRenderer(new Renderer());
        gameSmallList.setModel(defaultListGameModel);
        quantityGames.setText("GAMES (" + crudList.getGameList().size() + ")");

//        if (this.isPressHomeButton()) {
//            long startTime = System.currentTimeMillis() / 1000;
//            long endTime = startTime + 3;
//            while (this.isPressHomeButton()) {
//                if (startTime < endTime)
//                    startTime = System.currentTimeMillis() / 1000;
//                else {
//                    header.setIcon(new ImageIcon(homeImage.selectionImage()));
//                    endTime = System.currentTimeMillis() / 1000 + 3;
//                }
//            }
//        }

    }

    public void autoClickHomeFirst() {
        homeButton.doClick();
    }

}
