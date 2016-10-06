package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TipoExame implements Serializable{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	
	
	@Id
	@SequenceGenerator(name="seq_tipoexame", sequenceName="seq_tipoexame", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_tipoexame")
	@Column(name="id_tipoexame",nullable=false)
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
	

}
