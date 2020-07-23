package everisacademy;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import everisacademy.dao.DependenteDAO;
import everisacademy.dao.DependenteRepository;
import everisacademy.dao.PersonDAO;
import everisacademy.dao.PersonRepository;
import everisacademy.model.Dependente;
import everisacademy.model.Person;
import everisacademy.utils.ValidarNifApi;

@ManagedBean(name = "helloworld")
@ViewScoped
public class HelloWorldBean implements Serializable {
	private static final long serialVersionUID = -8425256913664822653L;

	private boolean res = false;

	private String message = "";
	private String nif;
	private List<String> estadoCivilLista;
	private Integer numDependentes;
	private List<Integer> quantidade;

	private Person person = new Person();
	private List<Person> personList = new ArrayList<>();

	private Dependente depen = new Dependente();
	private List<Dependente> listDependen = new ArrayList<>();
	private String nameDep;
	private Integer ageDep;

	//
	
	PersonDAO personDAO = new PersonRepository();
	DependenteDAO dependenteDAO = new DependenteRepository();

	private static final String WSENDPOINT = "http://localhost:9763/webservice/services/ServiceX2";
	
	private String xmlInput = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservices.com\">\r\n"
			+ "   <soapenv:Header/>\r\n" + "   <soapenv:Body>\r\n" + "      <web:getService>\r\n" + "         <web:nif>"
			+ nif + "</web:nif>\r\n" + "      </web:getService>\r\n" + "   </soapenv:Body>\r\n" + "</soapenv:Envelope>";

	@PostConstruct
	public void init() {

		estadoCivilLista = new ArrayList<>();
		// inicializando lista de estado civil
		estadoCivilLista.add("Solteiro(a)");
		estadoCivilLista.add("Casado(a)");
		estadoCivilLista.add("Viuva(o)");
		estadoCivilLista.add("Divorciado(a)");

		personList = personDAO.findAll();

	}

	public void showMessageInDialog() {
		if (numDependentes.equals(11)) {
			PrimeFaces.current().ajax().update("msgShow");
		}
	}

	public void info(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}

	public void saveDependente() {
		person.setDependenteList(new ArrayList<>());
		for (int i = 0; i < person.getNumDependentes(); i++) {
			person.getDependenteList().add(new Dependente());
		}
	}

	//metodo para renderizar e setar object 
	public void renderTelaCadastro() {
		person = new Person();
		depen = new Dependente();
		listDependen = new ArrayList<>();
		person.setDependenteList(new ArrayList<>());
		res = true;
	}

	//Metodo para deletar registro person 
	public void deletePerson(Person p) {
		personDAO.delete(p);
		info("Registro deletado com sucesso.");
	}

	//metodo para editar
	public void editar(Person p) {
		renderTelaCadastro();
		person = p;
		if (p.isSelected()) {
			message = "Person selecionada (ID | Nome) = " + p.getId() + "|" + p.getName();
			p.setSelected(false);
		}

	}

	//Metodo para savar e editar registro
	public void save() {
		String response = ValidarNifApi.getResponse(ValidarNifApi.validar(WSENDPOINT, xmlInput));
		System.out.println(response);
		if (response == "NIF valido!") {
			info("Nif valido!");
			if (person.getId() == null) {
				personDAO.save(person);
				personDAO.ultimoId();
				info("Registro Guardado com sucesso.");
			} else {
				personDAO.update(person);
				info("Registro editado com sucesso.");
			}
			res = false;
		} else {
			info("Nif Invalido!");
		}
		personDAO.findAll();
		message = "Name: " + person.getName() + " Estado Civil: " + person.getEstadoCivil() + " Nº de Dependentes: "
				+ person.getNumDependentes() + " " + person.getDependenteList();
		
	}

	public void cancelar() {
		person = new Person();
		depen = new Dependente();
		listDependen = new ArrayList<>();
		person.setDependenteList(new ArrayList<>());
		res = false;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNumDependentes() {
		return numDependentes;
	}

	public void setNumDependentes(Integer numDependentes) {
		this.numDependentes = numDependentes;
	}

	public List<Integer> getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(List<Integer> quantidade) {
		this.quantidade = quantidade;
	}

	public List<String> getEstadoCivilLista() {
		return estadoCivilLista;
	}

	public void setEstadoCivilLista(List<String> estadoCivilLista) {
		this.estadoCivilLista = estadoCivilLista;
	}

	public List<Dependente> getListDependen() {
		return listDependen;
	}

	public void setListDependen(List<Dependente> listDependen) {
		this.listDependen = listDependen;
	}

	public Dependente getDepen() {
		return depen;
	}

	public void setDepen(Dependente depen) {
		this.depen = depen;
	}

	public String getNameDep() {
		return nameDep;
	}

	public void setNameDep(String nameDep) {
		this.nameDep = nameDep;
	}

	public Integer getAgeDep() {
		return ageDep;
	}

	public void setAgeDep(Integer ageDep) {
		this.ageDep = ageDep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	public boolean isRes() {
		return res;
	}

	public void setRes(boolean res) {
		this.res = res;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

}