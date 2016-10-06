package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author Fidelis.Guimaraes
 *
 */
@Entity
public class Exame implements Serializable{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String sigla;
	private TipoExame tipo;
	
	@Id
	@SequenceGenerator(name="seq_exame", sequenceName="seq_exame", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_exame")
	@Column(name="id_exame",nullable=false)
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
	@Column(length = 10, nullable = false)
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_tipoexame", referencedColumnName="id_tipoexame")
	public TipoExame getTipo() {
		return tipo;
	}
	public void setTipo(TipoExame tipo) {
		this.tipo = tipo;
	}
	
}
