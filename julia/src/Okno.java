import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")
public class Okno extends JFrame implements MouseListener {
	
    private javax.swing.JComboBox<String> izbranaMnozica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel platno;
    private javax.swing.JTextField lambdaRe;
    private javax.swing.JTextField lambdaIm;
    private javax.swing.JButton narisi;
    private javax.swing.JButton barvaj;
    private javax.swing.JButton shrani;
    private javax.swing.JTextField zoomxmin;
    private javax.swing.JTextField zoomymin;
    private javax.swing.JTextField zoomsirina;
    private javax.swing.JLabel zxmin;
    private javax.swing.JLabel zymin;
    private javax.swing.JLabel zsirina;
    private javax.swing.JButton zoom;
    
    private int x = 250;
    private int y = 250;
    private int steviloKlikov = 0;

    /**
     * Creates new form NewFrame
     */
    public Okno() {
        initComponents();
        platno.setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//	komponente v oknu
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();	//platno, na katerem so gumbi in okna za pisat
        jPanel2 = new javax.swing.JPanel(); 
        platno = new Slika();	//glavno platno, ki nariše sliko
        izbranaMnozica = new javax.swing.JComboBox<>();	//izbor množice, ki jo naj nariše
        //Vnosna polja:
        lambdaRe = new javax.swing.JTextField();	//za realni del spremenljivke (Juliajeva množica)
        lambdaIm = new javax.swing.JTextField();	//za imaginarni del spremenljivke (Juliajeva množica)
        //Gumbi:
        narisi = new javax.swing.JButton();	//gumb "nariši"
        barvaj = new javax.swing.JButton(); //gumb "barvaj"
        shrani = new javax.swing.JButton();	//gumb "shrani"
        zoomxmin = new javax.swing.JTextField();
        zoomymin = new javax.swing.JTextField();
        zoomsirina = new javax.swing.JTextField();
        zxmin = new javax.swing.JLabel("xmin");
        zymin = new javax.swing.JLabel("ymin");
        zsirina = new javax.swing.JLabel("sirina");
        zoom = new javax.swing.JButton();

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
        jPanel2.setLayout(new java.awt.GridLayout());;
        
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
        
        jPanel2.add(zxmin, new java.awt.GridBagConstraints());
        
        zoomxmin.setText("0");
        jPanel2.add(zoomxmin, new java.awt.GridBagLayout());
        
        jPanel2.add(zymin, new java.awt.GridBagConstraints());
        
        zoomymin.setText("0");
        jPanel2.add(zoomymin, new java.awt.GridBagLayout());
        
        jPanel2.add(zsirina,  new java.awt.GridBagLayout());
        
        zoomsirina.setText("500");
        jPanel2.add(zoomsirina, new java.awt.GridBagConstraints());
        
        zoom.setText("zoom");
        zoom.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		zoomActionPerformed(evt);
        	}
        });
        jPanel2.add(zoom, new java.awt.GridBagConstraints());
        
        add(jPanel1, java.awt.BorderLayout.NORTH);
        add(jPanel2, java.awt.BorderLayout.SOUTH);

        platno.setBackground(new java.awt.Color(0, 0, 0));
        add(platno, java.awt.BorderLayout.CENTER);
        platno.addMouseListener(this);

        pack();
    }
    


	/**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    private void formComponentResized(java.awt.event.ComponentEvent evt) {
        int W = 4;  
        int H = 4;  
        Rectangle b = evt.getComponent().getBounds();
        evt.getComponent().setBounds(b.x, b.y, b.width, b.width+80*H/W);
        Slika.setDimensions(platno.getHeight());
        this.zoomsirina.setText(""+platno.getHeight());
        setSlika();
        this.repaint();
        }
    
	private void setSlika(){
    	Slika.setIm(Double.parseDouble(lambdaIm.getText()));
    	Slika.setRe(Double.parseDouble(lambdaRe.getText()));
    	Slika.setMnozica(izbranaMnozica.getSelectedItem().toString());
    	Slika.setXmin(Integer.parseInt(zoomxmin.getText()));
    	Slika.setYmin(Integer.parseInt(zoomymin.getText()));
    	Slika.setSirina(Integer.parseInt(zoomsirina.getText()));
		Slika.setSteviloKlikov(this.steviloKlikov);
    	Slika.setXkomponenta(this.x);
    	Slika.setYkomponenta(this.y);
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
    	this.x= platno.getHeight()/2;
    	this.y = platno.getWidth()/2;
    	this.steviloKlikov = 0;
    	this.zoomsirina.setText("" + this.platno.getHeight());
    	this.zoomxmin.setText("0");;
    	this.zoomymin.setText("0");;
    	setSlika();
    	this.repaint();
    }
    
    public void barvajActionPerformed(ActionEvent evt){
    	Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
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
			e1.printStackTrace();}
    }
    
    protected void zoomActionPerformed(ActionEvent evt) {
    	setSlika();
    	this.repaint();
    } 
    
    public static void main(String[] args) {
        Okno frame = new Okno();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 500);        
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		
		if(this.izbranaMnozica.getSelectedItem().toString() != "IFS"){
			Slika.setXmin(Slika.getXmin() + x*Slika.getSirina()/platno.getHeight() - Integer.parseInt(zoomsirina.getText())/4);
			Slika.setYmin(Slika.getYmin() + y*Slika.getSirina()/platno.getHeight() - Integer.parseInt(zoomsirina.getText())/4);
			this.zoomxmin.setText("" + Slika.getXmin());
			this.zoomymin.setText("" + Slika.getYmin());
			
			Slika.setSirina(Slika.sirina/2);		
			this.zoomsirina.setText("" + Slika.getSirina());
	
			setSlika();
			this.repaint();
		} 

	}
	
	public int potenca(int koliko, int na) {
		int i = 0;
		int stevilo = 1;
		while (i<na) {
			stevilo = stevilo * koliko;
			i += 1;
		};
		return stevilo;
	};

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}