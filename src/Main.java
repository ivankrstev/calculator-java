import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class Main extends JFrame implements ActionListener {
    JTextField textOutput = new JTextField("");
    JPanel top = new JPanel(), middle1 = new JPanel(), middle2 = new JPanel(), bottom = new JPanel();
    JButton[][] buttons = new JButton[4][3];
    JButton sum_btn = new JButton("+"), sub_btn = new JButton("-"), product_btn = new JButton("*"), div_btn = new JButton("/"), equal_btn = new JButton("=");
    JButton sqrt_btn = new JButton("\u221Ax"), pow_btn = new JButton("xʸ"), modulo_btn = new JButton("%"), frac_btn = new JButton("¹⁄ₓ");
    float result;
    boolean changedFunctions = false;
    String operation;

    Main() {
        // TOP
        textOutput.setMinimumSize(new Dimension(315, 50));
        textOutput.setPreferredSize(new Dimension(315, 50));
        textOutput.setEnabled(false);
        textOutput.setHorizontalAlignment(JLabel.RIGHT);
        textOutput.setFont(new Font("", Font.PLAIN, 32));
        textOutput.setBackground(Color.WHITE);
        textOutput.setOpaque(true);
        textOutput.setDisabledTextColor(Color.BLACK);
        top.setBorder(new EmptyBorder(15, 10, 5, 10));
        top.add(textOutput);
        top.setLayout(new GridLayout(1, 0));

        // MIDDLE 1
        for (int i = 0; i < 4; i++) {
            int t = 1;
            for (int y = 0; y < 3; y++) {
                buttons[i][y] = new JButton("0");
                if (i == 0) buttons[i][y] = new JButton("" + (t + 6));
                if (i == 1) buttons[i][y] = new JButton("" + (t + 3));
                if (i == 2) buttons[i][y] = new JButton("" + t);
                t++;
                if (i == 3 && y == 2) buttons[i][y] = new JButton(".");
                JButton button = buttons[i][y];
                button.addActionListener(this);
                if (i == 3 && y == 0) {
                    button.setVisible(false);
                }
                button.setFont(new Font("", Font.BOLD, 20));
                button.setPreferredSize(new Dimension(30, 30));
                middle1.add(button);
            }
        }
        middle1.setLayout(new GridLayout(5, 3, 5, 10));
        middle1.setBorder(new EmptyBorder(10, 10, 10, 10));
        middle1.setPreferredSize(new Dimension(210, 150));
        middle1.setSize(new Dimension(210, 150));
        // Middle 2
        sum_btn.setFont(new Font("", Font.BOLD, 20));
        sub_btn.setFont(new Font("", Font.BOLD, 20));
        product_btn.setFont(new Font("", Font.BOLD, 20));
        div_btn.setFont(new Font("", Font.BOLD, 20));
        equal_btn.setFont(new Font("", Font.BOLD, 20));
        sum_btn.addActionListener(this);
        sub_btn.addActionListener(this);
        product_btn.addActionListener(this);
        div_btn.addActionListener(this);
        equal_btn.addActionListener(this);
        sqrt_btn.setFont(new Font("", Font.BOLD, 20));
        pow_btn.setFont(new Font("", Font.BOLD, 20));
        modulo_btn.setFont(new Font("", Font.BOLD, 20));
        frac_btn.setFont(new Font("", Font.BOLD, 20));
        sqrt_btn.addActionListener(this);
        pow_btn.addActionListener(this);
        modulo_btn.addActionListener(this);
        frac_btn.addActionListener(this);
        firstFunc();
        middle2.setBorder(new EmptyBorder(10, 10, 10, 10));
        middle2.setLayout(new GridLayout(0, 1, 5, 10));
        middle2.setPreferredSize(new Dimension(90, 150));

        // BOTTOM
        JButton clear_btn = new JButton("Clear");
        clear_btn.setFont(new Font("", Font.BOLD, 15));
        clear_btn.setPreferredSize(new Dimension(125, 40));
        clear_btn.addActionListener(e -> {
            textOutput.setText("");
            result = 0f;
        });
        JButton sf_btn = new JButton("2ndF");
        sf_btn.addActionListener(e -> {
            if (!changedFunctions) {
                secondFunc();
                changedFunctions = true;
                sf_btn.setText("1stF ");
            } else {
                firstFunc();
                changedFunctions = false;
                sf_btn.setText("2ndF");
            }
        });
        sf_btn.setFont(new Font("", Font.PLAIN, 15));
        bottom.setPreferredSize(new Dimension(0, 60));
        bottom.add(clear_btn);
        bottom.add(sf_btn);

        // WINDOW
        this.add(top, BorderLayout.NORTH);
        this.add(middle1, BorderLayout.CENTER);
        this.add(middle2, BorderLayout.EAST);
        this.add(bottom, BorderLayout.SOUTH);
        this.setVisible(true);
        this.setTitle("Calculator");
        this.setMinimumSize(new Dimension(360, 450));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 3; y++) {
                if (button == buttons[i][y]) {
                    textOutput.setText(textOutput.getText() + buttons[i][y].getText());
                    break;
                }
            }
        }
        if (button == sum_btn) {
            textOutput.setText(textOutput.getText() + "+");
            operation = "sum";
        }
        if (button == sub_btn) {
            textOutput.setText(textOutput.getText() + "-");
            operation = "sub";
        }
        if (button == product_btn) {
            textOutput.setText(textOutput.getText() + "*");
            operation = "product";
        }
        if (button == div_btn) {
            textOutput.setText(textOutput.getText() + "/");
            operation = "division";
        }
        if (button == sqrt_btn) {
            result = (float) Math.sqrt(Float.parseFloat(textOutput.getText()));
            if (result % 1 == 0) textOutput.setText("" + (int) result);
            else textOutput.setText("" + result);
        }
        if (button == pow_btn) {
            textOutput.setText(textOutput.getText() + "^");
            operation = "pow";
        }
        if (button == modulo_btn) {
            textOutput.setText(textOutput.getText() + "%");
            operation = "modulo";
        }
        if (button == frac_btn) {
            result = (float) 1 / Float.parseFloat(textOutput.getText());
            if (result % 1 == 0) textOutput.setText("" + (int) result);
            else textOutput.setText("" + result);
        }
        // EQUAL
        if (e.getSource() == equal_btn) {
            if (Objects.equals(operation, "sum")) {
                String[] temp = textOutput.getText().split("\\+");
                result = 0f;
                for (String s : temp) {
                    result += Float.parseFloat(s);
                }
                operation = null;
            }
            if (Objects.equals(operation, "sub")) {
                String[] temp = textOutput.getText().split("-");
                result = Float.parseFloat(temp[0]);
                for (int i = 1; i < temp.length; i++) {
                    result -= Float.parseFloat(temp[i]);
                }
                operation = null;
            }
            if (Objects.equals(operation, "product")) {
                String[] temp = textOutput.getText().split("\\*");
                result = 1f;
                for (String s : temp) {
                    result *= Float.parseFloat(s);
                }
                operation = null;
            }
            if (Objects.equals(operation, "division")) {
                String[] temp = textOutput.getText().split("/");
                result = Float.parseFloat(temp[0]);
                for (int i = 1; i < temp.length; i++) {
                    result /= Float.parseFloat(temp[i]);
                }
                operation = null;
            }
            if (Objects.equals(operation, "pow")) {
                String[] temp = textOutput.getText().split("\\^");
                result = (float) Math.pow(Float.parseFloat(temp[0]), Float.parseFloat(temp[1]));
                operation = null;
            }
            if (Objects.equals(operation, "modulo")) {
                String[] temp = textOutput.getText().split("%");
                result = Float.parseFloat(temp[0]) % Float.parseFloat(temp[1]);
                operation = null;
            }
            if (result % 1 == 0) textOutput.setText("" + (int) result);
            else textOutput.setText("" + result);
        }
    }

    public void firstFunc() {
        middle2.removeAll();
        middle2.add(sum_btn);
        middle2.add(sub_btn);
        middle2.add(product_btn);
        middle2.add(div_btn);
        middle2.add(equal_btn);
        middle2.updateUI();
    }

    public void secondFunc() {
        middle2.removeAll();
        middle2.add(sqrt_btn);
        middle2.add(pow_btn);
        middle2.add(modulo_btn);
        middle2.add(frac_btn);
        middle2.add(equal_btn);
        middle2.updateUI();
    }

    public static void main(String[] args) {
        new Main();
    }
}
