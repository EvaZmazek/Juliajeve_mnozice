
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
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
 //       lambdaIm2 = new javax.swing.JTextField();
        izbranaMnozica = new javax.swing.JComboBox<>();
//        izbranaBarva = new javax.swing.JComboBox<>();
        narisi = new javax.swing.JButton();
        barvaj = new javax.swing.JButton();
        shrani = new javax.swing.JButton();
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
        
//        lambdaIm2.setText("0.11");
//        jPanel1.add(lambdaIm2, new java.awt.GridBagConstraints());

        izbranaMnozica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Juliajeva", "Mandelbrotova", "IFS" }));
        jPanel1.add(izbranaMnozica, new java.awt.GridBagConstraints());
        
//        izbranaBarva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pisana", "rdeca", "modra" }));
//        jPanel1.add(izbranaBarva, new java.awt.GridBagConstraints());
        
        narisi.setText("Narisi");
        narisi.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		narisiActionPerformed(evt);
        	}
        });
        jPanel1.add(narisi, new java.awt.GridBagConstraints());
        
        barvaj.setText("barvaj");
        barvaj.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		barvajActionPerformed(evt);
        	}
        });
        jPanel1.add(barvaj, new java.awt.GridBagConstraints());
        
        shrani.setText("Shrani");
        shrani.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		shraniActionPerformed(evt);
        	}
        });
        jPanel1.add(shrani, new java.awt.GridBagConstraints());

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
        
        Slika.setDimensions(platno.getHeight());
        
    
        System.out.println(platno.getWidth()+" sirine in " + platno.getHeight() + " visine");
        }
    
    public void narisiActionPerformed(ActionEvent evt){
    	System.out.println(izbranaMnozica.getSelectedItem().toString()+" "+lambdaRe.getText()+" i"+lambdaIm.getText());
		Slika.setImagjuliaconst(Double.parseDouble(lambdaIm.getText()));
    	Slika.setRealjuliaconst(Double.parseDouble(lambdaRe.getText()));
    	Slika.setMnozica(izbranaMnozica.getSelectedItem().toString());
    	this.repaint();
    }
    
    public void barvajActionPerformed(ActionEvent evt){
    	Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
    	System.out.println(newColor);;
    	Slika.setBarva(newColor);
    	Slika.setImagjuliaconst(Double.parseDouble(lambdaIm.getText()));
    	Slika.setRealjuliaconst(Double.parseDouble(lambdaRe.getText()));
    	Slika.setMnozica(izbranaMnozica.getSelectedItem().toString());
    	this.repaint();
    }
    
    public void shraniActionPerformed(ActionEvent evt){
    	BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB); 
    	Graphics g = bi.createGraphics();
    	this.paint(g);
		g.dispose();
		try{ImageIO.write(bi,"png",new File("test1.png"));}catch (Exception e) {}
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Okno frame = new Okno();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        
        //SlikaJulijajeva.setDimensions(frame.getWidth());
        
        //Algoritmi.juliajeva(frame);
        
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
//    private javax.swing.JTextField lambdaIm2;
    private javax.swing.JButton narisi;
    private javax.swing.JButton barvaj;
    private javax.swing.JButton shrani;

}
