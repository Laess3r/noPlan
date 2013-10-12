'use strict';

console.log('init conference');
angular.module('mytodoApp')
    .controller('ConferencesCtrl',function ($scope,$modal,$log,dataFactory) {
        console.log('init ConferenceCtrl');

        $scope.items = [];
        console.log("1");
        $scope.open = function () {

            var modalInstance = $modal.open({
                templateUrl: 'views/newConference.html',
                controller: ModalInstanceCtrl,
                resolve: {
                    items: function () {
                        return $scope.items;
                    }
                }
            });

            modalInstance.result.then(function (item) {
                console.log(item);
                //$scope.items.push(item);
                $scope.insertConference({name:item.name,description:item.description})
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };


        $scope.getConferences = function() {
            dataFactory.getConferences()
                .success(function (data) {
                    $scope.items = data;
                	console.log(data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to load customer data: ' + error.message;
                });
        }

        $scope.insertConference = function(data) {
            dataFactory.insertConference(data)
                .success(function (data) {
                    $scope.items.push(data);
                    console.log(data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to create customer data: ' + error.message;
                });
        }


        $scope.deleteConference = function(id,index) {
            dataFactory.deleteConference(id)
                .success(function (data) {
                    $scope.items.splice(index,1);
                    console.log("Delete",data,'@',index);
                })
                .error(function (error) {
                    $scope.status = 'Unable to delete customer data: ' + error.message;
                });
        }
        $scope.getConferences();

    })
    ;

