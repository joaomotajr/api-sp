angular.module('dependency', [])

    .config(['$httpProvider', function ($httpProvider) {

        $httpProvider.interceptors.push(function ($q, $rootScope) {
            return {
                'request': function (config) {
                    console.log('request intercept');
                    return config;
                },
                'requestError': function (rejection) {
                    console.log('request error');
                    return rejection;
                },

                'response': function (response) {
                    $rootScope.loading = false;
                    console.log('response intercept');
                    return response;
                },

                'responseError': function (response) {
                    console.log('response error');
                    return response;
                }

            };

        });

    }]);


app.controller('api_ctrl', function ($scope, $http, LdapService, $timeout, auditoriaServices, funcionarioServices, coordenacaoServices) {

   /*-------------------------------------------
   	    Obtem Coodenacao TI por Id Coordenador
   ---------------------------------------------*/

    $scope.obterCoordenacaoTiPorIdCoordenador = function (idCoordenador) {
        $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/obterCoordenacoesTiPorIdCoordenador/" +
            idCoordenador;

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {
                        
            if (response.data.list.length == 0 || response.data == null) {
                $scope.msgErro = "Nenhuma Coordenação atribuída ao usuário logado.";
            }
            else if (response.data != null) {
                $scope.coordenacoesTi = response.data;
            }            

        }, function errorCallback(response) {
            $scope.msgErro = "Request failed: " + response.statusText;;
        });
    };


   /*-------------------------------------------
    	Obtem Coodenacoes por Id Coordenador --> api-coordenacoes-service
   ---------------------------------------------*/

   $scope.obterCoordenacoesTiPorIdCoordenador = function (idCoordenador) {
       $scope.url = "http://" + $scope.ip +
           "/api-sharepoint/api/coordenacao/obterCoordenacoesPorIdCoordenador/" +
           idCoordenador;

       $http({
           method: 'GET',
           url: $scope.url,
       }).then(function successCallback(response) {

           if (response.data.coordenacao != null) {
               $scope.coordenacoesTi = response.data;
           }
           else {
               $scope.msgErro = response.data.message;
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: " + response.statusText;
       });
   };


    /*-------------------------------------------
    	Obtem Coodenacoes por Tipo
   ---------------------------------------------*/

   $scope.obterCoordenacoesPorTipo = function (idCoordenador) {
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
           $scope.msgErro = "Request failed: " + response.statusText;;
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
               $scope.coordenacoesProj = response.data;
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });
   };    


   /*-------------------------------------------
    	Coordenacao Ti
   ---------------------------------------------*/  
   $scope.processaChangeCoordenadorTi = function () {

       if ($scope.coordenacaoTi == null) {
           $scope.coordenadorTi = null;
           $scope.coordenacoesProj = [];
       } else {

           if ($scope.coordenacaoTi.coordenadorId != null) {

               funcionarioServices.obterFuncionarioPorId($scope.coordenacaoTi.coordenadorId).then(function (response) {
                   $scope.coordenadorTi = response.data.funcionario;
               }, function (response) {
                   if (response.data != null) {
                       $scope.msgErro = "Request failed: " + response.statusText;;
                   }
               });
           }
           else {
               $scope.coordenadorTi = null;
           }

           $scope.obterCoordenacoesProjPorId($scope.coordenacaoTi.uid);
       }

       $scope.showListTi = false;
       $scope.funcionarioTi = undefined;
   };

   $scope.obterFuncionarioSelecionadoTi = function () {
       var currentDate = new Date();

       var funcionario = {
           uid: 0,
           nome: $scope.funcionarioTi[0].name,
           cargo: $scope.funcionarioTi[0].title,
           matricula: $scope.funcionarioTi[0].sAMAccountName,
           depto: $scope.funcionarioTi[0].department,
           tipoFuncionario: 2,
           dataProfile: currentDate,
           email: $scope.funcionarioTi[0].mail,
           fone: $scope.funcionarioTi[0].telephonenumber,
           celular: $scope.funcionarioTi[0].mobile == '' ? null : $scope.funcionarioTi[0].mobile,
           empresa: $scope.funcionarioTi[0].company,
           logradouro: $scope.funcionarioTi[0].street,
           cidade: $scope.funcionarioTi[0].city,
           uf: $scope.funcionarioTi[0].st,
           cep: $scope.funcionarioTi[0].PostalCode
       }

       return funcionario;
   }

   $scope.trocarCoordenadorTi = function () {

       if ($scope.coordenadorTi != null && ($scope.coordenadorTi.nome == $scope.funcionarioTi[0].name)) {
           $scope.msgInfo = "Funcionário Já Selecionado, escolha outra Coordenador" ;
           $('#resultInfo').hide().show('slow');
           return;
       }

       var coordenador = $scope.obterFuncionarioSelecionadoTi();

       funcionarioServices.salvar(coordenador).then(function (response) {
           if (response.data != null) {
               $scope.coordenacaoTi.coordenadorId = response.data.funcionario.uid;               
               var antigoCoordenadorTi = ($scope.coordenadorTi != null) ? $scope.coordenadorTi.nome : "nenhum";
               $scope.coordenadorTi = response.data.funcionario;

               coordenacaoServices.setaCoordenador($scope.coordenacaoTi).then(function (response) {
                   
                   $scope.processResponseTrocaCoordenador(
                       response, $scope.enumAcao.alterou_coordenacao_ti, $scope.coordenadorTi.nome, antigoCoordenadorTi);
                   
               }, function (response) {
                   $scope.msgErro = "Request failed: " + response.statusText;;
               });

           }
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });            
   };


   $scope.processResponseTrocaCoordenador = function (response, acao, novoNome, antigoNome) {

       if (response.data != null) {
           if (response.data.isError) {

               $scope.msgInfo = response.data.message;
               $scope.putLog(acao, "Substituiu Funcionário [" + antigoNome + "] da Coordenação Por: [" + novoNome + "] " + $scope.msgInfo);
               $('#resultInfo').hide().show('slow');
           }
           else {

               $scope.msgSucesso = response.data.message;
               $scope.putLog(acao, "Substituiu Funcionário [" + antigoNome + "] da Coordenação Por: [" + novoNome + "]");
               $('#result').hide().show('slow').delay(2000).hide('slow');
           }
       }
       
   }

   /*-------------------------------------------
    	Coordenacao Projeto
   ---------------------------------------------*/
   $scope.processaChangeCoordenadorProj = function () {              
       
        $scope.showListProj = false;
            
        $scope.resetTablesAndLists();
        $scope.loadTablesAtivos();

        $scope.sistemaSelecionado = undefined;
        $scope.ativoSelecionado = undefined;
               
        $("#tab1").trigger("click");

        if ($scope.coordenacaoProj != null && $scope.coordenacaoProj.coordenadorId != null) {

            funcionarioServices.obterFuncionarioPorId($scope.coordenacaoProj.coordenadorId).then(function (response) {
                $scope.coordenadorProj = response.data.funcionario;
            }, function (response) {
                if (response.data != null) {
                    $scope.msgErro = "Request failed: " + response.statusText;;
                }
            });            
        }
        else {
            $scope.coordenadorProj = null;
        }
        
   };   


   $scope.obterFuncionarioSelecionadoProj = function () {
       var currentDate = new Date();

       var funcionario = {
           uid: 0,
           nome: $scope.funcionarioProj[0].name,
           cargo: $scope.funcionarioProj[0].title,
           matricula: $scope.funcionarioProj[0].sAMAccountName,
           depto: $scope.funcionarioProj[0].department,
           tipoFuncionario: 1,
           dataProfile: currentDate,
           email: $scope.funcionarioProj[0].mail,
           fone: $scope.funcionarioProj[0].telephonenumber,
           celular: $scope.funcionarioProj[0].mobile == '' ? null : $scope.funcionarioProj[0].mobile,
           empresa: $scope.funcionarioProj[0].company,
           logradouro: $scope.funcionarioProj[0].street,
           cidade: $scope.funcionarioProj[0].city,
           uf: $scope.funcionarioProj[0].st,
           cep: $scope.funcionarioProj[0].PostalCode
       }

       return funcionario;
   }

   $scope.trocarCoordenadorProj = function () {

       if ($scope.coordenadorProj != null && ($scope.coordenadorProj.nome == $scope.funcionarioProj[0].name)) {       
           $scope.msgInfo = "Funcionário Já Selecionado, escolha outra Coordenador";
           $('#resultInfo').hide().show('slow');
           return;
       }
       
       var coordenador = $scope.obterFuncionarioSelecionadoProj();

       funcionarioServices.salvar(coordenador).then(function (response) {
           if (response.data != null) {

               $scope.coordenacaoProj.coordenadorId = response.data.funcionario.uid;
               var antigoCoordenadorProj = ($scope.coordenadorProj != null) ? $scope.coordenadorProj.nome : "nenhum";
               $scope.coordenadorProj = response.data.funcionario;

               coordenacaoServices.setaCoordenador($scope.coordenacaoProj).then(function (response) {

                   $scope.processResponseTrocaCoordenador(
                       response, $scope.enumAcao.alterou_coordenacao_proj, $scope.coordenadorProj.nome, antigoCoordenadorProj);

               }, function (response) {
                   $scope.msgErro = "Request failed: " + response.statusText;;
               });

           }
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });
       
   };

    /*-------------------------------------------
    	Carrega DataTables
   ---------------------------------------------*/

   $scope.loadTablesAtivos = function () {
		
       if ($scope.coordenacaoProj != null) {

           $scope.carregarFuncionarioCoordenacao($scope.coordenacaoProj.uid);
           $scope.carregarSistemasPorCoordenacao($scope.coordenacaoProj.nome);
           $scope.carregarAtivosPorCoordenacao($scope.coordenacaoProj.uid);
       }
   }


    /*-------------------------------------------
    	Reinicia Listas
   ---------------------------------------------*/
   $scope.resetTablesAndLists = function () {

		//Lists
		$scope.funcionarioProj = undefined;
		$scope.funcionariosCoordenacao = [];
		$scope.sistemasCoordenacao = [];
		$scope.ativosCoordenacao = [];
       
		$scope.funcionariosSistemaSelecionado = [];
		$scope.funcionariosCoordenacaoSistemas = [];

		$scope.funcionariosAtivoSelecionado = [];
		$scope.funcionariosCoordenacaoAtivos = [];

		$('#tableFuncionariosCoordenacao').dataTable().fnDestroy();
		$timeout(function () {
			$scope.formatatableFuncionariosCoordenacao();
			$('body').removeClass('loading');
        }, 1000);
   }


   /*--------------------------------------------------	
         Checa se funcionário já está na lista       
   --------------------------------------------------*/
   $scope.matriculaExiste = function (tabela, col, matricula) {
       var retorno = false;

       try {
           var table = document.getElementById(tabela);

           for (var r = 1, n = table.rows.length; r < n; r++) {

               var matriculaCorrente = table.rows[r].cells[col].innerHTML;

               if (matriculaCorrente == matricula) {
                   retorno = true;
                   break;
               }
           }
       }
       catch (err) {
           $('#result').html("Erro ao pesquisa matricula no Grid.");
       }
       return retorno;
   }


   /*-------------------------------------------
    	Funcionarios AD
   ---------------------------------------------*/
   
   $scope.pesquisarUsuariosLdap = function (tipo) {

       if ($scope.todosFuncionariosLdap == undefined) {

           $('body').addClass('loading');

           LdapService.obterTodosUsuariosPorGrupo("G CS7266 usuarios").then(function (response) {
               if (response.data == null) {

               } else {
                   $('#tableAdUsuarios').dataTable().fnDestroy();

                   $scope.todosFuncionariosLdap = response.data.list;
                   $scope.btnFuncionarioIncluir = "Incluir";

                   $timeout(function () {
                       $('#tableAdUsuarios').dataTable({ "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>' }).fnSort([1, 'asc']);

                       if (tipo == 3)
                           $('#inclusaoFuncionarioAdModal').modal({ show: 'false' });

                       $('body').removeClass('loading');
                   }, 100);

               }
           }, function (response) {
               $scope.msgErro = "Request failed: " + response.statusText;
           });
       }
       else if (tipo == 3) {
           $('#inclusaoFuncionarioAdModal').modal({ show: 'false' });
       }

   };


    /*--------------------------------------
         CRUD Funcionarios da Coordenacao
    ----------------------------------------*/
   $scope.carregarFuncionarioCoordenacao = function (idCoordenacao) {
       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/funcionario/listFuncionariosPorCoordenacao/" + idCoordenacao;

       $http({
           method: 'GET',
           url: $scope.url,
       }).then(function successCallback(response) {

           if (response.data != null) {
			   $('body').addClass('loading');
			   $scope.funcionariosCoordenacao = response.data.list;
           }		   

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: " + response.statusText;
       });
   }

   $scope.obterFuncionarioSelecionadoCoordenacao = function (index) {
       var currentDate = new Date();

       var funcionario = {
           uid: 0,
           coordenacaoDto: { uid: $scope.coordenacaoProj.uid },
           nome: $scope.todosFuncionariosLdap[index].name,
           cargo: $scope.todosFuncionariosLdap[index].title,
           matricula: $scope.todosFuncionariosLdap[index].sAMAccountName,
           depto: $scope.todosFuncionariosLdap[index].department,
           tipoFuncionario: 1,
           dataProfile: currentDate,
           email: $scope.todosFuncionariosLdap[index].mail,
           fone: $scope.todosFuncionariosLdap[index].telephonenumber,
           celular: $scope.todosFuncionariosLdap[index].mobile == '' ? null : $scope.todosFuncionariosLdap[index].mobile,
           empresa: $scope.todosFuncionariosLdap[index].company,
           logradouro: $scope.todosFuncionariosLdap[index].street,
           cidade: $scope.todosFuncionariosLdap[index].city,
           uf: $scope.todosFuncionariosLdap[index].st,
           cep: $scope.todosFuncionariosLdap[index].PostalCode
       }

       return funcionario;
   }

   $scope.incluirFuncionarioCoordenacao = function (index) {
       
       btnId = '#bntFuncionarioLdap' + $scope.todosFuncionariosLdap[index].sAMAccountName

       var funcionario = $scope.obterFuncionarioSelecionadoCoordenacao(index);

       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/funcionario/setaFuncionarioParaCoordenacao";
	    
        $http({
            method: 'POST',
            url: $scope.url,
            data: funcionario
        }).then(function successCallback(response) {
            if (response.data.isError) {
                $(btnId).text(response.data.message).removeClass('btn-info').addClass('btn-default').prop('disabled', true);
            }
            else if (response.data != null) {                

                $('#tableFuncionariosCoordenacao').dataTable().fnDestroy();
                $(btnId).text('Incluído').removeClass('btn-info').addClass('btn-warning').prop('disabled', true);
                $scope.funcionariosCoordenacao.push(response.data.funcionario);

                $scope.putLog($scope.enumAcao.inclui_funcionario_coordenacao, "Incluído Funcionário [" + response.data.funcionario.nome + "] à Coordenação : [" + $scope.coordenacaoProj.nome + "]");
			}

        }, function errorCallback(response) {
            $scope.msgErro = "Request failed: " + response.statusText;;
        });
       
   };

   $scope.excluirFuncionarioCoordenacao = function (index) {
             
		btnId = "#btnFuncionario" + $scope.funcionariosCoordenacao[index].uid;
		idFuncionario = $scope.funcionariosCoordenacao[index].uid;
              
		$scope.url = "http://" + $scope.ip + "/api-sharepoint/api/funcionario/deletarFuncionario/" + idFuncionario;
		$('body').addClass('loading');
       $http({
           method: 'GET',
           url: $scope.url,
       }).then(function successCallback(response) {

           if (!response.data.isError) {
				$('#tableFuncionariosCoordenacao').dataTable().fnDestroy();

				var antigoNome = $scope.funcionariosCoordenacao[index].nome;
				$scope.funcionariosCoordenacao.splice(index, 1);

				$timeout(function () {

				    $scope.recarregarSisteasAtivosPosDelecaoCascata();
				    $scope.formatatableFuncionariosCoordenacao();

				    $scope.putLog($scope.enumAcao.exclui_funcionario_coordenacao, "Excluído Funcionário [" + antigoNome + "] da Coordenação : [" + $scope.coordenacaoProj.nome + "]");

					$('body').removeClass('loading');
				}, 500);
           }
           else
           {
               $('body').removeClass('loading');
               $scope.msgErro = response.data.message;
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: " + response.statusText;           
       });
   }
    
   $('#inclusaoFuncionarioAdModal').on('hidden.bs.modal', function () {             
        $timeout(function () {					
			$scope.formatatableFuncionariosCoordenacao();
		}, 500);
   })

   $scope.formatatableFuncionariosCoordenacao = function () {

       if (!$.fn.DataTable.isDataTable('#tableFuncionariosCoordenacao')) {
           $('#tableFuncionariosCoordenacao').dataTable({
               "scrollY": "215px",
               "scrollCollapse": true,
               "paging": false,
               "columnDefs": [
                   {
                       "targets": [0],
                       "visible": true,
                       "searchable": true
                   }],
               "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
           });
           $('#tableFuncionariosCoordenacao').dataTable().fnSort([1, 'asc']);
           $scope.$apply();
       }
   }


    /*-----------------------------------------------------------------	
         Recarrega Scopo de Sistema e Ativos Pós deleção Func. Coord.
    ------------------------------------------------------------------*/

   $scope.recarregarSisteasAtivosPosDelecaoCascata = function () {

       $scope.carregarSistemasPorCoordenacao($scope.coordenacaoProj.nome);
       $scope.carregarAtivosPorCoordenacao($scope.coordenacaoProj.uid);
       $scope.funcionariosSistemaSelecionado = [];
       $scope.funcionariosAtivoSelecionado = [];

   }


    /*-------------------------------------------------------------	
         Carrega sistemas pertencentes a coordenacao selecionada
    ---------------------------------------------------------------*/    
   $scope.carregarSistemasPorCoordenacao = function (coordenacaoNome) {
       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/siapp/obterSistemasPorNome/" + coordenacaoNome;

       $http({
           method: 'GET',
           url: $scope.url,
       }).then(function successCallback(response) {

           if (response.data != null) {

               $scope.sistemasCoordenacao = response.data.list;                             
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Sistemas da Coordenação] " + response.statusText;;
       });
   }

   
    /*-------------------------------------------------------------	
        Carrega ativos pertencentes a coordenacao selecionada
   ---------------------------------------------------------------*/
   $scope.carregarAtivosPorCoordenacao = function (coordenacaoId) {
       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/ativo/obterAtivosPorIdCoordenacao/" + coordenacaoId;

       $http({
           method: 'GET',
           url: $scope.url,
       }).then(function successCallback(response) {

           if (response.data != null) {

               $scope.ativosCoordenacao = response.data.list;              
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Ativos da Coordenação] " + response.statusText;;
       });
   }


    /*----------------------------------------------------------------------------------	
      CRUD Funcionarios a atribuir a sistemas, excluindo os já atribuidos ao mesmo
    ------------------------------------------------------------------------------------*/
   $scope.mostrarFuncionarioSistema = function (index) {

       $scope.sistemaSelecionado = $scope.sistemasCoordenacao[index];
       $scope.funcionariosSistemaSelecionado = $scope.sistemaSelecionado.funcionarios;  
   }

   $scope.carregarFuncionariosCoordenacaoSistema = function () {

       $scope.funcionariosCoordenacaoSistemas = [];

       for (var i = 0; i < $scope.funcionariosCoordenacao.length; i++) {

           if (!$scope.matriculaExiste('tableFuncionariosSistemaSelecionado',0,
               $scope.funcionariosCoordenacao[i].matricula)) {

               $scope.funcionariosCoordenacaoSistemas.push($scope.funcionariosCoordenacao[i]);
           }
       }                   

       $('#inclusaoFuncionarioSistemasModal').modal({ show: 'false' });
   }

   $scope.incluirFuncionarioSistema = function (index) {
              
       $scope.sistemaSelecionado.funcionarios.push($scope.funcionariosCoordenacaoSistemas[index]);

       btnId = '#bntFuncionarioSistema' + $scope.funcionariosCoordenacaoSistemas[index].uid;

       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/siapp/salvarSistema";

       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.sistemaSelecionado
       }).then(function successCallback(response) {

           if (response.data.isError) {
               $(btnId).text(response.data.message).removeClass('btn-info').addClass('btn-default').prop('disabled', true);

           }
           else if (response.data != null) {               

               $scope.putLog($scope.enumAcao.inclui_funcionario_sistema,
                   "Atribuido Funcionário [" + $scope.funcionariosCoordenacaoSistemas[index].nome + "] ao sistema : [" + $scope.sistemaSelecionado.sistema + "]");

               $scope.funcionariosCoordenacaoSistemas.splice(index, 1);

           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Inclusão Funcionários a Sistemas ] " + response.statusText;;
       });
   }

   $scope.excluirFuncionarioSistema = function (index) {

       var antigoFuncionario = $scope.sistemaSelecionado.funcionarios[index].nome;
       $scope.sistemaSelecionado.funcionarios.splice(index, 1);

       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/siapp/salvarSistema";

       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.sistemaSelecionado
       }).then(function successCallback(response) {

           if (response.data.isError) {
               $scope.msgErro = response.data.message;
           }
           else if (response.data != null) {

               $scope.putLog($scope.enumAcao.exclui_funcionario_sistema,
                    "Excluido Funcionário [" + antigoFuncionario + "] do sistema : [" + $scope.sistemaSelecionado.sistema + "]");
               
               $scope.msgSucesso = "Funcionário excluído do Sistema.";
               $('#result').hide().show('slow').delay(2000).hide('slow');               
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Exclusão Funcionários a Sistemas ] " + response.statusText;
       });
   }
    

    /*-------------------------------------------------------------	
         CRUD ativos pertencentes a coordenacao selecionada
    ---------------------------------------------------------------*/
   $scope.mostrarFuncionarioAtivo = function (index) {

       $scope.ativoSelecionado = $scope.ativosCoordenacao[index];
       $scope.funcionariosAtivoSelecionado = $scope.ativoSelecionado.funcionarios;
   }
    
   $scope.carregarFuncionariosCoordenacaoAtivo = function () {

       $scope.funcionariosCoordenacaoAtivos = [];

       for (var i = 0; i < $scope.funcionariosCoordenacao.length; i++) {

           if (!$scope.matriculaExiste('tableFuncionariosAtivoSelecionado', 0,
               $scope.funcionariosCoordenacao[i].matricula)) {

               $scope.funcionariosCoordenacaoAtivos.push($scope.funcionariosCoordenacao[i]);
           }
       }

       $('#inclusaoFuncionarioAtivosModal').modal({ show: 'false' });
   }
        
   $scope.incluirFuncionarioAtivo = function (index) {
       
       $scope.ativoSelecionado.funcionarios.push(
           $scope.funcionariosCoordenacaoAtivos[index]);

       btnId = '#bntFuncionarioAtivos' + $scope.funcionariosCoordenacaoAtivos[index].uid;

       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/ativo/save";

       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.ativoSelecionado
       }).then(function successCallback(response) {

           if (response.data != null) {

               $scope.putLog($scope.enumAcao.inclui_funcionario_ativo,
                    "Atribuido Funcionário [" + $scope.funcionariosCoordenacaoAtivos[index].nome + "] ao ativo : [" + $scope.ativoSelecionado.nome + "]");

               $scope.funcionariosCoordenacaoAtivos.splice(index, 1);
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Inclusão Funcionários a Sistemas ] " + response.statusText;
       });
   }

   $scope.excluirFuncionarioAtivo = function (index) {

       var antigoFuncionario = $scope.ativoSelecionado.funcionarios[index].nome;
       $scope.ativoSelecionado.funcionarios.splice(index, 1);

       $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/ativo/save";

       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.ativoSelecionado
       }).then(function successCallback(response) {

           if (response.data.isError) {
               $scope.msgErro = response.data.message;
           }
           else if (response.data != null) {

              $scope.putLog($scope.enumAcao.exclui_funcionario_ativo, "Excluido Funcionário [" + antigoFuncionario + "] do ativo : [" + $scope.ativoSelecionado.nome + "]");
              $scope.msgSucesso = "Funcionário excluído do Ativo.";
              $('#result').hide().show('slow').delay(2000).hide('slow');
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Exclusão Funcionários a Ativos ] " + response.statusText;
       });
   }

   $scope.putLog = function (acao, detalhe) {          

       var auditoria = ({
           uid: 0,           
           acao: acao,
           data: null,
           detalhe: detalhe,           
           coordenacaoDto: { uid: $scope.coordenacaoTi.uid },
           funcionarioDto: { uid: $scope.usrLogado.funcionario.uid }
       });

       auditoriaServices.gravaLog(auditoria).then(function (response) {
          
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;
       });
   }

   $scope.checkProfile = function () {
       if ($scope.usrLogado.funcionario.dataProfile == null) {
           $scope.msgInfo = " Acesse menu Profile e atualize seu perfil ou ";

           $('#msgComplemento').append("<a href='http://unidades/sites/CEDESSP/EQUIPES/SitePages/profile.aspx'>Clique Aqui</a>");
           
       }
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
       $scope.uri = "/";
   }

   $timeout(function () {
       $('#tableAdUsuarios').dataTable();
       $scope.formatatableFuncionariosCoordenacao();
   }, 500);

   $("li[id*='menu']").removeClass("active");
   $("#menuCoord").addClass("active");      

   $scope.$root.tituloMenu = "Manutenção de Coordenações";

   auditoriaServices.setServer("http://" + $scope.ip);
   funcionarioServices.setServer("http://" + $scope.ip);
   coordenacaoServices.setServer("http://" + $scope.ip);

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
   else if ($scope.usrLogado.funcionario.tipoFuncionario == "COORDENADOR_TI") {       

       $scope.obterCoordenacaoTiPorIdCoordenador($scope.usrLogado.funcionario.uid);
       $scope.checkProfile();
       $('body').removeClass('loading');

   }
   else if ($scope.usrLogado.funcionario.tipoFuncionario == "GERENTE_TI") {

       $scope.obterCoordenacoesPorTipo($scope.usrLogado.funcionario.uid);
       $scope.checkProfile();
       $('body').removeClass('loading');
   }
   else {       
       $scope.msgErro = "Acesso Restrito!!";
   }
  	  
});

