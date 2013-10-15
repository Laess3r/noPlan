'use strict';
console.log("init main");

angular.module('mytodoApp')
    .controller('MainCtrl',function ($scope,localStorageService){
        console.log('init MainCtrl')

        /*$scope.pushSomething = function() {
            BreadCrumbsService.push( 'home', {
                href: '#/library/data/foo',
                label: 'Foo'
            } );
        };*/

    });
