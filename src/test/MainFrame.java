package test;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    JTable table;
    private AddIngredientDialog addIngredientDialog;
    ShoppingList shoppingList;

    // 메인
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame(new Dimension(800, 600));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame(Dimension size) {
        setTitle("냉장고를 부탁해!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        setSize(size);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel titleLabel = new JLabel("냉장고를 부탁해!");
        titleLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 26));
        contentPane.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel categoryPanel = new JPanel();
        categoryPanel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
        contentPane.add(categoryPanel, BorderLayout.WEST);
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));

        JLabel categoryLabel = new JLabel("식재료 카테고리");
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        categoryLabel.setBorder(new MatteBorder(0, 0, 2, 0, new Color(192, 192, 192)));
        categoryPanel.add(categoryLabel);

        String[] categories = {"곡물", "채소", "육류", "수산물", "조미료", "향신료", "과일", "가공식품", "기호식품"};
        for (String category : categories) {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            JButton button = new JButton(category);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            button.setFont(new Font("맑은 고딕", Font.BOLD, 16));

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setForeground(Color.BLUE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(Color.BLACK);
                }
            });
            button.addActionListener(e -> updateTableForCategory(category));
            buttonPanel.add(button);
            categoryPanel.add(button);
        }

        JPanel mainPanel = new JPanel();
        contentPane.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        String[] columnNames = {"식재료 분류", "이름", "남은기간"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        table = new JTable(tableModel);
        table.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        table.setRowHeight(30);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        tableHeader.setBackground(Color.LIGHT_GRAY);
        tableHeader.setForeground(Color.BLACK);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(new Color(102, 102, 255));
        table.setSelectionForeground(Color.WHITE);
        table.setShowGrid(true);

        scrollPane.setViewportView(table);

        JPanel panel = new JPanel();
        mainPanel.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton addBtn = new JButton("추가");
        addBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        panel.add(addBtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addIngredientDialog.setVisible(true);
            }
        });

   

        
        //Add a Feature to Display a Popup of "shoppingList" 
        JButton shoppingBtn = new JButton("Shopping");
        shoppingBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        panel.add(shoppingBtn);
        ShoppingList shoppingList = new ShoppingList(MainFrame.this);
        shoppingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//                ShoppingList shoppingList = new ShoppingList(MainFrame.this);
//                shoppingList.setVisible(true);
//                setVisible(true);
                shoppingList.setVisible(true);
            }
        });
        
        JButton removeBtn = new JButton("삭제");
        removeBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        panel.add(removeBtn);

        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                if (tableModel.getRowCount() == 0) {
                    return;
                }
                int row = table.getSelectedRow();
                if (row == -1) return;
                tableModel.removeRow(row);
            }
        });

        addIngredientDialog = new AddIngredientDialog(this, "식재료 추가");
    }
// Add row from shoppinglist table
    public void addRow(Object[] rowData) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addRow(rowData);
    }

    private void updateTableForCategory(String category) {
        System.out.println("선택한 카테고리: " + category);
    }
}
