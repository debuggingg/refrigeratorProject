package test;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ShoppingList extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTable sourceTable;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private ShoppingDialog shoppingDialog;
    private MainFrame mainFrame;
    private JLabel lblNewLabel;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShoppingList frame = new ShoppingList(new MainFrame(new Dimension(800, 600)));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ShoppingList(MainFrame mainFrame) {
        this.mainFrame = mainFrame;  // MainFrame 인스턴스 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        // 크롤 컬럼 만들어서 추가 table ) 
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        String[] columnNames = {"category", "name","date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        
        sourceTable = new JTable(tableModel);
        sourceTable.setFont(new Font("Dialog", Font.BOLD, 20));
        scrollPane.setViewportView(sourceTable);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        
        // 목록 추가 
        JButton addButton = new JButton("목록추가");
        // action for shoppingDialog and * setVisible for button
        // this button only visible when we click 목록추가 button 
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shoppingDialog.setVisible(true);
                if (sourceTable.getRowCount() > 0) {  // Check if there are rows in the sourceTable
                    buttonPanel.setVisible(true);
                }
            }
        });
        addButton.setFont(new Font("굴림체", Font.BOLD, 24));
        panel.add(addButton);
        shoppingDialog = new ShoppingDialog(this, "목록추가");
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(buttonPanel, BorderLayout.EAST);
        
        
        //목록삭제 
        JButton delete = new JButton("DELETE");
        buttonPanel.add(delete);
        buttonPanel.setVisible(false);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) sourceTable.getModel();
                if (tableModel.getRowCount() == 0) {
                    return;
                }
                int row = sourceTable.getSelectedRow();
                if (row == -1) return;
                tableModel.removeRow(row);
            }
        });
        
           
        JButton add1 = new JButton("ADD");
        buttonPanel.add(add1);
        
        lblNewLabel = new JLabel("SHOPPING LIST");
        lblNewLabel.setBackground(new Color(180, 252, 255));
        contentPane.add(lblNewLabel, BorderLayout.NORTH);
        // this action is most important 
        // using addRowToMainFrame();for tablerow send it to mainFrame 
        
        add1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	addRowToMainFrame();
            }
        });
    }

    private void addRowToMainFrame() {
        int tableRow = sourceTable.getSelectedRow();//getSelectedRow method on sourceTable to get the index of the currently selected row.
        if (tableRow != -1) {// If no row is selected, it returns -1.
            // DefaultTableModel 형변환 하여  sourceModel. 에 data 넣기 
        	DefaultTableModel sourceModel = (DefaultTableModel) sourceTable.getModel();

            // Get data from the selected row 
        	
            Object[] rowData = new Object[sourceModel.getColumnCount()];// 'rowData' =a length equal to the number of columns in sourceModel
            for (int i = 0; i < sourceModel.getColumnCount(); i++) {// 예를 들면 ) column 이 2개면 처음 컬럼 값을 넣을때 마다 column 일고 다음 컬럼 읽어서 저장 
                rowData[i] = sourceModel.getValueAt(tableRow, i);// tableRow(테이블의몇번쨰 row 를표시.)i(각 테이블의 읽히는 column 줄 ) 
            }
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            rowData[2] = currentDate;

            // Add row to MainFrame's table
            mainFrame.addRow(rowData);

            // Optionally, remove row from source table
            sourceModel.removeRow(tableRow);
        }
    }
  
}
