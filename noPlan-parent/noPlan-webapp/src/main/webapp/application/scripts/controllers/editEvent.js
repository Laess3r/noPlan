'use strict';

angular.module('noPlan')
    .controller('editEvent',function ($scope,$log,dataFactory) {
        $scope.editState = false;
        $scope.times = {};

        if($scope.event.id === undefined){
            $scope.editState=true;
            $scope.event.id="tmp";
            $scope.sched["tmp"]={start:"00:00",end:"00:00"};
        }

        var tmpdate =  new Date($scope.event.startdate);
        for(var i=0;i<$scope.time.length;i++){
        	var d = new Date($scope.time[i].date);
        	if(d.getDate()===tmpdate.getDate()){
        		$scope.times = $scope.time[i];
        		break;
        	}
        	
        	
        	
        }
        
       $scope.$watch('times', function() {
        	// console.log("watch",$scope.times);
        	 
        	 $scope.event.startdate = $scope.event.enddate = new Date($scope.times.date);
        	}); // initialize the watch

        $scope.edit = function($index){
            $scope.editState = ! $scope.editState;





            if(!$scope.editState){
                var event=$scope.events[$index];
                if(event.id===undefined || event.id==="tmp"){
                	
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

