package grafos_and_algoritmos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS {
	
	private static ArrayList<Integer> L;
	private static boolean[] marcados;
	
	
	public static boolean esConexo(Grafo g) {
		if(g == null) {
			throw new IllegalArgumentException("Se ingreso un grafo null");
		}
		if(g.tamanio() == 0) {
			return true;
		}
		if(alcanzables(g, 0).size() == g.tamanio()) {
			return true;
		}
		return alcanzables(g, 0).size() == g.tamanio();
	}


	public static Set<Integer> alcanzables(Grafo g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();		
		inicializar(g, origen);
		
		while(L.size()> 0) {
			int i = L.get(0);
			marcados[i] = true;
			ret.add(i);
			
			agregarVecinosPendientes(g, i);
			L.remove(0);
		}
		return ret;
	}


	private static void agregarVecinosPendientes(Grafo g, int i) {
		for(int vertice : g.vecinos(i)) {
			if(marcados[vertice] == false && L.contains(vertice) == false) {
				L.add(vertice);
			}
		}
	}
	
	
	public static boolean circuito(Grafo g , int origen ,int destino) {
		Set<Integer> alcanzables =  BFS.alcanzables(g, origen);
		return alcanzables.contains(destino);
	}


	private static void inicializar(Grafo g, int origen) {
		L = new ArrayList<Integer>();	
		L.add(origen);
		marcados = new boolean[g.tamanio()];		
	}
}
