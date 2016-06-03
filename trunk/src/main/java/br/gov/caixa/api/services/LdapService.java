package br.gov.caixa.api.services;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.inject.Inject;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;

import br.gov.caixa.api.dto.PersonDto;
import br.gov.caixa.api.dto.PersonGroupsDto;
import br.gov.caixa.api.dto.ldapMapper.PersonAttributesMapper;
import br.gov.caixa.api.dto.ldapMapper.PersonGroupsAttributesMapper;
import br.gov.caixa.api.result.LdapResult;

@Service
public class LdapService {

	@Inject
	private LdapTemplate ldapTemplate;
		
	public LdapResult obterGruposUsuario(String matricula) {
		LdapQuery ldapQuery = query().base("OU=CAIXA,DC=corp,DC=caixa,DC=gov,DC=br").filter("(&(objectClass=User)(sAMAccountName=" + matricula + "))");
	    
		List<PersonGroupsDto> persons = ldapTemplate.search(ldapQuery, new PersonGroupsAttributesMapper());
		
		LdapResult result = new LdapResult();
		result.setList(persons);
		
		return result;
	}
	
	public LdapResult obterDadosUsuario(String matricula) {
		LdapQuery ldapQuery = query().base("OU=CAIXA,DC=corp,DC=caixa,DC=gov,DC=br").filter("(&(objectClass=User)(sAMAccountName=" + matricula + "))");
		
		List<PersonDto> persons = ldapTemplate.search(ldapQuery, new PersonAttributesMapper());
		
		LdapResult result = new LdapResult();
		result.setList(persons);
		
		return result;
	}
	
	public LdapResult obterTodosUsuariosPorGrupo(String grupo) {
		LdapQuery ldapQuery = query()
				.base("OU=CAIXA, DC=corp,DC=caixa,DC=gov,DC=br")
				.filter("memberOf=CN=" + grupo + ",OU=Outros,OU=Usuarios,OU=CAIXA,DC=corp,DC=caixa,DC=gov,DC=br");					

		List<PersonGroupsDto> persons = ldapTemplate.search(ldapQuery, new PersonGroupsAttributesMapper());
		
		LdapResult result = new LdapResult();
		result.setList(persons);
		
		return result;
	}
	
	public LdapResult obterUsuariosPorGrupo(String grupo) {
		LdapQuery ldapQuery = query()
				.base("OU=Usuarios, OU=CAIXA, DC=corp,DC=caixa,DC=gov,DC=br")
				.filter("memberOf=CN=" + grupo + ",OU=SP,OU=Grupos,OU=CAIXA,DC=corp,DC=caixa,DC=gov,DC=br");
		
		List<PersonGroupsDto> persons = ldapTemplate.search(ldapQuery, new PersonGroupsAttributesMapper());
		
		LdapResult result = new LdapResult();
		result.setList(persons);
		
		return result;
	}
	
}
