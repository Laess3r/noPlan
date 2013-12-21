'use strict';

angular.module('mytodoApp')
    .controller('EventsCtrl',function ($routeParams,$scope,$log,dataFactory) {
        $scope.trackId=$scope.track.id;
        $scope.trackName = $scope.track.name;
        $scope.sched = {};
        $scope.times = {};
        $scope.schedarray = [];
       

        $scope.days =[
            {date:"A"},
            {date:"B"},
            {date:"C"}

        ]

        $scope.events = [];

        var setTimes = function(data,index){
            var date = new Date(data.startdate)
            var obj = {};
            obj.start = (date.getHours() < 10 ? "0" : "" ) + date.getHours() +":"+ (date.getMinutes() < 10 ? "0" : "" ) + date.getMinutes()
            date = new Date(data.enddate)
            obj.end = (date.getHours() < 10 ? "0" : "" ) + date.getHours() +":"+ (date.getMinutes() < 10 ? "0" : "" ) + date.getMinutes()
            //data.sched=obj;
            $scope.sched[data.id]=obj;
        }

        $scope.getTimes = function(data,index){
            var date = new Date(data.startdate);
            var obj = $scope.sched[data.id]
            var time = obj.start.split(':');
            date.setHours(parseInt(time[0]))
            date.setMinutes(parseInt(time[1]));
            data.startdate = date;
            //data.sched=obj;
            
            date = new Date(data.enddate);
            time = obj.end.split(':');
            date.setHours(parseInt(time[0]));
            date.setMinutes(parseInt(time[1]));
            data.enddate = date;
            return data;
            
        }
        $scope.addEvent = function() {
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
                		$scope.addEventToCal(data[item]);
                		
                						
                		$scope.insertGanttEvent($scope.trackId, data[item]);
                		setTimes(data[item]);
                		
                	}
                    $scope.events = data;
                    
                })
                .error(function (error) {
                    $scope.status = 'Unable to load event data: ' + error.message;
                });
        }

        $scope.insertEvent = function(data) {
            data.trackId=$scope.trackId;
            $scope.getTimes(data);
            dataFactory.insertEvent(data)
                .success(function (data) {
                    $scope.events.push(data);
                    $scope.insertGanttEvent($scope.trackId, data);
                    setTimes(data);
                    $scope.addEventToCal(data);
                })
                .error(function (error) {
                    $scope.status = 'Unable to create event data: ' + error.message;
                });
        }

        $scope.updateEvent = function(data) {
            data.trackId=$scope.trackId;
            $scope.getTimes(data);
            dataFactory.updateEvent(data)
                .success(function (data) {
                    var len = $scope.events.length;
                    $scope.replacEventToCal(data)
                    for(var i=0;i<len;i++){
                        if(data.id===$scope.events[i].id){
                            $scope.events[i]=data;
                            $scope.insertGanttEvent($scope.trackId, data);
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
                })
                .error(function (error) {
                    $scope.status = 'Unable to delete event data: ' + error.message;
                });
        }
        //{"id": "1", "description": "Track 1", "order": 0, "tasks": [
                // Dates can be specified as string, timestamp or javascript date object. The data attribute can be used to attach a custom object
        //        {"id": "3", "subject": "Event 1", "color": "#93C47D", "from": new Date(2013,10,28,8,0,0), "to": new Date(2013,10,28,16,0,0), "data": "Can contain any custom data or object"},
        //        {"id": "4", "subject": "Event 2", "color": "#93C47D", "from": new Date(2013,10,29,12,0,0), "to": new Date(2013,10,29,17,0,0)}
        //    ], "data": "Can contain any custom data or object"},
        
        
        
        $scope.insertGanttEvent = function (trackid,data){
        	console.log("insertGantt",trackid,data)
        	var gdata = {};
        	gdata.id = trackid;
        	gdata.description = $scope.trackName;
        	gdata.order = trackid;
        	gdata.tasks = [];
        	var evdata = {};
        	evdata.id = data.id;
        	evdata.subject = data.name;
        	evdata.from = data.startdate;
        	evdata.to  = data.enddate;
        	evdata.color = "#33CC66";
        	gdata.tasks.push(evdata);
        	console.log(gdata);
        	
        	$scope.loadData([gdata]);
        	
        };
        
        $scope.getEvents();


        

    })
    ;

