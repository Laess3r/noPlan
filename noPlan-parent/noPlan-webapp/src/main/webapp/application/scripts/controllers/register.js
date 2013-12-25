'use strict';

angular
		.module('mytodoApp')
		.controller(
				'RegisterCtrl',
				function($scope, $rootScope, $location, dataFactory) {

					$scope.clicked = false;

					var user = {
						enabled : "true",
						username : "",
						password : "",
						firstname : "",
						lastname : "",
						email : ""
					};

					$scope.user = user;

					$scope.register = function() {
						$scope.clicked = true;

						dataFactory
								.createUserPublic(user)
								.success(
										function(data) {
											alert('User created successfully! You can now log in!');
											$location.path("/login");
										})
								.error(
										function(data) {
											$scope.clicked = false;
											console.log(data);
											alert('Unable to create user! \nMaybe it already existis?');
										});

					};

				});
