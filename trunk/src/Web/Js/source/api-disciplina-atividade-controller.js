app.controller('api_disciplina_Atividade_ctrl', function ($scope, $http, $timeout, disciplinaServices,
    atividadeServices, ferramentaServices, categoriaTecnologicaServices) {

    $scope.obterDisciplinaPorMetodologia = function (metodologia) {
        $scope.idDisciplinaSelecionada = undefined;

        disciplinaServices.obterDisciplinaPorMetodologia(metodologia.tipo)
        .then(function (response) {
            if (!response.data != null) {

                $scope.disciplinas = response.data.list;
            }
        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;
            }
        });
    }

    $scope.editarDisciplina = function (index) {
        $scope.idDisciplina = $scope.disciplinas[index].uid;
        $scope.novaDisciplina = $scope.disciplinas[index].nome;

        $('#novaDisciplina').focus();
    }

    $scope.cancelarDisciplina = function () {
        $scope.boxDisciplinaClear();
    }

    $scope.boxDisciplinaClear = function () {
        $scope.idDisciplina = undefined;
        $scope.novaDisciplina = undefined;
    }

    $scope.excluirDisciplina = function (index) {
        $scope.idDisciplina = $scope.disciplinas[index].uid;

        var itemDisciplina = {
            uid: $scope.idDisciplina == undefined ? 0 : $scope.idDisciplina,
            nome: null,
            metodologia: null
        }

        disciplinaServices.deletar(itemDisciplina)
        .then(function (response) {

            if (!response.data.isError) {

                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterDisciplinaPorMetodologia($scope.metodologia);
                    $scope.boxDisciplinaClear();

                    $('body').removeClass('loading');
                }, 500);
            } else {
                $scope.msgErro = response.data.message;
            }

        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;;
            }
        });
    }

    $scope.salvarDisciplina = function (metodologia) {

        if ($scope.itemExiste('tblDisciplina',0, $scope.novaDisciplina))  {
            $scope.msgErro = "Disciplina já Existe! Verifique."
            $('#resultErro').hide().show('slow').delay(5000).hide('slow');
            return;
        }

        var enumMetodologia = null;

        if (metodologia.tipo == 0) {
            enumMetodologia = "PUC";
        }
        else if (metodologia.tipo == 1) {
            enumMetodologia = "AGIL";
        }
        else if (metodologia.tipo == 2) {
            enumMetodologia = "ESTRUTURADA";
        }
        else if (metodologia.tipo == 3) {
            enumMetodologia = "PORTAL_WEB";
        }
        else if (metodologia.tipo == 4) {
            enumMetodologia = "DATAWAREHOUSE";
        }

        var disciplina = {
            uid: $scope.idDisciplina == undefined ? 0 : $scope.idDisciplina,
            nome: $scope.novaDisciplina,
            metodologia: enumMetodologia
        }

        disciplinaServices.salvar(disciplina).then(function (response) {
            if (response.data != null) {

                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterDisciplinaPorMetodologia(metodologia);
                    $scope.boxDisciplinaClear();

                    $('body').removeClass('loading');
                }, 500);
            }
        }, function (response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });
    };

    $scope.obterAtividadePorIdDisciplina = function (index) {

        $scope.nomeDisciplina = $scope.disciplinas[index].nome;
        $scope.idDisciplinaSelecionada = $scope.disciplinas[index].uid;

        $scope.obterAtividadePorIdDisciplinaService($scope.idDisciplinaSelecionada);
    }

    $scope.obterAtividadePorIdDisciplinaService = function (uid) {
        atividadeServices.obterAtividadePorIdDisciplina(uid)
        .then(function (response) {
            if (!response.data != null) {

                $scope.atividades = response.data.list;
            }
        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;
            }
        });
    }

    $scope.editarAtividade = function (index) {
        $scope.idAtividade = $scope.atividades[index].uid;
        $scope.novaAtividade = $scope.atividades[index].nome;

        $('#novaAtividade').focus();
    }

    $scope.cancelarAtividade = function () {
        $scope.boxAtividadeClear();
    }

    $scope.salvarAtividade = function () {

        if ($scope.itemExiste('tblAtividade', 0, $scope.novaAtividade)) {
            $scope.msgErro = "Atividade já Existe! Verifique."
            $('#resultErro').hide().show('slow').delay(5000).hide('slow');
            return;
        }

        var atividade = {
            uid: $scope.idAtividade == undefined ? 0 : $scope.idAtividade,
            nome: $scope.novaAtividade,
            disciplina: { uid: $scope.idDisciplinaSelecionada }
        }

        atividadeServices.salvar(atividade).then(function (response) {
            if (response.data != null) {

                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterAtividadePorIdDisciplinaService($scope.idDisciplinaSelecionada);
                    $scope.boxAtividadeClear();

                    $('body').removeClass('loading');
                }, 500);
            }
        }, function (response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });
    };

    $scope.excluirAtividade = function (index) {
        $scope.idAtividade = $scope.atividades[index].uid;

        var itemAtividade = {
            uid: $scope.idAtividade,
            nome: null,
            disciplina: null
        }

        atividadeServices.deletar(itemAtividade)
        .then(function (response) {

            if (!response.data.isError) {
                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterAtividadePorIdDisciplinaService($scope.idDisciplinaSelecionada);
                    $scope.boxAtividadeClear();

                    $('body').removeClass('loading');
                }, 500);
            } else {
                $scope.msgErro = response.data.message;
            }

        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;;
            }
        });
    }

    $scope.boxAtividadeClear = function () {
        $scope.idAtividade = undefined;
        $scope.novaAtividade = undefined;
    }

    $scope.novaDisciplinaKeyPress = function ($event) {
        if ($event.keyCode == 27) {
            $scope.cancelarDisciplina();
        }
        else if ($event.keyCode == 13 && $scope.novaDisciplina != undefined) {

            $scope.salvarDisciplina($scope.metodologia);
        }
    };

    $scope.novaAtividadeKeyPress = function ($event) {
        if ($event.keyCode == 27) {
            $scope.cancelarAtividade();
        }
        else if ($event.keyCode == 13 && $scope.novaAtividade != undefined) {

            $scope.salvarAtividade();
        }
    };

    $scope.itemExiste = function (tabela, col, item) {
        var retorno = false;

        try {
            var table = document.getElementById(tabela);

            for (var r = 1, n = table.rows.length; r < n; r++) {

                var itemCorrente = table.rows[r].cells[col].innerHTML;

                if (itemCorrente.toLowerCase() == item.toLowerCase()) {
                    retorno = true;
                    break;
                }
            }
        }
        catch (err) {
            $scope.msgErro ="Erro ao pesquisar itens na tabela.";
        }
        return retorno;
    }
    
    $scope.obterCategoria = function () {
        $scope.idCategoriaSelecionada = undefined;

        categoriaTecnologicaServices.obterCategoria()
        .then(function (response) {
            if (!response.data != null) {

                $scope.categorias = response.data.list;
            }
        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;
            }
        });
    }
   
    $scope.editarCategoria = function (index) {
        $scope.idCategoria = $scope.categorias[index].uid;
        $scope.novaCategoria = $scope.categorias[index].nome;

        $('#novaCategoria').focus();
    }

    $scope.cancelarCategoria = function () {
        $scope.boxCategoriaClear();
    }

    $scope.boxCategoriaClear = function () {
        $scope.idCategoria = undefined;
        $scope.novaCategoria = undefined;
    }

    $scope.excluirCategoria = function (index) {
        $scope.idCategoria = $scope.categorias[index].uid;

        var itemCategoria = {
            uid: $scope.idCategoria == undefined ? 0 : $scope.idCategoria,
            nome: null         
        }

        categoriaTecnologicaServices.deletar(itemCategoria)
        .then(function (response) {

            if (!response.data.isError) {

                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterCategoria();
                    $scope.boxCategoriaClear();

                    $('body').removeClass('loading');
                }, 500);
            } else {
                $scope.msgErro = response.data.message;
            }

        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;;
            }
        });
    }

    $scope.salvarCategoria = function () {

        if ($scope.itemExiste('tblCategoria', 0, $scope.novaCategoria)) {
            $scope.msgErro = "Categoria já Existe! Verifique."
            $('#resultErro').hide().show('slow').delay(5000).hide('slow');
            return;
        }
        
        var categoria = {
            uid: $scope.idCategoria == undefined ? 0 : $scope.idCategoria,
            nome: $scope.novaCategoria        
        }

        categoriaTecnologicaServices.salvar(categoria).then(function (response) {
            if (response.data != null) {

                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterCategoria();
                    $scope.boxCategoriaClear();

                    $('body').removeClass('loading');
                }, 500);
            }
        }, function (response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });
    };

    $scope.novaCategoriaKeyPress = function ($event) {
        if ($event.keyCode == 27) {
            $scope.cancelarCategoria();
        }
        else if ($event.keyCode == 13 && $scope.novaCategoria != undefined) {

            $scope.salvarCategoria();
        }
    };

    $scope.obterFerramentaPorIdCategoria = function (index) {

        $scope.nomeCategoria = $scope.categorias[index].nome;
        $scope.idCategoriaSelecionada = $scope.categorias[index].uid;

        $scope.obterFerramentaPorIdCategoriaService($scope.idCategoriaSelecionada);
    }

    $scope.obterFerramentaPorIdCategoriaService = function (uid) {
        ferramentaServices.obterFerramentaPorIdCategoria(uid)
        .then(function (response) {
            if (!response.data != null) {

                $scope.ferramentas = response.data.list;
            }
        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;
            }
        });
    }
    
    $scope.editarFerramenta = function (index) {
        $scope.idFerramenta = $scope.ferramentas[index].uid;
        $scope.novaFerramenta = $scope.ferramentas[index].nome;

        $('#novaFerramenta').focus();
    }

    $scope.cancelarFerramenta = function () {
        $scope.boxFerramentaClear();
    }

    $scope.salvarFerramenta = function () {

        if ($scope.itemExiste('tblFerramenta', 0, $scope.novaFerramenta)) {
            $scope.msgErro = "Ferramenta já Existe! Verifique."
            $('#resultErro').hide().show('slow').delay(5000).hide('slow');
            return;
        }

        var ferramenta = {
            uid: $scope.idFerramenta == undefined ? 0 : $scope.idFerramenta,
            nome: $scope.novaFerramenta,
            categoriaTecnologica: { uid: $scope.idCategoriaSelecionada }
        }

        ferramentaServices.salvar(ferramenta).then(function (response) {
            if (response.data != null) {

                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterFerramentaPorIdCategoriaService($scope.idCategoriaSelecionada);
                    $scope.boxFerramentaClear();

                    $('body').removeClass('loading');
                }, 500);
            }
        }, function (response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });
    };

    $scope.excluirFerramenta = function (index) {
        $scope.idFerramenta = $scope.ferramentas[index].uid;

        var itemFerramenta = {
            uid: $scope.idFerramenta,
            nome: null,
            categoriaTecnologica: null
        }

        ferramentaServices.deletar(itemFerramenta)
        .then(function (response) {

            if (!response.data.isError) {
                $('body').addClass('loading');

                $timeout(function () {

                    $scope.obterFerramentaPorIdCategoriaService($scope.idCategoriaSelecionada);                    
                    $scope.boxFerramentaClear();

                    $('body').removeClass('loading');
                }, 500);
            } else {
                $scope.msgErro = response.data.message;
            }

        }, function (response) {
            if (response.data != null) {
                $scope.msgErro = "Request failed: " + response.statusText;;
            }
        });
    }

    $scope.novaFerramentaKeyPress = function ($event) {
        if ($event.keyCode == 27) {
            $scope.cancelarFerramenta();
        }
        else if ($event.keyCode == 13 && $scope.novaFerramenta != undefined) {

            $scope.salvarFerramenta();
        }
    };

    $scope.boxFerramentaClear = function () {
        $scope.idFerramenta = undefined;
        $scope.novaFerramenta = undefined;
    }

    /*--------------------------
    	INICIA PROCESSAMENTO
   ---------------------------*/

    if (checkSiteIsProducao()) {
        $scope.ip = $scope.$root.ip;
        $scope.uri = "/producao/pages/";
    }
    else {
        $scope.ip = $scope.$root.ipDesenv;
        //$scope.ip = "localhost:8080";
        $scope.uri = "/";
    }

    $("li[id*='menu']").removeClass("active");
    $("#menuCoord").addClass("active");

    $scope.$root.tituloMenu = "Manutenção de Cadastros";

    disciplinaServices.setServer("http://" + $scope.ip);
    atividadeServices.setServer("http://" + $scope.ip);
    ferramentaServices.setServer("http://" + $scope.ip);
    categoriaTecnologicaServices.setServer("http://" + $scope.ip);

    $scope.matricula = ObtemMatriculaUsuarioLogado();
    $scope.usrLogado = synchronousService($scope.ip, "funcionario/obterFuncionarioPorMatricula/" + $scope.matricula, "GET");

    if ($scope.usrLogado == -1 || $scope.usrLogado == null) {
        $('body').removeClass('loading');
        $scope.msgErro = " Serviço Indisponível - Contate o Administrador";
        return;
    }
    else if ($scope.usrLogado.funcionario == "" || $scope.usrLogado.funcionario == null) {
        $('body').removeClass('loading');
        $scope.msgErro = " Funcionário não Cadastrado - Contate o Administrador";
        return;
    }
    else if ($scope.usrLogado.funcionario.tipoFuncionario == "COORDENADOR_TI" ||
             $scope.usrLogado.funcionario.tipoFuncionario == "GERENTE_TI") {

        $scope.metodologia = { tipo: 2 };
        $scope.obterDisciplinaPorMetodologia($scope.metodologia);
        $scope.obterCategoria();

        //$(".select2").select2();

        $('body').removeClass('loading');
    }
    else {
        $scope.msgErro = "Acesso Restrito!!";
    }

});

