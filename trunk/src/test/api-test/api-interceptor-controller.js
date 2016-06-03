//app.factory('httpInterceptor', function ($q, $rootScope) {
//    return {
//        // On request success
//        request: function (config) {
//            $scope.alerts.push({ msg: "Request done !" }); //There is no scope
//            return config || $q.when(config);
//        },
//        // On request failure
//        requestError: function (rejection) {
//            return $q.reject(rejection);
//        },
//        // On response success
//        response: function (response) {
//            return response || $q.when(response);
//        },
//        // On response failure
//        responseError: function (rejection) {
//            return $q.reject(rejection);
//        }
//    };
//});


//app.config('$httpProvider', function ($httpProvider) {
//    $httpProvider.interceptors.push('httpInterceptor');
//})



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
            //},
            //'responseError': function (response) {
            //    console.log('response error');
            //    return response;
            }
                         
        };
        
    });

}]);


app.controller('mycontroller', function ($scope, $http, $rootScope) {
  
    $scope.ip = "localhost:8080";

  $scope.testa = function () {
      $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/test/alive/OK";

      $http({
          method: 'GET',
          url: $scope.url,
      }).then(function successCallback(response) {

          if (response.data != null) {

              if (response.data) {
                  
                  $scope.resp = "Serviços Online";
              }
              else
                  $scope.resp = "Serviços Offline";
          }

      }, function errorCallback(response) {
          $scope.resp = "Request failed: " + response.statusText;
      });
  }

  $scope.ConsultarUsuarioMatricula = function () {
      $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/funcionario/obterFuncionarioPorMatricula/" + $scope.matriculaDB;

      $http({
          method: 'GET',
          url: $scope.url,
      }).then(function successCallback(response) {
          
          if (response.data != null) {                            
              $scope.resp = response.data.funcionario;              
          }
      }, function errorCallback(response) {
          $scope.resp = "Request failed: " + response.statusText;
      });
  };

  $scope.insertSIAPP = function () {

      siapp = [];
      funcionarios = [];
      $rootScope.action = 240;

      var rowReaded = ({
          uid: 0,
          codigo: 1000,
          sistema: "JUNIOR",
          carteira: "JUNIOR",
          descricao: "JUNIOR",
          coordenacaoProjeto: "",
          coordenacaoTi: "",
          funcionarios: funcionarios
      });

      siapp.push(rowReaded);

      $scope.url = "http://" + $scope.ip + "/api-sharepoint/api/siapp/salvarSistemas";

      $http({
          method: 'POST',
          url: $scope.url,
          data: siapp
      }).then(function successCallback(response) {

          if (response.data != null) {
              
              $scope.resp = response.data.message;
          }

      }, function errorCallback(response) {
          $scope.resp = "Request failed: " + response.statusText;
      });

  };
	  
});


