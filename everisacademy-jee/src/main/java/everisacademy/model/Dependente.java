package everisacademy.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Dependente implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private LocalDate age;
	private Person person;

	public Dependente() {
		super();
	}

	public Dependente(String nome, LocalDate age) {
		super();
		this.nome = nome;
		this.age = age;
	}

	public Dependente(Long id, String nome, LocalDate age) {
		super();
		this.id = id;
		this.nome = nome;
		this.age = age;
	}

	public Dependente(java.lang.Long id, String nome, LocalDate age, Person person) {
		super();
		this.id = id;
		this.nome = nome;
		this.age = age;
		this.person = person;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate age) {
		this.age = age;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Dependente other = (Dependente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Dependente [nome=" + nome + ", age=" + age + "]";
	}

}
