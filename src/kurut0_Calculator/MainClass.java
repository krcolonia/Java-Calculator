package kurut0_Calculator;

import java.awt.*;

public class MainClass {

    static GUI gui = new GUI();

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

/*
  Programmed by Kurt Robin P. Colonia
  https://github.com/krcolonia
 */