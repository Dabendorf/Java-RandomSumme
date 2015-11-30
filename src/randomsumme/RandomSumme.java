package randomsumme;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

/**
 * Dies ist die einzige Klasse des Projektes RandomSumme, welche einerseits die Grafik und andererseits die Generierung und Addition der Zahlen uebernimmt.
 * 
 * @author Lukas Schramm
 * @version 1.0
 * 
 */
public class RandomSumme {
	
	private JFrame frame1 = new JFrame("Summe zufälliger Zahlen");
	private JLabel labelVon = new JLabel();
	private JLabel labelBis = new JLabel();
	private JLabel labelSumAusgabe = new JLabel();
	private NumberFormat format1 = NumberFormat.getInstance(); 
	private NumberFormat format2 = NumberFormat.getInstance();
	private NumberFormat format3 = NumberFormat.getInstance();
	private NumberFormatter formatter1 = new NumberFormatter(format1);
    private NumberFormatter formatter2 = new NumberFormatter(format2);
    private NumberFormatter formatter3 = new NumberFormatter(format3);
	private JFormattedTextField feldZahlA = new JFormattedTextField(formatter1);
	private JFormattedTextField feldZahlB = new JFormattedTextField(formatter2);
	private JFormattedTextField feldAnzahl = new JFormattedTextField(formatter3);
	private JButton buttonRechnen = new JButton();
	private JList<String> zufallsliste = new JList<String>();
	private DefaultListModel<String> zufallslisteModel = new DefaultListModel<String>();
	private JScrollPane zufallslisteScrollPane = new JScrollPane(zufallsliste);

	public RandomSumme() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(400,300));
		frame1.setMinimumSize(new Dimension(400,300));
		frame1.setMaximumSize(new Dimension(600,450));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridBagLayout());
		
		JPanel sumpanel = new JPanel();
		sumpanel.setLayout(new BorderLayout());
		JLabel jLabel1 = new JLabel();
		sumpanel.add(jLabel1,BorderLayout.NORTH);
		JPanel vonBis = new JPanel();
		vonBis.setLayout(new FlowLayout());
		vonBis.add(labelVon);
		vonBis.add(feldZahlA);
		vonBis.add(labelBis);
		vonBis.add(feldZahlB);
		JPanel zwisch1 = new JPanel();
		JPanel zwisch2 = new JPanel();
		JPanel zwisch3 = new JPanel();
		JPanel zwisch4 = new JPanel();
		zwisch1.setLayout(new BorderLayout());
		zwisch2.setLayout(new BorderLayout());
		zwisch3.setLayout(new BorderLayout());
		zwisch4.setLayout(new BorderLayout());
		
		zwisch1.add(vonBis,BorderLayout.NORTH);
		zwisch1.add(zwisch2,BorderLayout.CENTER);
		zwisch2.add(buttonRechnen,BorderLayout.NORTH);
		zwisch2.add(zwisch3,BorderLayout.CENTER);
		zwisch4.add(new JLabel(" Anzahl:"),BorderLayout.WEST);
		zwisch4.add(feldAnzahl,BorderLayout.CENTER);
		zwisch3.add(zwisch4,BorderLayout.NORTH);
		zwisch3.add(labelSumAusgabe,BorderLayout.CENTER);
		
		sumpanel.add(zwisch1,BorderLayout.CENTER);
		
		JPanel quadpanel = new JPanel();
		quadpanel.setLayout(new BorderLayout());
		JLabel jLabelQuadratzahltext = new JLabel("Zufallszahlen");
		quadpanel.add(jLabelQuadratzahltext,BorderLayout.NORTH);
		quadpanel.add(zufallslisteScrollPane,BorderLayout.CENTER);
		
	    labelVon.setText("Von");
	    feldZahlA.setText("100");
	    labelBis.setText("bis");
	    feldZahlB.setText("300");
	    feldAnzahl.setText("100");
	    buttonRechnen.setText("Berechne");
	    buttonRechnen.setMargin(new Insets(2, 2, 2, 2));
	    buttonRechnen.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent evt) { 
	    		berechnen();
	    	}
	    });
	    labelSumAusgabe.setText("");
	    labelSumAusgabe.setHorizontalAlignment(SwingConstants.CENTER);
	    labelSumAusgabe.setVerticalAlignment(SwingConstants.NORTH);
	    feldAnzahl.setHorizontalAlignment(SwingConstants.RIGHT);
	    zufallsliste.setModel(zufallslisteModel);
	    
	    format1.setGroupingUsed(false);
	    format2.setGroupingUsed(false);
	    format3.setGroupingUsed(false);
	    formatter1.setAllowsInvalid(false);
	    formatter2.setAllowsInvalid(false);
	    formatter3.setAllowsInvalid(false);
		
		frame1.add(sumpanel,new GridBagFelder(0,0,1,1,0.6,1));
	    frame1.add(quadpanel,new GridBagFelder(1,0,1,1,0.4,1));
	    sumpanel.setPreferredSize(new Dimension(0,0));
	    quadpanel.setPreferredSize(new Dimension(0,0));
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}
	
	/**
	 * Diese Methode liest die Werte ein und addiert alle aus dem Intervall generierten Zufallszahlen.<br>
	 * Ausserdem gibt sie alle Zufallszahlen in einer Liste aus.
	 */
	private void berechnen() {
		try {
			int a = Integer.parseInt(feldZahlA.getText());
		    int b = Integer.parseInt(feldZahlB.getText());
			int anzahl = Integer.parseInt(feldAnzahl.getText());
			if(a>b) {
		    	int temp = a;
		    	a = b;
		    	b = temp;
		    	feldZahlA.setText(String.valueOf(a));
		    	feldZahlB.setText(String.valueOf(b));
		    }
			
			zufallslisteModel.clear();
		    Random wuerfel = new Random();
		    long summe = 0;
		    for(int i=0;i<anzahl;i++) {
		    	int z = wuerfel.nextInt(b-a+1)+a;
		    	summe += z;
		    	zufallslisteModel.addElement(String.valueOf(z));
		    }
		    labelSumAusgabe.setText("Σ: "+summe);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Du hast falsche Werte eingetragen."+System.getProperty("line.separator")+"Wenn Du dies nicht korrigierst"+System.getProperty("line.separator")+"bekommst Du kein Ergebnis!", "Falscheingabe", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new RandomSumme();
	}
}