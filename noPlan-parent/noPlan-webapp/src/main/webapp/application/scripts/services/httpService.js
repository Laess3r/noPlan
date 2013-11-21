
angular.module('mytodoApp')
    .factory('dataFactory', ['$http', function($http) {
        console.log("init dataFactory");

        var urlBase = '/noPlan-webapp/rest';
        var dataFactory = {};

        var param = {params:{user:"Stefan"}}

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

        dataFactory.checkSession = function (id) {
            return $http.get(urlBase + '/info/checksession');
        }

        return dataFactory;
    }]);
