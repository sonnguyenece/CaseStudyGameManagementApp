package swing;

import code.CRUDList;
import code.Game;
import code.HomeImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

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
    private JMenu sortMenu;
    private JMenu filterMenu;
    private JMenuItem sortFromLow;
    private JMenuItem sortFromHigh;
    private JMenuItem sortz_a;
    private JMenuItem sorta_z;
    private JPanel descriptionPanel;
    private JPanel screenshotText;
    private JPanel screenshotField;
    private JPanel spaceField;
    private JPanel screenshotTextField;
    private JPanel spaceField2;
    private JMenuBar menuBar;
    //    private JButton sortButton;
    private Object specifyJScrollPaneTemp;
    private DefaultListModel defaultListGameModel;
    private DefaultListModel defaultListGameSearchModel;
    private CRUDList crudList;
    private Game selectedGame;
    private boolean isPressHomeButton;
    private HomeImage homeImage;
    final JPopupMenu popupMenu = new JPopupMenu();
    private final JMenuItem play = new JMenuItem("Play");
    private final JMenuItem showInfo = new JMenuItem("Show");
    private final JMenuItem edit = new JMenuItem("Edit");
    private final JMenuItem delete = new JMenuItem("Delete");
    private int gameIndex;
    private boolean isSearching;

    public Screen() {
        super("GAME MANAGEMENT");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        isSearching = false;
        crudList = new CRUDList();
        defaultListGameModel = new DefaultListModel<>();
        specifyJpanel.setVisible(false);
        isPressHomeButton = false;

        gameSmallList.setModel(defaultListGameModel);

        gameSmallList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me)    // if right mouse button clicked
                        && !gameSmallList.isSelectionEmpty()            // and list selection is not empty
                        && gameSmallList.locationToIndex(me.getPoint()) // and clicked point is
                        == gameSmallList.getSelectedIndex()) {       //   inside selected item bounds
                    popupMenu.show(gameSmallList, me.getX(), me.getY());
                }

                if (gameIndex >= 0) {

                    isPressHomeButton = false;
                    displayRightScreen(true);
                    gameIndex = gameSmallList.getSelectedIndex();
                    if (!isSearching) {
                        selectedGame = crudList.getGameList().get(gameIndex);
                    } else {
//                        gameIndex = gameSearchList.getSelectedIndex();
                        selectedGame = crudList.getGameSearchList().get(gameIndex);
                    }

                    headerPanel.setPreferredSize(new Dimension(1100, 400));
                    header.setIcon(new ImageIcon(selectedGame.getHeaderImage()));
                    infoPanel.setPreferredSize(new Dimension(10, 10));
                    timePlayPanel.setPreferredSize(new Dimension(50, 50));
                    playButton.setPreferredSize(new Dimension(PLAYBUTTON_WIDTH, PLAYBUTTON_HEIGHT));
                    playButton.setIcon(new ImageIcon("image/playIcon.png"));
                    lastPlayed.setText("Last Played : " + selectedGame.getLastPlayed() + "        ");
                    lastPlayed.setFont(new Font("Arial", Font.PLAIN, 20));
                    playTime.setText("Play Time :" + " Hours            ");
                    playTime.setFont(new Font("Arial", Font.PLAIN, 20));
                    gameScore.setText("             Game Score : " + selectedGame.getGameScore());
                    gameScore.setFont(new Font("Arial", Font.PLAIN, 20));
                    developer.setText("Developer: " + selectedGame.getDeveloper() + "       ");
                    developer.setFont(new Font("Arial", Font.PLAIN, 20));
                    homepage.setText("Homepage: " + selectedGame.getGameHomepage());
                    homepage.setFont(new Font("Roboto Light Italic", Font.PLAIN, 20));
                    description.setText(selectedGame.getDescription());
                    description.setFont(new Font("Consolas", Font.PLAIN, 20));
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

        popUpListener();

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isPressHomeButton = true;
                homeImage = new HomeImage();
                header.setIcon(new ImageIcon(homeImage.selectionImage()));

                displayRightScreen(false);

                specifyJpanel.setVisible(true);

//                specifyJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

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
        sorta_z.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crudList.sortByName();
                refreshGameList();
            }
        });
        sortz_a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crudList.sortByRevertName();
                refreshGameList();
            }
        });
        sortFromHigh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crudList.sortFromHighestScore();
                refreshGameList();
            }
        });
        sortFromLow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crudList.sortFromLowestScore();
                refreshGameList();
            }
        });
        gameSmallList.addMouseListener(new MouseAdapter() {
        });
        quantityGames.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                searchTextField.setText("");
                refreshGameList();
            }
        });
        homepage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Desktop desktop = java.awt.Desktop.getDesktop();
                try {
                    URI defaultURL = new URI("https://www.google.com/");
                    URI homepageURL = new URI(selectedGame.getGameHomepage());
                    desktop.browse(homepageURL);
                } catch (Exception ex) {
                    System.out.println("wrong URL");
                    JOptionPane.showMessageDialog(null,
                            "Can not access this URL");
                }
            }
        });

        Screen scn = this;

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGameScreen addScreen = new AddGameScreen(scn, rootPaneCheckingEnabled);
                addScreen.setVisible(true);
                addScreen.setAlwaysOnTop(true);
                scn.setEnabled(false);
            }
        });
    }

    public CRUDList getCrudList() {
        return crudList;
    }

    public int getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
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

    public void displayRightScreen(Boolean b) {
        spaceField.setVisible(b);
        descriptionPanel.setVisible(b);
        infoPanel.setVisible(b);
        screenshotTextField.setVisible(b);
        screenshotField.setVisible(b);
        spaceField2.setVisible(b);
    }

    public void createUIComponents() {
        searchIcon = new JLabel(new ImageIcon("image/searchIcon.png"));
        searchButton = new JButton(new ImageIcon("image/filterMenu.png"));
        homeButton = new JButton(new ImageIcon("image/homeIcon.png"));
        addButton = new JButton(new ImageIcon("image/addIcon.png"));
    }

    public void refreshGameList() {
        if (searchTextField.getText().equals("")) isSearching = false;
        else isSearching = true;
        defaultListGameModel.removeAllElements();
        defaultListGameModel.clear();
        if (!isSearching) {
            sortMenu.setEnabled(true);
            for (Game g : crudList.getGameList()) {
                defaultListGameModel.addElement(new ImgsNText(g.getName(), new ImageIcon(g.getIconPath())));
            }
        } else {
            sortMenu.setEnabled(false);
            for (Game g : crudList.getGameSearchList()) {
                defaultListGameModel.addElement(new ImgsNText(g.getName(), new ImageIcon(g.getIconPath())));
            }
        }
        gameSmallList.setCellRenderer(new Renderer());
        gameSmallList.setModel(defaultListGameModel);
        quantityGames.setText("ALL GAMES (" + crudList.getGameList().size() + ")");

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

    private void popUpListener() {

        popupMenu.add(play);
        popupMenu.add(showInfo);
        popupMenu.add(edit);
        popupMenu.add(delete);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Preparing to launch " + selectedGame.getName() + "... ");
                selectedGame.playGame();
                selectedGame.toStringLastPlay();
            }
        });
        showInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Showed ");
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Edited ");
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int dialogResult = JOptionPane.showConfirmDialog(mainPanel,
                        "Do you really want to remove " + selectedGame.getName() + " ?",
                        "DELETE GAME", JOptionPane.WARNING_MESSAGE);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (!isSearching) {
                        crudList.deleteGame(gameIndex);
                    } else {
                        crudList.deleteSearchGame(gameIndex);
                    }
                    JOptionPane.showMessageDialog(null, "Deleted!");
                    homeButton.doClick();
                }
            }
        });
    }

    private void gameSmallListMouseClicked(java.awt.event.MouseEvent evt) {
        gameSmallList.setSelectedIndex(gameSmallList.locationToIndex(evt.getPoint()));
        if (SwingUtilities.isRightMouseButton(evt)
                && gameSmallList.locationToIndex(evt.getPoint())
                == gameSmallList.getSelectedIndex()) {
            if (!gameSmallList.isSelectionEmpty()) {
                popupMenu.show(gameSmallList, evt.getX(), evt.getY());
            }
        }
    }

}

