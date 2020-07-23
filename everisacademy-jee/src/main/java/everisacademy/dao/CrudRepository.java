package everisacademy.dao;

import java.util.List;

/**
 * @author Isaquiel Fernandes
 * Interface generico para realizar um CRUD (create read update e delete) e buscar por id
 * @param <T>
 * @param <ID>
 */
public interface CrudRepository<T, ID> {
	void save (T t);
	void update(T t);
	void delete(T t);
	List<T> findAll();
	T findById(ID id);
}
