
app.controller('api_pages_coordenacao_producao', function ($scope, $http, $timeout, coordenacaoServices_producao, funcionarioServices_producao, siappServices_producao, ativoServices_producao) {

    $scope.showListF = true;

    $scope.funcionariosCoordenacoes = [];
    $scope.SistemasCoordenacao = [];
    $scope.AtivosCoordenacao = [];

    $scope.coordenacao = {
        funcionarios: 0,
        sistemas: 0,
        ativos: 0,
        coordenacoes: [
            {
                funcionarios: 0,
                sistemas: 0,
                ativos: 0,
            },
            {
                funcionarios: 0,
                sistemas: 0,
                ativos: 0,
            },
            {
                funcionarios: 0,
                sistemas: 0,
                ativos: 0,
            }
        ]
    };
    
	$scope.ExpandePanel = function (item) {	    

	    if (item == 1) {
	        if ($('#panel1').hasClass('col-sm-4')) {
                
	            $('#reduzido1').hide();
	            $('#expandido1').show();
	            $('#panel2').hide();
	            $('#panel3').hide();
	            $('#resumo').fadeOut(800);	            

	            $('#spanPanel1').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
	            $('#panel1').removeClass('col-sm-4').addClass('col-sm-12');
	            $('#resumo1').fadeIn(500);
	         
	        }
	        else {
	            $('#reduzido1').show();
	            $('#expandido1').hide();

	            $('#panel1').removeClass('col-sm-12').addClass('col-sm-4');
	            $('#resumo1').hide();	            
	            $('#spanPanel1').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
	            $('#panel2').fadeIn(500);
	            $('#panel3').fadeIn(500);
	            $('#resumo').fadeIn(800)
	        }
	        
	    }
	    else if (item == 2) {
	        if ($('#panel2').hasClass('col-sm-4')) {
	            $('#reduzido2').hide();
	            $('#expandido2').show();

	            $('#panel1').hide();
	            $('#panel3').hide();
	            $('#resumo').fadeOut(800);
	            $('#spanPanel2').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
	            $('#panel2').removeClass('col-sm-4').addClass('col-sm-12');	            
	            $('#resumo2').fadeIn(500);
	        }
	        else {

	            $('#reduzido2').show();
	            $('#expandido2').hide();

	            $('#panel2').removeClass('col-sm-12').addClass('col-sm-4');
	            $('#resumo2').hide();	            
	            $('#spanPanel2').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
	            $('#panel1').show()
	            $('#panel3').show();
	            $('#resumo').fadeIn(800);
	        }
	    }
	    else if (item == 3) {
	        if ($('#panel3').hasClass('col-sm-4')) {
	            $('#reduzido3').hide();
	            $('#expandido3').show();

	            $('#panel1').hide();
	            $('#panel2').hide();
	            $('#resumo').fadeOut(800);
	            $('#panel3').removeClass('col-sm-4').addClass('col-sm-12');
	            $('#spanPanel3').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');	            
	            $('#resumo3').fadeIn(500);
                	            
	        }
	        else {
	            $('#reduzido3').show();
	            $('#expandido3').hide();

	            $('#panel3').removeClass('col-sm-12').addClass('col-sm-4');
	            $('#resumo3').hide();
	            $('#spanPanel3').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
	            $('#panel1').fadeIn(500);
	            $('#panel2').fadeIn(500);
	            $('#resumo').fadeIn(800);

	        }
	    }

	}

	$scope.obterCoordenacaoTi = function () {

	    coordenacaoServices_producao.obterCoordenacaoPorNome().then(function (response) {
	        if (response.data.coordenacao == null) {
	            $scope.msgErro = "Coordenação não encontrada";
	        } else {
	            $scope.coordenacaoTi = response.data;

	            $scope.obterCoordenacoesProjeto($scope.coordenacaoTi.coordenacao.uid);

	        }
	    }, function (response) {
	        $scope.msgErro = "Request failed: " + response.statusText;
	    });
	}

	$scope.obterCoordenacoesProjeto = function (idCoordenacao) {

	    coordenacaoServices_producao.obterCoordenacoesPorId(idCoordenacao).then(function (response) {
	        if (response.data.coordenacao == null) {
	            $scope.msgErro = "nenhuma Coordenação de projeto encontrada";
	        } else {
	            $scope.coordenacoesProjeto = response.data.list;

	            $scope.coordenacoesCount = $scope.coordenacoesProjeto.length;

	            $scope.obterDadosCoordenacoes(0);
	            $scope.coordenacoesCount > 1 ? $scope.obterDadosCoordenacoes(1) : null;
	            $scope.coordenacoesCount > 2 ? $scope.obterDadosCoordenacoes(2) : null;
	            
	        }
	    }, function (response) {
	        $scope.msgErro = "Request failed: " + response.statusText;
	    });
	}

	$scope.obterDadosCoordenacoes = function (index) {	    
        
	    funcionarioServices_producao.obterFuncionariosCoordenacao($scope.coordenacoesProjeto[index].uid).then(function (response) {
	        if (response.data.list != null) {
                
	            $scope.funcionariosCoordenacoes[index] = response.data.list;

	            $scope.coordenacao.funcionarios = $scope.coordenacao.funcionarios + $scope.funcionariosCoordenacoes[index].length;
	            $scope.coordenacao.coordenacoes[index].funcionarios = $scope.funcionariosCoordenacoes[index].length;

	        }
	    }, function (response) {
	        $scope.msgErro = "Request failed: " + response.statusText;
	    });	    
	    
	    siappServices_producao.obterSistemasCoordenacao($scope.coordenacoesProjeto[index].nome).then(function (response) {
	        if (response.data.list != null) {
	            $scope.SistemasCoordenacao[index] = response.data.list;

	            $scope.coordenacao.sistemas = $scope.coordenacao.sistemas + $scope.SistemasCoordenacao[index].length;
	            $scope.coordenacao.coordenacoes[index].sistemas = $scope.SistemasCoordenacao[index].length;
	        }
	    }, function (response) {
	        $scope.msgErro = "Request failed: " + response.statusText;
	    });	    
	    
	    ativoServices_producao.obterAtivosCoordenacao($scope.coordenacoesProjeto[index].uid).then(function (response) {
	        if (response.data.list != null) {
	            $scope.AtivosCoordenacao[index] = response.data.list;

	            $scope.coordenacao.ativos = $scope.coordenacao.ativos + $scope.AtivosCoordenacao[index].length;
	            $scope.coordenacao.coordenacoes[index].ativos = $scope.AtivosCoordenacao[index].length;
	        }
	    }, function (response) {
	        $scope.msgErro = "Request failed: " + response.statusText;
	    });	    
        	    
    }

	
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

	$scope.server = "http://" + $scope.ip + "";	
	$scope.coordencaoTiNome = ObtemPaginaCorrente().substring(0, 10).toUpperCase();
	$scope.$root.tituloMenu = $scope.coordencaoTiNome;
	$scope.usuarioLogado = ObtemMatriculaUsuarioLogado();
    	    	
	coordenacaoServices_producao.setServer($scope.server);
	coordenacaoServices_producao.setNomeCoordenacao($scope.coordencaoTiNome);

	ativoServices_producao.setServer($scope.server);
	siappServices_producao.setServer($scope.server);
	funcionarioServices_producao.setServer($scope.server);    
	
	$('body').removeClass('loading');

	var serviceAlive = synchronousService($scope.ip, "test/alive/OK", "GET");

	if (serviceAlive == -1 || serviceAlive == null) {
	    $scope.msgErro = " Serviço Indisponível - Contate o Administrador";
	    return;
	}

	$scope.obterCoordenacaoTi();
    	    		
});

app.directive('chartguardiao', ['$timeout', function ($timeout) {
    return {
        restrict: 'A',
        link: function ($scope, $elm, $attr) {
            $timeout(function () {

                var data = google.visualization.arrayToDataTable([
                  ['Task', 'Hours per Day'],
                  ['SIVAT', 11],
                  ['SIDMO', 2],
                  ['SISAG', 2],
                  ['SICAQ', 2],
                  ['SIICO', 7]
                ]);

                var options = {
                    title: 'Deploys por Mês',
                    pieHole: 0.4,
                };

                var chart = new google.visualization.PieChart(document.getElementById('chart_guardiao'));
                chart.draw(data, options);

            }, 500);
        }
    };
}]);

app.directive('chartsonar', ['$timeout', function ($timeout) {
    return {
        restrict: 'A',
        link: function ($scope, $elm, $attr) {
            $timeout(function () {

                var data = google.visualization.arrayToDataTable([
                   ['Mês', 'SIVAT', 'SIDMO', 'SISAG', 'SICAQ', 'SIICO', 'Média'],
                   ['Janeiro', 165, 938, 522, 998, 450, 614.6],
                   ['Fevereiro', 135, 1120, 599, 1268, 288, 682],
                   ['Março', 157, 1167, 587, 807, 397, 623],
                   ['Abril', 139, 1110, 615, 968, 215, 609.4],
                   ['Maio', 136, 691, 629, 1026, 366, 569.6]
                ]);

                var options = {
                    width: 700,
                    title: 'Violações por mês',
                    vAxis: { title: 'Violações' },
                    hAxis: { title: 'Mês' },
                    seriesType: 'bars',
                    series: { 5: { type: 'line' } }
                };

                var chart = new google.visualization.ComboChart(document.getElementById('chart_sonar'));
                chart.draw(data, options);

                /* var data = new google.visualization.DataTable();
                  data.addColumn('timeofday', 'Time of Day');
                  data.addColumn('number', 'Motivation Level');
    
                  data.addRows([
                    [{v: [8, 0, 0], f: '8 am'}, 1],
                    [{v: [9, 0, 0], f: '9 am'}, 2],
                    [{v: [10, 0, 0], f:'10 am'}, 3],
                    [{v: [11, 0, 0], f: '11 am'}, 4],
                    [{v: [12, 0, 0], f: '12 pm'}, 5],
                    [{v: [13, 0, 0], f: '1 pm'}, 6],
                    [{v: [14, 0, 0], f: '2 pm'}, 7],
                    [{v: [15, 0, 0], f: '3 pm'}, 8],
                    [{v: [16, 0, 0], f: '4 pm'}, 9],
                    [{v: [17, 0, 0], f: '5 pm'}, 10],
                  ]);
    
                  var options = {
                    width: 700,
                    title: 'Motivation Level Throughout the Day',
                    hAxis: {
                      title: 'Time of Day',
                      format: 'h:mm a',
                      viewWindow: {
                        min: [7, 30, 0],
                        max: [17, 30, 0]
                      }
                    },
                    vAxis: {
                      title: 'Rating (scale of 1-10)'
                    }
                  };
    
                  var chart = new google.visualization.ColumnChart(
                    document.getElementById('chart_sonar'));
    
                  chart.draw(data, options); */

            }, 500);
        }
    };
}]);

