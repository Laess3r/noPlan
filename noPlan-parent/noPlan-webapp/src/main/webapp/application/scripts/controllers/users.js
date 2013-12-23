'use strict';

console.log('init users');
angular.module('mytodoApp').controller(
		'UserCtrl',
		function($scope, $location, dataFactory) {
			console.log('init UserCtrl');

			$scope.editMode = false;
			$scope.users = [];

			$scope.addUser = function() {
				$scope.editMode = true;
				console.log("Edit mode when adding user:", $scope.editMode);

				var user = {
					enabled : "true",
					username : "",
					password : "",
					firstname : "",
					lastname : "",
					email : ""
				};
				$scope.users.push(user);
			};

			$scope.save = function() {

//				$scope.editMode = false;
				alert("TODO!");

			};

			$scope.discard = function() {
				$scope.editMode = false;
			};

			$scope.editUser = function($index) {
				$scope.editMode = !$scope.editMode;
				console
						.log("New Edit mode for index:", $index,
								$scope.editMode);
			};

			$scope.deleteUser = function($index) {
				console.log("removing index", $index);
				if ($scope.users[$index].id === undefined) {
					$scope.users.splice($index, 1);
					return;
				}

				alert("Do you really want to delete? Because you can't ;-)");
			};

			dataFactory.getAllUsers().success(function(data) {
				$scope.users = data;
				console.log(data.length, "Users loaded successfully!");
			}).error(
					function(error) {
						$scope.status = 'Unable to load customer data: '
								+ error.message;
					});
		});
