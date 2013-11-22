'use strict';

console.log('init edit');
angular.module('mytodoApp')
    .controller('editTrack',function ($scope,$log,dataFactory) {
        console.log('init EditCtrl',$scope.track.id);
        $scope.editState = false;

        if($scope.track.id === undefined){
            $scope.editState=true;
        }


        $scope.edit = function($index){
            $scope.editState = ! $scope.editState;
            console.log("edit",$scope.editState, $scope);





            if(!$scope.editState){
                var track=$scope.tracks[$index];
                if(track.id===undefined){
                    $scope.insertTrack(track);
                    //$scope.insertConference({name:track.name,description:track.description})
                }
                else{
                    $scope.updateTrack(track);
                    //$scope.updateConference({id:track.id,name:track.name,description:track.description})
                }

            }

            /*else {

                $scope.track.startDate= new Date($scope.track.startDate).toJSON().slice(0,10);
                $scope.track.endDate= new Date($scope.track.endDate).toJSON().slice(0,10);

            } */

        }


    });

