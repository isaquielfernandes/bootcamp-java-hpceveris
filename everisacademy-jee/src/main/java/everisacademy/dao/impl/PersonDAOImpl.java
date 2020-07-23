package everisacademy.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import everisacademy.dao.DAO;
import everisacademy.dao.DataBase;
import everisacademy.dao.DependenteDAO;
import everisacademy.dao.PersonDAO;
import everisacademy.model.Person;

public class PersonDAOImpl extends DAO implements PersonDAO {

	@Override
	public  void save(Person p) {
		Connection connection = DataBase.getConnection();
		try {
			String sql = "INSERT INTO person (name, estado_civil) VALUES (?,?)";
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getEstadoCivil());
			ps.executeUpdate();


//			ps.close();
//			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	@Override
	public void update(Person p) {
		Connection connection = DataBase.getConnection();
		String sql = "UPDATE boot.person SET name = ?, estado_civil = ? WHERE person_id = ?;";
		if (p == null || p.getId() == null) {
			System.out.println("Não foi possivel atualizar o registro");
			return;
		}
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, p.getName());
			ps.setString(2, p.getEstadoCivil());
			ps.setLong(3, p.getId());
			ps.executeUpdate();

			ps.close();
			connection.close();
			System.out.println("Registro atualizado com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Person p) {
		Connection connection = DataBase.getConnection();
		String sql = "DELETE FROM person WHERE person_id = ?";
		if (p == null || p.getId() == null) {
			System.out.println("Não foi possivel excluir o registro");
			return;
		}
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, p.getId());
			ps.executeUpdate();

			ps.close();
			connection.close();
			System.out.println("Registro excluido com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Person> findAll() {
		DependenteDAO dependenteDAO = new DependenteDAOImpl();
		List<Person> personList = new ArrayList<>();
		// List<Dependente> dependenteList = new ArrayList<>();
		String sql = "SELECT * FROM boot.person;";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Person person = new Person(rs.getLong(1), rs.getString(2), rs.getString(3));
				// dependenteList.add(dependenteDAO.findById(person.getId()));
				person.setNumDependentes(dependenteDAO.size(person.getId()));
				personList.add(person);

			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			personList = null;
		}
		return personList;
	}

	@Override
	public Person findById(Long id) {
		String sql = "SELECT * FROM boot.person WHERE person_id =?;";
		Person person = new Person();
		try {
			stmt = conn.createStatement();
			ps.setLong(1, id);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				person = new Person(rs.getLong(1), rs.getString(2), rs.getString(3));
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			person = null;
		}
		return person;
	}

	@Override
	public long ultimoId() {
		String sql = "SELECT max(person_id ) FROM boot.person;";
		long maxId = 0L;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				maxId = rs.getLong(1);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			maxId = 0L;
		}
		return maxId;
	}

}
