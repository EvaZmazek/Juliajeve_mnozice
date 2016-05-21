
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {

    /**
     * Creates new form NewFrame
     */
    public Okno() {
        initComponents();
        platno.setSize(500, 500);
    }
    

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lambdaRe = new javax.swing.JTextField();
        lambdaIm = new javax.swing.JTextField();
        izbranaMnozica = new javax.swing.JComboBox<>();
        narisi = new javax.swing.JButton();
        platno = new Slika();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lambdaRe.setText("-0.75");
        jPanel1.add(lambdaRe, new java.awt.GridBagConstraints());

        lambdaIm.setText("0.11");
        jPanel1.add(lambdaIm, new java.awt.GridBagConstraints());

        izbranaMnozica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Juliajeva", "Mandelbrotova", "IFS" }));
        jPanel1.add(izbranaMnozica, new java.awt.GridBagConstraints());

        narisi.setText("Narisi");
        narisi.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		narisiActionPerformed(evt);
        	}
        });
        jPanel1.add(narisi, new java.awt.GridBagConstraints());

        add(jPanel1, java.awt.BorderLayout.NORTH);

        platno.setBackground(new java.awt.Color(0, 0, 0));
        add(platno, java.awt.BorderLayout.CENTER);

        pack();
    }

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        // TODO add your handling code here:
        int W = 4;  
        int H = 4;  
        Rectangle b = evt.getComponent().getBounds();
        evt.getComponent().setBounds(b.x, b.y, b.width, b.width+51*H/W);
    
        System.out.println(platno.getWidth()+" sirine in " + platno.getHeight() + " visine");
    }
    
    public void narisiActionPerformed(ActionEvent evt){
    	System.out.println(izbranaMnozica.getSelectedItem().toString()+" "+lambdaRe.getText()+" i"+lambdaIm.getText());
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Okno().setVisible(true);
            }
        });
    }


    public javax.swing.JPanel getjPanel2() {
		return platno;
	}
	public void setjPanel2(javax.swing.JPanel jPanel2) {
		this.platno = jPanel2;
	}


    private javax.swing.JComboBox<String> izbranaMnozica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel platno;
    private javax.swing.JTextField lambdaRe;
    private javax.swing.JTextField lambdaIm;
    private javax.swing.JButton narisi;

}
