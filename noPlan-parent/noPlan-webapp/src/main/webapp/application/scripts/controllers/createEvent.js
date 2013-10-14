var ModalEventCtrl = function ($scope, $modalInstance,item) {
    console.log('init ModalEventCtrl');

    if(item !== undefined ){
        $scope.item=item;
    }

    else {
        console.log("new Item")
        $scope.item = { name:"Name",
                        presenter:"Presenter",
                        description:"Description",
                        startTime:"00:00",
                        endTime:"23:59",
                        infoLink: ""};
    }
    $scope.ok = function () {
        $modalInstance.close($scope.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};