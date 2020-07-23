package everisacademy.dao;

import everisacademy.model.Person;

public interface PersonDAO  extends CrudRepository<Person, Long>{
	long ultimoId();
}
