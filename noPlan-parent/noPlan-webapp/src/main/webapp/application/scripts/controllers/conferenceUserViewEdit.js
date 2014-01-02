'use strict';
console.log("init conferenceUserViewEdit");

angular.module('mytodoApp')
    .controller('ConferenceUserViewEditCtrl',function ($routeParams,$scope,dataFactory){
        console.log('init ConferenceUserViewEditCtrl')

        //load relevant conferences
        $scope.myConferences = [];
        $scope.getMyConferences = function() {
            dataFactory.getMyConference()
                .success(function (data) {
                    $scope.myConferences = data;
                    console.log(data);
                })
                .error(function (error) {
                    $scope.status = 'Unable to load customer data: ' + error.message;
                });
        };
        $scope.getMyConferences();

        $scope.preViewState= true;
        $scope.showConfName="Choose a Conference";
        $scope.setConfTitleToPreview= function(conf){
            $scope.preViewState= true;
            $scope.isDisabled= false;
            $scope.showConfName=conf.name;
            $scope.showConfDescription=conf.description;
        };

    });
