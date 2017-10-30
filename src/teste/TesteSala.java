package teste;


import org.junit.Test;

import lab5.Sala;

/**
 * @author geovanniovinhas
 *
 * 28 de out de 2017
 */
import org.junit.Assert;
import org.junit.Before;

public class TesteSala {
	private Sala sala;
	private final int NUM_LINHAS = 10;
	private final int NUM_COLUNAS = 10;

	@Before
	public void criaObjetos() throws Exception {
		sala = new Sala(NUM_LINHAS, NUM_COLUNAS);
	}

	@Test
	public void testaIsPosicaoLivre() throws Exception {
		Assert.assertTrue(sala.isPosicaoLivre(0, 0)); //true
		Assert.assertTrue(sala.isPosicaoLivre(9, 9));
		try {
			Assert.assertTrue(sala.isPosicaoLivre(-1, 0));
			Assert.fail("Uma excecao de posicao inexistente deve ser lancada.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem de erro", "Posicao inexistente.",
					e.getMessage());
		}
		try {
			Assert.assertTrue(sala.isPosicaoLivre(-1, -1));
			Assert.fail("Uma excecao de posicao inexistente deve ser lancada.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem de erro", "Posicao inexistente.",
					e.getMessage());
		}

		try {
			Assert.assertTrue(sala.isPosicaoLivre(0, -1));
			Assert.fail("Uma excecao de posicao inexistente deve ser lancada.");
		} catch (Exception e) {
			Assert.assertEquals("Mensagem de erro", "Posicao inexistente.",
					e.getMessage());
		}
	}
}