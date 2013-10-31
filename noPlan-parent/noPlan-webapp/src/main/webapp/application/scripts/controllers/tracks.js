'use strict';

console.log('init track');
angular.module('mytodoApp')
    .controller('TracksCtrl',function ($routeParams,$scope,$modal,$log,dataFactory) {
        console.log('init TrackCtrl',$routeParams);
        $scope.conferenceId = $routeParams.id;
        $scope.items = [];

        $scope.add = function() {
            var item= {
                name:"",
                description:"",
                location:"",
                resource:""
            };

            $scope.items.push(item);


        }



        $scope.getTracks = function() {
            console.log('getTracks')
            dataFactory.getTracks($scope.conferenceId)
                .success(function (data) {
                    $scope.items = data;
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
                    $scope.items.push(data);
                    console.log(data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to create track data: ' + error.message;
                });
        }

        $scope.updateTrack = function(data) {
            data.conferenceId=$scope.conferenceId;
            dataFactory.updateTrack(data)
                .success(function (data) {
                    var len = $scope.items.length;
                    for(var i=0;i<len;i++){
                        if(data.id===$scope.items[i].id){
                            $scope.items[i]=data;
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
                    $scope.items.splice(index,1);
                    console.log("Delete",data,'@',index);
                })
                .error(function (error) {
                    $scope.status = 'Unable to delete track data: ' + error.message;
                });
        }
        $scope.getTracks();

    })
    ;

