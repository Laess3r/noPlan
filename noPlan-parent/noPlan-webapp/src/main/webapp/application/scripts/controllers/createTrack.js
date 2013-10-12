var ModalTrackCtrl = function ($scope, $modalInstance,item) {
    console.log('init ModaTrackCtrl');

    if(item !== undefined ){
        $scope.item=item;
    }

    else {
         $scope.item = { name:"Name",
                         description:"Description",
                    };
    }
    $scope.ok = function () {
        console.log("item",$scope.item);
        $modalInstance.close($scope.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};