package br.gov.caixa.api.dto.ldapMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import br.gov.caixa.api.dto.PersonDto;

public class PersonAttributesMapper implements AttributesMapper<PersonDto>{

	public PersonDto mapFromAttributes(Attributes attrs) throws NamingException {
		PersonDto person = new PersonDto();
		
		person.setDepartment(attrs.get("department") == null ? "" : (String)attrs.get("department").get());		
		person.setName(attrs.get("name") == null ? "" : (String)attrs.get("name").get());
		person.setsAMAccountName(attrs.get("sAMAccountName") == null ? "" : (String)attrs.get("sAMAccountName").get());
		person.setTitle(attrs.get("title") == null ? "" : (String)attrs.get("title").get());
		String matricula = (String)attrs.get("sAMAccountName").get();		
		person.setUid(matricula.substring(1,7));
		
		person.setTelephonenumber(attrs.get("telephonenumber") == null ? "" : (String)attrs.get("telephonenumber").get());		
		person.setCompany(attrs.get("company") == null ? "" : (String)attrs.get("company").get());
		person.setStreet(attrs.get("street") == null ? "" : (String)attrs.get("street").get());
		person.setMobile(attrs.get("mobile") == null ? "" : (String)attrs.get("mobile").get());
		person.setMail(attrs.get("mail") == null ? "" : (String)attrs.get("mail").get());		
		person.setCity(attrs.get("city") == null ? "" : (String)attrs.get("l").get());
		person.setSt(attrs.get("st") == null ? "" : (String)attrs.get("st").get());
		person.setCo(attrs.get("co") == null ? "" : (String)attrs.get("co").get());
		person.setPostalCode(attrs.get("PostalCode") == null ? "" : (String)attrs.get("PostalCode").get());
		
		
		return person;
	}

}
