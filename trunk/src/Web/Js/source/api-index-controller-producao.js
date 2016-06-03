app.controller('index_ctrl_producao', function ($scope, $http, $timeout) {

    $scope.listaTodosSistemas = function () {
        $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/siapp/listCoordenacaoSistemas";

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data != null) {
                $('body').addClass('loading');
                
                $scope.populaFiltros(response.data.list);

                $timeout(function () {
                    $("body").removeClass("loading");
                }, 500);
            }

        }, function errorCallback(response) {
            $scope.msgErro = "Pesquisa indisponivel - Contate o administrador.";
        });
    }

    $scope.populaFiltros = function PopulaFiltros(sistemas) {
        
        for (var k = 0; k < sistemas.length; k++) {

            if (sistemas[k][0].length > 0)
                $("#" + sistemas[k][0]).addClass(sistemas[k][1]).addClass(sistemas[k][2]);

        }
    }

    if (checkSiteIsProducao()) {
        $scope.ip = $scope.$root.ip;
        $scope.uri = "/producao/pages/";
    }
    else {
        $scope.ip = $scope.$root.ipDesenv;
        $scope.uri = "/";
    }

    $scope.$root.tituloMenu = "Home";
	$("#menuHome").addClass("active");	
	$("body").removeClass("loading");	

	$scope.listaTodosSistemas();
	
});