'use strict';

console.log('init edit');
angular.module('mytodoApp')
    .controller('editEvent',function ($scope,$log,dataFactory) {
        console.log('init EditCtrl',$scope.event.id);
        $scope.editState = false;

        if($scope.event.id === undefined){
            $scope.editState=true;
        }

        $scope.$watch('times', function() {
        	 console.log($scope.event);
        	 $scope.event.startdate = $scope.event.enddate = new Date($scope.times.date);
        	 console.log($scope.event);
        	}); // initialize the watch

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

