package everisacademy.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import everisacademy.dao.DAO;
import everisacademy.dao.DataBase;
import everisacademy.dao.DependenteDAO;
import everisacademy.model.Dependente;

public class DependenteDAOImpl extends DAO implements DependenteDAO{

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
			ps.close();
			connection.close();
		} catch (Exception ex) {
		
		}
	}

	@Override
	public void update(Dependente d) {
		Connection connection = DataBase.getConnection();
		String sql = "UPDATE boot.dependente SET name =?, age = ? WHERE dependente_id = ?;";
		if (d == null || d.getId() == null) {
            System.out.println("Não foi possivel atualizar o registro");
            return;
        }
        try { 
        	ps = connection.prepareStatement(sql);
            ps.setString(1, d.getNome());
            ps.setDate(2, java.sql.Date.valueOf(d.getAge()));
            ps.setLong(3, d.getId());
            
            ps.executeUpdate();
            
            ps.close();
            connection.close();
            System.out.println("Registro atualizado com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void delete(Dependente d) {
		Connection connection = DataBase.getConnection();
		String sql = "DELETE FROM boot.dependente WHERE person_fk = ?;";
		if (d == null || d.getId() == null) {
            System.out.println("Não foi possivel excluir o registro");
            return;
        }
        try {
        	ps = connection.prepareStatement(sql);
            ps.setLong(1, d.getPerson().getId());
            ps.executeUpdate();
            
			ps.close();
			connection.close();
            System.out.println("Registro excluido com sucesso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Dependente> findAll() {
		List<Dependente> dependenteList = new ArrayList<>();
		String sql = "SELECT * FROM boot.dependente;";
		try {
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Dependente pependente = new Dependente(rs.getLong(1), rs.getString(2), rs.getDate(3).toLocalDate());
				dependenteList.add(pependente);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			dependenteList = null;
		}
		return dependenteList;
	}

	@Override
	public Dependente findById(Long id) {
		String sql = "SELECT * FROM boot.dependente WHERE dependente_id = ?;";
		Dependente dependente = new Dependente();
		try {
			stmt  = conn.createStatement();
			ps.setLong(1, id);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				dependente = new Dependente(rs.getLong(1), rs.getString(2), rs.getDate(3).toLocalDate());
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			dependente = null;
		}
		return dependente;
	}
	
	@Override
	public int size(Long id) {
		String sql = "SELECT count(*) FROM boot.dependente WHERE dependente_id = ?;";
		int count = 0;
		try {
			ps  = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			count = 0;
		}
		return count;
	}

}
