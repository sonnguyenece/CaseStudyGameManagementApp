package code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ListContextMain extends JFrame {
    DefaultListModel dm = new DefaultListModel();
    final JPopupMenu pop = new JPopupMenu();
    String row = "";
    private JList jList1;

    private void populate(String name) {
        dm.addElement(name);
        jList1.setModel(dm);
        addPopup();
    }

    private void addPopup() {
        JMenuItem add = new JMenuItem("Add");
        JMenuItem show = new JMenuItem("Show");
        JMenuItem edit = new JMenuItem("Edit");
        JMenuItem save = new JMenuItem("Save");

        pop.add(add);
        pop.add(show);
        pop.add(edit);
        pop.add(save);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Added " + row);
            }
        });
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Showed " + row);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Edited " + row);
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saved " + row);
            }
        });
    }

    private void Jlist1MouseClicked(java.awt.event.MouseEvent evt) {
        jList1.setSelectedIndex(jList1.locationToIndex(evt.getPoint()));
        if (SwingUtilities.isRightMouseButton(evt) && jList1.locationToIndex(evt.getPoint()) == jList1.getSelectedIndex()) {
            if (!jList1.isSelectionEmpty()) {
                pop.show(jList1, evt.getX(), evt.getY());
            }
        }
    }

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
        row = jList1.getSelectedValue().toString();
    }

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dm.clear();
        jList1.setModel(dm);
    }
}
