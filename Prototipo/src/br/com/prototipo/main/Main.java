package br.com.prototipo.main;

import java.io.IOException;

import br.com.prototipo.domain.Disciplina;
import br.com.prototipo.domain.Materia;
import br.com.prototipo.util.ManipuladorArquivo;

public class Main {

	public static void main(String[] args) throws IOException {
		ManipuladorArquivo manipula = new ManipuladorArquivo();
		Disciplina disciplina = new Disciplina();
		disciplina.setId((long) 1);
		disciplina.setNome("teste");
		Materia materia = new Materia();
		materia.setId((long) 1);
		disciplina.setMateria(materia);
		manipula.insereDisciplina(disciplina);

	}
}
