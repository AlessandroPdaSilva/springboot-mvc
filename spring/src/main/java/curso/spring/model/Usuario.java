package curso.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@NotNull(message = "Nome não pode ser null")
	private String nome;
	
	@Min(value = 18, message = "Idade minima 18 anos")
	private int idade;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "usuario")
	List<Telefone> listaTelefone;
	
	
	private String login;
	private String senha;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	
	
	// GET E SET
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
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}
	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", idade=" + idade + ", listaTelefone=" + listaTelefone + "]";
	}
	
	
	
	
}
