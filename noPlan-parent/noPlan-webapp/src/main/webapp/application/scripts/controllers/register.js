'use strict';

angular.module('noPlan').controller('RegisterCtrl',
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
			$scope.confirmedPassword = "";

			$scope.register = function() {
				
				console.log("User: ", user.password, "confirmed: ", $scope.confirmedPassword);
				if(user.password != $scope.confirmedPassword){
					alert("Entered passwords do not match!");
					return;
				}
				$scope.clicked = true;

				dataFactory.createUserPublic(user).success(function(data) {
					alert('User created successfully! You can now log in!');
					$location.path("/login");
				}).error(function(e) {
					$scope.clicked = false;

					if (e.indexOf("already exists!") != -1) {
						alert('USER \"' + user.username + '\" ALREADY EXISTS!  \nUnable to create user!');
						return;
					}
					
					alert('Unable to create user!');
				});

			};

		});

