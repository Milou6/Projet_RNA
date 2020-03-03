package packageRNA;

import java.util.ArrayList;
import java.util.Arrays;
import org.ejml.simple.SimpleMatrix;


/* Classe de base du package.
 * 
 * � l'initialisation, un objet Net est connect� � un objet de la classe DataBase.
 * 
 * L'objet Net peut instantier plusieurs objets Layer, qui forment les "couches" du RNA.
 * Chaque objet Layer contient des objets Neuron.
 * ____________________________________________________________________________________
 * Attributs:
 * 
 * ArrayList<Layer> layers : ArrayList qui store des objets Layer
 * 
 * DataBase netDataBase : R�f�rence � l'objet DataBase connect�
 */
public class Net {
	ArrayList<Layer> layers;
	DataBase netDataBase;
	
	//Constructeur
	public Net() {
		this.layers = new ArrayList<Layer>();
		this.netDataBase = new DataBase(this);
	}
	
	
	/* Rajoute un objet Layer � ArrayList<Layer> layers
	 * ___________________________________________________________________________________________
	 * Param�tres :
	 * 
	 * String layer_type : "input", "hidden" ou "output"
	 * 
	 * int layer_size : le nombre de Neuron � ajouter � l'objet Layer (ne compte pas le BiasNeuron)
	 * 
	 * boolean add_bias_neuron : Si true, rajoute un BiasNeuron comme dernier �l�ment de la Layer
	 */
	public void addLayer(String layer_type, int layer_size, boolean add_bias_neuron) {
		Layer new_layer = new Layer(this);
		//new_layer.parent = this;
		//Switch instancie diff�rents types de layer selon le param�tre
		switch(layer_type) {
			case "input":
				new_layer = new InputLayer(this);
				break;
			case "hidden":
				new_layer = new HiddenLayer(this);
				break;
			case "output":
				new_layer = new OutputLayer(this);
				break;
		}
		//On rajoute la layer vide � Net.layers
		layers.add(new_layer);
		//On rajoute les neurones � la layer
		for (int i=0; i<layer_size; i++) {
			new_layer.addNeuron("regular");
		}
		//Rajout du neurone biais
		if (add_bias_neuron == true) {
			new_layer.addNeuron("bias");
			new_layer.hasBiasNeuron = true;
		}
	}

	
	
	private void print() {
		System.out.println("\n Network layers: " + layers.size());
		
		for (Layer i : layers) {
			System.out.println(i);
			for (Neuron n : i.neurons) {
				System.out.println(n);
			}
			System.out.println("\n");
		}
	}
	
	/* M�thode qui entra�ne le RNA, en appliquant la forward- et back-propagation
	 * successivement pendant X epochs.
	 * __________________________________________________________________________
	 * Param�tres :
	 * 
	 */
	public void train() {
		DataBase dataBase = this.netDataBase;
		this.initializeWeights();
		
//		double[]activation_matrix = 
	}
	
	
	/* M�thode appel�e � l'int�rieur de Net.train()
	 * Initialise tous les poids du RNA � des valeurs al�atoires dans [0, 1]
	 * 
	 */
	public void initializeWeights() {
		//On mod�lise chaque "espace" entre 2 layers par une matrice de weights[][] qui connectent ces 2 layers
		for (int i=0; i<layers.size()-1; i++) {
			int current_layer_size = layers.get(i).layerSize;
			if (layers.get(i).hasBiasNeuron == true) {current_layer_size -= 1;}
			int next_layer_size = layers.get(i+1).layerSize;
			if (layers.get(i+1).hasBiasNeuron == true) {next_layer_size -= 1;}
			
			//Chaque matrice est initialis�e avec des valeurs al�atoires
			double [][] new_weights = new double[next_layer_size][current_layer_size];
			for (int j=0; j<new_weights.length; j++) {
				for (int k=0; k<new_weights[j].length; k++) {
				new_weights[j][k] = Math.random();
				}
			}
			SimpleMatrix weights_matrix = new SimpleMatrix(new_weights);
			//On rajoute chaque matrice � l'objet DataBase
			this.netDataBase.weights.add(weights_matrix);
		}
		
		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void main(String[] args) {
		
		// Tableau XOR: nos donn�es d'input pour l'entrainement du R�seau
		final int [][] x_test = {{0,0}, {0,1}, {1,0}, {1,1}};
		final int [] y_test = {0,1,1,0};

		
		//Cr�ation d'un objet Net
		Net myNet = new Net();
		
		//On rajoute 3 layers � l'objet
		myNet.addLayer("input", 2, true);
		myNet.addLayer("hidden", 4, true);
		myNet.addLayer("output", 1, false);
//		System.out.println(myNet.layers.get(0).neurons);
		
		//TEMPORAIRE POUR TESTER
		myNet.layers.get(0).neurons.get(0).activation = 2;
		myNet.layers.get(0).neurons.get(1).activation = 4;
		
		
		myNet.print();
		myNet.train();	
//		System.out.println(myNet.layers.get(0).hasBiasNeuron);
		
		//TEMPORAIRE POUR TESTER
		System.out.println(myNet.netDataBase.weights.get(0));
		//System.out.println(Arrays.deepToString(myNet.netDataBase.weights.get(1)));
		


	}
}
