package br.gov.caixa.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.caixa.api.dto.TimelineDto;

@Entity
@Table(name = "timeline")
public class Timeline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="FUNCIONARIO_ID")
	private Funcionario funcionario;
	
	@Column(name = "DATA")
	private Date data;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "DETALHE")
	private String detalhe;
	
	@Column(name = "TIPO_TIMELINE", columnDefinition = "int default 0")
	private TipoTimeline tipoTimeline;		

	@Enumerated(EnumType.ORDINAL) 
	private TipoTimeline TipoTimeline() { 
	    return tipoTimeline; 
	}
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
	public TipoTimeline getTipoTimeline() {
		return tipoTimeline;
	}

	public void setTipoTimeline(TipoTimeline tipoTimeline) {		
		if (tipoTimeline == null ) {			
			this.tipoTimeline = TipoTimeline.NENHUM;
		}	
		else { 
			this.tipoTimeline = tipoTimeline;
		}
	}
	
	public static Timeline fromDtoToTimeline(TimelineDto dto) {
		
		Timeline timeline = new Timeline();
		
		timeline.setUid(dto.getUid());		
		
		if (dto.getFuncionarioDto() != null) {			
			Funcionario funcionario = new Funcionario();
			funcionario.setUid(dto.getFuncionarioDto().getUid());
			timeline.setFuncionario(funcionario);			
		}				
		
		timeline.setTitulo(dto.getTitulo());
		timeline.setData(dto.getData());
		timeline.setDetalhe(dto.getDetalhe());
		timeline.setTipoTimeline(dto.getTipoTimeline());
		
		return timeline;
	}
}
