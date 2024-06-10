package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import xyz.itwill.swing.StudentFrameApp;

public class ShoppingDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField menu;
	private JComboBox<String> ingredientCB;
	 private String classification[] = {"식재료를 분류해주세요", "곡물", "채소", "육류", "수산물", "조미료", "향신료", "과일", "가공식품", "기호식품"};
	 

	public ShoppingDialog(JFrame frame,String title) {
		// boolean false 는 부모창 을 누를 수있고, true 면 부모창을 누를수없다.  
		
		super(frame,title,true);
		setBounds(700, 200, 450, 300);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				init();
			}
		});
//		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {100, 300};
		gbl_contentPanel.rowHeights = new int[] {50, 50, 50};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("category");
			lblNewLabel.setFont(new Font("굴림체", Font.BOLD, 24));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		 {
	            ingredientCB = new JComboBox(classification);
	            GridBagConstraints gbc_comboBox = new GridBagConstraints();
	            gbc_comboBox.insets = new Insets(0, 0, 5, 0);
	            gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	            gbc_comboBox.gridx = 1;
	            gbc_comboBox.gridy = 0;
	            contentPanel.add(ingredientCB, gbc_comboBox);
	        }
		
		{
			JLabel lblNewLabel_1 = new JLabel("menu");
			lblNewLabel_1.setFont(new Font("굴림체", Font.BOLD, 24));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			menu = new JTextField();
			menu.setFont(new Font("굴림체", Font.PLAIN, 20));
			GridBagConstraints gbc_nameTF = new GridBagConstraints();
			gbc_nameTF.fill = GridBagConstraints.BOTH;
			gbc_nameTF.insets = new Insets(0, 0, 5, 0);
			gbc_nameTF.gridx = 1;
			gbc_nameTF.gridy = 1;
			contentPanel.add(menu, gbc_nameTF);
			menu.setColumns(10);
		}
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("추가");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(ingredientCB.getSelectedIndex() == 0) {
                            JOptionPane.showMessageDialog(frame, "식재료 분류를 하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                        } else if (menu.getText().length() == 0) {
                            JOptionPane.showMessageDialog(frame, "식재료 이름을 입력하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                        } else {
                        	
                        	 String classification = (String) ingredientCB.getSelectedItem();
                        	 String name=menu.getText();
                        	 
                        	 Vector<String> vector=new Vector<String>();
                        	 vector.add(classification);
                        	 vector.add(name);
                        	 
                        	 TableModel tableModel=((ShoppingList)frame).sourceTable.getModel();
                        	 ((DefaultTableModel)tableModel).addRow(vector);

                        

						init();
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
					public void actionPerformed(ActionEvent e) {
						//JTextField 컴퍼넌트의 입력값 초기화
					init();
					}
				});
				cancelButton.setFont(new Font("굴림체", Font.BOLD, 20));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	//다이얼로그의 Jtext 컴퍼넌터 초기화하고 숨김 ,  메소드로 만듬 
	private void init() {
		ingredientCB.setSelectedIndex(0);
		menu.setText("");
	
		//JDialog 컨테이너 숨김 처리
		setVisible(false);
	}
	

}

