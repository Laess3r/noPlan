'use strict';

console.log('init conference');
angular.module('mytodoApp')
    .controller('ConferencesCtrl',function ($scope,$modal,$log,dataFactory) {
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
        $scope.open = function (index) {
            console.log("index",index);



            var modalInstance = $modal.open({
                templateUrl: 'views/newConference.html',
                controller: ModalConferenceCtrl,
                resolve: {
                    conferences: function () {
                        return $scope.conferences;
                    },
                    conference: function () {
                        return $scope.conferences[index];
                    }
                }
            });

            modalInstance.result.then(function (conference) {
                if(conference.id===undefined){
                    $scope.insertConference(conference);
                    //$scope.insertConference({name:conference.name,description:conference.description})
                }
                else{
                    $scope.updateConference(conference);
                    //$scope.updateConference({id:conference.id,name:conference.name,description:conference.description})
                }
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };


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
                    $scope.conferences.push(data);
                    console.log("create",data)
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

