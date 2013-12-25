
angular.module('mytodoApp')
    .factory('dataFactory', ['$http', function($http) {
        console.log("init dataFactory");
        var urlBase = '/rest';
//        var urlBase = '/noPlan-webapp/rest';
        var dataFactory = {};

        dataFactory.getConferences = function () {
            return $http.get(urlBase + '/conference/all');
        };

        dataFactory.getConference = function (id) {
            return $http.get(urlBase + '/conference/' + id);
        };

        dataFactory.insertConference = function (id) {
            return $http.post(urlBase + '/conference/create', id);
        };

        dataFactory.updateConference = function (id) {
            return $http.post(urlBase + '/conference/update', id)
        };

        dataFactory.deleteConference = function (id) {
            return $http.delete(urlBase + '/conference/delete/' + id);
        };

        dataFactory.getTracks = function (id) {
            return $http.get(urlBase + '/track/allforconference/'+id);
        };

        dataFactory.insertTrack = function (id) {
            return $http.post(urlBase + '/track/create', id);
        };

        dataFactory.updateTrack = function (id) {
            return $http.post(urlBase + '/track/update', id)
        };

        dataFactory.deleteTrack = function (id) {
            return $http.delete(urlBase + '/track/delete/' + id);
        };

        dataFactory.getEvents = function (id) {
            return $http.get(urlBase + '/event/allfortrack/'+id);
        };

        dataFactory.insertEvent = function (id) {
            return $http.post(urlBase + '/event/create', id);
        };

        dataFactory.updateEvent = function (id) {
            return $http.post(urlBase + '/event/update', id)
        };

        dataFactory.deleteEvent = function (id) {
            return $http.delete(urlBase + '/event/delete/' + id);
        };

        dataFactory.authenticateUser = function (id) {
            return $http.post(urlBase + '/user/authenticate', id);
        };

        dataFactory.getAllUsers = function () {
            return $http.get(urlBase + '/user/all');
        };
        
        dataFactory.getUserByName = function (username) {
            return $http.get(urlBase + '/user/name/' + name);
        };
        
        dataFactory.getUserById = function (id) {
            return $http.get(urlBase + '/user/' + id);
        };
        
        dataFactory.createUser = function (userDTO) {
            return $http.post(urlBase + '/user/create', userDTO);
        };
        
        dataFactory.createUserPublic = function (userDTO) {
            return $http.post(urlBase + '/user/createpublic', userDTO);
        };
        
        dataFactory.updateUser = function (userDTO) {
            return $http.post(urlBase + '/user/update', userDTO);
        };
        
        dataFactory.deleteUser = function (id) {
            return $http.delete(urlBase + '/user/delete/' + id);
        };
        
        dataFactory.checkSession = function () {
            return $http.get(urlBase + '/info/checksession');
        }
        
        dataFactory.addUserEvents = function (ids) {
            return $http.post(urlBase + '/userevents/addevents', ids);
        };
        
        dataFactory.getUserConferences = function () {
            return $http.get(urlBase + '/userevents/getconferences');
        };
        
        return dataFactory;
    }]);
