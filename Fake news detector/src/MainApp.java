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
                String headline = inputField.getText().trim();
                // Input validation
                if (headline.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Headline cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (headline.length() < 10 || headline.length() > 200) {
                    JOptionPane.showMessageDialog(frame, "Headline must be between 10 and 200 characters.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate input length (must be between 10–200 characters)
                if (headline.length() < 10 || headline.length() > 200) {
                    JOptionPane.showMessageDialog(frame, "Headline must be between 10 and 200 characters!",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                new Thread(() -> {
                    try {
                        String result = ApiCaller.checkHeadline(headline);
                        DatabaseManager.insertResult(headline, result);

                        SwingUtilities.invokeLater(() -> outputArea.setText(result));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(
                                null,
                                "Something went wrong: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        ));
                    }
                }).start();

            }
        }
        );
        frame.setVisible(true);
    }
}
