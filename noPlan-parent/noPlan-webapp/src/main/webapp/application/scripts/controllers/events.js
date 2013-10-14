'use strict';

console.log('init event');
angular.module('mytodoApp')
    .controller('EventsCtrl',function ($routeParams,$scope,$modal,$log,dataFactory) {
        console.log('init EventCtrl',$routeParams);
        $scope.trackId = $routeParams.id;
        $scope.items = [];
        $scope.open = function (index) {

            var modalInstance = $modal.open({
                templateUrl: 'views/newEvent.html',
                controller: ModalEventCtrl,
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
                    $scope.insertEvent({name:item.name,description:item.description})
                }
                else{
                    $scope.updateEvent({id:item.id,name:item.name,description:item.description})
                }

            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };


        $scope.getEvents = function() {
            console.log('getEvents')
            dataFactory.getEvents($scope.trackId)
                .success(function (data) {
                    $scope.items = data;
                	console.log(data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to load event data: ' + error.message;
                });
        }

        $scope.insertEvent = function(data) {
            data.trackId=$scope.trackId;
            dataFactory.insertEvent(data)
                .success(function (data) {
                    $scope.items.push(data);
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
                    var len = $scope.items.length;
                    for(var i=0;i<len;i++){
                        if(data.id===$scope.items[i].id){
                            $scope.items[i]=data;
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
                    $scope.items.splice(index,1);
                    console.log("Delete",data,'@',index);
                })
                .error(function (error) {
                    $scope.status = 'Unable to delete event data: ' + error.message;
                });
        }
        $scope.getEvents();

    })
    ;

