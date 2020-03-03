package packageRNA;

import java.util.ArrayList;
import org.ejml.simple.SimpleMatrix;


/* Classe qui est connectée à un objet de la classe Net lors de son initialisation.
* 
* Rassemble les informations du RNA sous forme de matrices pour faciliter les calculs de
* forward- et back-propagation.
* ______________________________________________________________________________________
* Attributs :
* 
* ArrayList<double [][]> weights : matrice 2D de tous les poids du RNA
* 
* Net parent : Référence à l'objet Net connecté
*/
public class DataBase {
ArrayList<SimpleMatrix> weights;
Net parent;

//Constructeur
public DataBase(Net parent) {
	this.weights = new ArrayList<SimpleMatrix>();
	this.parent = parent;
	 
}


public static void main(String[] args) {
	
	SimpleMatrix A = new SimpleMatrix(10, 5);
	System.out.println(A);
}
}





