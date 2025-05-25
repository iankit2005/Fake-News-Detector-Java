import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class MainApp {

    public static void main(String[] args) {
        // ✅ Step 1: Test MySQL Connection
        DatabaseManager.getConnection();  // This will print "Connected" or error in terminal

        // ✅ Step 2: Launch GUI
        SwingUtilities.invokeLater(() -> new MainApp().createAndShowGUI());
    }

    public void createAndShowGUI() {
        // 🖼️ Create frame
        JFrame frame = new JFrame("Fake News Detector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        // 📝 Input field
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(inputField, BorderLayout.NORTH);

        // 🔘 Button
        JButton checkButton = new JButton("Check Headline");
        frame.add(checkButton, BorderLayout.SOUTH);

        // 📄 Output area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // ⚙️ Button Action
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String headline = inputField.getText();

                // ✅ Multithreaded API call + DB insert
                new Thread(() -> {
                    String result = ApiCaller.checkHeadline(headline);
                    DatabaseManager.insertResult(headline, result);

                    // ✅ Update GUI back on Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> outputArea.setText(result));
                }).start();
            }
        });


        // ✅ Show GUI
        frame.setVisible(true);
    }
}
