var ModalInstanceCtrl = function ($scope, $modalInstance) {
    console.log('init ModalInstanceCtrl');
    $scope.item = { name:"Name",
                    description:"Description",
                    startDate:new Date().toJSON().slice(0,10),
                    endDate:new Date().toJSON().slice(0,10)};
    $scope.ok = function () {
        console.log("item",$scope.item);
        $modalInstance.close($scope.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};