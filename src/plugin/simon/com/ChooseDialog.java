package plugin.simon.com;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;

public class ChooseDialog extends JDialog {
    private JPanel contentPane;
    private JList jList;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox replaceAll;

    private ChooseListener chooseListener;

    public ChooseDialog(ArrayList<String> dataList) {
        assignViews(dataList);
    }


    private void assignViews(ArrayList<String> dataList) {
        setTitle("变量转换");
        setSize(400, 400);
        jList.setListData(dataList.toArray());
        replaceAll.setVisible(false);

        setDefaultLookAndFeelDecorated(true);
        setContentPane(contentPane);
        setModal(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                System.out.println(e.toString());
//                onCancel();
            }
        });

        jList.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_ENTER:
                        onOK();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // call onCancel() on ESCAPE
//        contentPane.registerKeyboardAction(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String value = jList.getSelectedValue().toString();
        if (value != null && !value.isEmpty() && chooseListener != null) {
            chooseListener.onChoose(value, replaceAll.isSelected());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("测试用1");
        dataList.add("测试用2");
        dataList.add("测试用3");
        dataList.add("测试用4");
        dataList.add("测试用5");
        ChooseDialog dialog = new ChooseDialog(dataList);
        dialog.setSize(400, 400);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


    public void setChooseListener(ChooseListener chooseListener) {
        this.chooseListener = chooseListener;
    }

    public interface ChooseListener {
        void onChoose(String words, boolean isReplaceAll);
    }
}
