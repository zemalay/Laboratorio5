package lab5;

import exceptions.CriaRoboException;
import exceptions.CriaSalaException;
import exceptions.MoveRoboException;
import exceptions.PosicoesException;

/**
 * @author efrem
 *
 * 29 de out de 2017
 * Classe executavel simular a sala e o robo
 */
public class Main {
	public static void main(String[] args) {
		try {
			Sala sala = new Sala(2,2);
			sala.inserirObstaculo(0, 0);
			sala.inserirObstaculo(0, 1);
			sala.inserirObstaculo(1, 0);
			
			Robo robo = new Robo(sala, 10);
			sala.inserirObstaculo(0, 1);
			robo.moveRobo("DIREITA");
			System.out.println("Posicao atual: "+robo.getPosicaoHorizontalRobo() + ","+ robo.getPosicaoVerticalRobo());
			System.out.println("Energia atual: "+robo.getEnergia());
			System.out.println("QTD Passo atual: "+robo.getPassos());
		} catch (CriaSalaException | CriaRoboException | PosicoesException | MoveRoboException e) {
			System.err.println(e);
		}
		
//	int array[][] = new int[4][2];
//	System.out.println(array.length);
//	for (int i = 0; i < array.length; i++) {
//		for (int j = 0; j < array.length; j++) {
//			System.out.println(i+","+j);
//		}
//	}
	}
}
