'use strict';

console.log('init app');
angular.module('mytodoApp', ['ui','ui.bootstrap','LocalStorageModule'])
  .config([ '$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
    console.log('routeProvider');
    var baseUrl="application/";
    $routeProvider
    .when('/main', {
            templateUrl: 'views/main.html',
            controller: 'MainCtrl'
        })
    .when('/users', {
            templateUrl: 'views/users.html',
            controller: 'UserCtrl'
    })
    .when('/conferences', {
            templateUrl: 'views/conferences.html',
            controller: 'ConferencesCtrl'
    })
    .when('/tracks/:id', {
            templateUrl: 'views/tracks.html',
            controller: 'TracksCtrl'
        })
    .when('/events/:id', {
            templateUrl: 'views/events.html',
            controller: 'EventsCtrl'
        })
    .when('/todo', {
        templateUrl: 'views/todo.html',
        controller: 'TodoCtrl'
    })
    .when('/login', {
            templateUrl: 'views/login.html',
            controller: 'LoginCtrl'
        })

    .otherwise({
        redirectTo: 'views/main.html',
        controller: 'MainCtrl'
  });
  
  var interceptor = function ($rootScope, $q, $location) {

                        function success(response) {
                            return response;
                        }

                        function error(response) {
                                
                            var status = response.status;
                            var config = response.config;
                            var method = config.method;
                            var url = config.url;

                            if (status === 401) {
                                    $location.path( "/login" );
                            } else {
                                    $rootScope.error = method + " on " + url + " failed with status " + status;
                            }
                            
                            return $q.reject(response);
                        }
 
                        return function (promise) {
                            return promise.then(success, error);
                        };
                    };
                    $httpProvider.responseInterceptors.push(interceptor);
  }]).run(function($rootScope,$location,$http,dataFactory){
        console.log("Run App");
        var user={
            username: "Admin",
            password: "admin"
        };
//        dataFactory.authenticateUser(user)
//            .success(function (user) {
//                //$scope.status = 'Success! '+user.username+' is now logged in! (Token: "'+user.token+ '" )';
//                $rootScope.user = user;
//                //$scope.user = user;
//
//                $http.defaults.headers.common['Auth-Token'] = user.token;
//
//                $rootScope.loggedIn = true;
//                console.log("logged in")
//                //$location.path("/main");
//
//            })
//            .error(function (error) {
//                //$scope.status = 'Login failed:' + error.message;
//                $rootScope.loggedIn = false;
//            });

    });
