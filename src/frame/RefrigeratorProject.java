package frame;

import javax.swing.*;
import java.awt.*;

public class RefrigeratorProject {
    public static void main(String[] args) {
        // 창 크기 정의
        Dimension windowSize = new Dimension(1000, 800);

        // CardLayout 설정해야 화면 부드럽게 넘어간다는거 알았음
        // 나중에 설정

        // EventQueue.invokeLater를 사용하여 스윙 코드를 이벤트 디스패치 스레드에서 실행
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // LoadingScreen 시작
                    LoadingScreen loadingScreen = new LoadingScreen(windowSize);
                    loadingScreen.setLocationRelativeTo(null);
                    loadingScreen.setVisible(true);

                    // 1.5초 후에 LoadingScreen을 닫고 MainFrame을 여는 타이머 설정
                    Timer timer = new Timer(1500, e -> {
                        loadingScreen.dispose(); // LoadingScreen 닫기
                        MainFrame mainFrame = new MainFrame(windowSize);
                        mainFrame.setLocationRelativeTo(null); // Center the main frame
                        mainFrame.setVisible(true);
                    });
                    timer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
                    timer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
