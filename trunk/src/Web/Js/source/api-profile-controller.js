
app.controller('api_profile_ctrl', function ($scope, $http, LdapService, $timeout, auditoriaServices, funcionarioServices, profileServices, timelineServices) {

	$scope.conhecimentos = ['A', 'B', 'C', 'D'];
    $scope.rating = 5;
    $scope.rateFunction = function(rating, conhecimento) {
		alert(rating + ", " + conhecimento);
    };

   $scope.pesquisarUsuarioLdap = function (matricula) {

        $('body').addClass('loading');

        LdapService.obterDadosUsuario(matricula).then(function (response) {
            if (response.data == null) {
                $scope.msgErro = "Matricula não localizada, contate o administrador!!";
            } else {

                $scope.dadosFuncionarioLdap = response.data.list[0];
                $scope.salvarFuncionario();

                $('body').removeClass('loading');
            }
        }, function (response) {
            $scope.msgErro = "Request failed: " + response.statusText;
        });      

   };

   $scope.obterFuncionario = function (uid) {

       funcionarioServices.obterFuncionarioPorId(uid).then(function (response) {
           $scope.funcionario = response.data.funcionario;
       }, function (response) {
           if (response.data != null) {
               $scope.msgErro = "Request failed: " + response.statusText;;
           }
       }); 
   }

   $scope.obterProfile = function (uid) {

   profileServices.obterProfile(uid).then(function (response) {
           if (!response.data.isError) {

               $scope.isProfile = true;
               $scope.profile = response.data.profile;
               $scope.loadProfileValues();

               if ($scope.profile.dataAdmissao != null) {

                   $scope.obterTimelinePorFuncionarioPosAdmissao();
                   $scope.obterTimelinePorFuncionarioAntesAdmissao();
               }
           }
           else
           {
               $scope.isProfile = false;
           }

       }, function (response) {
           if (response.data != null) {
               $scope.msgErro = "Request failed: " + response.statusText;;
           }
       });
   }
    
   $scope.obterTimelinePorFuncionarioPosAdmissao = function () {

       timelineServices.obterTimelinePorFuncionarioMaiorData(
           $scope.funcionario.uid,
           $scope.profileAdmissaoData)
       .then(function (response) {
           if (!response.data.isError) {

               $scope.timelinePosAdmissao = response.data.list;
           }
       }, function (response) {
           if (response.data != null) {
               $scope.msgErro = "Request failed: " + response.statusText;;
           }
       });
   }

   $scope.obterTimelinePorFuncionarioAntesAdmissao = function () {

       timelineServices.obterTimelinePorFuncionarioMenorData(
           $scope.funcionario.uid,
           $scope.profileAdmissaoData)
       .then(function (response) {
           if (!response.data.isError) {

               $scope.timelineAntesAdmissao = response.data.list;
           }
       }, function (response) {
           if (response.data != null) {
               $scope.msgErro = "Request failed: " + response.statusText;;
           }
       });
   }
   
   $scope.loadProfileValues = function () {
                        
       if (!$scope.isProfile) return;

       //Limpa elemento base (inputs) ao recarregar 
       $('.clearMe').remove();

        $scope.profileObjetivo = $scope.profile.objetivo;
        $scope.profileQualificacao = $scope.profile.qualificacao;
        $scope.profileAnotacoes = $scope.profile.anotacoes;

        if ($scope.profile.dataAdmissao != null)
            $scope.profileAdmissaoData = new Date($scope.profile.dataAdmissao);

        if ($scope.profile.horaEntrada != undefined) {
            var horarioEntrada = new Date();
            var entrada = $scope.profile.horaEntrada.split(':');
            horarioEntrada.setHours(entrada[0], entrada[1], 0, 0);
            $scope.profileHoraEntrada = horarioEntrada;
        }

        if ($scope.profile.horaSaida != undefined) {
            var horarioSaida = new Date();
            var saida = $scope.profile.horaSaida.split(':');
            horarioSaida.setHours(saida[0], saida[1], 0, 0);
            $scope.profileHoraSaida = horarioSaida;
        }        
        
        $scope.profileFormacao = [];
        if ($scope.profile.formacao != null) {
            var profileFormacao = $scope.profile.formacao.split(";", 3);
            for (var i = 0; i < profileFormacao.length; i++) {
                if (profileFormacao[i] != "") {
                    var item = { id: i, nome: profileFormacao[i] };
                    $scope.profileFormacao.push(item);
                }
            }
        }

        $scope.profileExperiencia = [];
        if ($scope.profile.experiencia != null) {
            var profileExperiencia = $scope.profile.experiencia.split(";", 3);
            for (var i = 0; i < profileExperiencia.length; i++) {
                if (profileExperiencia[i] != "") {
                    var item = { id: i, nome: profileExperiencia[i] };
                    $scope.profileExperiencia.push(item);
                }
            }
        }

        $scope.profileTreinamento = [];
        if ($scope.profile.treinamento != null) {
            var profileTreinamento = $scope.profile.treinamento.split(";", 3);
            for (var i = 0; i < profileTreinamento.length; i++) {
                if (profileTreinamento[i] != "") {
                    var item = { id: i, nome: profileTreinamento[i] };
                    $scope.profileTreinamento.push(item);
                }
            }
        }

        $scope.profileCertificacao = [];
        if ($scope.profile.certificacao != null) {
            var profileCertificacao = $scope.profile.certificacao.split(";", 3);
            for (var i = 0; i < profileCertificacao.length; i++) {
                if (profileCertificacao[i] != "") {
                    var item = { id: i, nome: profileCertificacao[i] };
                    $scope.profileCertificacao.push(item);
                }
            }
        }

    }

   $scope.salvarFuncionario = function () {

       var currentDate = new Date();       

       var funcionario = {
           uid: 0,
           nome: $scope.dadosFuncionarioLdap.name,
           cargo: $scope.dadosFuncionarioLdap.title,
           matricula: $scope.dadosFuncionarioLdap.sAMAccountName,
           depto: $scope.dadosFuncionarioLdap.department,
           tipoFuncionario: 1,
           dataProfile: currentDate,
           email: $scope.dadosFuncionarioLdap.mail,
           fone: $scope.dadosFuncionarioLdap.telephonenumber,
           celular: $scope.dadosFuncionarioLdap.mobile,
           empresa: $scope.dadosFuncionarioLdap.company,
           logradouro: $scope.dadosFuncionarioLdap.street,
           cidade: $scope.dadosFuncionarioLdap.city,
           uf: $scope.dadosFuncionarioLdap.st,
           cep: $scope.dadosFuncionarioLdap.PostalCode           
       }       

       funcionarioServices.salvar(funcionario).then(function (response) {
           if (response.data != null) {              
               $scope.funcionario = response.data.funcionario;

           }
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;;
       });

   };

   $scope.getOptions = function (campo) {
       var retorno = "";            

       retorno += ($("." + campo)[0] != undefined && $("." + campo)[0].value != "" ? $("." + campo)[0].value + ";" : "");
       retorno += ($("." + campo)[1] != undefined && $("." + campo)[1].value != "" ? $("." + campo)[1].value + ";" : "");
       retorno += ($("." + campo)[2] != undefined && $("." + campo)[2].value != "" ? $("." + campo)[2].value + ";" : "");

       return retorno == "" ? null : retorno;
   }

   $scope.salvarProfile = function () {
       
       var dataAdm = new Date($scope.profileAdmissaoData);             

       $scope.horaEntrada = null;
       if ($scope.profileHoraEntrada != undefined) {
           var horaEntrada = new Date($scope.profileHoraEntrada);
           $scope.horaEntrada = padZeros(horaEntrada.getHours(), 2) + ":" + padZeros(horaEntrada.getMinutes(), 2);
       }

       $scope.horaSaida = null;
       if ($scope.profileHoraSaida != undefined) {
           var horaSaida = new Date($scope.profileHoraSaida);
           $scope.horaSaida = padZeros(horaSaida.getHours(), 2) + ":" + padZeros(horaSaida.getMinutes(), 2);
       }

       var profile = ({
               uid: $scope.profile == undefined ? 0 : $scope.profile.uid,
               funcionarioDto: { uid: $scope.funcionario.uid },
               objetivo: $scope.profileObjetivo,
               qualificacao: $scope.profileQualificacao,
               formacao: $scope.getOptions("formacao"),
               experiencia: $scope.getOptions("experiencia"),
               treinamento: $scope.getOptions("treinamento"),
               certificacao: $scope.getOptions("certificacao"),
               anotacoes: $scope.profileAnotacoes,
               dataAdmissao: $scope.profileAdmissaoData == undefined ? null : dataAdm,
               horaEntrada: $scope.horaEntrada,
               horaSaida: $scope.horaSaida
           });             

       profileServices.salvar(profile).then(function (response) {
           if (response.data != null) {
                              
               $('body').addClass('loading');

               $timeout(function () {
                   $scope.obterProfile($scope.funcionario.uid);
                   $('body').removeClass('loading');
               }, 500);
           }
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;
       });
   };

   $scope.editTimelinePos = function (index) {

       var data = new Date($scope.timelinePosAdmissao[index].data);

       $scope.timelineUid =  $scope.timelinePosAdmissao[index].uid
       $scope.timelineData = data;
       $scope.timelineTitulo = $scope.timelinePosAdmissao[index].titulo;
       $scope.timelineDetalhe = $scope.timelinePosAdmissao[index].detalhe;

       $('#addTimeline').modal({ show: 'false' });
   }

   $scope.editTimelineAntes = function (index) {

       var data = new Date($scope.timelineAntesAdmissao[index].data);

       $scope.timelineUid = $scope.timelineAntesAdmissao[index].uid
       $scope.timelineData = data;
       $scope.timelineTitulo = $scope.timelineAntesAdmissao[index].titulo;
       $scope.timelineDetalhe = $scope.timelineAntesAdmissao[index].detalhe;

       $('#addTimeline').modal({ show: 'false' });
   }

   $scope.excluiTimelineAntes = function (index) {

       $scope.timelineUid = $scope.timelineAntesAdmissao[index].uid

       var itemTimeline = ({ uid: $scope.timelineUid, funcionarioDto: null, data: null, titulo: null, detalhe: null, tipoTimeline: null  });

       timelineServices.deletar(itemTimeline)
       .then(function (response) {
           if (!response.data.isError) {
               $scope.refreshTimeline();
           }
       }, function (response) {
           if (response.data != null) {
               $scope.msgErro = "Request failed: " + response.statusText;;
           }
       });

   }

   $scope.excluiTimelinePos = function (index) {

       $scope.timelineUid = $scope.timelinePosAdmissao[index].uid

       var itemTimeline = ({ uid: $scope.timelineUid, funcionarioDto: null, data: null, titulo: null, detalhe: null, tipoTimeline: null });

       timelineServices.deletar(itemTimeline)
       .then(function (response) {
           if (!response.data.isError) {
               $scope.refreshTimeline();
           }
       }, function (response) {
           if (response.data != null) {
               $scope.msgErro = "Request failed: " + response.statusText;;
           }
       });

   }

   $scope.salvarItemTimeline = function () {

       var date = new Date($scope.timelineData);             

       var itemTimeline = ({
           uid: $scope.timelineUid != undefined ? $scope.timelineUid : 0,
           funcionarioDto: { uid: $scope.funcionario.uid },
           data: date,
           titulo: $scope.timelineTitulo,
           detalhe: $scope.timelineDetalhe,
           tipoTimeline : 0
       });

       timelineServices.salvar(itemTimeline).then(function (response) {
           if (response.data != null) {
               $scope.msgSucesso = "Item Timeline Salvo com Sucesso.";
               $scope.refreshTimeline();
               
           }
       }, function (response) {
           $scope.msgErro = "Request failed: " + response.statusText;
       });
   };

   $scope.refreshTimeline = function () {
       $('body').addClass('loading');

       $timeout(function () {       
           $scope.clearFormTimeline();
           $scope.obterTimelinePorFuncionarioPosAdmissao();
           $scope.obterTimelinePorFuncionarioAntesAdmissao();          

           $('body').removeClass('loading');
       }, 500);
   }

   $scope.clearFormTimeline = function () {

       $scope.timelineUid = undefined;
       $scope.timelineData = '';
       $scope.timelineTitulo = '';
       $scope.timelineDetalhe = '';
   }

   //$scope.salvarProfileInfoAdmissao = function () {             

   //   var dataAdm = new Date($scope.profileAdmissaoData);
   //   var horaEntrada = new Date($scope.profileHoraEntrada);
   //   var horaSaida = new Date($scope.profileHoraSaida);
       
   //   profileServices.salvarInfoAdmissao(
   //       dataAdm,
   //       padZeros(horaEntrada.getHours(),2) + ":" + padZeros(horaEntrada.getMinutes(),2),
   //       padZeros(horaSaida.getHours(),2) + ":" + padZeros(horaSaida.getMinutes(),2),
   //       $scope.profile == undefined ? 0 : $scope.profile.uid)
   //       .then(function (response) {
   //        if (response.data != null) {
   //            $scope.msgSucesso = "Perfile Salvo com Sucesso.";
   //        }
   //    }, function (response) {
   //        $scope.msgErro = "Request failed: " + response.statusText;
   //    });
   //};

   $scope.salvarInformacoes = function () {
       var dataAdmissao =  $scope.profiledataAdmissao;
   }

   $scope.addPhoto = function () {
       $('#addPhoto').modal({ show: 'false' });
   }

   $scope.addTimeline = function () {
       $('#addTimeline').modal({ show: 'false' });
   }
       
   $('input[name=filePhoto]').change(function () {
       $('#btnUploadPhoto').prop('disabled', false);
   });

   $scope.uploadPhoto = function () {

       var file = jQuery("#inputFile")[0].files[0];      

       if (file!=null)
            $scope.uploadFileLocal(file, "imagensPerfil");
       
   }

   $scope.editProfile = function () {       
       $('#editProfile').modal({
           show: 'false',
           backdrop: 'static',
           keyboard: false
       });
   }

   $scope.editInformacoes = function () {
       $('#editInformacoes').modal({
           show: 'false',
           backdrop: 'static',
           keyboard: false
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

   $scope.uploadFileLocal = function (file, libraryName) {

       $('body').addClass('loading');

       var digest = jQuery("#__REQUESTDIGEST").val();
       var webUrl = _spPageContextInfo.webAbsoluteUrl;
       //var fileName = $scope.matricula + "." + file.name.split('.').pop();

       var reader = new FileReader();
       var arrayBuffer;

       reader.onload = function (e) {
           arrayBuffer = reader.result;

           url = webUrl + "/_api/web/lists/getByTitle(@TargetLibrary)/RootFolder/files/add(url=@TargetFileName,overwrite='true')?" +
              "@TargetLibrary='" + libraryName + "'" +
              "&@TargetFileName='" + $scope.matricula + "'";

           jQuery.ajax({
               url: url,
               type: "POST",
               data: arrayBuffer,
               headers: {
                   "Accept": "application/json; odata=verbose",
                   "X-RequestDigest": digest
               },
               contentType: "application/json; odata=verbose",
               processData: false,
               success: function () {
                   
                   $timeout(function () {
                       $scope.currentImage = 'http://unidades/sites/CEDESSP/EQUIPES/imagensPerfil/' + $scope.matricula;
                       $('body').removeClass('loading');
                   }, 2000);
                                     
               },
               error: function (arr, error) {
                   $('body').removeClass('loading');
                   alert("Erro na carga do arquivo local.");                   
               }
           });
       };

       reader.readAsArrayBuffer(file);
   };


   /*--------------------------
    	INICIA PROCESSAMENTO
   ---------------------------*/      
    
   iniciaControles();
       
   $scope.lookup = 1;
   $scope.isProfile = false;
       
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

   $scope.$root.tituloMenu = "Perfil de Usuários";

   auditoriaServices.setServer("http://" + $scope.ip);
   funcionarioServices.setServer("http://" + $scope.ip);
   profileServices.setServer("http://" + $scope.ip);
   timelineServices.setServer("http://" + $scope.ip);
   
   $scope.matricula = ObtemMatriculaUsuarioLogado();
   $scope.usrLogado = synchronousService($scope.ip, "funcionario/obterFuncionarioPorMatricula/" + $scope.matricula, "GET");
   

   if ($scope.usrLogado == -1 || $scope.usrLogado == null) {
       
       $scope.msgErro = " Serviço Indisponível - Contate o Administrador";
       return;
   }
   else if ($scope.usrLogado.funcionario == null) { //   || $scope.usrLogado.funcionario.dataProfile == null) {

       $scope.pesquisarUsuarioLdap($scope.matricula);
       $scope.currentImage = '../StyleLibrary/Images/avatar-tiny.jpg';       
   }
   else {
              
       $scope.obterFuncionario($scope.usrLogado.funcionario.uid);
       $scope.obterProfile($scope.usrLogado.funcionario.uid);
       $scope.currentImage = 'http://unidades/sites/CEDESSP/EQUIPES/imagensPerfil/' + $scope.matricula;       
   }    	 
   
});

