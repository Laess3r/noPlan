'use strict';

angular.module('noPlan')
    .controller('TracksUserViewCtrl',function ($routeParams,$scope,dataFactory,$timeout) {

        $scope.saveEvents = false;
        $scope.editStateYourPlan = false;
        $scope.yourPlan = "newPlan!";


        //double selection
        $scope.doubleSelection = [];
   

        //accordion
        $scope.collapseStateYourPlan = "collapse";     

        /*
        $scope.$watch('$index', function() {
            $scope.collapseStateYourPlan = "collapse in";
        });
        */


        //get throught routeParam the actual conference ID
        $scope.conferenceId = $routeParams.id;
        

        //get tracks from server by ID
        $scope.conferenceItems = [];
        $scope.getTracks = function() {
            dataFactory.getTracks($scope.conferenceId)
                .success(function (data) {
                    $scope.conferenceItems = data;                 
                })
                .error(function (error) {
                    $scope.status = 'Unable to load track data: ' + error.message;
                });
        };
        $scope.getTracks();

        
        
        //add events to myEvent list and disable button
        $scope.myPlanItems= [];
        $scope.isDisabled=[];
        $scope.addToMyPlan = function(itemMy) {
            setTimes(itemMy);
            $scope.myPlanItems.push(itemMy);  
            $scope.isDisabled[itemMy.id]=true;
            $scope.collapseStateYourPlan = "collapse in";
            $scope.updateGantt();
        }; 

 

        //remove events from myEvent list
        $scope.removeFromMyPlan = function(itemMy) {
            $scope.myPlanItems.splice($scope.myPlanItems.indexOf(itemMy),1);
            $scope.isDisabled[itemMy.id]=false;
            $scope.updateGantt(); 
        };


        //sorted myEvents by id >> TODO sorted by date/time
        $scope.DATE="startdate";


        //save chosen events DB via json
        $scope.saveYourEventChoice = function(){
            $scope.editStateYourPlan= false;

            $scope.comparedEventIDsToAdd=[];
            $scope.comparedEventIDsToRem=[];

            $scope.compareEventsToSave();

            //send events to add json  
            if($scope.comparedEventsToAdd.length>=1)
            {
                var sendAdd=[]; 
                for(var i=0;i<$scope.comparedEventsToAdd.length;i++)
                {
                    sendAdd[i]=$scope.comparedEventsToAdd[i].id;
                }
              
                $scope.comparedEventIDsToAdd= sendAdd;
                
                console.log("savedEvents:");
                for(var k=0;k<sendAdd.length;k++)
                {
                   console.log(sendAdd[k]); 
                }
                                
                dataFactory.addUserEvents(sendAdd)
                    .success(function () {
                        console.log("savedEvents:success");
                    })
                    .error(function (error) {
                        $scope.status = 'Unable to ADD event data: ' + error.message;
                    });
            }
            else
            {
                $scope.comparedEventIDsToAdd="Nothing to add!";
            }


            //send events to remove json         
            if($scope.comparedEventsToRemove.length>=1)
            {
                var sendRemove=[];
                for(var i=0;i<$scope.comparedEventsToRemove.length;i++)
                {
                    sendRemove[i]=$scope.comparedEventsToRemove[i].id;
                }
        
                $scope.comparedEventIDsToRem= sendRemove;

                console.log("deletedEvents:");
                for(var k=0;k<sendRemove.length;k++)
                {
                   console.log(sendRemove[k]); 
                }

                dataFactory.deleteUserEvents(sendRemove)
                    .success(function () {
                        console.log("deletedEvents:success");
                    })
                    .error(function (error) {
                        $scope.status = 'Unable to DELETE event data: ' + error.message;
                    });
            }
            else
            {
                $scope.comparedEventIDsToRem="Nothing to delete!";
            }

            $scope.saveEvents = true;
            //update myEvents
            $timeout(function(){$scope.getMyConferenceTracks();console.log("call mytracks");}, 500);           
        };




        //area for load an show tracks and events
        //load tracks 
        $scope.myLoadedTracks = [];    
        $scope.getMyConferenceTracks = function() {
            dataFactory.getMyConferenceTrack($scope.conferenceId)
                .success(function (data) {
                    $scope.myLoadedTracks = data;
                    console.log("loadedTracks:");
                    console.log(data);
   
                    $scope.myPlanItems= [];
                    $scope.myLoadedEvents = [];

                    angular.forEach($scope.myLoadedTracks, function(track){
                        $scope.getMyConferenceEvents(track.id, track.name);
                    });
                })
                .error(function (error) {
                    $scope.status = 'Unable to load event data: ' + error.message;
                });            
        };      
        $scope.getMyConferenceTracks();



        //load events
        $scope.myEvents = [];
        $scope.getMyConferenceEvents = function(trackId, trackName) {
            dataFactory.getMyConferenceEvent(trackId)
                .success(function (data) {
                    $scope.myEvents = data;
                    console.log("loadedEvents:");
                    console.log(data);

                    angular.forEach($scope.myEvents, function(eventMy){
                        $scope.addToMyPlan(eventMy);
                        $scope.myLoadedEvents.push(eventMy);
                    }); 
                      
                })
                .error(function (error) {
                    $scope.status = 'Unable to load event data: ' + error.message;
                });           
        };



        //inser events into gantt
        $scope.scale = 'hour';        
        $scope.insertGanttEvent = function (trackid,trackName,data){
            //console.log("insertGantt",trackid,data)
            var gdata = {};
            gdata.id = trackid;
            gdata.description = trackName;
            gdata.order = trackid;
            gdata.tasks = [];
            var evdata = {};
            evdata.id = data.id;
            evdata.subject = data.name;
            evdata.from = data.startdate;
            evdata.to  = data.enddate;
            if($scope.editStateYourPlan==false)
            {
                evdata.color = "#33CC66"; //#33CC66
            }
            else{
                evdata.color = "#ee5f5b";//"#5bc0de";
            }
            gdata.tasks.push(evdata);
            //console.log(gdata);          
            $scope.loadData([gdata]);     
        };

        $scope.updateGantt= function()
        {
            $scope.clearData([{}]);
            var trackName='*'; 
            angular.forEach($scope.myPlanItems, function(myEvent){           
                    angular.forEach($scope.conferenceItems, function(track){
                        if(myEvent.trackId===track.id)
                        {
                            trackName=track.name;
                        }             
                    });       
                    $scope.insertGanttEvent(myEvent.trackId,trackName, myEvent);
            });          
        };
    
        

        //compares loaded events with new choice and return events to change in DB
        $scope.compareEventsToSave = function() {                  
            $scope.comparedEventsToAdd = [];         
            $scope.comparedEventsToRemove = [];           

            //check if some event is removed        
            angular.forEach($scope.myLoadedEvents, function(item){
                if( $scope.myPlanItems.indexOf(item)==-1 ){
                    $scope.comparedEventsToRemove.push(item);
                    console.log("to delete "+ item.id +$scope.myPlanItems.indexOf(item));       
                }                
            });

            //check if some event is added     
            angular.forEach($scope.myPlanItems, function(item){
                if($scope.myLoadedEvents.indexOf(item)==-1){
                    $scope.comparedEventsToAdd.push(item); 
                    console.log("addedToAdd "+ item.id +$scope.myPlanItems.indexOf(item));
                }
                else
                {                  
                    console.log("oldItem "+ item.id);
                }     
            });
            
 
            //compare arrays to save
            angular.forEach($scope.comparedEventsToRemove, function(item1){
                for(var x=0; x<$scope.comparedEventsToAdd.length;x++)
                {
                    if($scope.comparedEventsToAdd[x].id==item1.id)
                    {
                        $scope.comparedEventsToRemove.splice($scope.comparedEventsToRemove.indexOf(item1),1);
                        $scope.comparedEventsToAdd.splice($scope.comparedEventsToAdd.indexOf(item1),1);
                    }
                }         
            });                             
        };



        //checks if double selection
        $scope.compareTimetable = function() {     
            var tmp=[];
            angular.forEach($scope.myPlanItems,function(myEvent,index)
            {
                tmp.push(myEvent);
                $scope.doubleSelection[index]=false;          
            });

            //sort function sorted by conference startdate
            tmp.sort(function(a,b)
            {      
                var dateA=new Date(a.startdate);
                var dateB=new Date(b.startdate);
                return dateA-dateB;           
                //return a.id-b.id             
            });
            
            console.log("sort:");
            console.log(tmp.length);
            console.log(tmp);

            for (var i=0;i< tmp.length-1;i++)
            {
                if( tmp[i].enddate > tmp[i+1].startdate)
                {           
                    //$scope.doubleSelection[i]=true;
                    $scope.doubleSelection[i+1]=true;
                    console.log(tmp[i].id+" "+tmp[i].enddate+" > " +tmp[i+1].id+" "+ tmp[i+1].startdate);        
                }
            }          
        };

        console.log("compareTimetable:");
        $timeout(function(){$scope.compareTimetable();}, 1000);

        $scope.clearTimetableState = function() {     
            angular.forEach($scope.myPlanItems,function(myEvent,index)
            {
                $scope.doubleSelection[index]=false;          
            });
        };

        //set time by event startdate
        $scope.schedMyPlanEvents = {};
        var setTimes = function(data){
            var date = new Date(data.startdate);
            var obj = {};
            obj.start = (date.getHours() < 10 ? "0" : "" ) + date.getHours() +":"+ (date.getMinutes() < 10 ? "0" : "" ) + date.getMinutes();
            date = new Date(data.enddate);
            obj.end = (date.getHours() < 10 ? "0" : "" ) + date.getHours() +":"+ (date.getMinutes() < 10 ? "0" : "" ) + date.getMinutes();
            $scope.schedMyPlanEvents[data.id]=obj;
        }
        



    });






