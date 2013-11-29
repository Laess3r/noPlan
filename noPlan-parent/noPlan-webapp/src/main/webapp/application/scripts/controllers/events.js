'use strict';

console.log('init event');
angular.module('mytodoApp')
    .controller('EventsCtrl',function ($routeParams,$scope,$log,dataFactory) {
        $scope.trackId=$scope.track.id;
        $scope.sched = {};
        $scope.times = {};

        console.log('init EventCtrl',$scope);
        $scope.days =[
            {date:"A"},
            {date:"B"},
            {date:"C"}

        ]

        $scope.events = [];

        var setTimes = function(data){
        	console.log("setTimes", data.startdate);
            var date = new Date(data.startdate)
            console.log(date);
            $scope.sched.start = parseInt(date.getHours()) +":"+ parseInt(date.getMinutes());
            date = new Date(data.enddate)
            $scope.sched.end = parseInt(date.getHours()) +":"+ parseInt(date.getMinutes());
           
        }

        $scope.getTimes = function(data){
            var date = new Date(data.startdate);
            var time = $scope.sched.start.split(':');
            date.setHours(parseInt(time[0]))
            date.setMinutes(parseInt(time[1]));
            data.startdate = date;
            
            date = new Date(data.enddate);
            time = $scope.sched.end.split(':');
            date.setHours(parseInt(time[0]));
            date.setMinutes(parseInt(time[1]));
            data.enddate = date;
            console.log(date);
            return data;
            
        }
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
                	
                	for(var item=0;item<data.length;item++){
                		console.log(data[item].startdate);
                		setTimes(data[item]);
                		console.log(new Date(data[item].startdate));
                	}
                	console.log(data);
                    $scope.events = data;
                    
                })
                .error(function (error) {
                    $scope.status = 'Unable to load event data: ' + error.message;
                });
        }

        $scope.insertEvent = function(data) {
            data.trackId=$scope.trackId;
            console.log(data);
            $scope.getTimes(data);
            console.log(data);
            dataFactory.insertEvent(data)
                .success(function (data) {
                    $scope.events.push(data);
                    setTimes(data);
                    console.log(data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to create event data: ' + error.message;
                });
        }

        $scope.updateEvent = function(data) {
            data.trackId=$scope.trackId;
            console.log(data);
            $scope.getTimes(data);
            console.log(data);
            dataFactory.updateEvent(data)
                .success(function (data) {
                    var len = $scope.events.length;
                    for(var i=0;i<len;i++){
                        if(data.id===$scope.events[i].id){
                            $scope.events[i]=data;
                            setTimes(data);

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

