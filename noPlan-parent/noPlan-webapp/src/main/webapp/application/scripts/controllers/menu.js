'use strict';

console.log('init menu');
angular.module('mytodoApp')
    .controller('NavbarCtrl',function ($scope, $location,$route) {

        $scope.isAdmin = false;

        $scope.breadcrumbs = [

            ];

        var setbreadcrumbs = function (path){
            path = path.split('/');

            if(path[1]==="conferences"){

                    $scope.breadcrumbs=[{name:"Conferences", link:"#/conferences"}];

                return
            }
            if(path[1]==="tracks" && path[2] && $scope.breadcrumbs.length>=1){
                var item = {name:"Conference " + path[2], link:"#/tracks/"+path[2]};
                $scope.breadcrumbs.splice(1,5)
                $scope.breadcrumbs.push(item)
                return
            }

            if(path[1]==="events" && path[2] && $scope.breadcrumbs.length>=2){
                var item = {name:"Track " + path[2], link:"#/events/"+path[2]};
                $scope.breadcrumbs.splice(2,5)
                $scope.breadcrumbs.push(item)
                return
            }
            else{
                $scope.breadcrumbs=[];

            }



        }


        $scope.login = function(){
            $scope.isAdmin = true;

        }

        $scope.logout = function(){
            $scope.isAdmin = false;

        }


        $scope.$on('$routeChangeSuccess', function(scope,next,current){
            setbreadcrumbs($location.path())

        })
        console.log('init NavbarCtrl')
        $scope.isActive = function (viewLocation){
          return viewLocation === $location.path();





        };








    });
