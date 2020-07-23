package everisacademy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import everisacademy.model.Dependente;


/**
 * @author Isaquiel Fernandes
 *
 */
public class DependenteRepository implements DependenteDAO {
	
	private static final Logger logger = Logger.getLogger(DependenteRepository.class);
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	protected Statement stmt = null;

	@Override
	public void save(Dependente d) {
		Connection connection = DataBase.getConnection();

		String sql = "INSERT INTO boot.dependente (name,age,person_fk) VALUES (?,?,?);";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, d.getNome());
			ps.setDate(2, java.sql.Date.valueOf(d.getAge()));
			ps.setLong(3, d.getPerson().getId());
			ps.executeUpdate();

		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			DataBase.close(connection, ps);
		}
	}

	@Override
	public void update(Dependente d) {
		Connection connection = DataBase.getConnection();
		String sql = "UPDATE boot.dependente SET name =?, age = ? WHERE dependente_id = ?;";
		if (d == null || d.getId() == null) {
			logger.info("Não foi possivel atualizar o registro");
			return;
		}
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, d.getNome());
			ps.setDate(2, java.sql.Date.valueOf(d.getAge()));
			ps.setLong(3, d.getId());

			ps.executeUpdate();

			logger.info("Registro atualizado com sucesso");
		} catch (SQLException ex) {
			logger.error(ex);
		} finally {
			DataBase.close(connection, ps);
		}
	}

	@Override
	public void delete(Dependente d) {
		Connection connection = DataBase.getConnection();
		String sql = "DELETE FROM boot.dependente WHERE person_fk = ?;";
		if (d == null || d.getId() == null) {
			logger.info("Não foi possivel excluir o registro");
			return;
		}
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, d.getPerson().getId());
			ps.executeUpdate();

			logger.info("Registro excluido com sucesso");
		} catch (SQLException ex) {
			logger.error(ex);
		} finally {
			DataBase.close(connection, ps);
		}

	}

	@Override
	public List<Dependente> findAll() {
		Connection connection = DataBase.getConnection();

		List<Dependente> dependenteList = new ArrayList<>();
		String sql = "SELECT * FROM boot.dependente;";
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Dependente pependente = new Dependente(rs.getLong(1), rs.getString(2), rs.getDate(3).toLocalDate());
				dependenteList.add(pependente);
			}
		} catch (SQLException e) {
			dependenteList = null;
		} finally {
			DataBase.close(connection, stmt, rs);
		}
		return dependenteList;
	}

	@Override
	public Dependente findById(Long id) {
		Connection connection = DataBase.getConnection();

		String sql = "SELECT * FROM boot.dependente WHERE dependente_id = ?;";
		Dependente dependente = new Dependente();
		try {
			stmt = connection.createStatement();
			ps.setLong(1, id);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				dependente = new Dependente(rs.getLong(1), rs.getString(2), rs.getDate(3).toLocalDate());
			}
		} catch (SQLException e) {
			dependente = null;
		} finally {
			DataBase.close(connection, ps, stmt, rs);
		}
		return dependente;
	}

	@Override
	public int size(Long id) {
		Connection connection = DataBase.getConnection();

		String sql = "SELECT count(*) FROM boot.dependente WHERE dependente_id = ?;";
		int count = 0;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			ps.close();
			rs.close();
			connection.close();
		} catch (SQLException e) {
			count = 0;
		}finally {
			DataBase.close(connection, ps, rs);
		}
		return count;
	}

}
