package swing;

import javax.swing.*;
import java.awt.*;

public class Renderer extends DefaultListCellRenderer implements ListCellRenderer<Object> {
    @Override
    public Component getListCellRendererComponent(JList<?> jList, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        ImgsNText is = (ImgsNText) value;
        setText(is.getName());
        setIcon(is.getImg());

        if (isSelected) {
            setBackground(jList.getSelectionBackground());
            setForeground(jList.getSelectionForeground());
        } else {
            setBackground(jList.getBackground());
            setForeground(jList.getForeground());
        }
        setEnabled(true);
        setFont(jList.getFont());
        return this;
    }
}
