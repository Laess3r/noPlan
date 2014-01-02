'use strict';

angular.module('mytodoApp')
    .controller('EventsUserViewCtrl',function ($routeParams,$scope,dataFactory) {

        //get current track id
        $scope.trackId= $scope.track.id;

        
   
        //get events from server by track ID
        $scope.trackItems = [];
        $scope.getEvents = function() {
            dataFactory.getEvents($scope.trackId)
                .success(function (data) {

                    $scope.trackItems = data;
                    for (var i=0;i<$scope.trackItems.length;i++)
                    {
                        setTimes($scope.trackItems[i]);
                    }
                    console.log(data);


                })
                .error(function (error) {
                    $scope.status = 'Unable to load event data: ' + error.message;
                });
        }   
        $scope.getEvents();


        //set time by event startdate 
        $scope.schedEvents = {};    
        var setTimes = function(data){
            var date = new Date(data.startdate);
            var obj = {};
            obj.start = (date.getHours() < 10 ? "0" : "" ) + date.getHours() +":"+ (date.getMinutes() < 10 ? "0" : "" ) + date.getMinutes();
            date = new Date(data.enddate);
            obj.end = (date.getHours() < 10 ? "0" : "" ) + date.getHours() +":"+ (date.getMinutes() < 10 ? "0" : "" ) + date.getMinutes();
            $scope.schedEvents[data.id]=obj;
        }
          
    });



