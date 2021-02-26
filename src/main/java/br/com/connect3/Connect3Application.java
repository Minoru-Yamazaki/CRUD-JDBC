package br.com.connect3;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.connect3.model.Aluno;
import br.com.connect3.persistencia.AlunoDAO;

@SpringBootApplication
public class Connect3Application {

	public static void main(String[] args) {
		SpringApplication.run(Connect3Application.class, args);
		
		AlunoDAO alunoDAO = new AlunoDAO();		
				
		alunoDAO.updateAlunoById(2, new Aluno("Minoru", 28, "SP"));
		
		List<Aluno> listaAlunos = alunoDAO.list();
		
		listaAlunos.stream().forEach(System.out::println);		
	}

}
