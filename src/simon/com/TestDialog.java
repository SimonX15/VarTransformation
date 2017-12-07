package simon.com;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;

public class TestDialog extends JDialog {
    private JPanel contentPane;
    private JList jList;

    public TestDialog(ArrayList<String> dataList) {
        assignViews(dataList);
    }


    private void assignViews(ArrayList<String> dataList) {
        jList.setListData(dataList.toArray());

        setDefaultLookAndFeelDecorated(true);
        setContentPane(contentPane);
        setModal(true);

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

//                onCancel();
            }
        });


        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (e.getID()) {
                    case KeyEvent.VK_ENTER:
                        onCancel();
                }

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
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
        TestDialog dialog = new TestDialog(dataList);
        dialog.setSize(400, 400);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
