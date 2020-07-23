package everisacademy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String estadoCivil;
	private Integer numDependentes;
	private List<Dependente> dependenteList = new ArrayList<>();
	private boolean selected;

	public Person() {
		super();
	}

	public Person(Long id, String name, String estadoCivil) {
		super();
		this.id = id;
		this.name = name;
		this.estadoCivil = estadoCivil;
	}

	public Person(Long id, String name, String estadoCivil, List<Dependente> dependenteList) {
		super();
		this.id = id;
		this.name = name;
		this.estadoCivil = estadoCivil;
		this.dependenteList = dependenteList;
	}

	public Person(Long id, String name, String estadoCivil, Integer numDependentes, List<Dependente> dependenteList) {
		super();
		this.id = id;
		this.name = name;
		this.estadoCivil = estadoCivil;
		this.numDependentes = numDependentes;
		this.dependenteList = dependenteList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Integer getNumDependentes() {
		return numDependentes;
	}

	public void setNumDependentes(Integer numDependentes) {
		this.numDependentes = numDependentes;
	}

	public List<Dependente> getDependenteList() {
		return dependenteList;
	}

	public void setDependenteList(List<Dependente> dependenteList) {
		this.dependenteList = dependenteList;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dependenteList == null) ? 0 : dependenteList.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (dependenteList == null) {
			if (other.dependenteList != null)
				return false;
		} else if (!dependenteList.equals(other.dependenteList)) {
			return false;
		}
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
