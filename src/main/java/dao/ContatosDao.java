package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.SingletonConnection;
import entidades.ContatosBean;

public class ContatosDao {

	private Connection connection;

	public ContatosDao() {
		connection = SingletonConnection.getConnection();
	}

	public void cadastrar(ContatosBean ct) {
		String sql = "INSERT INTO lista_contato(nome, email, numero) VALUES(?,?,?)";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, ct.getNome());
			ps.setString(2, ct.getEmail());
			ps.setString(3, ct.getNumero());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ContatosBean> listar() {
		List<ContatosBean> lista = new ArrayList<ContatosBean>();
		String sql = "SELECT * FROM lista_contato";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ContatosBean ct = new ContatosBean();
					ct.setId(rs.getLong("id"));
					ct.setNome(rs.getString("nome"));
					ct.setEmail(rs.getString("email"));
					ct.setNumero(rs.getString("numero"));
					lista.add(ct);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;

	}
	
	public ContatosBean buscar(Long id) {
		String sql = "SELECT * FROM lista_contato WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql) ){
			ps.setLong(1, id);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					ContatosBean ct = new ContatosBean();
					ct.setId(rs.getLong("id"));
					ct.setNome(rs.getString("nome"));
					ct.setEmail(rs.getString("email"));
					ct.setNumero(rs.getString("numero"));
					return ct;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void atualizar(ContatosBean ct) {
		String sql = "UPDATE lista_contato SET nome = ?, email = ?, numero = ? WHERE id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, ct.getNome());
			ps.setString(2, ct.getEmail());
			ps.setString(3, ct.getNumero());
			ps.setLong(4, ct.getId());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean validarNumero(String numero) {
		String sql = "SELECT COUNT(1) as qtd FROM lista_contato WHERE numero = ?";
		try(PreparedStatement ps = connection.prepareStatement(sql) ){
			ps.setString(1, numero);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					return rs.getInt("qtd") <= 0;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void deletar(Long id) {
		String sql = "DELETE FROM lista_contato WHERE id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ContatosBean> consulta(String selecao, String pesquisa) {
		List<ContatosBean> lista = new ArrayList<ContatosBean>();
		String sql = "SELECT * FROM lista_contato WHERE " + selecao +" like'%"+ pesquisa + "%'";
		try(PreparedStatement ps = connection.prepareStatement(sql) ){
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					ContatosBean ct = new ContatosBean();
					ct.setId(rs.getLong("id"));
					ct.setNome(rs.getString("nome"));
					ct.setEmail(rs.getString("email"));
					ct.setNumero(rs.getString("numero"));
					lista.add(ct);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
