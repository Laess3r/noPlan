'use strict';

console.log("init conferenceUserView");

angular.module('noPlan')
    .controller('ConferenceUserViewCtrl',function ($routeParams,$scope,dataFactory){
        console.log('init ConferenceUserViewCtrl')

        $scope.conferences = [];
        $scope.getConferences = function() {
            dataFactory.getConferences()
                .success(function (data) {
                    $scope.conferences = data;
                    console.log(data);
                })
                .error(function (error) {
                    $scope.status = 'Unable to load customer data: ' + error.message;
                });
        };
        $scope.getConferences();

        $scope.preViewState= true;
        $scope.showConfName="Choose a Conference";
        $scope.setConfTitleToPreview= function(conf){
            $scope.preViewState= true;
            $scope.isDisabled= false;
            $scope.showConfName=conf.name;
            $scope.showConfDescription=conf.description;
        };

    });
