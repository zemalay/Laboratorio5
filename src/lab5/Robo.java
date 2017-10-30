package lab5;

import exceptions.CriaRoboException;
import exceptions.MoveRoboException;
import exceptions.PosicoesException;

/**
 * @author efrem
 *
 *         28 de out de 2017
 * 
 *         Classe Robo
 */
public class Robo {
	private Sala sala;
	private int energia;
	private int posicaoHorizontalRobo;
	private int posicaoVerticalRobo;
	private int passos;

	public Robo(Sala sala, int energia) throws CriaRoboException, PosicoesException {
		this.sala = sala;
		if (energia <= 0) {
			throw new CriaRoboException("A sala nao poder ser null ou a energia nao pode ser zero ou negativo");
		}
		setPosicaoRoboInit(0, 0);
		this.passos = 0;
		this.energia = energia;
	}

	/**
	 * Seta a posicao inicial do robo (default 0,0), se a posicao dada for
	 * ocupada, percorre a sala para buscar a posicao livre.
	 * 
	 * @param posicaoHoraizontal
	 * @param posicaoVertical
	 * @return {@link Boolean}
	 * @throws PosicoesException
	 * @throws CriaRoboException
	 */
	private boolean setPosicaoRoboInit(int posicaoHoraizontal, int posicaoVertical)
			throws PosicoesException, CriaRoboException {
		if (!sala.isPosicaoLivre(posicaoHoraizontal, posicaoVertical)) {

			for (int i = 0; i < sala.getNumPosicoesHorizontais(); i++) {
				for (int j = 0; j < sala.getNumPosicoesVerticais(); j++) {
					if (sala.isPosicaoLivre(i, j)) {
						sala.setPosicao(i, j, Sala.OCUPADO);
						this.posicaoHorizontalRobo = i;
						this.posicaoVerticalRobo = j;
						return true;
					}
				}
			}
			throw new CriaRoboException("A Sala esta ocupada");
		}
		sala.setPosicao(posicaoHoraizontal, posicaoHoraizontal, Sala.OCUPADO);
		this.posicaoHorizontalRobo = posicaoHoraizontal;
		this.posicaoVerticalRobo = posicaoVertical;
		return true;
	}

	/**
	 * Atualizar posicao do robo
	 * 
	 * @param posicaoDestinoHorizontal
	 * @param posicaoDestinoVertical
	 * @return {@link Boolean}
	 * @throws PosicoesException
	 * @throws MoveRoboException
	 * 
	 */
	private boolean atualizaPosicaoRobo(int posicaoDestinoHorizontal, int posicaoDestinoVertical)
			throws PosicoesException, MoveRoboException {
		if (sala.posicaoValida(posicaoDestinoHorizontal, posicaoDestinoVertical) && this.energia > 0) {
			if (sala.isPosicaoLivre(posicaoDestinoHorizontal, posicaoDestinoVertical)) {
				sala.setPosicao(this.posicaoHorizontalRobo, this.posicaoVerticalRobo, Sala.LIVRE);
				this.posicaoHorizontalRobo = posicaoDestinoHorizontal;
				this.posicaoVerticalRobo = posicaoDestinoVertical;
				sala.setPosicao(posicaoDestinoHorizontal, posicaoDestinoVertical, Sala.OCUPADO);

				this.passos++;
				this.energia--;
				return true;
			}
			throw new MoveRoboException("Posicao ocupada ou o robo nao possui mais energia");
		}
		throw new MoveRoboException("Posicao inexistente");
	}

	/**
	 * Metodo para mover o Robo (FRENTE, DIREITA, ESQUERDA, TRAS,
	 * 
	 * @param direcao
	 * @return {@link Boolean}
	 * @throws PosicoesException
	 * @throws MoveRoboException
	 */
	public boolean moveRobo(String direcao) throws PosicoesException, MoveRoboException {
		int posicaoDestinoHorizontal;
		int posicaoDestinoVertical;
		switch (direcao) {
		case "FRENTE":
			posicaoDestinoHorizontal = this.posicaoHorizontalRobo - 1;
			posicaoDestinoVertical = this.posicaoVerticalRobo;
			return atualizaPosicaoRobo(posicaoDestinoHorizontal, posicaoDestinoVertical);
		case "DIREITA":
			posicaoDestinoHorizontal = this.posicaoHorizontalRobo;
			posicaoDestinoVertical = this.posicaoVerticalRobo + 1;
			return atualizaPosicaoRobo(posicaoDestinoHorizontal, posicaoDestinoVertical);
		case "TRAS":
			posicaoDestinoHorizontal = this.posicaoHorizontalRobo + 1;
			posicaoDestinoVertical = this.posicaoVerticalRobo;
			return atualizaPosicaoRobo(posicaoDestinoHorizontal, posicaoDestinoVertical);
		case "ESQUERDA":
			posicaoDestinoHorizontal = this.posicaoHorizontalRobo;
			posicaoDestinoVertical = this.posicaoVerticalRobo - 1;
			return atualizaPosicaoRobo(posicaoDestinoHorizontal, posicaoDestinoVertical);
		}
		return false;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getPosicaoHorizontalRobo() {
		return posicaoHorizontalRobo;
	}

	public void setPosicaoHorizontalRobo(int posicaoHorizontalRobo) {
		this.posicaoHorizontalRobo = posicaoHorizontalRobo;
	}

	public int getPosicaoVerticalRobo() {
		return posicaoVerticalRobo;
	}

	public void setPosicaoVerticalRobo(int posicaoVerticalRobo) {
		this.posicaoVerticalRobo = posicaoVerticalRobo;
	}

	public int getPassos() {
		return passos;
	}

	public void setPassos(int passos) {
		this.passos = passos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posicaoHorizontalRobo;
		result = prime * result + posicaoVerticalRobo;
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
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
		Robo other = (Robo) obj;
		if (posicaoHorizontalRobo != other.posicaoHorizontalRobo)
			return false;
		if (posicaoVerticalRobo != other.posicaoVerticalRobo)
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		return true;
	}

}
