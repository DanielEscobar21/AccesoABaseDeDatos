
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class exe {

    public static void main(String[] args) {
        VentanaPrincipal app = new VentanaPrincipal();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setSize(1000, 650);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }

}
