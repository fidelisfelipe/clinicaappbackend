package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Anamnese implements Serializable{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * Anamnese
	 */
	private String anamnese;
	/**
	 * Diagnóstico Diferencial
	 */
	private String diagnostico;
	/**
	 * Pedidos de Exame
	 */
	private String pedido;
	
	/**
	 * Condulta Clinica
	 */
	private String condulta;
	
	@Id
	@SequenceGenerator(name="seq_anamnese", sequenceName="seq_anamnese", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="seq_anamnese")
	@Column(name="id_anamnese", nullable=false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(length = 500)
	public String getAnamnese() {
		return anamnese;
	}
	public void setAnamnese(String anamnese) {
		this.anamnese = anamnese;
	}
	
	@Column(length = 500)
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
	@Column(length = 500)
	public String getPedido() {
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	
	@Column(length = 500)
	public String getCondulta() {
		return condulta;
	}
	public void setCondulta(String condulta) {
		this.condulta = condulta;
	}
	
	
}
