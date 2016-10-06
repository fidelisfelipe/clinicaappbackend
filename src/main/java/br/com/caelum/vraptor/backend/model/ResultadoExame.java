package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ResultadoExame implements Serializable{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String valor;
	private Date data = new Date();
	private Exame exame;
	
	@Id
	@SequenceGenerator(name="seq_resultadoexame", sequenceName="seq_resultadoexame", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_resultadoexame")
	@Column(name="id_resultadoexame",nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 20, nullable = false)
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Column(name = "cadastro", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_exame", referencedColumnName="id_exame")
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	

}
