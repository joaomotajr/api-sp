function ConsultarGruposUsuario() {
	//var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/test/" + $("#message").val();
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/ldap/obterGruposUsuario/" + $("#matricula").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
	
	request.done(function( data ) {
		$("#retorno").append("<br />" + data.list[0].name + "<br />");
		$("#retorno").append(data.list[0].sAMAccountName + "<br />");
		
		$("#retorno").html("");
		
		for (i = 0; i < data.list[0].memberof.length; i++) {
			$("#retorno").append("<span>" + data.list[0].memberof[i] + "</span><br />");
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function ConsultarDadosUsuario() {
	
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/ldap/obterDadosUsuario/" + $("#matricula2").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
	
	request.done(function( data ) {
		$("#retorno").append(data.list[0].uid + "<br />");
		$("#retorno").append(data.list[0].name + "<br />");
		$("#retorno").append(data.list[0].sAMAccountName + "<br />");		
		$("#retorno").append(data.list[0].department + "<br />");
		$("#retorno").append(data.list[0].company + "<br />");
		$("#retorno").append(data.list[0].mobile + "<br />");
		$("#retorno").append(data.list[0].mail + "<br />");
		$("#retorno").append(data.list[0].street + "<br />");
		$("#retorno").append(data.list[0].telephonenumber + "<br />");
		$("#retorno").append(data.list[0].title + "<br />");		
				
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function consultarUsuariosGrupo() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/ldap/obterUsuariosPorGrupo/" + $("#GrupoAd").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
	
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
		for (i = 0; i < data.list.length; i++) {
						
			$("#retorno").append("UID: <input type='text' id='uid" + String(i) +  "' value='0' /> ");
			$("#retorno").append("Nome: <input type='text' id='nome" + String(i) +  "' value='" + data.list[i].name + "' /> ");
			$("#retorno").append("Matricula: <input type='text' id='matricula" + String(i) +  "' value='" + data.list[i].sAMAccountName + "' /> ");
			$("#retorno").append("Cargo: <input type='text' id='cargo" + String(i) +  "' value='" + data.list[i].title + "' /> ");
			$("#retorno").append("Depto: <input type='text' id='depto" + String(i) +  "' value='" + data.list[i].department + "' /> ");
			
			$("#retorno").append("<input id='id" + String(i) +  "' type='button' value='inserir' onclick='InsereFuncionario(" + String(i) + ");' />");
			
			$("#retorno").append("<br />");
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function InsereFuncionario(origem) {

	var uid = $("#uid" + origem).val();
	var nome = $("#nome" + origem).val();
	var matricula = $("#matricula" + origem).val();
	var cargo = $("#cargo" + origem).val();
	var depto = $("#depto" + origem).val();
		
	var funcionario = {uid:uid, nome: nome, cargo:cargo,  matricula:matricula, depto:depto};
		
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/funcionario/salvarFuncionario";
	var request = $.ajax({
		contentType: "application/json",
        dataType: "json",
		data: JSON.stringify(funcionario),
		type: "POST",
		url: url		
	});
		
	request.done(function( data ) {
				
		if (data.funcionario == null) {
			$("#retorno").append("<span>" + data.message +  "</span><br />");
		}
		else {						
			$("#retorno").append("<span> Funcionario ID:: " + data.funcionario.uid + " Inserido.</span><br />");					
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}
	
function consultarTodosUsuariosGrupo() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/ldap/obterTodosUsuariosPorGrupo/" + $("#TestGrupoAd").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
	
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
		for (i = 0; i < data.list.length; i++) {
						
			$("#retorno").append("UID: <input type='text' id='uid" + String(i) +  "' value='0' /> ");
			$("#retorno").append("Nome: <input type='text' id='nome" + String(i) +  "' value='" + data.list[i].name + "' /> ");
			$("#retorno").append("Matricula: <input type='text' id='matricula" + String(i) +  "' value='" + data.list[i].sAMAccountName + "' /> ");
			$("#retorno").append("Cargo: <input type='text' id='cargo" + String(i) +  "' value='" + data.list[i].title + "' /> ");
			$("#retorno").append("Depto: <input type='text' id='depto" + String(i) +  "' value='" + data.list[i].department + "' /> ");
			
			$("#retorno").append("<input id='id" + String(i) +  "' type='button' value='inserir' onclick='InsereFuncionario(" + String(i) + ");' />");
			
			$("#retorno").append("<br />");
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}


