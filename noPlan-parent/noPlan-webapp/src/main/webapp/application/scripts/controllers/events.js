'use strict';

console.log('init event');
angular.module('mytodoApp')
    .controller('EventsCtrl',function ($routeParams,$scope,$log,dataFactory) {
        $scope.trackId=$scope.track.id;

        console.log('init EventCtrl',$scope);


        $scope.events = [];

        $scope.addEvent = function() {
            console.log("Add Event")
            var track= {
                name:"Name",
                description:"Desc",
                location:""
            };

            $scope.events.push(track);


        }
        


        $scope.getEvents = function() {
            dataFactory.getEvents($scope.trackId)
                .success(function (data) {
                    $scope.events = data;
                })
                .error(function (error) {
                    $scope.status = 'Unable to load event data: ' + error.message;
                });
        }

        $scope.insertEvent = function(data) {
            data.trackId=$scope.trackId;
            dataFactory.insertEvent(data)
                .success(function (data) {
                    $scope.events.push(data);
                    console.log(data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to create event data: ' + error.message;
                });
        }

        $scope.updateEvent = function(data) {
            data.trackId=$scope.trackId;
            dataFactory.updateEvent(data)
                .success(function (data) {
                    var len = $scope.events.length;
                    for(var i=0;i<len;i++){
                        if(data.id===$scope.events[i].id){
                            $scope.events[i]=data;
                            break;
                        }
                    }

                })
                .error(function (error) {
                    $scope.status = 'Unable to update event data: ' + error.message;
                });
        }

        $scope.deleteEvent = function(id,index) {
            dataFactory.deleteEvent(id)
                .success(function (data) {
                    $scope.events.splice(index,1);
                    console.log("Delete",data,'@',index);
                })
                .error(function (error) {
                    $scope.status = 'Unable to delete event data: ' + error.message;
                });
        }
        $scope.getEvents();

    })
    ;

