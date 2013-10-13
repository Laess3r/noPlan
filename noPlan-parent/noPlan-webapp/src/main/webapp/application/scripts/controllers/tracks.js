'use strict';

console.log('init track');
angular.module('mytodoApp')
    .controller('TracksCtrl',function ($routeParams,$scope,$modal,$log,dataFactory) {
        console.log('init TrackCtrl',$routeParams);
        $scope.conferenceId = $routeParams.id;
        $scope.items = [];
        $scope.open = function (index) {

            var modalInstance = $modal.open({
                templateUrl: 'views/newTrack.html',
                controller: ModalTrackCtrl,
                resolve: {
                    items: function () {
                        return $scope.items;
                    },
                    item: function () {
                        return $scope.items[index];
                    }
                }
            });

            modalInstance.result.then(function (item) {
                console.log(item);
                //$scope.items.push(item);
                if(item.id===undefined){
                    $scope.insertTrack({name:item.name,description:item.description})
                }
                else{
                    $scope.updateTrack({id:item.id,name:item.name,description:item.description})
                }

            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };


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

