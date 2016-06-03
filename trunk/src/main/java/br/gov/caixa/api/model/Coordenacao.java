package br.gov.caixa.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.gov.caixa.api.dto.CoordenacaoDto;

@Entity
@Table(name = "coordenacao")
public class Coordenacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long uid;
	
	@Column(name = "NOME")
	private String nome;

	@Column(name = "URL")
	private String url;

	@Column(name = "GRUPO")
	private String grupo;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "DESCRICAO")
	private String descricao;	
		
	@Column(name = "COORDENADOR_ID")
	private Long coordenadorId;

	@ManyToOne
    @Cascade(value = CascadeType.ALL)
    @JoinColumn(name="parent_id")
    private Coordenacao parent;
	    
    @OneToMany(mappedBy="parent")
    private Set<Coordenacao> childs = new HashSet<Coordenacao>();
	
	@ManyToOne
	@JoinColumn(name="SEGMENTO_ID")
	private SegmentoNegocio segmentoNegocio;
	
	@Column(name = "TITULO")
	private String titulo;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	public Long getCoordenadorId() {
		return coordenadorId;
	}

	public void setCoordenadorId(Long coordenadorId) {
		this.coordenadorId = coordenadorId;
	}
	
    public Set<Coordenacao> getchilds() {
		return childs;
	}

	public void setChilds(Set<Coordenacao> childs) {
		this.childs = childs;
	}
	
	public Coordenacao getParent() {
		return parent;
	}

	public void setParent(Coordenacao parent) {
		this.parent = parent;
	}

	public SegmentoNegocio getSegmentoNegocio() {
		return segmentoNegocio;
	}

	public void setSegmentoNegocio(SegmentoNegocio segmentoNegocio) {
		this.segmentoNegocio = segmentoNegocio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public static Coordenacao fromDtoToCoordenacao(CoordenacaoDto coordenacaoDto) {
		
		Coordenacao coordenacao = new Coordenacao();
		
		coordenacao.setUid(coordenacaoDto.getUid());
		coordenacao.setNome(coordenacaoDto.getNome());		
		coordenacao.setUrl(coordenacaoDto.getUrl());
		coordenacao.setGrupo(coordenacaoDto.getGrupo());
		coordenacao.setTipo(coordenacaoDto.getTipo());
		coordenacao.setDescricao(coordenacaoDto.getDescricao());
		coordenacao.setTitulo(coordenacaoDto.getTitulo());
		coordenacao.setCoordenadorId(coordenacaoDto.getCoordenadorId());	
		
		if (coordenacaoDto.getParent() != null) {
			Coordenacao parent = fromDtoToCoordenacao(coordenacaoDto.getParent());
			coordenacao.setParent(parent);
		}
		
		if(coordenacaoDto.getSegmentoNegocio() != null){
			SegmentoNegocio segmentoNegocio = SegmentoNegocio.fromDtoToSegmentoNegocio(coordenacaoDto.getSegmentoNegocio());
			coordenacao.setSegmentoNegocio(segmentoNegocio);
		}

		return coordenacao;
	}

}
