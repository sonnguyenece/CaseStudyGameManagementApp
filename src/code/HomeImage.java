package code;

import java.util.ArrayList;
import java.util.List;

public class HomeImage {
    private List<String> homeImg;

    public HomeImage() {
        homeImg = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            homeImg.add("image/homeImage/homeImg" + i + ".jpg");
        }
    }

    public List<String> getHomeImg() {
        return homeImg;
    }

    public void setHomeImg(List<String> homeImg) {
        this.homeImg = homeImg;
    }

    public String selectionImage() {
        int selectIndex =(int) (Math.random() * 10);
        return this.homeImg.get(selectIndex);
    }
}
