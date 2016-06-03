app.service('funcionarioServices_producao', function ($http, $q) {

    var constFuncionarioUri = "/api-sharepoint/api/funcionario";
    
    url = "";
    server = "";

    this.setServer = function (server) {
        this.server = server;
        this.setUrl();
    };

    this.setUrl = function () {
        this.url = this.server + constFuncionarioUri;
    }

    this.obterFuncionariosCoordenacao = function (idCoordenacao) {

        var deferred = $q.defer();
        
        $http({
            method: 'GET',
            url: this.url + "/listFuncionariosPorCoordenacao/" + idCoordenacao,
        }).then(function successCallback(response) {
            deferred.resolve(response);
        }, function errorCallback(response) {
            deferred.reject(response);
        });

        return deferred.promise;

    };
  
});

