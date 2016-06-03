//function ConsultarUsuarioMatricula() {
//	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/funcionario/obterFuncionarioPorMatricula/" + $("#matriculaDB").val();
//	var request = $.ajax({
//		type: "GET",
//		url: url		
//	});
//		
//	request.done(function( data ) {
//		
//		$("#retorno").html("<br />");
//		
//		if (data.funcionario == null) {
//			$("#retorno").append("<span>" + data.message +  "</span><br />");
//		}
//		else {						
//			$("#retorno").append("<span>" + data.funcionario.nome + "</span><br />");					
//		}
//	});
// 
//	request.fail(function( jqXHR, textStatus ) {
//		alert( "Request failed: " + textStatus );
//	});
//}
//
//function ConsultarUsuarioId() {
//	var url = "http://" + $("#ip").val() + ":8080/api-sharepoint/api/funcionario/obterNomeFuncionarioPorId/" + $("#matriculaDB").val();
//	var request = $.ajax({
//		type: "GET",
//		url: url		
//	});
//		
//	request.done(function( data ) {
//		
//		$("#retorno").html("<br />");
//		
//		if (data == null) {
//			$("#retorno").append("<span>" + data +  "</span><br />");
//		}
//		else {						
//			$("#retorno").append("<span>" + data[0] + "</span><br />");					
//		}
//	});
// 
//	request.fail(function( jqXHR, textStatus ) {
//		alert( "Request failed: " + textStatus );
//	});
//}
//
