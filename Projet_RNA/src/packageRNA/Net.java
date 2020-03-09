package packageRNA;

import java.util.ArrayList;
import org.apache.commons.math3.linear.*;


/* Classe de base du package.
 * 
 * À l'initialisation, un objet Net est connecté à un objet de la classe DataBase.
 * 
 * L'objet Net peut instantier plusieurs objets Layer, qui forment les "couches" du RNA.
 * Chaque objet Layer contient des objets Neuron.
 * ____________________________________________________________________________________
 * Attributs:
 * 
 * ArrayList<Layer> layers : ArrayList qui store des objets Layer
 * 
 * DataBase netDataBase : Référence à l'objet DataBase connecté
 */
public class Net {
	ArrayList<Layer> layers;
	DataBase netDataBase;
	
	//Constructeur
	public Net() {
		this.layers = new ArrayList<Layer>();
		this.netDataBase = new DataBase(this);
	}
	
	private void print() {
		System.out.println("\n NETWORK PRINT ( " + layers.size() + " layers )");
		
		for (Layer i : layers) {
			System.out.println("\n Layer " + layers.indexOf(i) + " :");
			for (Neuron n : i.neurons) {
				System.out.println(n);
			}
		}
	}
	
	
	/* Rajoute un objet Layer à ArrayList<Layer> layers
	 * ___________________________________________________________________________________________
	 * Paramètres :
	 * 
	 * String layer_type : "input", "hidden" ou "output"
	 * 
	 * String layer_activation_function : détermine le type de fonction d'actiovation des neurones de la Layer ("sigmoid")
	 * 
	 * int layer_size : le nombre de RegularNeuron à ajouter à l'objet Layer (sans compter le BiasNeuron)
	 * 
	 * boolean add_bias_neuron : Si true, rajoute un BiasNeuron comme dernier élément de la Layer
	 */
	public void addLayer(String layer_type, String layer_activation_function, int layer_size, boolean add_bias_neuron) {
		Layer new_layer = new Layer(this, layer_activation_function);
		//new_layer.parent = this;
		//Switch instancie différents types de layer selon le paramètre
		switch(layer_type) {
			case "input":
				new_layer = new InputLayer(this, layer_activation_function);
				break;
			case "hidden":
				new_layer = new HiddenLayer(this, layer_activation_function);
				break;
			case "output":
				new_layer = new OutputLayer(this, layer_activation_function);
				break;
		}
		//On rajoute la layer vide à Net.layers
		layers.add(new_layer);
		//On rajoute les neurones à la layer
		for (int i=0; i<layer_size; i++) {
			new_layer.addNeuron("regular");
		}
		//Rajout du neurone biais
		if (add_bias_neuron == true) {
			new_layer.addNeuron("bias");
			new_layer.hasBiasNeuron = true;
		}
	}

	
	
	/* Méthode qui entraîne le RNA, en appliquant la forward- et back-propagation
	 * successivement pendant X epochs.
	 * __________________________________________________________________________
	 * Paramètres :
	 * 
	 * int[][] x_test : tableau de données d'Input
	 * 
	 * int[][] y_test : labels (=résultat attendu) des données d'Input
	 * 
	 * int epochs : nombre d'itérations sur la totalité des données d'entraînement
	 */
	public void train(int[][] x_test, int[] y_test, int epochs) {
		//On initialise l'objet DataBase, ainsi que les matrices de poids et activations de l'objet
		DataBase dataBase = this.netDataBase;
		this.initializeWeights();
		this.initializeActivations();
		
		//Boucle des Epochs
		for (int e=0; e<epochs; e++) {
			
			// Boucle des Batch (chaque Batch correspond à une propagation en avant pour chaque entrée des données d'Input) 
//			for (int f=0; f<x_test.length; f++) {
			for (int f=0; f<1; f++) {
				
				//Pour chaque RegularNeuron de la Layer0, on initialise son activation (valeurs des données d'Input)
				for (int n=0; n<layers.get(0).layerSize; n++) {
					Neuron current_neuron = layers.get(0).neurons.get(n);
					if (current_neuron instanceof RegularNeuron) {
						current_neuron.activation = x_test[f][n];
					}
				}
				
				
				//FORWARD-PROP ICI
				for (Layer l : this.layers) {
					l.forwardPropagate(x_test, y_test);
				}
//				this.print();
				
				//Calculer l'erreur entre output du RNA / output réel
				
				
				
				
				//FORWARD-PROP ICI

			}	
		}//Epochs for-loop	
	}
	
	
	/* Méthode appelée à l'intérieur de Net.train()
	 * Crée les matrices de poids du RNA et les rajoute à l'objet DataBase.
	 * Initialise tous les éléments de ces matrices à des valeurs aléatoires dans [0, 1].
	 */
	public void initializeWeights() {
		//On modélise chaque "espace" entre 2 layers par une matrice de weights[][] qui connectent ces 2 layers
		for (int i=0; i<layers.size()-1; i++) {
			int current_layer_size = layers.get(i).layerSize;
			int next_layer_size = layers.get(i+1).layerSize;
			//Les neurones d'une layer(l) ne doivent pas connecter au BiasNeuron de la layer(l+1)
			if (layers.get(i+1).hasBiasNeuron == true) {next_layer_size -= 1;}
			
			//Chaque matrice est initialisée avec des valeurs aléatoires
			double [][] new_weights = new double[next_layer_size][current_layer_size];
			for (int j=0; j<new_weights.length; j++) {
				for (int k=0; k<new_weights[j].length; k++) {
				new_weights[j][k] = Math.random();
				}
			}
			// On transforme le double[][] en RealMatrix
			RealMatrix weights_matrix = new Array2DRowRealMatrix(new_weights);
			//On rajoute chaque matrice à l'objet DataBase
			this.netDataBase.weights.add(weights_matrix);
		}
		//On demande à la DataBase de mettre à jour les poids de chaque neurone
		this.netDataBase.sendWeightsToNeurons();	
	}

	
	/* Méthode appelée à l'intérieur de Net.train()
	 * Crée les matrices d'activations du RNA et les rajoute à l'objet DataBase.
	 * Ces matrices sont remplies de 0.0, sauf pour les index des BiasNeuron (où l'on a des 1.0).
	 */
	public void initializeActivations() {
		for (int i=0; i<layers.size(); i++) {
			int current_layer_size = layers.get(i).layerSize;
			double [][] activation_vector = new double[current_layer_size][1];
			// On transforme le double[][] en RealMatrix
			RealMatrix activations_matrix = new Array2DRowRealMatrix(activation_vector);
			
			// On initialise chaque valeur des activation_matrix
			for (int j=0; j<layers.get(i).layerSize; j++) {
				Neuron current_neuron = layers.get(i).neurons.get(j);
				activations_matrix.setEntry(j, 0, current_neuron.activation);
			}
			//On rajoute le tout à la DataBase.activations
			this.netDataBase.activations.add(activations_matrix);
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public static void main(String[] args) {
		
		// Tableau XOR: nos données d'input pour l'entrainement du Réseau
		final int [][] x_test = {{4,2}, {0,1}, {1,0}, {1,1}};
		final int [] y_test = {0,1,1,0};

		
		//Création d'un objet Net
		Net myNet = new Net();
		
		//On rajoute 3 layers à l'objet
		myNet.addLayer("input", "sigmoid", 2, true);
		myNet.addLayer("hidden", "sigmoid", 4, true);
		myNet.addLayer("output", "sigmoid", 1, false);
//		System.out.println(myNet.layers.get(0).neurons);
		
		//TEMPORAIRE POUR TESTER : 
//		myNet.layers.get(0).neurons.get(0).activation = 2;
//		myNet.layers.get(0).neurons.get(1).activation = 4;
		
		
		
		myNet.train(x_test, y_test, 1);	
		myNet.print();
//		System.out.println(myNet.layers.get(0).hasBiasNeuron);
		
		//TEMPORAIRE POUR TESTER		
		myNet.netDataBase.print();


	}
}
