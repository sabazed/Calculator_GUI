import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {

    private enum Operators {
        ADD("+"), SUB("-"), MUL("*"), DIV("/"), EQUAL("=");
        private final String str;
        Operators(String  str) {
            this.str = str;
        }
    }

    private JPanel frame;

    private JButton del;
    private JButton clear;

    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton zero;

    private JButton add;
    private JButton sub;
    private JButton div;
    private JButton mul;

    private JButton equal;
    private boolean wasEq = false;

    private JButton point;
    private boolean pointed = false;

    private static StringBuilder value = new StringBuilder("");
    private static StringBuilder current = new StringBuilder("0");
    private static double prev = 0;
    private static Operators lastop = null;
    private static Operators currop = null;

    private boolean opOrNum;
    private JLabel val;
    private JLabel result;

    private KeyListener listener = new KeyListener() {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_BACK_SPACE -> del.doClick();
                case KeyEvent.VK_NUMPAD0 -> zero.doClick();
                case KeyEvent.VK_NUMPAD1 -> one.doClick();
                case KeyEvent.VK_NUMPAD2 -> two.doClick();
                case KeyEvent.VK_NUMPAD3 -> three.doClick();
                case KeyEvent.VK_NUMPAD4 -> four.doClick();
                case KeyEvent.VK_NUMPAD5 -> five.doClick();
                case KeyEvent.VK_NUMPAD6 -> six.doClick();
                case KeyEvent.VK_NUMPAD7 -> seven.doClick();
                case KeyEvent.VK_NUMPAD8 -> eight.doClick();
                case KeyEvent.VK_NUMPAD9 -> nine.doClick();
                case KeyEvent.VK_PLUS -> add.doClick();
                case KeyEvent.VK_ADD -> add.doClick();
                case KeyEvent.VK_MINUS -> sub.doClick();
                case KeyEvent.VK_SUBTRACT -> sub.doClick();
                case KeyEvent.VK_MULTIPLY -> mul.doClick();
                case KeyEvent.VK_ASTERISK -> mul.doClick();
                case KeyEvent.VK_DIVIDE -> div.doClick();
                case KeyEvent.VK_SLASH -> div.doClick();
                case KeyEvent.VK_0 -> zero.doClick();
                case KeyEvent.VK_1 -> one.doClick();
                case KeyEvent.VK_2 -> two.doClick();
                case KeyEvent.VK_3 -> three.doClick();
                case KeyEvent.VK_4 -> four.doClick();
                case KeyEvent.VK_5 -> five.doClick();
                case KeyEvent.VK_6 -> six.doClick();
                case KeyEvent.VK_7 -> seven.doClick();
                case KeyEvent.VK_8 -> eight.doClick();
                case KeyEvent.VK_9 -> nine.doClick();
                case KeyEvent.VK_C -> clear.doClick();
                case KeyEvent.VK_ENTER -> equal.doClick();
                case KeyEvent.VK_EQUALS -> equal.doClick();
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {}
        @Override
        public void keyTyped(KeyEvent e) {}
    };

    public Calculator(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(frame);
        this.pack();

        this.addKeyListener(listener);

        one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(1);
                update();
            }
        });

        two.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(2);
                update();
            }
        });

        three.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(3);
                update();
            }
        });

        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(4);
                update();
            }
        });

        five.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(5);
                update();
            }
        });

        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(6);
                update();
            }
        });

        seven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(7);
                update();
            }
        });

        eight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(8);
                update();
            }
        });

        nine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(9);
                update();
            }
        });

        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.append(0);
                update();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currop = Operators.ADD;
                operate();
            }
        });

        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currop = Operators.SUB;
                operate();
            }
        });

        mul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currop = Operators.MUL;
                operate();
            }
        });

        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currop = Operators.DIV;
                operate();
            }
        });

        point.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pointed) {
                    current.append('.');
                    pointed = true;
                    update();
                }
            }
        });

        equal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currop = Operators.EQUAL;
                operate();
            }
        });

        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wasEq) {
                    current = new StringBuilder("0");
                    wasEq = false;
                }
                else {
                    int temp = current.length();
                    if (temp > 1) {
                        current.deleteCharAt(temp - 1);
                    } else if (temp == 1) {
                        current = new StringBuilder("0");
                    }
                }
                update();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wasEq = false;
                pointed = false;
                current = new StringBuilder("0");
                value = new StringBuilder("");
                val.setText(" ");
                prev = 0;
                lastop = currop = null;
                update();
            }
        });

    }

    private void update() {
        int tmp = current.length();
        if (tmp == 10) current.delete(9, 10);
        if (lastop == currop && currop == Operators.EQUAL) {
            value = new StringBuilder();
            current.delete(0, current.length() - 1);
            prev = 0;
            currop = lastop = null;
            wasEq = false;
        }
        if (current.charAt(0) == '.') current.insert(0, 0);
        if (current.length() > 1 && current.charAt(0) == '0') {
            if (current.charAt(1) == '0') current.deleteCharAt(1);
            else if (current.charAt(1) != '.') current.deleteCharAt(0);
        }
        result.setText(current.toString());
        opOrNum = true;
    }

    private void operate() {
        if (opOrNum) value.append(current + " " + currop.str + " ");
        else {
            int tmp = value.length();
            if (tmp != 1) value.replace(tmp - 2, tmp - 1, currop.str);
        }
        val.setText(value.toString());
        pointed = false;
        boolean isEq = currop == Operators.EQUAL;
        double temp;
        try {
            temp = Double.parseDouble(current.toString());
        }
        catch (NumberFormatException e) {
            if (lastop == Operators.DIV || lastop == Operators.MUL) temp = 1;
            else temp = 0;
        }
        if (lastop == null) {
            prev = temp;
        }
        else switch (lastop) {
            case ADD -> prev += temp;
            case SUB -> prev -= temp;
            case MUL -> prev *= temp;
            case DIV -> prev /= temp;
            case EQUAL -> {
                if (prev % 1 == 0) value = new StringBuilder((int) prev + " " + currop.str + " ");
                else value = new StringBuilder(prev + " " + currop.str + " ");
                val.setText(value.toString());
            }
        }
        if (isEq) wasEq = true;
        else current = new StringBuilder();
        lastop = currop;
        if (prev % 1 == 0) result.setText(String.valueOf((int) prev));
        else result.setText(String.valueOf(prev));
        opOrNum = false;
    }

    public static void main(String[] args) {
        JFrame calc = new Calculator("Calculator");
        calc.setResizable(false);
        calc.setFocusable(true);
        calc.setVisible(true);
    }
}

