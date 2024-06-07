package frame;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoadingScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoadingScreen frame = new LoadingScreen();
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
    public LoadingScreen() {}

    public LoadingScreen(Dimension size) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(size);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setUndecorated(true);
        setContentPane(contentPane);

        // 이미지 로드
        //		ImageIcon loadingImage = new ImageIcon("");
        //		JLabel label = new JLabel(loadingImage);
        JLabel label = new JLabel("냉장고 프로그램 로딩중...");
        add(label);

        // 화면 표시
        this.setVisible(true);
    }

}
