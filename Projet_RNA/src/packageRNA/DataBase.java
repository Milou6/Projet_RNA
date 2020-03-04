package packageRNA;

import java.util.ArrayList;
import org.apache.commons.math3.*;
import org.apache.commons.math3.linear.*;


/* Classe qui est connect�e � un objet de la classe Net lors de son initialisation.
* 
* Rassemble les informations du RNA sous forme de matrices pour faciliter les calculs de
* forward- et back-propagation.
* ______________________________________________________________________________________
* Attributs :
* 
* ArrayList<double [][]> weights : matrice 2D de tous les poids du RNA
* 
* Net parent : R�f�rence � l'objet Net connect�
*/
public class DataBase {
//ArrayList<SimpleMatrix> weights;
Net parent;

//Constructeur
public DataBase(Net parent) {
//	this.weights = new ArrayList<SimpleMatrix>();
	this.parent = parent;
	 
}


public static void main(String[] args) {
	
//	SimpleMatrix A = new SimpleMatrix(10, 5);
//	System.out.println(A);
	double[][] matrixData = { {1d,2d,3d}, {2d,5d,3d}};
	RealMatrix m = MatrixUtils.createRealMatrix(matrixData);
	System.out.println(m);

}
}





