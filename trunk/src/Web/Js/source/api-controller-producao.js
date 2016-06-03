app.controller('api_ctrl_producao', function ($scope, $http, LdapService_producao, $timeout) {
    var matricula = ObtemMatriculaUsuarioLogado();


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
           $scope.msgErro = "Request failed: " + response.statusText;;
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
           $scope.coordenacoesProj = [];
       } else {
           $scope.obterCoordenacoesProjPorId($scope.coordenacaoTi.uid);
       }
       $scope.showListTi = false;
       $scope.funcionarioTi = undefined;
   };
    //Deprecated
   $scope.carregarFuncionariosCoordenacaoTiLdap = function () {
       $('body').addClass('loading');
       LdapService_producao.obterUsuariosPorGrupo("G CS7266 " + $scope.coordenacaoTi.grupo).then(function (response) {
           if (response.data == null) {

           } else {
               $scope.funcionariosCoordenacaoTiLdap = response.data.list;
               $('body').removeClass('loading');
           }
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });
   };

   $scope.trocarCoordenadorTi = function () {

       if ($scope.coordenacaoTi.coordenador != null && ($scope.coordenacaoTi.coordenador.nome == $scope.funcionarioTi[0].name)) {
           $scope.msgInfo = "Funcionário Já Selecionado, escolha outra Coordenador" ;
           $('#resultInfo').hide().show('slow');
           return;
       }

       var coordenador = {
           uid: 0,
           nome: $scope.funcionarioTi[0].name,
           cargo: $scope.funcionarioTi[0].title,
           matricula: $scope.funcionarioTi[0].sAMAccountName,
           depto: $scope.funcionarioTi[0].department,
           tipoFuncionario: 2
       }

       $scope.coordenacaoTi.coordenador = coordenador;
       $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/setaCoordenador";

       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.coordenacaoTi
       }).then(function successCallback(response) {

           if (response.data != null) {

               if (response.data.isError) {
                   $scope.msgInfo = response.data.message;
                   $('#resultInfo').hide().show('slow');
               }
               else {
                   $scope.msgSucesso = response.data.message;
                   $('#result').hide().show('slow').delay(2000).hide('slow');
               }
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });
   };

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
        
   };
    //Deprecated
   $scope.carregarFuncionariosCoordenacaoProjLdap = function () {
       $('body').addClass('loading');


       LdapService_producao.obterUsuariosPorGrupo("G CS7266 " + $scope.coordenacaoProj.grupo).then(function (response) {
           if (response.data == null) {

           } else {
               $scope.funcionariosCoordenacaoProjLdap = response.data.list;
               $('body').removeClass('loading');
           }
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });

   };

   $scope.trocarCoordenadorProj = function () {

       if ($scope.coordenacaoProj.coordenador != null && ($scope.coordenacaoProj.coordenador.nome == $scope.funcionarioTi[0].name)) {
           $scope.msgInfo = "Funcionário Já Selecionado, escolha outra Coordenador";
           $('#resultInfo').hide().show('slow');
           return;
       }

       var coordenador = {
           uid: 0,
           nome: $scope.funcionarioProj[0].name,
           matricula: $scope.funcionarioProj[0].sAMAccountName,
           cargo: $scope.funcionarioProj[0].title,
           depto: $scope.funcionarioProj[0].department,
           tipoFuncionario: 1
       }

       $scope.coordenacaoProj.coordenador = coordenador;
       $scope.url = "http://" + $scope.ip +
            "/api-sharepoint/api/coordenacao/setaCoordenador";

       $http({
           method: 'POST',
           url: $scope.url,
           data: $scope.coordenacaoProj
       }).then(function successCallback(response) {

           if (response.data.isError) {
               $scope.msgInfo = response.data.message;
               $('#resultInfo').hide().show('slow');
           }
           else {
               $scope.msgSucesso = response.data.message;
               $('#result').hide().show('slow').delay(2000).hide('slow');
           }           

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });
   };

    /*-------------------------------------------
    	Carrega DataTables
   ---------------------------------------------*/

   $scope.loadTablesAtivos = function () {
		
       $scope.carregarFuncionarioCoordenacao($scope.coordenacaoProj.uid);
       $scope.carregarSistemasPorCoordenacao($scope.coordenacaoProj.nome);
       $scope.carregarAtivosPorCoordenacao($scope.coordenacaoProj.uid);
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

           LdapService_producao.obterTodosUsuariosPorGrupo("G CS7266 usuarios").then(function (response) {
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

   $scope.incluirFuncionarioCoordenacao = function (index) {
       
       btnId = '#bntFuncionarioLdap' + $scope.todosFuncionariosLdap[index].sAMAccountName

       var funcionario = ( {
           uid: 0,
           coordenacaoDto: { uid: $scope.coordenacaoProj.uid },
           nome: $scope.todosFuncionariosLdap[index].name,
           cargo: $scope.todosFuncionariosLdap[index].title,
           matricula: $scope.todosFuncionariosLdap[index].sAMAccountName,
           depto: $scope.todosFuncionariosLdap[index].department,
           tipoFuncionario: 1});              

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

				$scope.funcionariosCoordenacao.splice(index, 1);

				$timeout(function () {

				    $scope.recarregarSisteasAtivosPosDelecaoCascata();

				    $scope.formatatableFuncionariosCoordenacao();

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

               $scope.funcionariosCoordenacaoSistemas.splice(index, 1);
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Inclusão Funcionários a Sistemas ] " + response.statusText;;
       });
   }

   $scope.excluirFuncionarioSistema = function (index) {

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

           if (response.data.isError) {
               $(btnId).text(response.data.message).removeClass('btn-info').addClass('btn-default').prop('disabled', true);
           }
           else if (response.data != null) {
               $scope.funcionariosCoordenacaoAtivos.splice(index, 1);
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Inclusão Funcionários a Sistemas ] " + response.statusText;
       });
   }

   $scope.excluirFuncionarioAtivo = function (index) {

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
               $scope.msgSucesso = "Funcionário excluído do Ativo.";
               $('#result').hide().show('slow').delay(2000).hide('slow');
           }

       }, function errorCallback(response) {
           $scope.msgErro = "Request failed: [Exclusão Funcionários a Ativos ] " + response.statusText;
       });
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

       $scope.obterCoordenacoesPorTipo(
           objFuncionario.funcionario.uid
       );

       $('body').removeClass('loading');
   }
   else {
       $scope.msgErro = "Acesso Restrito!!";
   }
  	  
});

