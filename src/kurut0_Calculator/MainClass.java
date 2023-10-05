package kurut0_Calculator;

import java.awt.*;

public class MainClass {

    static kurut0_Calculator.GUI gui = new kurut0_Calculator.GUI();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                gui.setVisible(true);
            }
            catch (Exception ex) {
                System.out.println("GUI ERROR!");
            }
        });
    }
}