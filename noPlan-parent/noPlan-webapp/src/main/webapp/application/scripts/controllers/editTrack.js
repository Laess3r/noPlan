'use strict';

console.log('init edit');
angular.module('mytodoApp')
    .controller('editTrack',function ($scope,$modal,$log,dataFactory) {
        console.log('init EditCtrl',$scope.item.id);
        $scope.editState = false;

        if($scope.item.id === undefined){
            $scope.editState=true;
        }


        $scope.edit = function($index){
            $scope.editState = ! $scope.editState;
            console.log("edit",$scope.editState, $scope);





            if(!$scope.editState){
                var item=$scope.items[$index];
                if(item.id===undefined){
                    $scope.insertTrack(item);
                    //$scope.insertConference({name:item.name,description:item.description})
                }
                else{
                    $scope.updateTrack(item);
                    //$scope.updateConference({id:item.id,name:item.name,description:item.description})
                }

            }

            /*else {

                $scope.item.startDate= new Date($scope.item.startDate).toJSON().slice(0,10);
                $scope.item.endDate= new Date($scope.item.endDate).toJSON().slice(0,10);

            } */

        }


    });

