package br.com.caelum.vraptor.backend.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
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
public class ResultadoExame implements Serializable, Comparator<ResultadoExame>{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String valor;
	private Date data;
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
	
	@Column(name = "cadastro", updatable = true)
	@Temporal(TemporalType.DATE)
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
	@Override
	public int compare(ResultadoExame arg0, ResultadoExame arg1) {
		Calendar data1 = Calendar.getInstance();
		data1.setTime(arg0.getData());
		
		Calendar data2 = Calendar.getInstance();
		data2.setTime(arg1.getData());
		
		boolean maior =  data1.before(data2);
		boolean menor = data1.after(data2);
		//boolean igual = data1.equals(data2);
		
		if(maior){
			return 1;
		}else if(menor){
			return -1;
		}else{
			return 0;
		}
	}
	

}
