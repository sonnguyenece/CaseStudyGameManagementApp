package swing;

import code.CRUDList;
import code.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class FilterScreen extends JDialog {
    public static final int SCREEN_POS_X = 335;
    public static final int SCREEN_POS_Y = 57;
    private ArrayList genreList = new ArrayList();
    private JPanel mainPanel;
    private JCheckBox actionCheckBox;
    private JCheckBox advCheckbox;
    private JCheckBox casualCheckbox;
    private JCheckBox coopCheckbox;
    private JCheckBox fpsCheckBox;
    private JCheckBox horrorCheckBox;
    private JCheckBox strategyBox;
    private JCheckBox survivalBox;
    private JButton cancelButton;
    private ArrayList<Game> listGame;
    private ArrayList filterList;

    FilterScreen(Screen screen) {
        setTitle("ADD GAME");
        setLocation(SCREEN_POS_X, SCREEN_POS_Y);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setAlwaysOnTop(true);
        this.pack();
        listGame = screen.getCrudList().getGameList();
        screen.getFilterBut().setEnabled(false);
        eventListenerHandle(screen);
    }

    private void eventListenerHandle(Screen screen) {
        actionCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                actionCheckBox.setActionCommand("action");
                creatFilterArr(screen);
            }
        });
        advCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                advCheckbox.setActionCommand("adventure");
                creatFilterArr(screen);
            }
        });
        casualCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                casualCheckbox.setActionCommand("casual");
                creatFilterArr(screen);
            }
        });
        coopCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                coopCheckbox.setActionCommand("co-op");
                creatFilterArr(screen);
            }
        });
        fpsCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                fpsCheckBox.setActionCommand("fps");
                creatFilterArr(screen);
            }
        });
        horrorCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                horrorCheckBox.setActionCommand("horror");
                creatFilterArr(screen);
            }
        });
        strategyBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                strategyBox.setActionCommand("strategy");
                creatFilterArr(screen);
            }
        });
        survivalBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                survivalBox.setActionCommand("survival");
                creatFilterArr(screen);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                screen.cancelFilter();
            }
        });
    }

    public void creatFilterArr(Screen screen) {
        ArrayList<Game> filterList = new ArrayList();
        genreList.clear();
        filterList.clear();
        if (actionCheckBox.isSelected()) genreList.add("Action");
        if (advCheckbox.isSelected()) genreList.add("Adventure");
        if (casualCheckbox.isSelected()) genreList.add("Casual");
        if (coopCheckbox.isSelected()) genreList.add("Co-op");
        if (fpsCheckBox.isSelected()) genreList.add("Fps");
        if (horrorCheckBox.isSelected()) genreList.add("Horror");
        if (strategyBox.isSelected()) genreList.add("Strategy");
        if (survivalBox.isSelected()) genreList.add("Survival");
        if (!genreList.isEmpty()) {
            boolean checkFilter = true;
            for (Game game :
                    listGame) {
                if (game.getGenre() != null) {
                    for (int i = 0; i < genreList.size(); i++) {
                        String genre = (String) genreList.get(i);
                        if (game.getGenre().toLowerCase().contains(genre.toLowerCase())) {
                            checkFilter = true;
                        } else {
                            checkFilter = false;
                            break;
                        }
                    }
                    if (checkFilter) {
                        filterList.add(game);
                    }
                }
            }

            screen.getCrudList().setGameFilterList(filterList);

            screen.filterDisplay();
        }

    }

    public List getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList genreList) {
        this.genreList = genreList;

    }
}
