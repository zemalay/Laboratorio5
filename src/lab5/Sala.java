package lab5;

import java.util.Arrays;

import exceptions.CriaSalaException;
import exceptions.PosicoesException;

/**
 * @author efrem
 *
 *         28 de out de 2017
 *         
 *         Classe Sala
 */

public class Sala {
	private int numPosicoesHorizontais;
	private int numPosicoesVerticais;
	private int sala[][];
	private int tamanho;
	public static int OCUPADO = -1;
	public static int LIVRE = 0;
	
	public Sala(int posicoesHorizontais, int posicoesVerticais) throws CriaSalaException {
		if (posicoesHorizontais <= 0 || posicoesVerticais <= 0) {
			throw new CriaSalaException(
					"Parametros incorretos para a criacao da sala. As dimensoes da sala devem ser representadas por valores positivos maiores que zero");
		}
		this.numPosicoesHorizontais = posicoesHorizontais;
		this.numPosicoesVerticais = posicoesVerticais;
		this.sala = new int[posicoesHorizontais][posicoesVerticais];
		this.tamanho = this.sala.length;
	}
	
	/**
	 * Verificar se a sala esta vazia
	 * @return {@link Boolean}
	 */
	public boolean isVazia() {
		for (int i = 0; i < this.getNumPosicoesHorizontais(); i++) {
			for (int j = 0; j < this.getNumPosicoesVerticais(); j++) {
				int elemento = sala[i][j];

				if (elemento == -1 || elemento == 1) {
					return false;
				}
			}
		}
		return true;

	}

	/**
	 * setar a posicao da sala para (Ocupada ou Livre)
	 * @param posicaoHorizontais
	 * @param posicaoVerticais
	 * @return {@link Boolean}
	 */
	public boolean setPosicao(int posicaoHorizontais, int posicaoVerticais, int status) {
		if (posicaoValida(posicaoHorizontais, posicaoVerticais)) {
			if (status == Sala.OCUPADO) {
				this.sala[posicaoHorizontais][posicaoVerticais] = -1;
			} else if (status == Sala.LIVRE) {
				this.sala[posicaoHorizontais][posicaoVerticais] = 0;
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param posicaoHorizontais
	 * @param posicaoVerticais
	 * @return {@link Boolean}
	 */
	public boolean inserirObstaculo(int posicaoHorizontais, int posicaoVerticais) {
		if (posicaoValida(posicaoHorizontais, posicaoVerticais)) {
			this.sala[posicaoHorizontais][posicaoVerticais] = -1;
			return true;
		}
		return false;
	}

	/**
	 * Verifica se aposicao dada eh valida ou nao
	 * @param posicaoHorizontais
	 * @param posicaoVerticais
	 * @return {@link Boolean}
	 */
	public boolean posicaoValida(int posicaoHorizontais, int posicaoVerticais) {
		if ((posicaoHorizontais < 0 || posicaoVerticais < 0) || (posicaoHorizontais > this.numPosicoesHorizontais - 1
				|| posicaoVerticais > this.numPosicoesVerticais - 1)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verfica se a posicao dada eh livre ou nao
	 * @param posicaoHorizontais
	 * @param posicaoVerticais
	 * @return {@link Boolean}
	 */
	public boolean isPosicaoLivre(int posicaoHorizontais, int posicaoVerticais) throws PosicoesException {
		if (posicaoValida(posicaoHorizontais, posicaoVerticais)) {
			if (this.sala[posicaoHorizontais][posicaoVerticais] == -1) {
				return false;
			}
			return true;
		}
		throw new PosicoesException("Posicao inexistente.");
	}


	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getNumPosicoesHorizontais() {
		return numPosicoesHorizontais;
	}

	public void setNumPosicoesHorizontais(int numPosicoesHorizontais) {
		this.numPosicoesHorizontais = numPosicoesHorizontais;
	}

	public int getNumPosicoesVerticais() {
		return numPosicoesVerticais;
	}

	public void setNumPosicoesVerticais(int numPosicoesVerticais) {
		this.numPosicoesVerticais = numPosicoesVerticais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numPosicoesHorizontais;
		result = prime * result + numPosicoesVerticais;
		result = prime * result + Arrays.deepHashCode(sala);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (numPosicoesHorizontais != other.numPosicoesHorizontais)
			return false;
		if (numPosicoesVerticais != other.numPosicoesVerticais)
			return false;
		if (!Arrays.deepEquals(sala, other.sala))
			return false;
		return true;
	}

}
