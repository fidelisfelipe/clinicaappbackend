package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * @author Fidelis.Guimaraes
 */
@Entity
public class Paciente implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	@NotNull
	private String cpf;
	private String rg;
	private String email;
	private Date dataNascimento;
	private String responsavel;
	private String telefone;
	private String sexo;
	private String estadoCivil;
	private String naturalidade;
	private String profissao;
	private Date data = new Date();
	private Date atualizacao = new Date();
	private List<ResultadoExame> resultadoList;
	private List<Consulta> consultaList;
	
	@Id
	@SequenceGenerator(name="seq_paciente", sequenceName="seq_paciente", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_paciente")
	@Column(name="id_paciente",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Column(length = 80)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(length = 89)
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	
	@Column(name = "dataNascimento")
	@Temporal(TemporalType.DATE)
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Column
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(length = 20)
	public String getSexo() {
		return sexo;
	}
	
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Column(length = 20)
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	@Column(length = 80)
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	
	@Column(length = 80)
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	
	@Column(length = 11)
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Column(length = 20)
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	
	@Column(length = 80)
	@Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinTable(name="ta_paciente_resultado", joinColumns={@javax.persistence.JoinColumn(name="id_paciente")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="id_resultado")})
	public List<ResultadoExame> getResultadoList() {
		return resultadoList;
	}
	public void setResultadoList(List<ResultadoExame> resultadoList) {
		this.resultadoList = resultadoList;
	}
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinTable(name="ta_paciente_consulta", joinColumns={@javax.persistence.JoinColumn(name="id_paciente")}, inverseJoinColumns={@javax.persistence.JoinColumn(name="id_consulta")})
	public List<Consulta> getConsultaList() {
		return consultaList;
	}
	public void setConsultaList(List<Consulta> consultaList) {
		this.consultaList = consultaList;
	}
	
	
	
	
}