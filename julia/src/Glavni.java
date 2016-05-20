
import javax.swing.JFrame;

public class Glavni {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Okno frame = new Okno();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400, 400);
        
        Algoritmi.juliajeva(frame);
        
    }
    
}
