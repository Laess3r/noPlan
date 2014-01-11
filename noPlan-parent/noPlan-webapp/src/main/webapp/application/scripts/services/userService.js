
angular.module('noPlan')
    .factory('UserService', [ "$q","$timeout",function($q,$timeout) {
        console.log("init userService");

        var sdo = {};

        sdo.isLogged =  false;
        sdo.username = '';

        sdo.login = function(){
            console.log("Userservice Login")
            var deferred = $q.defer();
            console.log("init get Messages")

            $timeout(function() {
                console.log("execute get Messages")
                deferred.resolve(['Hello', 'world!']);
            }, 10000);
            return deferred.promise;
        };
        sdo.logout = function(){
            console.log("Userservice Logout")
        };


        return sdo;

    }]);
