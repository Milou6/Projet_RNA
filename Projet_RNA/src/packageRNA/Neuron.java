package packageRNA;

import java.util.ArrayList;


/* Classe qui représente les neurones individuels du RNA.
 * S'étend aux sous-classes RegularNeuron et BiasNeuron.
 * 
 * _______________________________________________________________________________
 * Attributs:
 * 
 * String activation_function : Définit à la construction le type de fonction d'activation du neurone ("sigmoid", "tanh")
 * 
 * double activation : La valeur actuelle du neurone. Par défaut 0 pour les RegularNeuron, et 1 pour les BiasNeuron
 * 
 * ArrayList<Double> forwardWeights : Contient les poids partant du neurone actuel, vers tous les RegularNeuron de la Layer suivante
 */
public class Neuron {
	String activation_function;
	double activation;
	ArrayList<Double> forwardWeights;
	
	//Constructeur
	public Neuron(String activation_function, double activation) {
		// TODO Auto-generated constructor stub
		this.activation_function = activation_function;
		this.activation = activation;
		this.forwardWeights = new ArrayList<Double>();
	}
	
	@Override
	public String toString() {
		return "Neuron [activation_function=" + activation_function + ", activation=" + activation + ", forwardWeights="
				+ forwardWeights + "]";
	}

}


/* TODO Sous-classe de Neuron
 */
class RegularNeuron extends Neuron {
	
	public RegularNeuron() {
		super("sigmoid", 0.0);
		
	}
}


/* TODO Sous-classe de Layer
 */
class BiasNeuron extends Neuron {
	
	public BiasNeuron() {
		super("sigmoid", 1.0);
		
	}
}