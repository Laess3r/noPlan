'use strict';

console.log('init edit');
angular.module('mytodoApp')
    .controller('editEvent',function ($scope,$modal,$log,dataFactory) {
        console.log('init EditCtrl',$scope.event.id);
        $scope.editState = false;

        if($scope.event.id === undefined){
            $scope.editState=true;
        }


        $scope.edit = function($index){
            $scope.editState = ! $scope.editState;
            console.log("edit",$scope.editState, $scope);





            if(!$scope.editState){
                var event=$scope.events[$index];
                if(event.id===undefined){
                    $scope.insertEvent(event);
                    //$scope.insertConference({name:event.name,description:event.description})
                }
                else{
                    $scope.updateEvent(event);
                    //$scope.updateConference({id:event.id,name:event.name,description:event.description})
                }

            }

            /*else {

                $scope.event.startDate= new Date($scope.event.startDate).toJSON().slice(0,10);
                $scope.event.endDate= new Date($scope.event.endDate).toJSON().slice(0,10);

            } */

        }


    });

