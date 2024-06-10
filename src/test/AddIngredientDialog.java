package test;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;

public class AddIngredientDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    // 필드
    private JComboBox<String> ingredientCB;
    private JTextField nameTF;
    private JTextField DDayTF;


    private String classification[] = {"식재료를 분류해주세요", "곡물", "채소", "육류", "수산물", "조미료", "향신료", "과일", "가공식품", "기호식품"};
    //	JTextField buyDateTF; // 구매일자
    //	JTextField expirationDateTF; // 유통기한

    /**
     * Create the dialog.
     */
    public AddIngredientDialog(JFrame frame, String title) {
        super(frame, title, true);

        setBounds(100, 100, 597, 582);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                nameTF.setText("");
                DDayTF.setText("");
                setVisible(false);
            }
        });

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] {100, 300};
        gbl_contentPanel.rowHeights = new int[] {0, 50, 50, 50};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        contentPanel.setLayout(gbl_contentPanel);


        {
            JLabel lblNewLabel_1 = new JLabel("식재료 분류");
            lblNewLabel_1.setFont(new Font("굴림체", Font.BOLD, 20));
            GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
            gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
            gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
            gbc_lblNewLabel.gridx = 0;
            gbc_lblNewLabel.gridy = 1;
            contentPanel.add(lblNewLabel_1, gbc_lblNewLabel);
        }
        {
            ingredientCB = new JComboBox(classification);
            GridBagConstraints gbc_comboBox = new GridBagConstraints();
            gbc_comboBox.insets = new Insets(0, 0, 5, 0);
            gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBox.gridx = 1;
            gbc_comboBox.gridy = 1;
            contentPanel.add(ingredientCB, gbc_comboBox);
        }
        {
            JLabel lblNewLabel_2 = new JLabel("식재료 이름");
            lblNewLabel_2.setFont(new Font("굴림체", Font.BOLD, 20));
            GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
            gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
            gbc_lblNewLabel.gridx = 0;
            gbc_lblNewLabel.gridy = 2;
            contentPanel.add(lblNewLabel_2, gbc_lblNewLabel);
        }
        {
            nameTF = new JTextField();
            nameTF.setFont(new Font("굴림체", Font.PLAIN, 20));
            GridBagConstraints gbc_nameTF = new GridBagConstraints();
            gbc_nameTF.fill = GridBagConstraints.HORIZONTAL;
            gbc_nameTF.insets = new Insets(0, 0, 5, 0);
            gbc_nameTF.gridx = 1;
            gbc_nameTF.gridy = 2;
            contentPanel.add(nameTF, gbc_nameTF);
        }
        /*
         * 나중에는 현재 날짜 기준으로 잡고, 유통기한만 입력할 수 있게 진행
         * 유통기한이 일주일 정도 남았으면 프로그램 작동시킬 때 경고창으로
         * **재료 유통기한이 *일 남았습니다. 라고 띄워주기
         * */
        {
            JLabel lblNewLabel_3 = new JLabel("남은 기한");
            lblNewLabel_3.setFont(new Font("굴림체", Font.BOLD, 20));
            GridBagConstraints gbc_DDayTF = new GridBagConstraints();
            gbc_DDayTF.insets = new Insets(0, 0, 0, 5);
            gbc_DDayTF.gridx = 0;
            gbc_DDayTF.gridy = 3;
            contentPanel.add(lblNewLabel_3, gbc_DDayTF);
        }
        {
            DDayTF = new JTextField();
            DDayTF.setFont(new Font("굴림체", Font.PLAIN, 20));
            GridBagConstraints gbc_DDayTF = new GridBagConstraints();
            gbc_DDayTF.fill = GridBagConstraints.HORIZONTAL;
            gbc_DDayTF.gridx = 1;
            gbc_DDayTF.gridy = 3;
            contentPanel.add(DDayTF, gbc_DDayTF);
        }

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("추가");
                okButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // JComboBox가 기본값이라면
                        if(ingredientCB.getSelectedIndex() == 0) {
                            JOptionPane.showMessageDialog(frame, "식재료 분류를 하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                        } else if (nameTF.getText().length() == 0) {
                            JOptionPane.showMessageDialog(frame, "식재료 이름을 입력하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                        } else {
                            String classification = (String) ingredientCB.getSelectedItem();
                            String name = nameTF.getText();
                            String DDay = DDayTF.getText();

                            System.out.println(classification);
                            System.out.println(name);
                            System.out.println(DDay);


                            Vector<String> vector = new Vector<>();
                            vector.add(classification);
                            vector.add(name);
                            vector.add(DDay);

                            TableModel tablemodel = ((MainFrame)frame).table.getModel();
                            ((DefaultTableModel)tablemodel).addRow(vector);

                            ingredientCB.setSelectedIndex(0);
                            nameTF.setText("");
                            DDayTF.setText("");
                            setVisible(false);
                        }
                    }
                });
                okButton.setFont(new Font("굴림체", Font.BOLD, 20));
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("취소");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ingredientCB.setSelectedIndex(0);
                        nameTF.setText("");
                        DDayTF.setText("");
                        setVisible(false);
                    }
                });
                cancelButton.setFont(new Font("굴림체", Font.BOLD, 20));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
