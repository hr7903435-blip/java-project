import javax.swing.*;
import java.awt.*;

public class CalculatorUI {

    private JFrame frame;
    private JTextField display;
    private CalculatorLogic logic;

    public CalculatorUI() {

        logic = new CalculatorLogic();

        frame = new JFrame("Function Calculator");
        frame.setSize(420, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);

        frame.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 4, 8, 8));

        String[] buttons = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+",
                "sin","cos","tan","^",
                "sqrt","log","(",")",
                "C","DEL","",""
        };

        for (String text : buttons) {

            if (text.isEmpty()) {
                panel.add(new JLabel());
                continue;
            }

            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.addActionListener(e -> handleClick(text));
            panel.add(btn);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void handleClick(String text) {

        if (text.equals("C")) {
            display.setText("");
            return;
        }

        if (text.equals("DEL")) {
            String curr = display.getText();
            if (!curr.isEmpty()) {
                display.setText(curr.substring(0, curr.length() - 1));
            }
            return;
        }

        if (text.equals("=")) {
            try {
    double result = logic.evaluate(display.getText());
    display.setText(String.valueOf(result));
} catch (Exception e) {
    display.setText("Error");
}

            return;
        }

        display.setText(display.getText() + text);
    }
}

