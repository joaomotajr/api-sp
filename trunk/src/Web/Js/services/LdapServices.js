app.factory('LdapService_producao', function ($http, $q) {

    var url = "http://10.1.32.240:8090/api-sharepoint/api/ldap";

    return {
        obterUsuariosPorGrupo: function (grupo) {
        
        	var uri = "/obterUsuariosPorGrupo/" + grupo;

            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: url + uri,
            }).then(function successCallback(response) {
                deferred.resolve(response);
            }, function errorCallback(response) {
                deferred.reject(response);
            });

            return deferred.promise;
        },

        obterTodosUsuariosPorGrupo: function (grupo) {

            var uri = "/obterTodosUsuariosPorGrupo/" + grupo;

            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: url + uri,
            }).then(function successCallback(response) {
                deferred.resolve(response);
            }, function errorCallback(response) {
                deferred.reject(response);
            });

            return deferred.promise;
        }
    }
});

