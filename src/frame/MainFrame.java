package frame;

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

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    JTable table;
    private AddIngredientDialog addIngredientDialog;

    //	메인
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
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

    // RefrigeratorProject에서 실행시키는게 아니라 MainFrame만 실행시키고 싶으면
    // MainFrame() 매개변수 지우고 참조되는거 다 지우고 RefrigeratorProject 파일에서 MainFrame 호출할 때 생성자 지우면
    // MainFrame()만 호출 가능합니다.
    public MainFrame() {}

    public MainFrame(Dimension size) {
        setTitle("냉장고를 부탁해!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        setSize(size);
        // setSize(1000,800);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel titleLabel = new JLabel("냉장고를 부탁해!");
        titleLabel.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 26));
        contentPane.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));


        /* 카테고리 부분 개발 */
        JPanel categoryPanel = new JPanel();
        categoryPanel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
        contentPane.add(categoryPanel, BorderLayout.WEST);
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));

        JLabel categoryLabel = new JLabel("식재료 카테고리");
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 라벨을 가운데 정렬합니다.
        categoryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        categoryLabel.setBorder(new MatteBorder(0,0,2,0, new Color(192, 192, 192)));
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
            // 카테고리 누르면 Main DB 바뀔 수 있게 람다로 처리
            button.addActionListener(e -> updateTableForCategory(category));

            buttonPanel.add(button);
            categoryPanel.add(button);
        }

        /* 메인 DB 부분 개발 */
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

        // 테이블 헤더 커스터마이즈
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        tableHeader.setBackground(Color.LIGHT_GRAY);
        tableHeader.setForeground(Color.BLACK);

        // 기본 셀 렌더러 커스터마이즈
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        cellRenderer.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // 테이블 그리드 색상 및 선택 색상 설정
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

        JButton removeBtn = new JButton("삭제");
        removeBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        panel.add(removeBtn);

        // 쇼핑
        JButton shoppingBtn = new JButton("Shopping");
        shoppingBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        panel.add(shoppingBtn);
        shoppingBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 메인 비활성화
                setVisible(false);

                ShoppingListDialog shoppingListDialog = new ShoppingListDialog();
                shoppingListDialog.setVisible(true);

                setVisible(true);
            }
        });



        // 삭제 이벤트
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = (DefaultTableModel)table.getModel();

                if(tableModel.getRowCount() == 0) {
                    return;
                }

                int row = table.getSelectedRow();
                if(row == -1)return;

                tableModel.removeRow(row);
            }
        });
        addIngredientDialog = new AddIngredientDialog(this, "식재료 추가");
    }

    /* 이벤트 처리 */
    private void updateTableForCategory(String category) {
        // 카테고리에 따라 JTable 업데이트 로직 구현
        // 예: tableModel.setDataVector(data, columnNames);
        System.out.println("선택한 카테고리: " + category);
    }
}
