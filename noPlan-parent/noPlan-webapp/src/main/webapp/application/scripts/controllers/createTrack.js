var ModalTrackCtrl = function ($scope, $modalInstance) {
    console.log('init ModaTrackCtrl');
    $scope.item = { name:"Name",
                    description:"Description",
                    };
    $scope.ok = function () {
        console.log("item",$scope.item);
        $modalInstance.close($scope.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};