app.controller('api_funcionario', function($scope, $http) {

  $scope.ConsultarUsuarioMatricula = function() {
	$scope.ip = "localhost";
	$scope.url = "http://" + $scope.ip + ":8080/api-sharepoint/api/funcionario/obterFuncionarioPorMatricula/" + $scope.matriculaDB;	
		
	$http({
	  method: 'GET',
	  url: $scope.url,
	}).then(function successCallback(response) {
		$("#retorno").html("<br />");
		
		if (response.data == null) {
					
			$("#retorno").append("<span>" + response.data +  "</span><br />");
		}
		else {
			$scope.funcionario = response.data[0];
			$("#retorno").append("<span>" + response.data[0] + "</span><br />");					
		}
	  }, function errorCallback(response) {
		alert( "Request failed: " + response.statusText  );
	  });
	};
	  
});

