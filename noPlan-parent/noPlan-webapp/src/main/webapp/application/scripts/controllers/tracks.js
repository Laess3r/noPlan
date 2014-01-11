'use strict';

console.log('init track');
angular.module('noPlan')
    .controller('TracksCtrl',function ($routeParams,$scope,$log,dataFactory) {
        var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
        
        $scope.conferenceId = $routeParams.id;
        $scope.tracks = [];
        $scope.confevents = [];
        
        
        
        $scope.eventRender = function(event, element) {
        	element.find('.fc-event-inner').append('<div class"calPres" >' + event.presenter ? event.presenter : "" + '</div>');
        	element.find('.fc-event-inner').append('<div class"calDesc" >' + event.description ? event.description : "" + '</div>');
        	//element.attr('title', event.description);
        	element.qtip({ // Grab some elements to apply the tooltip to
        	    content: {
        	    	titel: event.title,
        	        text: event.description,
        	    }
        	});
           
        };
        
        /* config object */
        $scope.uiConfig = {
          calendar:{
        	defaultView: 'agendaWeek',
            height: 450,
            editable: false,
            firstHour: 6,
            contentHeight: 800,
            header:{
              left: 'month agendaWeek agendaDay',
              center: 'title',
              right: 'today prev,next'
            },
            dayClick: $scope.alertEventOnClick,
            //eventResize: $scope.resize,
            //eventDrop: $scope.drop,
            eventRender: $scope.eventRender,
          }
          
        };
        
       

        $scope.add = function() {
            var track= {
                name:"",
                description:"",
                location:"",
                resource:""
            };

            $scope.tracks.push(track);


        }



        $scope.getTracks = function() {
            dataFactory.getTracks($scope.conferenceId)
                .success(function (data) {
                    $scope.tracks = data;
                	console.log(data)
                	
                })
                .error(function (error) {
                    $scope.status = 'Unable to load track data: ' + error.message;
                });
        }

        $scope.insertTrack = function(data) {
            data.conferenceId=$scope.conferenceId;
            dataFactory.insertTrack(data)
                .success(function (data) {
                    var len = $scope.tracks.length;
                    for(var i=0;i<len;i++){
                        if($scope.tracks[i].id === undefined){
                            $scope.tracks[i]=data;
                            break;
                        }
                    }
                })
                .error(function (error) {
                    $scope.status = 'Unable to create track data: ' + error.message;
                });
        }

        $scope.updateTrack = function(data) {
            data.conferenceId=$scope.conferenceId;
            dataFactory.updateTrack(data)
                .success(function (data) {
                    var len = $scope.tracks.length;
                    for(var i=0;i<len;i++){
                        if(data.id===$scope.tracks[i].id){
                            $scope.tracks[i]=data;
                            break;
                        }
                    }

                })
                .error(function (error) {
                    $scope.status = 'Unable to update track data: ' + error.message;
                });
        }

        $scope.deleteTrack = function(id,index) {
            dataFactory.deleteTrack(id)
                .success(function (data) {
                    $scope.tracks.splice(index,1);
                })
                .error(function (error) {
                    $scope.status = 'Unable to delete track data: ' + error.message;
                });
        }

        
        $scope.getConferenceData = function(){
        	dataFactory.getConference($scope.conferenceId)
            .success(function (data) {
            	var currentDate = new Date(data.startDate);
            	var stopDate = new Date(data.endDate);
            	$scope.time = [];
            	
        	   while (currentDate <= stopDate) {
        	        $scope.time.push( {date:currentDate.toUTCString()} );
        	        currentDate = new Date(currentDate.getFullYear(),currentDate.getMonth(),currentDate.getDate ()+1);        	   
        	        }
        	   		//$scope.time.splice(0, 1);
        	   		var d= new Date($scope.time[0].date);
        	   		$scope.myCalendar.fullCalendar('gotoDate',d.getFullYear(),d.getMonth(),d.getDate());
        	   		$scope.fromDate = $scope.time[0].date;
            })
            .error(function (error) {
                $scope.status = 'Unable to load conference data: ' + error.message;
            });
        	
        }
        
        
        
        $scope.addEventToCal = function(data) {
            
        	$scope.confevents.push({
        	  id: data.id,
              title: data.name ,
              presenter: data.presenter,
              description: data.description,
              start: new Date(data.startdate),
              end: new Date(data.enddate),
              allDay : false
            });
        }
        
        $scope.replacEventToCal = function(data) {
            var len=$scope.confevents.length;
            for(var i=0;i<len;i++){
            	
            	if ($scope.confevents[i].id===data.id){
            
		        	$scope.confevents[i] = {
		        	  id: data.id,
		              title: data.name + " " + data.presenter,
		              start: new Date(data.startdate),
		              end: new Date(data.enddate),
		              allDay : false
		            };
		        	return;
            	}
            }
        }
        
        $('#collapseOne').on('shown.bs.collapse', function () {
        	$scope.myCalendar.fullCalendar('render');
        })
        
        $scope.renderCalendar = function(){
        	$scope.myCalendar.fullCalendar('render');
        }
        
        $scope.drop = function(event){
        }
        
        $scope.resize = function(event){
        }
        $scope.getTracks();
        $scope.getConferenceData();
        
       // $scope.uiConfig.ca
        

        $scope.eventSources = [$scope.confevents];
        
        
        
        //
        
        
        $scope.mode = "custom";
        $scope.firstDay = 1;
        $scope.weekendDays = [0,6];
        $scope.maxHeight = 0;
        $scope.showWeekends = true;
        

        $scope.addSamples = function () {
//        	var data1 = [
//            // Order is optional. If not specified it will be assigned automatically
//            {"id": "1", "description": "Track 1","tasks": [
//                // Dates can be specified as string, timestamp or javascript date object. The data attribute can be used to attach a custom object
//                {"id": "3", "subject": "Event 1", "color": "#93C47D", "from": new Date(2013,10,28,8,0,0), "to": new Date(2013,10,28,16,0,0)},
//                {"id": "4", "subject": "Event 2", "color": "#93C47D", "from": new Date(2013,10,29,12,0,0), "to": new Date(2013,10,29,17,0,0)}
//            ]},
//            {"id": "2", "description": "Track 2","tasks": [
//	              // Dates can be specified as string, timestamp or javascript date object. The data attribute can be used to attach a custom object
//	              {"id": "4", "subject": "Event 3", "color": "red", "from": new Date(2013,10,28,8,0,0), "to": new Date(2013,10,28,12,0,0)},
//	              {"id": "5", "subject": "Event 4", "color": "red", "from": new Date(2013,10,28,13,0,0), "to": new Date(2013,10,28,17,0,0)}
//	          ]}
//        	];
//        	$scope.loadData(data1);
        };

        $scope.removeSomeSamples = function () {
            
        };

        $scope.removeSamples = function () {
            $scope.clearData();
        };

        $scope.rowEvent = function(event) {
            // A row has been added, updated or clicked. Use this event to save back the updated row e.g. after a user re-ordered it.
            //console.log('Row event: ' + event.date + ', ' + event.row.description + ', ' + event.row.data);
        };

        $scope.scrollEvent = function(event) {
            if (angular.equals(event.direction, "left")) {
                // Raised if the user scrolled to the left side of the Gantt. Use this event to load more data.
                //console.log('Scroll event: Left');
            } else if (angular.equals(event.direction, "right")) {
                // Raised if the user scrolled to the right side of the Gantt. Use this event to load more data.
                //console.log('Scroll event: Right');
            }
        };

        $scope.taskEvent = function(event) {
            // A row has been added, updated or clicked.
            //console.log('Task event: ' + event.row.description + " | " + event.task.subject + " | " + event.task.data);
        };
        
        $scope.scale = 'hour';
        
    })
    
    ;

