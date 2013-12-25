'use strict';

console.log('init app');
angular.module('mytodoApp', ['ngRoute','ngSanitize','ui.calendar','gantt','LocalStorageModule','ngCookies','textAngular'])
  .config([ '$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
    console.log('routeProvider');
//    var baseUrl="/noPlan-webapp/application/";
    var baseUrl="/application/";
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
    .when('/register', {
            templateUrl: 'views/register.html',
            controller: 'RegisterCtrl'
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
                                    console.log('redirecting to login');   
                                 
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
  }]).run(function($rootScope,$location,$http,$cookies, dataFactory){

        var user={
        };
        if($cookies.token !== undefined){
            $rootScope.token=$cookies.token;

            $http.defaults.headers.common['Auth-Token'] = $cookies.token;
            console.log("cookie token: ",$cookies.token);

            dataFactory.checkSession()
                .success(function (data) {
                	
                	user.username = $rootScope.token.split(':')[0];
                	user.token = $cookies.token;
                    console.log('Success! '+user.username+' is now logged in! (Token: "'+user.token+ '" )');

                    $rootScope.loggedIn = true;
                    console.log("logged in");
                })
                .error(function (error) {
                    console.log('Login failed:' + error.message);
                    $rootScope.loggedIn = false;
                    $location.path("/login");
                });
        }


    });
