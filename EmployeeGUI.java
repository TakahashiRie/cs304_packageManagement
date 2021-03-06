import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class EmployeeGUI extends JFrame {
    private JPanel MainP;
    private JButton divisionButton;
    private JPanel NorthP;
    private JButton showAllDataButton;
    private JTable table1;
    private JButton maxPriceButton;
    private JPanel SouthP;
    private JComboBox comboBox1;
    private JButton sumPriceButton;
    private JButton backToLoginButton;
    protected CreateOrder temp = new CreateOrder();
    protected Employee func = new Employee();

    public EmployeeGUI() {
        setContentPane(MainP);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1500, 500);
        setVisible(true);
        showAllDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Order> orderslist;
                    orderslist = EmployeeGUI.this.temp.getAllOrders();
                    OrderTableModel model = new OrderTableModel(orderslist);
                    table1.setModel(model);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }
            }
        });
        divisionButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<CompanyAddress> companyAddresses = EmployeeGUI.this.func.divisionAddr();
                    Map<Long, Company> companyMap = EmployeeGUI.this.func.divisionComp();
                    CompanyTableModel cModel = new CompanyTableModel(companyMap, companyAddresses);
                    table1.setModel(cModel);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }
            }
        }));
        maxPriceButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = func.maxPrice();
                    if (s != "") {
                        JOptionPane.showMessageDialog(null, s);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }
            }
        }));

        sumPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String select = comboBox1.getSelectedItem().toString();
                    if (select == "Delivery Company") {
                        select = "companyID";
                    } else if (select == "Customer ID") {
                        select = "customerID";
                    }
                    String s = temp.groupBySum(select);
                    if (s != "") {
                        JOptionPane.showMessageDialog(null, s);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }
            }
        });
        backToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI.main(null);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeGUI frame = new EmployeeGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainP = new JPanel();
        MainP.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        NorthP = new JPanel();
        NorthP.setLayout(new GridLayoutManager(2, 7, new Insets(0, 0, 0, 0), -1, -1));
        MainP.add(NorthP, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        divisionButton = new JButton();
        divisionButton.setText("Most popular company");
        NorthP.add(divisionButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        NorthP.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        showAllDataButton = new JButton();
        showAllDataButton.setText("Show all data");
        NorthP.add(showAllDataButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxPriceButton = new JButton();
        maxPriceButton.setText("Max price");
        NorthP.add(maxPriceButton, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Group by");
        NorthP.add(label1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Delivery Company");
        defaultComboBoxModel1.addElement("Customer ID");
        comboBox1.setModel(defaultComboBoxModel1);
        NorthP.add(comboBox1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sumPriceButton = new JButton();
        sumPriceButton.setText("Sum price");
        NorthP.add(sumPriceButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backToLoginButton = new JButton();
        backToLoginButton.setText("Back to Login");
        NorthP.add(backToLoginButton, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SouthP = new JPanel();
        SouthP.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainP.add(SouthP, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        SouthP.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane1.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainP;
    }
}
