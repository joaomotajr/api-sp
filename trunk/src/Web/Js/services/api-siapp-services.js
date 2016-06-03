app.service('siappServices_producao', function ($http, $q) {

    var constSiappUri = "/api-sharepoint/api/siapp";

    url = "";
    server = "";

    this.setServer = function (server) {
        this.server = server;
        this.setUrl();
    };

    this.setUrl = function () {
        this.url = this.server + constSiappUri;
    }

    this.obterSistemasCoordenacao = function (nomeCoordenacao) {

        var deferred = $q.defer();

        $http({
            method: 'GET',
            url: this.url + "/obterSistemasPorNome/" + nomeCoordenacao,
        }).then(function successCallback(response) {
            deferred.resolve(response);
        }, function errorCallback(response) {
            deferred.reject(response);
        });

        return deferred.promise;

    };
    
});

