import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class MainApp {

    public static void main(String[] args) {
        DatabaseManager.getConnection();

        SwingUtilities.invokeLater(() -> new MainApp().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Fake News Detector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(inputField, BorderLayout.NORTH);

        JButton checkButton = new JButton("Check Headline");
        frame.add(checkButton, BorderLayout.SOUTH);


        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String headline = inputField.getText();

                new Thread(() -> {
                    String result = ApiCaller.checkHeadline(headline);
                    DatabaseManager.insertResult(headline, result);

                    SwingUtilities.invokeLater(() -> outputArea.setText(result));
                }).start();
            }
        });
        frame.setVisible(true);
    }
}
