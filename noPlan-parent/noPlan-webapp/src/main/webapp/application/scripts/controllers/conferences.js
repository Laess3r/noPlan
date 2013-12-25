'use strict';

console.log('init conference');
angular.module('mytodoApp')
    .controller('ConferencesCtrl',function ($scope,$log,dataFactory) {
        console.log('init ConferenceCtrl');

        $scope.conferences = [];

        $scope.add = function() {
            var conference= {
                    name:"",
                    description:"",
                    startDate:"",
                    endDate:"",
                    location:"",
                    infolink:""

            };

            $scope.conferences.push(conference);


        }



        $scope.getConferences = function() {
            dataFactory.getConferences()
                .success(function (data) {
                    $scope.conferences = data;
                	console.log(data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to load customer data: ' + error.message;
                });
        }

        $scope.insertConference = function(data) {
            dataFactory.insertConference(data)
                .success(function (data) {
                	var len = $scope.conferences.length;
                    for(var i=0;i<len;i++){
                        if($scope.conferences[i].id === undefined){
                            $scope.conferences[i]=data;
                            break;
                        }
                    }
                })
                .error(function (error) {
                    $scope.status = 'Unable to create customer data: ' + error.message;
                });
        }

        $scope.updateConference = function(data) {
            dataFactory.updateConference(data)
                .success(function (data) {
                    var len = $scope.conferences.length;
                    for(var i=0;i<len;i++){
                        if(data.id===$scope.conferences[i].id){
                            $scope.conferences[i]=data;
                            break;
                        }
                    }

                })
                .error(function (error) {
                    $scope.status = 'Unable to update customer data: ' + error.message;
                });
        }

        $scope.deleteConference = function(id,index) {
            if(id === undefined){
               $scope.conferences.splice(index,1);
               return; 
            }
            dataFactory.deleteConference(id)
                .success(function (data) {
                    $scope.conferences.splice(index,1);
                    console.log("Delete",data,'@',index);
                })
                .error(function (error) {
                    $scope.status = 'Unable to delete customer data: ' + error.message;
                });
        }
        $scope.getConferences();

    });

