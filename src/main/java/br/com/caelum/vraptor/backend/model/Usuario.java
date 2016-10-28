package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;

/**
 * @author fidelis.guimaraes
 *
 */
@Entity
public class Usuario implements Serializable {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private boolean isAuthorized;
	
	private String cpf;
	private String telefone;

	private List<Perfil> perfis;

	private Date data = new Date();
	private Date atualizacao = new Date();
	private boolean ativo;
	
	@Id
	@SequenceGenerator(name="seq_usuario", sequenceName="seq_usuario", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_usuario")
	@Column(name="id_usuario",nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 80, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(length = 11)
	public String getCpf() {
		if(cpf != null){
			cpf = cpf.replace(".", "").replace("-", "").replace(" ", "");
		}
		return cpf;
	}

	@Column(length = 15)
	public String getTelefone() {
		if(telefone != null){
			telefone = telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
		}
		return telefone;
	}
	public void setTelefone(String telefone) {
		if(telefone != null){
			telefone = telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
		}
		this.telefone = telefone;
	}
	
	@Email
	@Column(length = 80)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCpf(String cpf) {
		if(cpf != null){
			cpf = cpf.replace(".", "").replace("-", "");
		}
		this.cpf = cpf;
	}

	@Column(length = 30)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="ta_usuario_perfil", joinColumns={@javax.persistence.JoinColumn(name="id_usuario")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="id_perfil")})
	public List<Perfil> getPerfis()
	{
	  return this.perfis;
	}
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	@Column(name = "ativo", columnDefinition = "BOOLEAN DEFAULT false")
	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Column(name = "cadastro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	@Transient
	public boolean isAuthorized() {
		return isAuthorized;
	}

	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	
	
	
}