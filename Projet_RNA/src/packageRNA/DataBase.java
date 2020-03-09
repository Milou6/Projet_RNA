package packageRNA;

import java.util.ArrayList;
import java.util.Arrays;

//import org.apache.commons.math3.*;
import org.apache.commons.math3.linear.*;


/* Classe qui est connectée à un objet de la classe Net lors de son initialisation.
* 
* Rassemble les informations du RNA sous forme de matrices pour faciliter les calculs de
* forward- et back-propagation.
* ______________________________________________________________________________________
* Attributs :
* 
* ArrayList<RealMatrix> weights : Liste de matrices 2D contenant tous les poids (entre Layers) du RNA
* 
* ArrayList<RealMatrix> activations : Liste de matrices 1D (vecteurs) contenant les activations de chaque Layer du RNA
* 
* Net parent : Référence à l'objet Net connecté
*/
public class DataBase {
	ArrayList<RealMatrix> weights;
	ArrayList<RealMatrix> activations;
	Net parent;
	
	//Constructeur
	public DataBase(Net parent) {
		this.weights = new ArrayList<RealMatrix>();
		this.activations = new ArrayList<RealMatrix>();
		this.parent = parent;
		 
	}
	
	  public void print() {
		  if (weights.size() > 0) {
			  System.out.println("\n DATABASE WEIGTHS PRINT :");
			  for (int i=0; i<weights.size(); i++) {
				  double[][] matrix_data = weights.get(i).getData();
				  System.out.println("\n Weights between Layer " + i + " --- Layer " + (i+1) + " :");
				  for (double[] row : matrix_data) {
					  System.out.println(Arrays.toString(row));
				  }
			  }
		  }	  
		  if (activations.size() > 0) {
			  System.out.println("\n DATABASE ACTIVATIONS PRINT :");
			  for (int i=0; i<activations.size(); i++) {
				  double[][] matrix_data = activations.get(i).getData();
				  System.out.println("\n Activations in Layer " + i + " :");
				  for (double[] row : matrix_data) {
					  System.out.println(Arrays.toString(row));
				  }
			  }
		  }
	  }
	
	public static RealMatrix sigmoid(RealMatrix x) {
		for (int row=0; row<x.getRowDimension(); row++) {
			x.setEntry(row, 0, (1/( 1 + Math.pow(Math.E,(-1*x.getEntry(row, 0))))) );
		}
		return x;
	}
	
	  
  
	
	/* Prends les poids stockés dans DataBase.weights et les envoie aux neurones
	* correspondants du RNA.
	*/
	public void sendWeightsToNeurons() {
		for (int i=0; i<parent.layers.size()-1; i++) {
			Layer current_layer = parent.layers.get(i);
			for (int j=0; j<current_layer.layerSize; j++) {
				Neuron current_neuron = current_layer.neurons.get(j);
				current_neuron.forwardWeights = weights.get(i).getColumnMatrix(j);
			}
		}
	}
	
	
	/* Prends les ativations stockées dans DataBase.activations et les envoie aux neurones
	* correspondants du RNA.
	*/
	public void sendActivationsToNeurons() {
		for (int i=0; i<parent.layers.size(); i++) {
			Layer current_layer = parent.layers.get(i);
			for (int j=0; j<current_layer.layerSize; j++) {
				Neuron current_neuron = current_layer.neurons.get(j);
				current_neuron.activation = this.activations.get(i).getEntry(j, 0);
			}
		}
	}
	
	
	

	// Classe main pour Test seulement.
	public static void main(String[] args) {	
		double[][] matrixData = { {7d,2d,3d}, {2d,5d,3d}};
		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
		System.out.println(m);
//		
//		
//		RealMatrix mat = MatrixUtils.createRealMatrix(1, 1);
//		System.out.println(mat);
//		
//		mat = m.copy();
//		System.out.println(mat);
		
		m = sigmoid(m);
		System.out.println(m);
		System.out.println("non");
	}
}


// Salut j'aime les pattes



