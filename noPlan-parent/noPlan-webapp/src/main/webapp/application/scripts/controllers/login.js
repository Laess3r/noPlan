'use strict';

angular
		.module('mytodoApp')
		.controller(
				'LoginCtrl',
				function($scope, $log, $http, $rootScope, $cookies, $location,
						dataFactory) {
					console.log("login Controller");
					$rootScope.loggedIn = false;
					$scope.remember = true;

					$scope.login = function(user) {
						dataFactory
								.authenticateUser(user)
								.success(
										function(user) {
											$scope.status = 'Success! '
													+ user.username
													+ ' is now logged in! (Token: "'
													+ user.token + '" )';
											$rootScope.user = user;
											$scope.user = user;

											$http.defaults.headers.common['Auth-Token'] = user.token;
											console.log("token", user.token);
											if ($scope.remember) {
												$cookies.token = user.token;
											}

											else {
												$cookies.token = undefined;
												console.log('DELETING TOKEN!');
											}
											$rootScope.loggedIn = true;

											$location.path("/main");

										}).error(
										function(error) {
											$scope.status = 'Login failed:'
													+ error.message;
											$rootScope.loggedIn = false;
										});
					}

					$scope.logout = function() {
						$scope.status = $rootScope.user.username
								+ ' logged out.';
						delete $rootScope.user;
						delete $scope.user;
						delete $http.defaults.headers.common['Auth-Token'];
						$rootScope.loggedIn = false;
						$location.path("/main");

					}

					$scope.register = function() {
						$location.path("/register");
					}

				});
