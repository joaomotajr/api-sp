
app.controller('api_ativo_producao', function ($scope, $http) {

    $scope.$root.tituloMenu = "Manutenção de Cadastros";
    $("li[id*='menu']").removeClass("active");
    $("#menuCadastros").addClass("active");


    $scope.obterAtivoAll = function () {
        $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/ativo/all";

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {
            if (response.data != null) {
                $scope.ativoAll = response.data;
            }
        }, function errorCallback(response) {
            alert("Request failed: " + response.statusText);
        });
    };

    $scope.obterAtivoAllFilterByCoordenacaoTi = function (nomeCoordenacao) {
        $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/ativo/all";

        $scope.filtro = nomeCoordenacao;

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {
            if (response.data != null) {
                var result = response.data;

                for (var i = 0; i < result.list.length; i++) {
                    result.list[i].TI = result.list[i].coordenacaoDto.parent.nome;
                }

                $scope.ativoAll = result;
            }
        }, function errorCallback(response) {
            alert("Request failed: " + response.statusText);
        });
    };

    $scope.novoAtivo = function () {
        $scope.idAtivo = 0;
        $scope.nome = null;
        $scope.descricao = null;
        $scope.selTipoAtivo = undefined;
        $scope.coordenacaoTi = undefined;
        $scope.coordenacaoProj = undefined;
        $scope.erroTipoAtivo = false;
        $scope.erroNome = false;
        $scope.erroDescricao = false;
        $scope.erroCoordenacaoTI = false;
        $scope.erroCoordenacaoProjeto = false;
    };

    $scope.updateAtivo = function (ativo) {
        $scope.idAtivo = ativo.uid;
        $scope.nome = ativo.nome;
        $scope.descricao = ativo.descricao;
        if (ativo.tipoAtivo == "DISCIPLINA") {
            $scope.selTipoAtivo = "0"
        } else if (ativo.tipoAtivo == "FERRAMENTAS") {
            $scope.selTipoAtivo = "1"
        }
        $scope.coordenacaoTi = ativo.coordenacaoDto.parent.uid;
        $scope.coordenacaoProj = ativo.coordenacaoDto.uid;
        $scope.erroTipoAtivo = false;
        $scope.erroNome = false;
        $scope.erroDescricao = false;
        $scope.erroCoordenacaoTI = false;
        $scope.erroCoordenacaoProjeto = false;
    };

    $scope.saveAtivo = function (modal) {
        if ($scope.valida()) {
            var tipoAtivo = $scope.selTipoAtivo * 1;
            var ativo = {
                uid: $scope.idAtivo,
                nome: $scope.nome,
                descricao: $scope.descricao,
                tipoAtivo: tipoAtivo,
                coordenacaoDto: { uid: $scope.coordenacaoProj.uid }
            }
            $scope.ativo = ativo;
            $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/ativo/save";
            $http({
                method: 'POST',
                url: $scope.url,
                data: $scope.ativo
            }).then(function successCallback(response) {
                if (modal == "I") {
                    $('#modalIncluir').modal('hide')
                } else if (modal == "A") {
                    $('#modalAlterar').modal('hide')
                }

                $scope.refreshList();
                
            }, function errorCallback(response) {
                alert("Request failed: " + response.statusText);
            });
        }
    };

    $scope.deleteAtivo = function (idAtivo) {
        var ativo = {
            uid: idAtivo
        }
        $scope.ativo = ativo;
        $scope.url = "http://" + $scope.ip +
			"/api-sharepoint/api/ativo/delete";
        $http({
            method: 'POST',
            url: $scope.url,
            data: $scope.ativo
        }).then(function successCallback(response) {

            $scope.refreshList();

        }, function errorCallback(response) {
            alert("Request failed: " + response.statusText);
        });
    };

    $scope.valida = function () {
        retorno = true;
        $scope.erroNome = false;
        $scope.erroDescricao = false;
        $scope.erroTipoAtivo = false;
        $scope.erroCoordenacaoTI = false;
        $scope.erroCoordenacaoProjeto = false;

        if ($scope.nome == null || $scope.nome == "") {
            $scope.erroNome = true;
            $scope.msgErrorNome = "Preencha o nome."
            retorno = false;
        }
        if ($scope.descricao == null || $scope.descricao == "") {
            $scope.erroDescricao = true;
            $scope.msgErrorDescricao = "Preencha a descrição.";
            retorno = false;
        }
        if ($scope.selTipoAtivo == null || $scope.selTipoAtivo == undefined) {
            $scope.erroTipoAtivo = true;
            $scope.msgErrorTipoAtivo = "Selecione um tipo."
            retorno = false;
        }
        if ($scope.coordenacaoTi == null || $scope.coordenacaoTi == undefined) {
            $scope.erroCoordenacaoTI = true;
            $scope.msgErrorCoordenacaoTI = "Selecione uma coordenação de TI."
            retorno = false;
        }
        if ($scope.coordenacaoProj == null || $scope.coordenacaoProj == undefined) {
            $scope.erroCoordenacaoProjeto = true;
            $scope.msgErrorCoordenacaoProjeto = "Selecione uma coordenação de projeto."
            retorno = false;
        }
        return retorno;
    };

    /*-------------------------------------------
      Obtem Coodenacao TI por Id Coordenador
 ---------------------------------------------*/

    $scope.obterCoordenacaoTiPorIdCoordenador = function (idCoordenador) {
        $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/obterCoordenacaoPorIdCoordenador/" +
            idCoordenador;

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data != null) {
                $scope.coordenacoesTi = response.data;
                $scope.coordenacoesTi.list = [];
                $scope.coordenacoesTi.list.push(response.data.coordenacao);
                $scope.coordenacoesTi.coordenacao = response.data.coordenacao.parent;

                $scope.obterAtivoAllFilterByCoordenacaoTi($scope.coordenacoesTi.list[0].nome);
            }

        }, function errorCallback(response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });
    };


    /*-------------------------------------------
    	Obtem Coodenacoes por Tipo
	---------------------------------------------*/

    $scope.obterCoordenacoesTI = function () {
        $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/obterCoordenacoesPorTipo/1";

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data != null) {
                $scope.coordenacoesTi = response.data;
            }
            else {
                $scope.msgErro = response.data.message;
            }

        }, function errorCallback(response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });
    };

    $scope.processaChangeCoordenadorTi = function () {
        if ($scope.coordenacaoTi == null) {
            $scope.coordenacoesProj = [];
        } else {
            $scope.obterCoordenacoesProjPorId($scope.coordenacaoTi.uid);
        }
        $scope.showListTi = false;
        $scope.funcionarioTi = undefined;
    };

    $scope.obterCoordenacoesProjPorId = function (idCoordenacao) {
        $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/coordenacao/obterCoordenacoesPorId/" + idCoordenacao;

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data != null) {
                $scope.coordenacoesProj = response.data;
            }

        }, function errorCallback(response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });
    };


    $scope.refreshList = function () {

        if ($scope.funcionario.funcionario.tipoFuncionario == "COORDENADOR_TI") {

            $scope.obterCoordenacaoTiPorIdCoordenador($scope.funcionario.funcionario.uid);
        }
        else if ($scope.funcionario.funcionario.tipoFuncionario == "GERENTE_TI") {

            $scope.obterAtivoAll();
        }
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

    $scope.filtro = "";

    var matricula = ObtemMatriculaUsuarioLogado();

    $scope.funcionario = synchronousService($scope.ip, "funcionario/obterFuncionarioPorMatricula/" + matricula, "GET");

    if ($scope.funcionario == -1 || $scope.funcionario == null) {
        $('body').removeClass('loading');
        $scope.msgErro = " Serviço Indisponível - Contate o Administrador";
        return;
    }
    else if ($scope.funcionario.funcionario == "" || $scope.funcionario.funcionario == null) {
        $('body').removeClass('loading');
        $scope.msgErro = " Funcionário não Cadastrado - Contate o Administrador";
        return;
    }
    else if ($scope.funcionario.funcionario.tipoFuncionario == "COORDENADOR_TI") {

        $scope.obterCoordenacaoTiPorIdCoordenador($scope.funcionario.funcionario.uid);
        $("body").removeClass("loading");

    }
    else if ($scope.funcionario.funcionario.tipoFuncionario == "GERENTE_TI") {

        $scope.obterCoordenacoesTI();
        $scope.obterAtivoAll();
        $("body").removeClass("loading");
    }
    else {
        $scope.msgErro = "Acesso Restrito!!";
        return;
    }

});

