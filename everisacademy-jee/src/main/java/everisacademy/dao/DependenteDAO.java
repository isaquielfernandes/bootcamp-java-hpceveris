package everisacademy.dao;

import everisacademy.model.Dependente;

public interface DependenteDAO extends CrudRepository<Dependente, Long> {
	int size(Long id);
}
