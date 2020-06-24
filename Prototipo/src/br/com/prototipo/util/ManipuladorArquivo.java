package br.com.prototipo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import br.com.prototipo.domain.Alternativa;
import br.com.prototipo.domain.Aluno;
import br.com.prototipo.domain.Disciplina;
import br.com.prototipo.domain.Materia;
import br.com.prototipo.domain.Prova;
import br.com.prototipo.domain.Questao;
import br.com.prototipo.domain.dto.ProvaDTO;
import br.com.prototipo.domain.dto.QuestaoDTO;
import br.com.prototipo.exception.InvalidLoginOrPassword;

public class ManipuladorArquivo {

	Conversor conversor = new Conversor();

	public ProvaDTO pegaProva(Integer id) {

		ProvaDTO provaDTO = new ProvaDTO();
		try {
			Prova prova = leitorDeProva(id);
			Questao[] questoes = pegaQuestoes(id);

			QuestaoDTO[] questoesDTO = new QuestaoDTO[questoes.length];
			Integer count = 0;
			for (Questao questao : questoes) {

				Alternativa[] alternativasQ = pegaAlternativas(questao.getId());

				QuestaoDTO questaoDTO = conversor.questaoConverter(questao, alternativasQ);
				questoesDTO[count] = questaoDTO;
				count++;
			}

			provaDTO = conversor.provaConverter(prova, questoesDTO);

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(provaDTO);
		return provaDTO;
	};

	public Prova leitorDeProva(Integer idProva) throws IOException {
		BufferedReader buffReadProva = new BufferedReader(new FileReader("files/Prova.txt"));
		String linhaProva = buffReadProva.readLine();

		Prova prova = new Prova();
		while (true) {

			if (linhaProva != null) {

				String segments[] = linhaProva.split(";");
				if (Integer.parseInt(segments[0]) == idProva) {

					prova.setId(Long.parseLong(segments[0]));
					prova.setData(segments[1]);
					if (!segments[2].isEmpty()) {
						prova.setNota(Double.parseDouble(segments[2]));
					}
					prova.setAluno(pegaAluno(Integer.parseInt(segments[3])));

				}

			} else
				break;
			linhaProva = buffReadProva.readLine();
		}
		buffReadProva.close();
		return prova;
	}

	public Aluno pegaAluno(Integer idAluno) throws IOException {
		BufferedReader buffReadAluno = new BufferedReader(new FileReader("files/Aluno.txt"));
		String linhaAluno = buffReadAluno.readLine();

		Aluno aluno = new Aluno();
		while (true) {

			if (linhaAluno != null) {

				String segments[] = linhaAluno.split(";");

				if (Integer.parseInt(segments[0]) == idAluno) {

					aluno.setId(Long.parseLong(segments[0]));
					aluno.setNome(segments[1]);
					aluno.setCpf(segments[2]);
					aluno.setEmail(segments[3]);
					aluno.setSenha(segments[4]);
					aluno.setTelefone(segments[5]);
					aluno.setSexo(segments[6]);
				}

			} else
				break;
			linhaAluno = buffReadAluno.readLine();
		}
		buffReadAluno.close();
		return aluno;
	}

	public Aluno login(String key, String password) throws IOException {
		BufferedReader buffReadAluno = new BufferedReader(new FileReader("files/Aluno.txt"));
		String linhaAluno = buffReadAluno.readLine();

		Aluno aluno = new Aluno();
		while (true) {

			if (linhaAluno != null) {

				String segments[] = linhaAluno.split(";");

				if (key.contains("@")) {
					if (segments[3].equals(key) && segments[4].equals(password)) {

						aluno.setId(Long.parseLong(segments[0]));
						aluno.setNome(segments[1]);
						aluno.setCpf(segments[2]);
						aluno.setEmail(segments[3]);
						aluno.setSenha(segments[4]);
						aluno.setTelefone(segments[5]);
						aluno.setSexo(segments[6]);
					} else if (segments[3].equals(key) && !segments[4].equals(password)) {
						throw new InvalidLoginOrPassword();
					}
				} else {
					if (segments[2].equals(key) && segments[4].equals(password)) {

						aluno.setId(Long.parseLong(segments[0]));
						aluno.setNome(segments[1]);
						aluno.setCpf(segments[2]);
						aluno.setEmail(segments[3]);
						aluno.setSenha(segments[4]);
						aluno.setTelefone(segments[5]);
						aluno.setSexo(segments[6]);
					} else if (segments[2].equals(key) && !segments[4].equals(password)) {
						throw new InvalidLoginOrPassword();
					}

				}

			} else
				break;
			linhaAluno = buffReadAluno.readLine();
		}
		buffReadAluno.close();
		return aluno;
	}

	public Integer pegaQuantidadeQuestoes(Integer idProva) throws IOException {
		BufferedReader buffReadProvaQ = new BufferedReader(new FileReader("files/ProvaQuestao.txt"));
		String linhaProvaQuestao = buffReadProvaQ.readLine();

		Integer count = 0;

		while (true) {

			if (linhaProvaQuestao != null) {

				String segments[] = linhaProvaQuestao.split(";");

				if (Integer.parseInt(segments[2]) == idProva) {

					count++;
				}

			} else
				break;
			linhaProvaQuestao = buffReadProvaQ.readLine();
		}
		buffReadProvaQ.close();
		return count;
	}

	public Questao[] pegaQuestoes(Integer idProva) throws IOException {

		BufferedReader buffReadProvaQ = new BufferedReader(new FileReader("files/ProvaQuestao.txt"));
		String linhaProvaQuestao = buffReadProvaQ.readLine();
		int quantQuestoes = pegaQuantidadeQuestoes(idProva);
		String[] ids = new String[quantQuestoes];
		int count = 0;
		while (true) {

			if (linhaProvaQuestao != null) {

				String segments[] = linhaProvaQuestao.split(";");

				if (Integer.parseInt(segments[2]) == idProva) {

					ids[count] = segments[1];

					count++;
				}

			} else
				break;
			linhaProvaQuestao = buffReadProvaQ.readLine();
		}
		buffReadProvaQ.close();

		Questao[] questoes = new Questao[quantQuestoes];
		for (int i = 0; i < ids.length; i++) {
			questoes[i] = pegaQuestao(Integer.parseInt(ids[i]));
		}

		return questoes;
	}

	public Questao pegaQuestao(Integer idQuestao) throws IOException {

		BufferedReader buffReadQuestao = new BufferedReader(new FileReader("files/Questao.txt"));
		String linhaQuestao = buffReadQuestao.readLine();

		Questao questao = new Questao();
		while (true) {

			if (linhaQuestao != null) {

				String segments[] = linhaQuestao.split(";");

				if (Integer.parseInt(segments[0]) == idQuestao) {

					questao.setId(Long.parseLong(segments[0]));
					questao.setStatus(Boolean.valueOf(segments[1]));
					questao.setTipo(segments[2]);
					questao.setDissetacao(segments[3]);
					questao.setContador(Integer.parseInt(segments[4]));
					questao.setDificuldade(Integer.parseInt(segments[5]));
					questao.setDisciplina(pegaDisciplina(Integer.parseInt(segments[6])));
				}

			} else
				break;
			linhaQuestao = buffReadQuestao.readLine();
		}
		buffReadQuestao.close();

		return questao;
	}

	public Materia pegaMateria(int idMateria) throws IOException {
		BufferedReader buffReadMateria = new BufferedReader(new FileReader("files/Disciplina.txt"));
		String linhaMateria = buffReadMateria.readLine();

		Materia materia = new Materia();
		while (true) {

			if (linhaMateria != null) {

				String segments[] = linhaMateria.split(";");

				if (Integer.parseInt(segments[0]) == idMateria) {

					materia.setId(Long.parseLong(segments[0]));
					materia.setNome(segments[1]);
				}

			} else
				break;
			linhaMateria = buffReadMateria.readLine();
		}
		buffReadMateria.close();

		return materia;
	}

	public Disciplina pegaDisciplina(long idDisciplina) throws IOException {
		BufferedReader buffReadDisciplina = new BufferedReader(new FileReader("files/Disciplina.txt"));
		String linhaDisciplina = buffReadDisciplina.readLine();

		Disciplina disciplina = new Disciplina();
		while (true) {

			if (linhaDisciplina != null) {

				String segments[] = linhaDisciplina.split(";");

				if (Integer.parseInt(segments[0]) == idDisciplina) {

					disciplina.setId(Long.parseLong(segments[0]));
					disciplina.setNome(segments[1]);
					disciplina.setMateria(pegaMateria(Integer.parseInt(segments[2])));
				}

			} else
				break;
			linhaDisciplina = buffReadDisciplina.readLine();
		}
		buffReadDisciplina.close();

		return disciplina;
	}

	public Alternativa[] pegaAlternativas(Long idQuestao) throws IOException {

		BufferedReader buffReadAlternativa = new BufferedReader(new FileReader("files/Alternativa.txt"));
		String linhaProvaAlternativa = buffReadAlternativa.readLine();
		int count = 0;
		Alternativa[] alternativas = new Alternativa[5];
		while (true) {

			if (linhaProvaAlternativa != null) {

				String segments[] = linhaProvaAlternativa.split(";");

				if (Integer.parseInt(segments[3]) == idQuestao) {

					Alternativa alternativa = new Alternativa();
					alternativa.setId(Long.parseLong(segments[0]));
					alternativa.setAlternativa(segments[1]);
					alternativa.setCorreta(Boolean.valueOf((segments[2])));

					alternativa.getQuestao().setId(Long.parseLong(segments[3]));

					alternativas[count] = alternativa;

					count++;
				}

			} else
				break;
			linhaProvaAlternativa = buffReadAlternativa.readLine();
		}
		buffReadAlternativa.close();

		return alternativas;

	}

	public Integer pegaNumeroDeDiscpPorIdMateria(Integer idMateria) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader("files/Disciplina.txt"));
		String linha = buffRead.readLine();

		Integer count = 0;
		Disciplina disciplina = new Disciplina();
		while (true) {

			if (linha != null) {

				String segments[] = linha.split(";");

				if (Integer.parseInt(segments[2]) == idMateria) {
					count++;

				}

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();

		return count;
	}

	public Integer pegaNumeroDeLinhas(String fileName) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader("files/" + fileName + ".txt"));
		String linha = buffRead.readLine();

		Integer count = 0;
		while (true) {

			if (linha != null) {

				count++;

			} else
				break;
			linha = buffRead.readLine();
		}
		return count;
	}

	public Materia[] pegarTodasMaterias() throws IOException {
		BufferedReader buffReadMateria = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linhaMateria = buffReadMateria.readLine();

		Integer count = pegaNumeroDeLinhas("Materia");

		Integer aux = 0;
		Materia[] materias = new Materia[count];
		while (true) {

			if (linhaMateria != null) {
				Materia materia = new Materia();
				String segments[] = linhaMateria.split(";");

				materia.setId(Long.parseLong(segments[0]));
				materia.setNome(segments[1]);
				materias[aux] = materia;
				aux++;

			} else
				break;
			linhaMateria = buffReadMateria.readLine();
		}
		buffReadMateria.close();

		return materias;
	}

	public Disciplina[] pegarTodasDisciplinas() throws IOException {
		BufferedReader buffReadDisciplina = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linhaDisciplina = buffReadDisciplina.readLine();

		Integer count = pegaNumeroDeLinhas("Disciplina");

		Integer aux = 0;
		Disciplina[] disciplinas = new Disciplina[count];
		while (true) {

			if (linhaDisciplina != null) {
				Disciplina disciplina = new Disciplina();
				String segments[] = linhaDisciplina.split(";");

				disciplina.setId(Long.parseLong(segments[0]));
				disciplina.setNome(segments[1]);
				disciplina.setMateria(pegaMateria(Integer.parseInt(segments[2])));
				disciplinas[aux] = disciplina;
				aux++;

			} else
				break;
			linhaDisciplina = buffReadDisciplina.readLine();
		}
		buffReadDisciplina.close();

		return disciplinas;
	}

	public Disciplina[] pegarDisciplinaPorMateria(Integer idMateria) throws IOException {
		BufferedReader buffReadDisciplina = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linhaDisciplina = buffReadDisciplina.readLine();

		Integer count = pegaNumeroDeDiscpPorIdMateria(idMateria);

		Integer aux = 0;
		Disciplina[] disciplinas = new Disciplina[count];
		while (true) {

			if (linhaDisciplina != null) {

				String segments[] = linhaDisciplina.split(";");

				if (segments[2].equals(idMateria.toString())) {
					Disciplina disciplina = new Disciplina();
					disciplina.setId(Long.parseLong(segments[0]));
					disciplina.setNome(segments[1]);
					disciplina.setMateria(pegaMateria(Integer.parseInt(segments[2])));
					disciplinas[aux] = disciplina;
					aux++;
					System.out.println(disciplina);
				}

			} else
				break;
			linhaDisciplina = buffReadDisciplina.readLine();
		}
		buffReadDisciplina.close();

		return disciplinas;
	}

	public void insereMateria(Materia materia) throws IOException {
		File arq = new File("files/", "Materia.txt");

		BufferedReader buffRead = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linha = buffRead.readLine();
		while (true) {

			if (linha != null) {

			} else

				linha = buffRead.readLine();
			String conteudo = materia.getId() + ";" + materia.getNome() + ";\n";
			FileWriter writer = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(writer);

			print.write(conteudo);
			print.flush();
			print.close();
			writer.close();
			break;
		}

	}

	public void insereDisciplina(Disciplina disciplina) throws IOException {

		File arq = new File("files/", "Disciplina.txt");

		BufferedReader buffRead = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linha = buffRead.readLine();
		while (true) {

			if (linha != null) {

			} else

				linha = buffRead.readLine();
			String conteudo = disciplina.getId() + ";" + disciplina.getNome() + ";" + disciplina.getMateria().getId()
					+ ";\n";
			FileWriter writer = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(writer);

			print.write(conteudo);
			print.flush();
			print.close();
			writer.close();
			break;
		}

	}

	public void insereAluno(Aluno aluno) throws IOException {
		File arq = new File("files/", "Aluno.txt");

		BufferedReader buffRead = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linha = buffRead.readLine();
		while (true) {

			if (linha != null) {

			} else

				linha = buffRead.readLine();
			String conteudo = aluno.getId() + ";" + aluno.getNome() + ";" + aluno.getCpf() + ";" + aluno.getEmail()
					+ ";" + aluno.getSenha() + ";" + aluno.getTelefone() + ";" + aluno.getSexo() + "\n";
			FileWriter writer = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(writer);

			print.write(conteudo);
			print.flush();
			print.close();
			writer.close();
			break;
		}

	}

	public void insereQuestao(Materia materia) throws IOException {
		File arq = new File("files/", "Materia.txt");

		BufferedReader buffRead = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linha = buffRead.readLine();
		while (true) {

			if (linha != null) {

			} else
				break;
			linha = buffRead.readLine();
			String conteudo = materia.getId() + ";" + materia.getNome() + ";\n";
			FileWriter writer = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(writer);

			print.write(conteudo);
			print.flush();
			print.close();
			writer.close();
		}

	}

	public void insereAlternativa(Materia materia) throws IOException {
		File arq = new File("files/", "Materia.txt");

		BufferedReader buffRead = new BufferedReader(new FileReader("files/Disciplina.txt"));

		String linha = buffRead.readLine();
		while (true) {

			if (linha != null) {

			} else
				break;
			linha = buffRead.readLine();
			String conteudo = materia.getId() + ";" + materia.getNome() + ";\n";
			FileWriter writer = new FileWriter(arq, true);
			PrintWriter print = new PrintWriter(writer);

			print.write(conteudo);
			print.flush();
			print.close();
			writer.close();
		}

	}

	public Prova gerarProva(Integer idAluno, Integer idMateria) throws IOException {

		Materia materia = pegaMateria(idMateria);
		return null;
	}
}
