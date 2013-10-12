
angular.module('mytodoApp')
    .factory('UserService', [ function() {
        console.log("init userService");

        var sdo = {
            isLogged: false,
            username: ''
        };
        return sdo;

    }]);
