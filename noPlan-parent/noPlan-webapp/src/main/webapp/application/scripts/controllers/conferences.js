'use strict';

console.log('init conference');
angular.module('mytodoApp')
    .controller('ConferencesCtrl',function ($scope,$modal,$log,dataFactory) {
        console.log('init ConferenceCtrl');

        $scope.items = [];

        $scope.add = function() {
            var item= {
                    name:"",
                    description:"",
                    startDate:"",
                    endDate:"",
                    location:"",
                    infolink:""

            };

            $scope.items.push(item);


        }
        $scope.open = function (index) {
            console.log("index",index);



            var modalInstance = $modal.open({
                templateUrl: 'views/newConference.html',
                controller: ModalConferenceCtrl,
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
                if(item.id===undefined){
                    $scope.insertConference(item);
                    //$scope.insertConference({name:item.name,description:item.description})
                }
                else{
                    $scope.updateConference(item);
                    //$scope.updateConference({id:item.id,name:item.name,description:item.description})
                }
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
                    console.log("create",data)
                })
                .error(function (error) {
                    $scope.status = 'Unable to create customer data: ' + error.message;
                });
        }

        $scope.updateConference = function(data) {
            dataFactory.updateConference(data)
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
                    $scope.status = 'Unable to update customer data: ' + error.message;
                });
        }

        $scope.deleteConference = function(id,index) {
            if(id === undefined){
               $scope.items.splice(index,1);
               return; 
            }
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

    });

