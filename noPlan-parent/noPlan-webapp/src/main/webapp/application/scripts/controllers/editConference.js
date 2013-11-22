'use strict';

console.log('init edit');
angular.module('mytodoApp')
    .controller('editConference',function ($scope,$log,dataFactory) {
        console.log('init EditCtrl',$scope.conference.id);
        $scope.editState = false;

        if($scope.conference.id === undefined){
            $scope.editState=true;
        }


        $scope.edit = function($index){
            $scope.editState = ! $scope.editState;
            console.log("edit",$scope.editState, $scope);





            if(!$scope.editState){
                var conference=$scope.conferences[$index];
                if(conference.id===undefined){
                    $scope.insertConference(conference);
                    //$scope.insertConference({name:conference.name,description:conference.description})
                }
                else{
                    $scope.updateConference(conference);
                    //$scope.updateConference({id:conference.id,name:conference.name,description:conference.description})
                }

            }

            else {

                $scope.conference.startDate= new Date($scope.conference.startDate).toJSON().slice(0,10);
                $scope.conference.endDate= new Date($scope.conference.endDate).toJSON().slice(0,10);

            }

        }


    });

