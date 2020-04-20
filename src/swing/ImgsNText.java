package swing;

import javax.swing.*;

public class ImgsNText {
    private String name;
    private Icon img;

    public ImgsNText(String name, Icon img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getImg() {
        return img;
    }

    public void setImg(Icon img) {
        this.img = img;
    }
}
