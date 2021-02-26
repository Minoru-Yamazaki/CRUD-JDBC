package br.com.connect3.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connect3.factory.ConnectionFactory;
import br.com.connect3.model.Aluno;

public class AlunoDAO {

	// 1 - consulta
	public List<Aluno> list() {

		List<Aluno> alunos = new ArrayList<>();

		try (Connection conn = ConnectionFactory.getConnection()) {
			PreparedStatement prst = conn.prepareStatement("SELECT * FROM alunos");
			ResultSet rs = prst.executeQuery();

			while (rs.next()) { // Percorre a tabela enquanto tiver registro (String nome, int idade, String estado)
				int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String estado = rs.getString("estado");
                
                alunos.add(new Aluno(id, nome, idade, estado));
			}

		} catch (Exception e) {
			System.out.println("Falha ao consultar aluno");
			e.printStackTrace();
		}
		return alunos;
	}
	
	public Aluno getById(int id) {
		
		Aluno aluno = new Aluno();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM alunos WHERE id = ?";
			
			//Prepara Statment com os parametros recebidos
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			
			//Executa consulta e armazana o retorno no objeto rs
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				aluno.setId(rs.getInt("id"));
				aluno.setNome(rs.getString("nome"));
				aluno.setIdade(rs.getInt("idade"));
				aluno.setEstado(rs.getString("estado"));
			}
			
		}catch(SQLException e) {
			System.out.println("Falha ao buscar aluno");
			e.printStackTrace();
		}
		return aluno;
	}
	
	public void createAluno(Aluno aluno) {
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String insetSQL = "INSERT INTO alunos(nome, idade, estado) VALUES(?, ?, ?)";
			
			//Prepara Statment com os parametros recebidos
			PreparedStatement stmt = conn.prepareStatement(insetSQL);
			stmt.setString(1, aluno.getNome());
			stmt.setInt(2, aluno.getIdade());
			stmt.setString(3, aluno.getEstado());
			
			//Executa inserção e armazena o numemro de linhas afetadas
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println("Inserção bem sucedida! Foi adicionado " + rowsAffected + " linha");
			
		}catch(SQLException e) {
			System.out.println("Falha ao buscar aluno");
			e.printStackTrace();
		}
	}

	public void deleteAlunoById(int id) {
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String deleteSQL = "DELETE FROM alunos WHERE id = ?";
			
			//
			PreparedStatement stmt = conn.prepareStatement(deleteSQL);
			stmt.setInt(1, id);
			
			//Executa inserção e armazena o numemro de linhas afetadas
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println("Exclusão bem sucedida! Foi removida " + rowsAffected + " linha");
			
		}catch(SQLException e) {
			System.out.println("Falha ao excluir aluno");
			e.printStackTrace();
		}
	}
	
	public void updateAlunoById(int idUpdade, Aluno aluno) {
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String updateSQL = "UPDATE alunos SET nome = ?, idade = ?, estado = ? WHERE id = ?";
			
			//
			PreparedStatement stmt = conn.prepareStatement(updateSQL);
			stmt.setInt(4, idUpdade);
			stmt.setString(1, aluno.getNome());
			stmt.setInt(2, aluno.getIdade());
			stmt.setString(3, aluno.getEstado());
			
			//Executa inserção e armazena o numemro de linhas afetadas
			int rowsAffected = stmt.executeUpdate();
			
			System.out.println("Inserção bem sucedida! Foi adicionado " + rowsAffected + " linha");
			
		}catch(SQLException e) {
			System.out.println("Falha ao buscar aluno");
			e.printStackTrace();
		}
	}
}

