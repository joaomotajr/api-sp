app.controller('api_ctrl', function($scope, $http) {

  $scope.ip = "localhost";  
  
  $scope.ConsultarUsuarioId = function() {
	$scope.url = "http://" + $scope.ip + ":8080/api-sharepoint/api/funcionario/obterNomeFuncionarioPorId/" + $scope.matriculaDB;	
		
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

	
	$scope.ConsultarUsuarioMatricula = function() {
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
				
				$scope.funcionario = response.data.funcionario;
				
				$("#retorno").append("<span>" + response.data.funcionario.nome + "</span><br />");					
			}
		  }, function errorCallback(response) {
			alert( "Request failed: " + response.statusText  );
		  });
	};
		
	$scope.ConsultarFuncionariosIdCoordenacao = function() {
		$scope.url = "http://" + $scope.ip + ":8080/api-sharepoint/api/funcionario/listFuncionariosPorCoordenacao/" + $scope.funcionariosDB;	
			
		$http({
		  method: 'GET',
		  url: $scope.url,
		}).then(function successCallback(response) {
			$("#retorno").html("<br />");
			
			if (response.data == null) {
						
				$("#retorno").append("<span>" + response.data +  "</span><br />");
			}
			else {
				
				for (i = 0; i < response.data.list.length; i++) {				
				
					$("#retorno").append("<span>" + response.data.list[i].nome + "</span><br />");				
				}							
									
			}
		  }, function errorCallback(response) {
			alert( "Request failed: " + response.statusText  );
		  });
	};
	
		
	$scope.obterAllSiappPorCoordenacao = function () {
			$scope.ip = "localhost";
			$scope.url = "http://" + $scope.ip + ":8080/api-sharepoint/api/siapp/obterSistemasPorNome/" + $scope.siappDB;
				
			$http({
			  method: 'GET',
			  url: $scope.url,
			}).then(function successCallback(response) {
				$("#retorno").html("<br />");
				
				if (response.data == null) {
							
					$("#retorno").append("<span>" + response.data +  "</span><br />");
				}
				else {					

					for (i = 0; i < response.data.list.length; i++) {

					    $("#retorno").append("<span>" + response.data.list[i].descricao + "</span><br />");
					}

				}
			  }, function errorCallback(response) {
				alert( "Request failed: " + response.statusText  );
			  });
		};
		
		 $scope.insertSIAPP = function () {         
			 
		     siapp = [];
		     funcionarios = []; 

	            var rowReaded = ({
	                uid: 0,
	                codigo: 1000,
	                sistema: "JUNIOR",
	                carteira: "JUNIOR",
	                descricao: "JUNIOR",
	                coordenacaoProjeto: "",
	                coordenacaoTi: "",
                    funcionarios : funcionarios
	            });

	            siapp.push(rowReaded);
		        
		        $scope.url = "http://" + $scope.ip + ":8080/api-sharepoint/api/siapp/salvarSistemas";

		        $http({
		            method: 'POST',
		            url: $scope.url,
		            data: siapp
		        }).then(function successCallback(response) {

		            if (response.data != null) {
		                $scope.funcionario = response.data.message;
		            }

		        }, function errorCallback(response) {
		            $scope.funcionario = "Request failed: " + response.statusText;
		        });

		    };
		
	  
});

