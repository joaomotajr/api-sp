var ip = "localhost";

function InsereCoordenacao(origem) {
	
	var nome = $("#CoordenacaoNome").val();
	var url = $("#CoordenacaoURL").val();
	var tipo = $("#CoordenacaoTipo").val();
	var descricao = $("#CoordenacaoDescricao").val();
	var grupo = $("#CoordenacaoGrupo").val();
		
	var coordenacao = {uid:null, parent:null, nome: nome, url:url, tipo:tipo, descricao:descricao, grupo:grupo};
		
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/salvarCoordenacao";
	var request = $.ajax({
		contentType: "application/json",
        dataType: "json",
		data: JSON.stringify(coordenacao),
		type: "POST",
		url: url		
	});
		
	request.done(function( data ) {
				
		if (data.coordenacao == null) {
			$("#retorno").append("<span>" + data.message +  "</span><br />");
		}
		else {						
			$("#retorno").append("<span> coordenacao ID:: " + data.coordenacao.uid + " Inserido.</span><br />");					
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function SetaParent() {
	
	var uid = $("#CoordenacaoUID").val();
	var parent = $("#CoordenacaoParent").val();	
		
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/setaParent/" + uid + "/" + parent;
	var request = $.ajax({		
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
				
		if (data == null) {
			$("#retorno").append("<span>" + data.message +  "</span><br />");
		}
		else {						
			$("#retorno").append("<span> Atualizado(s): " + data + " Registro(s).</span><br />");
		}

	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function SetaCoordenador() {
	
	var uid = $("#CoordenacaoUID").val();
	var CoordenacaoCoordenador = $("#CoordenacaoCoordenador").val();	
		
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/setaCoordenador/" + uid + "/" + CoordenacaoCoordenador;
	var request = $.ajax({		
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
				
		if (data == null) {
			$("#retorno").append("<span>" + data.message +  "</span><br />");
		}
		else {						
			$("#retorno").append("<span> Atualizado(s): " + data + " Registro(s).</span><br />");
		}

	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function SetaDescricao() {
	
	var uid = $("#CoordenacaoUID").val();
	var descricao = $("#CoordenacaoDescricao").val();	
		
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/setaDescricao/" + uid + "/" + descricao;
	var request = $.ajax({		
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
				
		if (data == null) {
			$("#retorno").append("<span>" + data.message +  "</span><br />");
		}
		else {						
			$("#retorno").append("<span> Atualizado(s): " + data + " Registro(s).</span><br />");
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function consultarCoordenacaoIdFuncionario() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/obterCoordenacaoPorIdCoordenador/" + $("#coordenacao").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
		if (data.coordenacao == null) {
			$("#retorno").append("<span>Nada localizado.</span><br />");
		}
		else {						
			$("#retorno").append("<span>" + data.coordenacao.descricao + "</span><br />");					
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}
         
function consultarCoordenacaoId() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/obterCoordenacaoPorId/" + $("#coordenacao").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
		if (data.coordenacao == null) {
			$("#retorno").append("<span>Nada localizado.</span><br />");
		}
		else {						
			$("#retorno").append("<span>" + data.coordenacao.descricao + "</span><br />");					
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}

function consultarCoordenacaoNome() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/obterCoordenacaoPorNome/" + $("#coordenacao").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
		if (data.coordenacao == null) {
			$("#retorno").append("<span>Nada localizado.</span><br />");
		}
		else {						
			$("#retorno").append("<span>" + data.coordenacao.descricao + "</span><br />");					
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}
function consultarCoordenacaoParentId() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/obterCoordenacoesPorParent/" + $("#coordenacao").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
//		if (data.map == null) {
//			$("#retorno").append("<span>Nada localizado.</span><br />");
//		}
//		else {
//			for(var key in data.map) {
//				if(data.map.hasOwnProperty(key)) {									
//					$("#retorno").append("<span> ID:" + key + " - Nome: "  +  data.map[key] + "</span><br />");
//				}
//			}
//		}		

		$("#retorno").html("<br />");
		
		if (data.coordenacao == null) {
			$("#retorno").append("<span>Nada localizado.</span><br />");
		}
		else {						
			$("#retorno").append("<span>" + data.coordenacao.descricao + "</span><br />");					
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}


function consultarCoordenacoesIdFuncionario() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/obterCoordenacoesPorIdCoordenador/" + $("#coordenacao").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
		if (data.coordenacao == null) {
			$("#retorno").append("<span>Nada localizado.</span><br />");
		}
		else {						
			for (i = 0; i < data.list.length; i++) {
				$("#retorno").append("<span> ID:" +  data.list[i].uid + " - Nome: "  + data.list[i].nome + "</span><br />");
			}
			
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}


function consultarCoordenacoesId() {
	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/coordenacao/obterCoordenacoesPorId/" + $("#coordenacao").val();
	var request = $.ajax({
		type: "GET",
		url: url		
	});
		
	request.done(function( data ) {
		
		$("#retorno").html("<br />");
		
		if (data.coordenacao == null) {
			$("#retorno").append("<span>Nada localizado.</span><br />");
		}
		else {						
			for (i = 0; i < data.list.length; i++) {
				$("#retorno").append("<span> ID:" +  data.list[i].uid + " - Nome: "  + data.list[i].nome + "</span><br />");
			}
			
		}
	});
 
	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
}