app.controller('api_coord_proj_ctrl_producao', function ($scope, $http) {
    $scope.$root.tituloMenu = "Manutenção de Cadastros";
    $("li[id*='menu']").removeClass("active");
    $("#menuCadastros").addClass("active");

    var matricula = ObtemMatriculaUsuarioLogado();
    var constFuncionarioCoodernadorTi = 2;
    var constFuncionarioCoodernadorProj = 1;
    var constFuncionarioGerenteTi = 3;

    $scope.obterCoordenacaoTiPorIdCoordenador = function (idCoordenador) {
        $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/obterCoordenacaoPorIdCoordenador/" +
            idCoordenador;

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data != null) {
                $scope.coordenacoesTI = response.data;
                $scope.coordenacoesTI.list = [];
                $scope.coordenacoesTI.list.push(response.data.coordenacao);

                $scope.obterCoordenacoesProjPorId($scope.coordenacoesTI.list[0].uid);
            }

        }, function errorCallback(response) {
            $scope.msgErro = "Request failed: " + response.statusText;;
        });
    };


    $scope.obterCoordenacaoProjetoAll = function (tipoCoordenacao) {
        $scope.url = "http://" + $scope.ip +
			"/api-sharepoint/api/coordenacao/obterCoordenacoesPorTipo/" + tipoCoordenacao;
        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {
            if (response.data != null) {
                $scope.coordenacoesProjeto = response.data;
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


    /*-------------------------------------------
    	Obtem Coodenacoes por Id Coordenador
    ---------------------------------------------*/

    $scope.obterCoordenacoesTiPorIdCoordenador = function (idCoordenador) {
        $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/obterCoordenacoesPorIdCoordenador/" +
            idCoordenador;

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data != null) {
                $scope.coordenacoesTi = response.data;
            }

        }, function errorCallback(response) {
            alert("Request failed: " + response.statusText);
        });
    };

    /*-------------------------------------------
    	Obtem Coodenacoes por Id
    ---------------------------------------------*/

    $scope.obterCoordenacoesProjPorId = function (idCoordenacao) {
        $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/coordenacao/obterCoordenacoesPorId/" + idCoordenacao;

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data != null) {
                $scope.coordenacoesProjeto = response.data;
            }

        }, function errorCallback(response) {
            alert("Request failed: " + response.statusText);
        });
    };

    /*--------------------------------------------------------------------------
    	            Eventos
    ----------------------------------------------------------------------------*/

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
            $scope.obterCoordenacaoProjetoAll('2');
        }, function errorCallback(response) {
            alert("Request failed: " + response.statusText);
        });
    };

    $scope.updateCoordenacao = function (coordenacao) {
        $scope.idCooordenacao = coordenacao.uid;
        $scope.nome = coordenacao.nome;
        $scope.urlCoordenacao = coordenacao.url;
        $scope.grupo = coordenacao.grupo;
        $scope.descricao = coordenacao.descricao;
        $scope.titulo = coordenacao.titulo;
        $scope.selCoordenacoesTI = coordenacao.parent;
        $scope.erroNome = false;
        $scope.erroURL = false;
        $scope.erroGrupo = false;
        $scope.erroDescricao = false;
        $scope.erroCoordenacaoTI = false;
        $scope.erroTitulo = false;
    };

    $scope.novoCoordenacao = function () {
        $scope.idCooordenacao = 0
        $scope.nome = null;
        $scope.urlCoordenacao = null;
        $scope.grupo = null;
        $scope.descricao = null;
        $scope.selCoordenacoesTI = null;
        $scope.titulo = null;
        $scope.erroNome = false;
        $scope.erroSegmento = false;
        $scope.erroURL = false;
        $scope.erroGrupo = false;
        $scope.erroDescricao = false;
        $scope.erroCoordenacaoTI = false;
        $scope.erroTitulo = false;
    };

    $scope.saveCoordenacao = function (modal) {
        if ($scope.valida()) {
            var coordenacao = {
                uid: $scope.idCooordenacao,
                nome: $scope.nome,
                url: $scope.urlCoordenacao,
                grupo: $scope.grupo,
                tipo: '2',
                descricao: $scope.descricao,
                parent: { uid: $scope.selCoordenacoesTI.uid },
                titulo: $scope.titulo
            }
            $scope.coordenacao = coordenacao;
            $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/coordenacao/salvarCoordenacao";
            $http({
                method: 'POST',
                url: $scope.url,
                data: $scope.coordenacao
            }).then(function successCallback(response) {
                if (modal == "I") {
                    $('#modalIncluir').modal('hide')
                } else if (modal == "A") {
                    $('#modalAlterar').modal('hide')
                }
                $scope.obterCoordenacaoProjetoAll('2');
            }, function errorCallback(response) {
                alert("Request failed: " + response.statusText);
            });
        }
    };

    $scope.valida = function () {
        retorno = true;
        if ($scope.nome == null || $scope.nome == "") {
            $scope.erroNome = true;
            $scope.msgErrorNome = "Preencha o nome."
            retorno = false;
        }
        if ($scope.urlCoordenacao == null || $scope.urlCoordenacao == "") {
            $scope.erroURL = true;
            $scope.msgErrorURL = "Preencha a URL.";
            retorno = false;
        }
        if ($scope.grupo == null || $scope.grupo == "") {
            $scope.erroGrupo = true;
            $scope.msgErrorGrupo = "Preencha o grupo.";
            retorno = false;
        }
        if ($scope.descricao == null || $scope.descricao == "") {
            $scope.erroDescricao = true;
            $scope.msgErrorDescricao = "Preencha a descrição.";
            retorno = false;
        }
        if ($scope.selCoordenacoesTI == null || $scope.selCoordenacoesTI == undefined) {
            $scope.erroCoordenacaoTI = true;
            $scope.msgCoordenacaoTI = "Selecione uma coordenação de T.I.";
            retorno = false;
        }
        if ($scope.titulo == null || $scope.titulo == "") {
            $scope.erroTitulo = true;
            $scope.msgErrorTitulo = "Preencha o título do dashboard.";
            retorno = false;
        }
        return retorno;
    };

    //$scope.obterCoordenacoesPorTipo = function () {
    //	$scope.url = "http://" + $scope.ip + "/api-sharepoint/api/coordenacao/obterCoordenacoesPorTipo/1";

    //	$http({
    //       method: 'GET',
    //       url: $scope.url,
    //	}).then(function successCallback(response) {

    //       if (response.data != null) {
    //           $scope.coordenacoesTI = response.data;
    //       }
    //       else {
    //           $scope.msgErro = response.data.message;
    //       }

    //	}, function errorCallback(response) {
    //       $scope.msgErro = "Request failed: " + response.statusText;;
    //	});
    //};

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

    $("li[id*='menu']").removeClass("active");
    $("#menuCoord").addClass("active");

    $scope.$root.tituloMenu = "Manutenção de Coordenações";

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
    else if (objFuncionario.funcionario.tipoFuncionario == "COORDENADOR_TI") {

        $scope.obterCoordenacaoTiPorIdCoordenador(
            objFuncionario.funcionario.uid
        );

        $('body').removeClass('loading');

    }
    else if (objFuncionario.funcionario.tipoFuncionario == "GERENTE_TI") {

        $scope.obterCoordenacaoProjetoAll('2');

        $scope.obterCoordenacaoTIAll('1');

        $("#divPrincipal").removeClass("loader");
    }
    else {
        $scope.msgErro = "Acesso Restrito!!";
    }

});