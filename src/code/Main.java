package code;

import javax.swing.*;

import swing.Screen;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Screen screen = new Screen();
        screen.setVisible(true);
        initAddGameList(screen);
        inputFileSave(screen);
        screen.autoClickHomeFirst();
    }

    public static void inputFileSave(Screen screen) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream("saveGameList.DAT");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Game> initGameList = (ArrayList<Game>) ois.readObject();
            if (initGameList != null && !initGameList.isEmpty()) {
                screen.getCrudList().setGameList(initGameList);
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't load fileSave!");
        }
    }

    public static void exportFileSave(Screen screen) {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("saveGameList.DAT");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(screen.getCrudList().getGameList());
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initAddGameList(Screen screen) {

        Game supertux2 = new Game("Supertux 2", false, "supertux2", "image/Game Datas/Supertux2/image/icon.jpg", "3");
        supertux2.setGameScore("5");

        Game left4Dead = new Game("Left4Dead 2", true, "550", "image/Game Datas/Left4Dead 2/image/icon.jpg", "5");
        left4Dead.setGameScore("8.6");

        Game dontStarve = new Game("Don't Starve ", true, "322330", "image/Game Datas/Don't Starve Together/image/icon.jpg", "7");
        dontStarve.setGameScore("8.1");



        screen.getCrudList().addGame(supertux2);

        screen.getCrudList().addGame(left4Dead);
        screen.getCrudList().addGame(dontStarve);
        screen.getCrudList().sortByName();
    }

    public static void addHalfLife2(Screen screen) {
        Game halfLife = new Game();
        halfLife.setName("Half Life 2");
        halfLife.setDescription("<html>Description: Half-Life 2 is a 2004 first-person shooter game developed and published by Valve." +
                "Like the original Half-Life, it combines shooting, puzzles, and storytelling, and adds features" +
                "such as vehicles and physics-based gameplay ... The game was created using Valve's Source engine," +
                "developed alongside."
                + "<br/> Play this game on Steam</html>");
        halfLife.setHeaderImage("image/Game Datas/HalfLife2/image/header.resized.png");
        halfLife.setDeveloper("Valve");
        halfLife.setGameScore("9.6");
        halfLife.setGameHomepage("http://orange.half-life2.com/");
        halfLife.setScreenShot("image/Game Datas/HalfLife2/image/screenshot4.jpg," +
                "image/Game Datas/HalfLife2/image/screenshot3.jpg," +
                "image/Game Datas/HalfLife2/image/screenshot2.jpg," +
                "image/Game Datas/HalfLife2/image/screenshot1.jpg,");
        halfLife.setGameGenre("Fps, Adventure");



        screen.getCrudList().addGame(halfLife);
    }


}
