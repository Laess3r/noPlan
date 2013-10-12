'use strict';

console.log('init users');
angular.module('mytodoApp')
        .controller('UserCtrl',function ($scope, $location, $http) {
        console.log('init UserCtrl');
        $scope.message="Hello World";
        $http.get('/users')
            .success(function(data,status,headers,config){
               $scope.data=data;
                $scope.message="Successfull";
            })
            .error(function(data,status,headers,config){

               $scope.message="NOT Successfull";
            });










    });
