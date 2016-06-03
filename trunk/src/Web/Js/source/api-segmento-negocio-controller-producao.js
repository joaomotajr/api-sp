app.controller('api_seg_ctrl_producao', function ($scope, $http) {
	$scope.$root.tituloMenu = "Manutenção de Cadastros";
	$("li[id*='menu']").removeClass("active");
	$("#menuCadastros").addClass("active");

    var matricula = ObtemMatriculaUsuarioLogado();
    var constFuncionarioCoodernadorTi = 2;
    var constFuncionarioCoodernadorProj = 1;
    var constFuncionarioGerenteTi = 3;
	
	$scope.obterSegmentoNegocioAll = function () {
		$scope.url = "http://" + $scope.ip +
			"/api-sharepoint/api/segmentoNegocio/all";

		$http({
			method: 'GET',
			url: $scope.url,
		}).then(function successCallback(response) {
			if (response.data != null) {				
				$scope.segmentoNegocio = response.data;
			}

		}, function errorCallback(response) {
			alert("Request failed: " + response.statusText);
		});
    };
	
	$scope.obterCoordenacaoTIAll = function (tipoCoordenacao) {
		$scope.url = "http://" + $scope.ip +
			"/api-sharepoint/api/coordenacao/obterCoordenacoesPorTipo/" + tipoCoordenacao;
		$http({
			method: 'GET',
			url: $scope.url,
		}).then(function successCallback(response) {
			if (response.data != null) {
				$scope.coordenacoesTI = response.data;
			}
		}, function errorCallback(response) {
			alert("Request failed: " + response.statusText);
		});
    };      
   
   $scope.deleteSegmentoNegocio = function (idSegmento, descricao) {
       var segmentoNegocio = {
           uid: idSegmento,
           descricao: descricao
       }

       $scope.segmentoNegocio = segmentoNegocio;
       $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/segmentoNegocio/delete";
       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.segmentoNegocio
       }).then(function successCallback(response) {
	   
			$scope.obterSegmentoNegocioAll();
           
       }, function errorCallback(response) {
           alert("Request failed: " + response.statusText);
       });
   };
   
	$scope.deleteCoordenacao = function (idCoordenacao) {
       var coordenacao = {
           uid: idCoordenacao
       }
       $scope.coordenacao = coordenacao;
       $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/delete";
       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.coordenacao
       }).then(function successCallback(response) {	   
			$scope.obterCoordenacaoTIAll('1');
       }, function errorCallback(response) {
           alert("Request failed: " + response.statusText);
       });
	};
   
   $scope.updateSegmentoNegocio = function (idSegmento, descricao) {
		$scope.idSegmento = idSegmento;
		$scope.descricaoSegmento = descricao;
   };
   
   $scope.updateCoordenacao = function (coordenacao) {		
		$scope.idCooordenacao = coordenacao.uid;
		$scope.nome = coordenacao.nome;
		$scope.urlCoordenacao = coordenacao.url;
		$scope.grupo = coordenacao.grupo;
		$scope.descricao = coordenacao.descricao;
		$scope.titulo = coordenacao.titulo;
		if(coordenacao.parent != null){
			$scope.idParent = coordenacao.parent.uid;
		}
		$scope.selSegmentoNegocio = coordenacao.segmentoNegocio;
		$scope.erroNome = false;
		$scope.erroSegmento = false;
		$scope.erroURL = false;
		$scope.erroGrupo = false;
		$scope.erroDescricao = false;
		$scope.erroTitulo = false;
   };
   
   $scope.novoSegmentoNegocio = function () {
		$scope.idSegmento = 0;
		$scope.descricaoSegmento = null;
   };
   
   $scope.novoCoordenacao = function () {		
		$scope.idCooordenacao = 0
		$scope.nome = null;
		$scope.urlCoordenacao = null;
		$scope.grupo = null;
		$scope.descricao = null;
		$scope.titulo = null;
		$scope.selSegmentoNegocio = 0;
		$scope.erroNome = false;
		$scope.erroSegmento = false;
		$scope.erroURL = false;
		$scope.erroGrupo = false;
		$scope.erroDescricao = false;
		$scope.erroTitulo = false;
   };
   
   $scope.saveSegmentoNegocio = function (modal) {
		var segmentoNegocio = {
		   uid: $scope.idSegmento,
		   descricao: $scope.descricaoSegmento
		}
		$scope.segmentoNegocio = segmentoNegocio;
		$scope.url = "http://" + $scope.ip +
			"/api-sharepoint/api/segmentoNegocio/save";
		$http({
		   method: 'POST',
		   url: $scope.url,
		   data: $scope.segmentoNegocio
		}).then(function successCallback(response) {
			if(modal == "I"){
				$('#modalIncluir').modal('hide')
			}else if(modal == "A"){
				$('#modalAlterar').modal('hide')
			}
			$scope.obterSegmentoNegocioAll();
		}, function errorCallback(response) {
		   alert("Request failed: " + response.statusText);
		});
   };
   
   $scope.saveCoordenacao = function (modal) {
		if($scope.valida()){
			var uidSegmento;
			if($scope.selSegmentoNegocio.uid == undefined){
				uidSegmento = $scope.selSegmentoNegocio;
			}else{
				uidSegmento = $scope.selSegmentoNegocio.uid;
			}
			if($scope.idParent == undefined){
				var coordenacao = {
					uid: $scope.idCooordenacao,
					nome: $scope.nome,
					url: $scope.urlCoordenacao,
					grupo: $scope.grupo,
					tipo: '1',
					descricao: $scope.descricao,
					segmentoNegocio : {uid : uidSegmento},
					titulo: $scope.titulo
				}
			}else{
				var coordenacao = {
					uid: $scope.idCooordenacao,
					nome: $scope.nome,
					url: $scope.urlCoordenacao,
					grupo: $scope.grupo,
					tipo: '1',
					descricao: $scope.descricao,			
					parent : {uid : $scope.idParent},
					segmentoNegocio : {uid : uidSegmento},
					titulo: $scope.titulo
				}
			}	
			$scope.coordenacao = coordenacao;
			$scope.url = "http://" + $scope.ip + "/api-sharepoint/api/coordenacao/salvarCoordenacao";
			$http({
			   method: 'POST',
			   url: $scope.url,
			   data: $scope.coordenacao
			}).then(function successCallback(response) {
				if(modal == "I"){
					$('#modalIncluir').modal('hide')
				}else if(modal == "A"){
					$('#modalAlterar').modal('hide')
				}
				$scope.obterCoordenacaoTIAll('1');
			}, function errorCallback(response) {
			   alert("Request failed: " + response.statusText);
			});
		}else {
		
		}
   };

   $scope.valida = function () {
		retorno = true;
		if($scope.nome == null || $scope.nome == ""){
			$scope.erroNome = true;
			$scope.msgErrorNome = "Preencha o nome."
			retorno = false;
		}
		if($scope.selSegmentoNegocio == 0 || $scope.selSegmentoNegocio == null){
			$scope.erroSegmento = true;
			$scope.msgErrorSegmento = "Selecione um segmento de negócio."
			retorno = false;
		}
		if($scope.urlCoordenacao == null || $scope.urlCoordenacao == ""){
			$scope.erroURL = true;
			$scope.msgErrorURL = "Preencha a URL.";
			retorno = false;
		}
		if($scope.grupo == null || $scope.grupo == ""){
			$scope.erroGrupo = true;
			$scope.msgErrorGrupo = "Preencha o grupo.";
			retorno = false;
		}
		if($scope.descricao == null || $scope.descricao == ""){
			$scope.erroDescricao = true;
			$scope.msgErrorDescricao = "Preencha a descrição.";
			retorno = false;
		}
		if($scope.titulo == null || $scope.titulo == ""){
			$scope.erroTitulo = true;
			$scope.msgErrorTitulo = "Preencha o título do dashboard.";
			retorno = false;
		}
		return retorno;
   };
      
    /*---------------------------
    	INICIA PROCESSAMENTO
    -----------------------------*/
   	if (checkSiteIsProducao()) {
	    $scope.ip = $scope.$root.ip;
	    $scope.uri = "/producao/pages/";
	}
	else {
	    $scope.ip = $scope.$root.ipDesenv;
	    $scope.uri = "/";
	}

   var matricula = ObtemMatriculaUsuarioLogado();
           
   var objFuncionario = synchronousService(
           $scope.ip,
           "funcionario/obterFuncionarioPorMatricula/" + matricula,
           "GET"
           );
    
   if (objFuncionario == -1 || objFuncionario == null) {
       $('body').removeClass('loading');
       $scope.msgErro = " Serviço Indisponível - Contate o Administrador";
       return;
   }
   else if (objFuncionario.funcionario == "" || objFuncionario.funcionario == null) {
       $('body').removeClass('loading');
       $scope.msgErro = " Funcionário não Cadastrado - Contate o Administrador";
       return;
   }
   else if (objFuncionario.funcionario.tipoFuncionario == "GERENTE_TI") {

       $scope.obterSegmentoNegocioAll();

       $scope.obterCoordenacaoTIAll('1');


       $("body").removeClass("loading");
   }
   else {
       $scope.msgErro = "Acesso Restrito!!";
       return;
   }
  	  
});

