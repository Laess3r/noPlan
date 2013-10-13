
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

        return dataFactory;
    }]);
