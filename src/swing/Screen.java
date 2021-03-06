package swing;

import code.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Screen extends JFrame {
    public static final int PLAYBUTTON_WIDTH = 180;
    public static final int PLAYBUTTON_HEIGHT = 50;
    public static final int HOME_IMG_DELAY = 5000;
    public static final int SEC_COUNTER = 1000;
    private JPanel mainPanel;
    private JPanel homeSpecifyPanel;
    private JButton homeButton;
    private JLabel searchIcon;
    private JButton searchButton;
    private JTextField searchTextField;
    private JButton addButton;
    private JLabel quantityGames;
    private JList displayGameList;
    private JList gameSearchList;
    private JPanel headerImg;
    private JLabel header;
    private JLabel description;
    private JButton playButton;
    private JPanel specifyJpanel;
    private JLabel lastPlayed;
    private JLabel Genre;
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
    private JButton filterBut;
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
    private JLabel date;
    private JLabel hour;
    private Object specifyJScrollPaneTemp;
    private DefaultListModel defaultListGameModel;
    private DefaultListModel defaultListGameSearchModel;
    private CRUDList crudList;
    private Game selectedGame;
    private boolean isPressHomeButton;
    private HomeImage homeImage;
    private final JPopupMenu popupMenu = new JPopupMenu();
    private final JMenuItem play = new JMenuItem("Play");
    private final JMenuItem edit = new JMenuItem("Edit");
    private final JMenuItem delete = new JMenuItem("Delete");
    private final JMenuItem showInfo = new JMenuItem("Show");
    private int gameIndex;
    private boolean isSearching;
    private EditGameScreen editScreen;
    private Timer showCurTime;
    private Timer homeIMGTimer;
    private boolean isFilter;

    public Screen() {
        super("GAMELIST MANAGER");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        Screen scn = this;
        isFilter = false;
        isSearching = false;
        crudList = new CRUDList();
        defaultListGameModel = new DefaultListModel<>();
        displayGameList.setModel(defaultListGameModel);
        specifyJpanel.setVisible(false);
        displayTime();

        eventListenerHandle(scn);

        filterBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FilterScreen ft = new FilterScreen(scn);
                ft.setVisible(true);
            }
        });
        homeButton.addKeyListener(new KeyAdapter() {
        });
    }

    public void displayTime() {
        showCurTime = new Timer(SEC_COUNTER, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTime();
            }
        });
        showCurTime.start();
        homeIMGTimer = new Timer(HOME_IMG_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeImage = new HomeImage();
                header.setIcon(new ImageIcon(homeImage.selectionImage()));
                displayRightScreen(false);
                specifyJpanel.setVisible(true);
            }
        });
        homeIMGTimer.start();
    }

    public void eventListenerHandle(Screen scn) {
        displayGameList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                homeIMGTimer.stop();
                if (!displayGameList.isSelectionEmpty()) {
                    Color selectionColor = new Color(147, 147, 147);
                    displayGameList.setSelectionBackground(selectionColor);
                    displayGameList.setSelectionForeground(Color.WHITE);

                    isPressHomeButton = false;
                    displayRightScreen(true);
                    gameIndex = displayGameList.getSelectedIndex();
                    if (!isSearching) {
                        try {
                            selectedGame = crudList.getGameList().get(gameIndex);
                        } catch (Exception ex) {
                            System.out.println("So many click in gameList !!");
                        }
                    } else {
                        try {
                            selectedGame = crudList.getGameSearchList().get(gameIndex);
                        } catch (Exception exce) {
                            System.out.println(exce.getMessage());
                        }
                    }
                    displayGameInfo();
                }
            }
        });

        displayGameList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)
                        && (crudList.getQuantityGames() > 0)) {
                    if (!isSearching) {
                        gameIndex = displayGameList.locationToIndex(e.getPoint());
                        selectedGame = crudList.getGameList().get(gameIndex);
                    } else {
                        gameIndex = displayGameList.locationToIndex(e.getPoint());
                        selectedGame = crudList.getGameSearchList().get(gameIndex);
                    }
                    popUpDisplay(e);
                }
            }
        });
        popUpListener(scn);
        //scn da dc khai bao o tren. tai sao neu tao ham popUpListenner ko tham so
        //  thi o trong method popUPListener() gia tri scn =null ?
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPressHomeButton = true;
                homeIMGTimer.restart();
                homeImage = new HomeImage();
                header.setIcon(new ImageIcon(homeImage.selectionImage()));
                displayRightScreen(false);
                specifyJpanel.setVisible(true);
                searchTextField.setText("");
                refreshGameList();
                crudList.sortByName();
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

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedGame.playGame();
                selectedGame.toStringLastPlay();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Color color = new Color(84, 3, 128, 168);
                playButton.setBackground(color);
                playButton.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Color color = new Color(9, 132, 9);
                playButton.setBackground(color);
                playButton.setForeground(Color.WHITE);
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
        quantityGames.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                searchTextField.setText("");
                refreshGameList();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGameScreen addScreen =
                        new AddGameScreen(scn, rootPaneCheckingEnabled);
                addScreen.setAlwaysOnTop(true);
                addScreen.setVisible(true);
            }
        });

        homepage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                homepage.setForeground(Color.red);
                Desktop desktop = Desktop.getDesktop();
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

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                homepage.setForeground(Color.getHSBColor(35, 4, 36));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                homepage.setForeground(Color.white);
            }
        });

        displayGameList.addMouseListener(new MouseAdapter() {
        });
    }

    public void popUpDisplay(MouseEvent e) {
        popupMenu.add(play);
//        popupMenu.add(showInfo);
        popupMenu.add(edit);
        popupMenu.add(delete);
        popupMenu.show(displayGameList, e.getX(), e.getY());

    }

    public void displayGameInfo() {
        headerPanel.setPreferredSize(new Dimension(1100, 400));
        header.setIcon(new ImageIcon(selectedGame.getHeaderImage()));
        infoPanel.setPreferredSize(new Dimension(10, 10));
        timePlayPanel.setPreferredSize(new Dimension(50, 50));
        playButton.setPreferredSize(new Dimension(PLAYBUTTON_WIDTH, PLAYBUTTON_HEIGHT));
        playButton.setIcon(new ImageIcon("image/playIcon.png"));
        lastPlayed.setText("Last Played : " + selectedGame.getLastPlayed() + "        ");
        lastPlayed.setFont(new Font("Arial", Font.PLAIN, 17));
        Genre.setText("Genre: " + selectedGame.getGenre());
        Genre.setFont(new Font("Arial", Font.PLAIN, 17));
        gameScore.setText("             Game Score : " + selectedGame.getGameScore());
        gameScore.setFont(new Font("Arial", Font.PLAIN, 17));
        developer.setText("Developer: " + selectedGame.getDeveloper() + "       ");
        developer.setFont(new Font("Arial", Font.PLAIN, 17));
        homepage.setText("Homepage: " + selectedGame.getGameHomepage());
        homepage.setFont(new Font("Arial", Font.PLAIN, 17));
        homepage.setForeground(Color.white);
        description.setText(selectedGame.getDescription());
        description.setFont(new Font("Consolas", Font.PLAIN, 20));
        description.setForeground(Color.WHITE);
        screenshotLabel.setText("SCREENSHOTS");
        screenshotLabel.setFont(new Font("Arial", Font.BOLD, 25));
        screenshotLabel.setForeground(Color.ORANGE);
        ArrayList<String> tempScreenShot = selectedGame.getScreenShot();
        int lastIndexScr = tempScreenShot.size() - 1;
        screenshot1.setIcon(new ImageIcon(tempScreenShot.get(lastIndexScr)));
        screenshot2.setIcon(new ImageIcon(selectedGame.getScreenShot().get(lastIndexScr - 1)));
        screenshot3.setIcon(new ImageIcon(selectedGame.getScreenShot().get(lastIndexScr - 2)));
        screenshot4.setIcon(new ImageIcon(selectedGame.getScreenShot().get(lastIndexScr - 3)));
    }

    public boolean isSearching() {
        return isSearching;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public CRUDList getCrudList() {
        return crudList;
    }

    public JButton getFilterBut() {
        return filterBut;
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
        if (!isFilter) {
            if (searchTextField.getText().equals("")) isSearching = false;
            else isSearching = true;
        }
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
        displayGameList.setCellRenderer(new Renderer());
        displayGameList.setModel(defaultListGameModel);
        quantityGames.setText("ALL GAMES (" + crudList.getGameList().size() + ")");

//        if (EditGameScreen.isSaved) {
//            crudList.addGame(EditGameScreen.game);
//            EditGameScreen.isSaved = false;
//            refreshGameList();
//        }
        Main.exportFileSave(this);
    }

    public void showTime() {
        long currentTimeMs = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hourFormat = new SimpleDateFormat("hh:mm:ss");
        Date resultDate = new Date(currentTimeMs);
        date.setText(dateFormat.format(resultDate));
        hour.setText(hourFormat.format(resultDate));
        date.setFont(new Font("Consolas", Font.PLAIN, 15));
        hour.setFont(new Font("Consolas", Font.PLAIN, 15));
    }

    public void autoClickHomeFirst() {
        isPressHomeButton = true;
        homeButton.doClick();
    }

    public void popUpListener(Screen scn) {
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGame.playGame();
                selectedGame.toStringLastPlay();
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog(mainPanel,
                        "Do you want to edit " + selectedGame.getName() + " ?",
                        "EDIT GAME", JOptionPane.WARNING_MESSAGE);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (!isSearching) {
                        Game editGame = crudList.getGameList().get(gameIndex);
                        editScreen = new EditGameScreen(scn, rootPaneCheckingEnabled,
                                editGame, gameIndex);
                        editScreen.setAlwaysOnTop(true);
                        editScreen.setVisible(true);
                        homeButton.doClick();
                    } else {
                        Game editGame = crudList.getGameSearchList().get(gameIndex);
                        editScreen = new EditGameScreen(scn, rootPaneCheckingEnabled,
                                editGame, gameIndex);
                        editScreen.setAlwaysOnTop(true);
                        editScreen.setVisible(true);
                    }

                }
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

//        showInfo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Showed ");
//            }
//        });
    }

    public void filterDisplay() {
        isFilter = true;
        searchTextField.setEnabled(false);
        sortMenu.setEnabled(false);
        crudList.filterToSearch();
        isSearching = true;
        refreshGameList();
    }

    public void cancelFilter() {
        filterBut.setEnabled(true);
        searchTextField.setEnabled(true);
        sortMenu.setEnabled(true);
        isFilter = false;
    }


    /*------------------------------Maybe use later------------------------------*/
    public int getGameIndex() {
        return gameIndex;
    }

    public void setFilterBut(JButton filterBut) {
        this.filterBut = filterBut;
    }

    public void setGameIndex(int gameIndex) {
        this.gameIndex = gameIndex;
    }

    public void setCrudList(CRUDList crudList) {
        this.crudList = crudList;
    }
}

