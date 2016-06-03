package br.gov.caixa.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.caixa.api.dto.TestDto;

@Entity
@Table(name="test_tb")
public class Test {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="uid")
    private Long uid;
    
    @Column(name="descricao")
    private String descricao;
    
    @Column(name="data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public static List<TestDto> fromTestToDto(List<Test> list) {
		List<TestDto> returnList = new ArrayList<TestDto>();
		for (Test test : list) {
			TestDto dto = new TestDto();
			dto.setUid(test.getUid());
			dto.setDescricao(test.getDescricao());
			dto.setData(test.getData());
			
			returnList.add(dto);
		}
		return returnList;
	}
}