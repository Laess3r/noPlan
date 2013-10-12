var ModalConferenceCtrl = function ($scope, $modalInstance,item) {
    console.log('init ModalConferenceCtrl');

    if(item !== undefined ){
        $scope.item=item;
    }

    else {
        console.log("new Item")
        $scope.item = { name:"Name",
                        description:"Description",
                        startDate:new Date().toJSON().slice(0,10),
                        endDate:new Date().toJSON().slice(0,10)};
    }
    $scope.ok = function () {
        $modalInstance.close($scope.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};