app.controller('upl_ctrl_producao', function ($scope, $http, $timeout) {

    if (checkSiteIsProducao()) {
        $scope.ip = $scope.$root.ip;
    }
    else {
        $scope.ip = $scope.$root.ipDesenv;
    }

    $('body').removeClass('loading');

    $scope.$root.tituloMenu = "Upload SIAPP";

    $("li[id*='menu']").removeClass("active");
    $("#menuUpload").addClass("active");


    var matricula = ObtemMatriculaUsuarioLogado();

    var objFuncionario = synchronousService($scope.ip,"funcionario/obterFuncionarioPorMatricula/" + matricula,"GET");

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
    else if (objFuncionario.funcionario.tipoFuncionario != "COORDENADOR_TI" && objFuncionario.funcionario.tipoFuncionario != "GERENTE_TI") {

        $scope.msgErro = "Acesso Restrito!!";
    }
                
    $scope.carrega = function() {        

        if (!$scope.validated()) {

            jQuery('#lblResult').text("Por favor, selecione o arquivo!");
            return;
        }
        
        var file = jQuery("#AttachmentUploadField")[0].files[0];

        var reader = new FileReader();

        $scope.siapp = [];
        
        reader.onload = function (progressEvent) {

            var lines = this.result.split('\n');
            for (var line = 0; line < lines.length; line++) {

                var lineValue = lines[line].split(",");
                if (lineValue[1] == "CEDESSP") {

                    var rowReaded = ({
                        uid: 0,
                        codigo: lineValue[6],
                        sistema: lineValue[7],
                        carteira: lineValue[5],
                        descricao: lineValue[8],
                        coordenacaoProjeto: lineValue[3],
                        coordenacaoTi: lineValue[2]
                    });

                    $scope.siapp.push(rowReaded);
                }
            }            

            $('#lblResult').html("Arquivo lido com Sucesso, " + $scope.siapp.length + " Linhas.");            

        };

        reader.readAsText(file, "ASCII");
    }

    $scope.validated = function() {
        var file = $("#AttachmentUploadField")[0].files[0];

        if (file == null) {
            return false;
        }
        else {
            return true;
        }
    }


    $scope.insertSIAPP = function () {

        if ($scope.siapp == [] || $scope.siapp == undefined) {
            jQuery('#lblResult').text("Arquivo ainda não carregado.");
            return;
        }

        $('#lblResult').html("");
        $('body').addClass('loading');

        $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/siapp/salvarSistemas";

        $http({
            method: 'POST',
            url: $scope.url,
            data: $scope.siapp
        }).then(function successCallback(response) {
                        
            if (response.data.isError) {
                $('body').removeClass('loading');
                $scope.msgErro = "Request failed: " + response.statusText;
                
            }
            else if (response.data != null) {

                $timeout(function () {                    

                    $scope.msgSucesso = "Tabelas SIAPP Atualizadas com Sucesso!";
                    $('#result').hide().show('slow').delay(2000);
                    
                    $('body').removeClass('loading');
                }, 500);                
            }

        }, function errorCallback(response) {
            $('body').removeClass('loading');
            $scope.msgErro = "Request failed: [Carregar Sistemas ] Serviço não disponível. " + response.statusTex;
        });

    };

    $scope.listaSistemas = function (index) {
                
        $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/siapp/all";

        $('body').addClass('loading');

        $http({
            method: 'GET',
            url: $scope.url,
        }).then(function successCallback(response) {

            if (response.data.isError) {
                $('body').removeClass('loading');
                $scope.msgErro = response.data.message;                
            }
            else if (response.data != null) {
                
                $timeout(function () {
                    $('#tableSistemas').show();
                    $scope.todosSistemas = response.data.list;

                    $('body').removeClass('loading');
                }, 100);
            }

        }, function errorCallback(response) {
            $('body').removeClass('loading');
            $scope.msgErro = "Request failed: [Listar Sistemas ]  Serviço não disponível. " + response.statusText;
        });
    }

    $scope.formataTable = function () {

        $('#tableSistemas').dataTable({
            "bLengthChange": false,
            "filter": true,
            "info": false,
            "scrollY": "400px",
            "paging": false,
            "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
        });

    }

  
});

