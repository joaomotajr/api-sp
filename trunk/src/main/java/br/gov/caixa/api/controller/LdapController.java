package br.gov.caixa.api.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.caixa.api.result.LdapResult;
import br.gov.caixa.api.services.LdapService;


@RestController
public class LdapController {

	@Inject
	private LdapService ldapService;
	   
	@RequestMapping(value="/api/ldap/obterGruposUsuario/{matricula}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public LdapResult consultaGrupossUsuario(@PathVariable String matricula) {
		
		return ldapService.obterGruposUsuario(matricula);
   }
	
	@RequestMapping(value="/api/ldap/obterDadosUsuario/{matricula}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public LdapResult consultaDadosUsuario(@PathVariable String matricula) {
		
		return ldapService.obterDadosUsuario(matricula);
   }
	   
	@RequestMapping(value="/api/ldap/obterUsuariosPorGrupo/{grupo}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public LdapResult consultaUsuariosPorGrupo(@PathVariable String grupo) {		
		
		return ldapService.obterUsuariosPorGrupo(grupo);
   }
	
	@RequestMapping(value="/api/ldap/obterTodosUsuariosPorGrupo/{grupo}", method=RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public LdapResult consultaTodosUsuariosPorGrupo(@PathVariable String grupo) {		
		
		return ldapService.obterTodosUsuariosPorGrupo(grupo);
   }
}