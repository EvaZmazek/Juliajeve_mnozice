import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")
public class Okno extends JFrame {
	
    private javax.swing.JComboBox<String> izbranaMnozica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel platno;
    private javax.swing.JTextField lambdaRe;
    private javax.swing.JTextField lambdaIm;
    private javax.swing.JButton narisi;
    private javax.swing.JButton barvaj;
    private javax.swing.JButton shrani;
    

    /**
     * Creates new form NewFrame
     */
    public Okno() {
        initComponents();
        platno.setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//	komponente v oknu
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();	//platno, na katerem so gumbi in okna za pisat
        platno = new Slika();	//glavno platno, ki nariše sliko
        izbranaMnozica = new javax.swing.JComboBox<>();	//izbor množice, ki jo naj nariše
        //Vnosna polja:
        lambdaRe = new javax.swing.JTextField();	//za realni del spremenljivke (Juliajeva množica, IFS)
        lambdaIm = new javax.swing.JTextField();	//za imaginarni del spremenljivke (Juliajeva množica, IFS)
        //Gumbi:
        narisi = new javax.swing.JButton();	//gumb "nariši"
        barvaj = new javax.swing.JButton(); //gumb "barvaj"
        shrani = new javax.swing.JButton();	//gumb "shrani"
        

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
        
        izbranaMnozica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Juliajeva", "Mandelbrotova", "IFS" }));
        jPanel1.add(izbranaMnozica, new java.awt.GridBagConstraints());
        
        izbranaMnozica.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent evt) {
				mnozicaActionPerformed(evt);
				
			}
        });  

        //Vnosna polja: ..............................................................
        lambdaRe.setText("-0.75");
        jPanel1.add(lambdaRe, new java.awt.GridBagConstraints());

        lambdaIm.setText("0.11");
        jPanel1.add(lambdaIm, new java.awt.GridBagConstraints());
        
        narisi.setText("Narisi");
        narisi.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		narisiActionPerformed(evt);
        	}
        });
        jPanel1.add(narisi, new java.awt.GridBagConstraints());
        
        barvaj.setText("Barvaj");
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
        }
    
	private void setSlika(){
    	Slika.setIm(Double.parseDouble(lambdaIm.getText()));
    	Slika.setRe(Double.parseDouble(lambdaRe.getText()));
    	Slika.setMnozica(izbranaMnozica.getSelectedItem().toString());
    }
    
    protected void mnozicaActionPerformed(ItemEvent evt) {
		if ((izbranaMnozica.getSelectedItem().toString() == "Mandelbrotova") || (izbranaMnozica.getSelectedItem().toString() == "IFS")) {
    		lambdaRe.setVisible(false);
            lambdaIm.setVisible(false);
    	} else {
    		lambdaRe.setVisible(true);
            lambdaIm.setVisible(true);
    	}	
	}
    
    public void narisiActionPerformed(ActionEvent evt){
    	setSlika();
    	this.repaint();
    }
    
    public void barvajActionPerformed(ActionEvent evt){
    	Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
    	//System.out.println(newColor);;
    	Slika.setBarva(newColor);
    	setSlika();
    	this.repaint();
    }
    
    public void shraniActionPerformed(ActionEvent evt){
		BufferedImage screenshot = new BufferedImage(platno.getWidth(),platno.getHeight(), BufferedImage.TYPE_INT_ARGB);
		platno.paint(screenshot.getGraphics());
		
		try {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".png", "png");
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showSaveDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File izbranaDatoteka = fileChooser.getSelectedFile();
				if (!izbranaDatoteka.getPath().toLowerCase().endsWith(".png")) {
					File popravljenaDatoteka = new File(izbranaDatoteka.getPath() + ".png");
					ImageIO.write(screenshot, "PNG", popravljenaDatoteka);
				} else {
					ImageIO.write(screenshot, "PNG", izbranaDatoteka);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();}
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Okno frame = new Okno();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);
        
        //SlikaJulijajeva.setDimensions(frame.getWidth());
        
    }


    /*public javax.swing.JPanel getjPanel2() {
		return platno;
	}
	//public void setjPanel2(javax.swing.JPanel jPanel2) {
		//this.platno = jPanel2;
	//}
	
	*/

}