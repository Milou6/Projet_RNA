package packageRNA;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Panel;
import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.io.Console;
import java.awt.event.ActionEvent;


public class ApplicationWindow {

	private JFrame mainFrame;
	private TextArea txtConsoleOutput;
	private Panel pnlRNA;
	private Net myNet;
	private Button btnAddLayer;
	private Button btnPopLayer;
	private Button btnImportData;
	private Button btnTrain;
	private Button btnPredict;
	private Button btnPrint;
	
	private double [][] x_test;
	private double [][] y_test;
//	private XYSeriesCollection dataset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow window = new ApplicationWindow();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		
		mainFrame = new JFrame();
		mainFrame.setTitle("Projet RNA");
		mainFrame.setBounds(100, 100, 800, 610);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new MigLayout("", "[grow][fill]", "[50px:50px][50px:50px][50px:50px][50px:50px][50px:50px][50px:50px][5px:n,grow][]"));
		
		pnlRNA = new Panel();
		pnlRNA.setBackground(Color.WHITE);
			
		
		mainFrame.getContentPane().add(pnlRNA, "cell 0 0 1 7,grow");
		
		
		
		// Ajout de couche
		btnAddLayer = new Button("addLayer");
		btnAddLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On rajoute 3 layers à l'objet
				myNet.addLayer("input", "sigmoid", 2, true);
				myNet.addLayer("hidden", "sigmoid", 4, true);
//				myNet.addLayer("hidden", "sigmoid", 4, true);
//				myNet.addLayer("hidden", "sigmoid", 4, true);
				myNet.addLayer("output", "sigmoid", 1, false);
				
				txtConsoleOutput.append("\n Création des couches efféctuées");
				btnPopLayer.setEnabled(true);
				btnImportData.setEnabled(true);
				//btnPrint.setEnabled(true);
			}
		});
		mainFrame.getContentPane().add(btnAddLayer, "cell 1 0,grow");
		
		
		
		//suppresion de couche
		btnPopLayer = new Button("popLayer");
		btnPopLayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnPopLayer.setEnabled(false);
		mainFrame.getContentPane().add(btnPopLayer, "cell 1 1,grow");
		
		
		
		// Importation des données
		btnImportData = new Button("Importer donn\u00E9es d'input");
		btnImportData.setEnabled(false);
		btnImportData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				// Tableau XOR: nos données d'input pour l'entrainement du Réseau
				final double [][] x_import = {{0,0}, {0,1}, {1,0}, {1,1}};
				final double [][] y_import = {{0}, {1}, {1}, {0}};
				
//				final double [][] y_import =  { {0},{0.198669331},{0.389418342},{0.564642473},{0.717356091},{0.841470985},{0.932039086},{0.98544973},{0.999573603},{0.973847631},{0.909297427},{0.808496404},{0.675463181},{0.515501372},{0.33498815},{0.141120008},{-0.058374143},{-0.255541102},{-0.442520443},{-0.611857891},{-0.756802495},{-0.871575772},{-0.951602074},{-0.993691004},{-0.996164609},{-0.958924275},{-0.883454656},{-0.772764488},{-0.631266638},{-0.464602179},{-0.279415498},{-0.083089403},{0.116549205},{0.311541364},{0.494113351},{0.656986599},{0.793667864},{0.898708096},{0.967919672},{0.998543345},{0.989358247},{0.940730557} };		
//				final double [][] x_import = { {0},{0.2},{0.4},{0.6},{0.8},{1},{1.2},{1.4},{1.6},{1.8},{2},{2.2},{2.4},{2.6},{2.8},{3},{3.2},{3.4},{3.6},{3.8},{4},{4.2},{4.4},{4.6},{4.8},{5},{5.2},{5.4},{5.6},{5.8},{6},{6.2},{6.4},{6.6},{6.8},{7},{7.2},{7.4},{7.6},{7.8},{8},{8.2} };
				

//				final double [][] x_import = {{1,3}, {2,4}, {3,1}, {4,2}};
//				final double [][] y_import = {{0,0}, {1,1}, {0,0}, {1,1}};
				
//				final double [][] x_import = {{1,1}, {1,2}, {2,1}, {2,2}, {3,3}, {3,4}, {4,3}, {4,4},
//											{1,3}, {1,4}, {2,3}, {2,4}, {3,1}, {3,2}, {4,1}, {4,4}};
//				final double [][] y_import = {{0}, {0},{0}, {0},{0}, {0},{0}, {0},  
//											{1}, {1},{1}, {1},{1}, {1},{1}, {1}};
				
				// logic gate AND
//				final double [][] x_import = {{0,0}, {0,1}, {1,0}, {1,1}};
//				final double [][] y_import = {{0}, {0}, {0}, {1}};
				
				// x au carré
//				final int [][] x_import = {{1}, {3}, {12}, {11}, {2}, {4}};
//				final int [][] y_import = {{1}, {9}, {144}, {121}, {4}, {16}};
				
				// nombre pair?
//				final int [][] x_import = {{1}, {3}, {12}, {11}, {2}, {4}, {8}, {10}, {32}, {5}, {9}};
//				final int [][] y_import = {{0}, {0}, {1}, {0}, {1}, {1}, {1}, {1}, {1}, {0}, {0}};
				
				
				//import des valeurs de tests
				x_test = x_import;
				y_test = y_import;
				
				txtConsoleOutput.append("\n Importations efféctuées");
				btnTrain.setEnabled(true);
			}
		});
		mainFrame.getContentPane().add(btnImportData, "cell 1 2,grow");
		
		
		
		
		//entrainement 
		btnTrain = new Button("train");
		btnTrain.setEnabled(false);
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myNet.train(x_test, y_test,  2000, 3);
				//txtConsoleOutput.append(txtConsoleOutput.toString());
				//txtConsoleOutput.append("\n\n" + System.in.toString());
				
				txtConsoleOutput.append("\n\n" + myNet.networkError.toString());
				
				XYSeriesCollection dataset = new XYSeriesCollection();
				XYSeries series1 = new XYSeries("Object 1", false);
				
				for (int i=0; i<myNet.networkError.size()-1; i++) {
					series1.add(i, myNet.networkError.get(i));
				}
				dataset.addSeries(series1);
				
				JFreeChart chart = ChartFactory.createXYLineChart("Network_Error", "error", "Epoch", dataset);
				chart.createBufferedImage(500, 500);
				
				ChartPanel CP = new ChartPanel(chart);
				pnlRNA.removeAll();
				pnlRNA.add(CP,BorderLayout.CENTER);
				pnlRNA.validate();
				
			}
		});
		mainFrame.getContentPane().add(btnTrain, "cell 1 3,grow");
		
		
		
		//prédiction sur les nouvelles donées
		btnPredict = new Button("predict");
		btnPredict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPredict.setEnabled(false);
		mainFrame.getContentPane().add(btnPredict, "cell 1 4,grow");
		
		
		
		
		//affichage
		btnPrint = new Button("print");
		btnPrint.setEnabled(false);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtConsoleOutput.append(myNet.print());
				//myNet.print();
			}
		});
		mainFrame.getContentPane().add(btnPrint, "cell 1 5,grow");
		
		txtConsoleOutput = new TextArea();
		txtConsoleOutput.setEditable(false);
		txtConsoleOutput.setForeground(Color.GREEN);
		txtConsoleOutput.setBackground(Color.BLACK);
		txtConsoleOutput.setText("Sortie Console :");
		mainFrame.getContentPane().add(txtConsoleOutput, "cell 0 7 2 1,grow");
		
		//création du résaux de neurone
		myNet = new Net();
		txtConsoleOutput.append("\n Résaux de neurone Créer");
		
	}
}


