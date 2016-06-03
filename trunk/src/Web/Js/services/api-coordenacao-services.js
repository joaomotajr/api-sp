app.service('coordenacaoServices_producao', function ($http, $q) {

    var constCoordenacaoUri = "/api-sharepoint/api/coordenacao";

    nomeCoordenacao = "";
    url = "";
    server = "";

    this.setServer = function (server) {
        this.server = server;
        this.setUrl();
    };

    this.setNomeCoordenacao = function (nomeCoordenacao) {
        this.nomeCoordenacao = nomeCoordenacao;
    };

    this.setUrl = function () {
        this.url = this.server + constCoordenacaoUri;
    }
        

    /*-------------------------------------------
    	Obtem Coodenacoes por Id Coordenador
   ---------------------------------------------*/

    this.obterCoordenacaoPorNome = function () {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: this.url + "/obterCoordenacaoPorNome/" + this.nomeCoordenacao,
        }).then(function successCallback(response) {
            deferred.resolve(response);
        }, function errorCallback(response) {
            deferred.reject(response);
        });

        return deferred.promise;

    };

    this.obterCoordenacoesPorId = function (idCoordenacao) {
        
        var deferred = $q.defer();
        
        $http({
            method: 'GET',
            url: this.url + "/obterCoordenacoesPorId/" + idCoordenacao,
        }).then(function successCallback(response) {
            deferred.resolve(response);
        }, function errorCallback(response) {
            deferred.reject(response);
        });

        return deferred.promise;
    };

    

  
});

