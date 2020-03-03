package packageRNA;

import java.util.ArrayList;


/* Classe qui représente une "couche" du RNA.
 * S'étend aux sous-classes InputLayer, HiddenLayer et OutputLayer.
 * 
 * Un objet Layer garde un ArrayList contenant des objets Neuron.
 * 
 * 
 * _______________________________________________________________________________
 * Attributs:
 * 
 * final Net parent : Référence à l'objet Net qui contient la Layer
 * 
 * ArrayList<Neuron> neurons: ArrayList qui store les objets Neuron de la Layer
 * 
 * int layerSize : Taille de la Layer, en nombre de neurones (inclue les BiasNeuron)
 * 
 * boolean hasBiasNeuron : true si la Layer contient un BiasNeuron
 * 
 */
public class Layer {
	final Net parent;
	ArrayList<Neuron> neurons;
	int layerSize;
	boolean hasBiasNeuron;
	
	//Constructeur
	public Layer(Net parent) {
		this.neurons = new ArrayList<Neuron>();
		this.layerSize = 0;
		this.parent = parent;
		this.hasBiasNeuron = false;
	}
	
	
	@Override
	public String toString() {
		return "Layer [parent=" + parent + ", neurons=" + neurons + ", layerSize=" + layerSize + "]";
	}
	

	/* Rajoute un objet Neuron (RegularNeuron ou BiasNeuron) à la Layer
	 * ________________________________________________________________
	 * Paramètres :
	 * 
	 * String neuron_type : "regular" ou "bias"
	 */
	public void addNeuron(String neuron_type) {
		Neuron new_neuron = new Neuron("sigmoid", 0.0);
		
		switch (neuron_type) {
			case "regular":
				new_neuron = new RegularNeuron();
				this.neurons.add(new_neuron);
				break;
			case "bias":
				new_neuron = new BiasNeuron();
				this.neurons.add(new_neuron);
				break;
		}
		this.layerSize += 1;
	}


	
	
}


/* TODO Sous-classe de Layer
 */
class InputLayer extends Layer {

	public InputLayer(Net parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	
}


/* TODO Sous-classe de Layer
 */
class HiddenLayer extends Layer {

	public HiddenLayer(Net parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	

}


/* TODO Sous-classe de Layer
 */
class OutputLayer extends Layer {

	public OutputLayer(Net parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}
	

}