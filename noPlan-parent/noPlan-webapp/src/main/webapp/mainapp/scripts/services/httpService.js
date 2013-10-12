
angular.module('mytodoApp')
    .factory('dataFactory', ['$http', function($http) {
        console.log("init dataFactory");

        var urlBase = '/noPlan-webapp/rest';
        var dataFactory = {};

        var param = {params:{user:"Stefan"}}

        dataFactory.getConferences = function () {
            return $http.get(urlBase + '/conference/all',param);
        };

        dataFactory.getConference = function (id) {
            return $http.get(urlBase + '/conference/' + id);
        };

        dataFactory.insertConference = function (id) {
            return $http.post(urlBase + '/conference/create', id);
        };

        dataFactory.updateConference = function (id) {
            return $http.put(urlBase + '/conference/' + id.ID, id)
        };

        dataFactory.deleteConference = function (id) {
            return $http.delete(urlBase + '/conference/delete/' + id);
        };

        dataFactory.getTracks = function (id) {
            return $http.get(urlBase + '/' + id + '/tracks/all');
        };

        return dataFactory;
    }]);
