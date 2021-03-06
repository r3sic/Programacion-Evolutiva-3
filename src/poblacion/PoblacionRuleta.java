package poblacion;

import java.util.Random;

import cromosoma.Cromosoma;
import cromosoma.FactoriaCromosoma;

public class PoblacionRuleta extends Poblacion{

	public PoblacionRuleta(int tam, String mut, double elitismo, String ini, int hMax, boolean creciente) {
		super(tam, mut, elitismo, ini, hMax, creciente);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void seleccion() {
		// TODO Auto-generated method stub
		Cromosoma[] pob_aux = new Cromosoma[_tam];
		Random r = new Random();
		double seleccionado;
		int pos;
		double traslacion_acum = 0;
	    double traslacion_punt[] = new double[_tam];
		int minimo = buscarMenor(_tam, _pob);
	    if (_pob[minimo].aptitud() < 0){
	    	traslacion_punt[0] = _pob[0].aptitud() + - _pob[minimo].aptitud();
	    	for(int i = 1; i < _tam; i++) {
	     		traslacion_punt[i] = traslacion_punt[i-1]+_pob[i].aptitud() - _pob[minimo].aptitud();
	     	}
	     	traslacion_acum = traslacion_punt[_tam-1];
			for(int i = 0; i < _tam; i++) {
				seleccionado = r.nextDouble()*traslacion_acum;
				pos = 0;
				boolean encontrado = false;
				while(pos < _tam && !encontrado) {
					if(seleccionado <= traslacion_punt[pos])
						encontrado = true;
					else pos++;
				}
				if(!encontrado) pos = _tam-1;
				pob_aux[i] = FactoriaCromosoma.getCromosomaCopia(_pob[pos]);
			}
	    }
	    else {
			for(int i = 0; i < _tam; i++) {
				seleccionado = r.nextDouble()*_suma_aptitud;
				pos = 0;
				boolean encontrado = false;
				while(pos < _tam && !encontrado) {
					if(seleccionado <= _punt_acum[pos])
						encontrado = true;
					else pos++;
				}
				if(!encontrado) pos = _tam-1;
				pob_aux[i] = FactoriaCromosoma.getCromosomaCopia(_pob[pos]);
			}
		}
		System.arraycopy(pob_aux, 0, _pob, 0, _tam);
	}
}
