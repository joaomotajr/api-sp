//Funcionando

app.factory('apiSPService', function($http, $q) {
    
    return {
		getFuncionario: function(uri) {
		
			var deferred = $q.defer(); 			
			
			$http({
				  method: 'GET',
				  url: "http://localhost:8080/api-sharepoint/api/" + uri,
				}).then(function successCallback(response) {
						deferred.resolve(response);
				}, function errorCallback(response) {
					deferred.reject(response);
				});
				
				return deferred.promise;
		}
	}
});

app.controller('api_ctrl', function($scope, $http, apiSPService) {
    	 
	$scope.funcionario =  function() {
		apiSPService.getFuncionario("funcionario/obterFuncionarioPorMatricula/f752766").then(function (response) {
			if (response.data == null) {
				$("#retorno").append("<span>" + response.data +  "</span><br />");
			} else {
				return response.data.funcionario;
			}
		}, function (response) {
			alert( "Request failed: " + response.statusText  );
		});
	};
	  
	$scope.funcionario;
	
});