'use strict';

console.log('init edit');
angular.module('mytodoApp')
    .controller('editCtrl',function ($scope,$modal,$log,dataFactory) {
        console.log('init EditCtrl');
        $scope.editState = false;

        $scope.edit = function($index){
            $scope.editState = ! $scope.editState;
            console.log("edit",$scope.editState, $scope);



            if(!$scope.editState){
                var item=$scope.items[$index];
                if(item.id===undefined){
                    $scope.insertConference(item);
                    //$scope.insertConference({name:item.name,description:item.description})
                }
                else{
                    $scope.updateConference(item);
                    //$scope.updateConference({id:item.id,name:item.name,description:item.description})
                }

            }

        }


    });

