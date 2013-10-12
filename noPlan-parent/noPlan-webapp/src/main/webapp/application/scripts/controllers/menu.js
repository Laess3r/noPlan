'use strict';

console.log('init menu');
angular.module('mytodoApp')
    .controller('NavbarCtrl',function ($scope, $location, $route, UserService) {
        console.log('init NavbarCtrl')
        $scope.isActive = function (viewLocation){
          console.log($location.path());
          return viewLocation === $location.path();
        };








    });
