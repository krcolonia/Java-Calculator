package kurut0_Calculator;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.InputStream;
import java.text.DecimalFormat;

public class GUI extends JFrame {

    // DecimalFormat for the "screen" in the Calculator App. Only shows decimals when necessary
    DecimalFormat df = new DecimalFormat("#########.########");

    // Colors
    Color japaneseIndigo = new Color(0x27374D),
            cadetBlue = new Color(0x9DB2BF),
            azureWhite = new Color(0xDDE6ED);

    // UI Components
    JTextField inout = new JTextField();

    // Numerical Buttons
    JButton zero = new JButton("0"), dblZero = new JButton("00"), decPoint = new JButton("."),
            one = new JButton("1"), two = new JButton("2"), three = new JButton("3"),
            four = new JButton("4"), five = new JButton("5"), six = new JButton("6"),
            seven = new JButton("7"), eight = new JButton("8"), nine = new JButton("9");
    // Operations Buttons
    JButton multBtn = new JButton("×"), divBtn = new JButton("÷"),
            addBtn = new JButton("+"), subBtn = new JButton("-"),
            equal = new JButton("="), backspace = new JButton("<-"),
            clr = new JButton("C");

    public GUI() {
        this.setTitle("Calculator | Developed by カート　コロニア");
        this.setSize(389, 450);
        this.setIconImage(jframeIco().getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        getContentPane().setBackground(japaneseIndigo);

        inout.setBounds(5,5,366,80);
        inout.setHorizontalAlignment(JTextField.RIGHT);
        inout.setText("0");
        inout.setBorder(null);
        inout.setEditable(false);
        inout.setBackground(azureWhite);
        inout.setFont(fontInit("digi"));
        this.add(inout);

        // Top Row Buttons
        setBtn(backspace,227,90,70);
        delBtn(backspace);
        setBtn(clr,301, 90,70);
        clrBtn(clr);

        // Number Pad Buttons
        setNumBtn(seven,5,90, "7");
        setNumBtn(eight,79,90, "8");
        setNumBtn(nine,153,90, "9");
        setNumBtn(four,5,164, "4");
        setNumBtn(five, 79,164, "5");
        setNumBtn(six,153,164, "6");
        setNumBtn(one,5,238, "1");
        setNumBtn(two,79,238, "2");
        setNumBtn(three,153,238, "3");
        setNumBtn(zero,5,312, "0");
        setNumBtn(dblZero,79, 312, "00");

        setBtn(decPoint,153,312,70);
        decBtn(decPoint);

        // Operations Buttons
        setBtn(multBtn, 227,164,70);
        opsBtn(multBtn,"*");
        setBtn(divBtn,301,164,70);
        opsBtn(divBtn,"/");
        setBtn(addBtn,227,238,70);
        opsBtn(addBtn,"+");
        setBtn(subBtn,301,238,70);
        opsBtn(subBtn,"-");
        setBtn(equal,227,312,144);
        opsBtn(equal,"=");
    }

    // Finds app's icon and returns it, else if not found it returns a null value.
    ImageIcon jframeIco() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("calculator.png");
            if(is == null) {
                return null;
            }
            return new ImageIcon(ImageIO.read(is));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method used in setting the attributes of each passed button
    void setBtn(JButton btn, int x, int y, int width) {
        btn.setBounds(x,y,width, 70);
        btn.setFont(fontInit("heav"));
        btn.setFocusable(false);
        btn.setBorder(null);
        btn.setBackground(cadetBlue);
        this.add(btn);
    }


    // Method used in setting the attr of each passed numerical button
    void setNumBtn(JButton btn, int x, int y, String btnNum) {
        btn.setBounds(x, y, 70, 70);
        btn.setFont(fontInit("heav"));
        btn.setFocusable(false);
        btn.setBorder(null);
        btn.setBackground(cadetBlue);
        btnFunc(btn, btnNum);
        this.add(btn);
    }

    // ------------------------------ MUST OPTIMIZE FROM THIS LINE FORTH ------------------------------//

    /*

    TO-DO: Optimize the if-else statements down below
          they are so nested, even birds live in it

    */

    // Variables used in dealing with input and output
    String currInput, // currInput is retrieved from current number on app's "screen"
            currOp; // currOp holds the current operation that is being executed (i.e. Addition)
    Float num1 = null, // num1 holds the first value in an operation
            num2 = null, // num2 holds the second value in an operation
            result = null; // result holds the result of the operation.

    // method that sets the function of each passed button
    void btnFunc(JButton btn, String btnNum) {
        btn.addActionListener(e -> {
            try {
                if(result != null) { // If the equals button is used
                    inout.setText("0");
                    result = null;
                }

                currInput = inout.getText();

                if (!currInput.equals("Math ERROR") && !currInput.equals("0") && !currInput.equals("00"))
                    inout.setText(inout.getText() + btnNum);
                else if (!currInput.equals("Math ERROR") && currInput.equals("0") && !currInput.equals("00"))
                    inout.setText(btnNum);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // Method used to set the function of the decimal point button
    void decBtn(JButton btn) {
        btn.addActionListener(e -> {
           try{
               if(num2 != null) {
                   inout.setText("0");
               }

               char decimal = '.';
               int decimalCount = 0;
               currInput = inout.getText();

               for(int i = 0;i < currInput.length(); i++)
                   if(currInput.charAt(i) == decimal)
                       decimalCount++;

               if(decimalCount < 1)
                   inout.setText(currInput + '.');
           }
           catch (Exception ex) {
               ex.printStackTrace();
           }
        });
    }

    int opClkCnt = 1; // integer used as a counter for how many times an operation button is pressed

    // Method used to set the function of an operation button (ie. the button for Multiplication)
    void opsBtn(JButton btn, String func) {
        btn.addActionListener(e -> {
            try {
                currInput = inout.getText();

                if(opClkCnt == 1 && !currInput.equals("Math ERROR")) {
                    num1 = Float.parseFloat(currInput);
                    inout.setText("0");
                    opClkCnt++;
                }
                else if (opClkCnt != 1 && !currInput.equals("Math ERROR") && Float.parseFloat(currInput) != num1) {
                    num2 = Float.parseFloat(currInput);
                }

                System.out.println(num1 + "\n" + num2 + "\n" + opClkCnt);

                if(!currInput.equals("Math ERROR") && currOp != null && opClkCnt == 2 && num2 != null) {
                    inout.setText("0");
                    switch (currOp) {
                        case "*" -> num1 = Operations.mult(num1, num2);
                        case "/" -> num1 = Operations.div(num1, num2);
                        case "+" -> num1 = Operations.add(num1, num2);
                        case "-" -> num1 = Operations.sub(num1, num2);
                    }

                    if(func.equals("=")) {
                        if (num1.isInfinite()) {
                            inout.setText("Math ERROR");
                        }
                        else {
                            result = num1;
                            inout.setText(df.format(result));
                        }
                    }
                    num2 = null;
                }
                currOp = func;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // Method used to set the fucntion of the clear button
    void clrBtn(JButton btn) {
        btn.addActionListener(e -> {
            try { // sets all variables used in the operations back to null
                num1 = null;
                num2 = null;
                result = null;
                opClkCnt = 1; // Resets the counter of operation button clicks.
                inout.setText("0"); // Sets the output panel's text to 0
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // Method used to set the function of the backspace button
    void delBtn(JButton btn) {
        btn.addActionListener(e -> {
            currInput = inout.getText();
            try {
                if(currInput.equals("Math ERROR") || result != null)
                    return;

                if(currInput.equals("0"))
                    inout.setText(currInput);
                else if(currInput.length() < 2)
                    inout.setText("0");
                else {
                    currInput = currInput.substring(0, currInput.length() - 1);
                    inout.setText(currInput);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    // method used to initialize the fonts used within the app.
    Font fontInit(String fontFam) {
        try {
            switch(fontFam) {
                case "digi" -> { // Digital-7 Font. Used in the inout Panel
                    InputStream digiIS = getClass().getClassLoader().getResourceAsStream("digital.ttf");
                    assert digiIS != null;
                    return Font.createFont(Font.TRUETYPE_FONT, digiIS).deriveFont(65f);
                }
                case "heav" -> { // Heavitas Font. Used in the buttons
                    InputStream heavIS = getClass().getClassLoader().getResourceAsStream("heavitas.ttf");
                    assert heavIS != null;
                    return Font.createFont(Font.TRUETYPE_FONT, heavIS).deriveFont(15f);
                }
            }
        }
        catch(FontFormatException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

/*
  Programmed by Kurt Robin P. Colonia
  https://github.com/krcolonia
 */
