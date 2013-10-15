
console.log("Init BreadCrumbs")
angular.module('mytodoApp')
    .factory('BreadCrumbsService',[ '$rootScope', '$location' ,function($rootScope, $location) {
        console.log("Init BreadCrumbservice");
        var breadcrumbsService = {};
        var breadcrumbs = [
            {
                name:"Conferences",
                link: "#/conferences"

            },
            {
                name:"Conference 1",
                link: "#/tracks/1"
            },
            {
                name:"Track 1",
                link: "#/events/10"
            }
        ];



        breadcrumbsService.getAll = function() {
            console.log(breadcrumbs);
            return breadcrumbs;
        };

        breadcrumbsService.getFirst = function() {
            return breadcrumbs[0] || {};
        };

        return breadcrumbsService;

}]);