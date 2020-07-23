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
import everisacademy.model.Person;

public class PersonRepository implements PersonDAO {

	private static final Logger logger = Logger.getLogger(PersonRepository.class);
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	protected Statement stmt = null;

	@Override
	public void save(Person p) {
		Connection connection = DataBase.getConnection();
		try {
			String sql = "INSERT INTO person (name, estado_civil) VALUES (?,?)";

			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getEstadoCivil());
			ps.executeUpdate();

		} catch (SQLException ex) {
			logger.error(ex);
		} finally {
			DataBase.close(connection, ps);
		}

	}

	@Override
	public void update(Person p) {
		Connection connection = DataBase.getConnection();
		String sql = "UPDATE boot.person SET name = ?, estado_civil = ? WHERE person_id = ?;";
		if (p == null || p.getId() == null) {
			logger.info("Não foi possivel atualizar o registro");
			return;
		}
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getEstadoCivil());
			ps.setLong(3, p.getId());
			ps.executeUpdate();

			logger.info("Registro atualizado com sucesso");
		} catch (SQLException ex) {
			logger.error(ex);
		} finally {
			DataBase.close(connection, ps);
		}
	}

	@Override
	public void delete(Person p) {
		Connection connection = DataBase.getConnection();
		String sql = "DELETE FROM person WHERE person_id = ?";
		if (p == null || p.getId() == null) {
			logger.info("Não foi possivel excluir o registro");
			return;
		}
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, p.getId());
			ps.executeUpdate();

			ps.close();
			connection.close();

		} catch (SQLException ex) {
			logger.error(ex);
		} finally {
			DataBase.close(connection, ps);
		}
	}

	@Override
	public List<Person> findAll() {
		Connection connection = DataBase.getConnection();

		DependenteDAO dependenteDAO = new DependenteRepository();
		List<Person> personList = new ArrayList<>();
		List<Dependente> dependenteList = new ArrayList<>(); 
		String sql = "SELECT * FROM boot.person;";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Person person = new Person(rs.getLong(1), rs.getString(2), rs.getString(3));
				person.setNumDependentes(dependenteDAO.size(person.getId()));
				
				if(person.getNumDependentes() != 0) {
					dependenteList.add(dependenteDAO.findById(person.getId()));
					person.setDependenteList(dependenteList);
				}
				personList.add(person);
			}
		} catch (SQLException e) {
			personList = null;
		} finally {
			DataBase.close(connection, ps);
		}
		return personList;
	}

	@Override
	public Person findById(Long id) {
		Connection connection = DataBase.getConnection();

		String sql = "SELECT * FROM boot.person WHERE person_id =?;";
		Person person = new Person();
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				person = new Person(rs.getLong(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			person = null;
		} finally {
			DataBase.close(connection, ps);
		}
		return person;
	}

	@Override
	public long ultimoId() {
		Connection connection = DataBase.getConnection();

		String sql = "SELECT max(person_id ) FROM boot.person;";
		long maxId = 0L;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				maxId = rs.getLong(1);
			}
		} catch (SQLException e) {
			maxId = 0L;
		} finally {
			DataBase.close(connection, stmt);
		}
		return maxId;
	}

}
