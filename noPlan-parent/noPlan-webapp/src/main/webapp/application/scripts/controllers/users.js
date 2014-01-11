'use strict';

angular
		.module('noPlan')
		.controller(
				'UserCtrl',
				function($scope, $location, dataFactory) {

					$scope.isEditMode = function($index) {
						return $scope.editRow == $index;
					};

					$scope.editRow = -1;

					$scope.users = [];

					$scope.addUser = function() {

						var user = {
							enabled : "true",
							username : "",
							password : "",
							firstname : "",
							lastname : "",
							email : "",
							isadmin : "false"
						};
						$scope.users.push(user);

						$scope.editRow = $scope.users.length - 1;
					};

					$scope.save = function($index) {
						var user = $scope.users[$index];

						if (user.id === undefined) {
							$scope.createUser($scope.users[$index]);
						} else {
							$scope.updateUser($scope.users[$index]);
						}
						$scope.editRow = -1;
					};

					$scope.createUser = function(user) {
						dataFactory.createUser(user).success(function(data) {

							$scope.users[$scope.users.length - 1] = data;

						}).error(function(user) {
							alert('Unable to create user: ', user.message);
						});
					};

					$scope.updateUser = function(user) {
						dataFactory.updateUser(user).success(function(user) {

							var len = $scope.users.length;
							for (var i = 0; i < len; i++) {
								if (user.id === $scope.users[i].id) {
									$scope.users[i] = user;
									break;
								}
							}

						}).error(function(user) {
							alert('Unable to update user: ', user.message);
						});
					};

					$scope.discard = function() {
						var len = $scope.users.length;
						for (var i = 0; i < len; i++) {
							if ($scope.users[i].id === undefined) {
								$scope.users.splice(i, 1);
							}
						}
						$scope.editRow = -1;
					};

					$scope.editUser = function($index) {
						if ($scope.editRow == $index) {
							$scope.editRow = -1;
						} else {
							$scope.discard();
							$scope.editRow = $index;
						}
					};

					$scope.deleteUser = function($index) {
						if ($scope.users[$index].id === undefined) {
							$scope.users.splice($index, 1);
							return;
						}

						var answer = confirm("Do you really want to delete \""
								+ $scope.users[$index].username + "\" and ALL data related to this user?");
						if (answer) {
							dataFactory
									.deleteUser($scope.users[$index].id)
									.success(function(data) {
										$scope.users.splice($index, 1);
									})
									.error(
											function(error) {
												alert('Unable to delete user! Maybe it is referenced by other data?\n Server message:'
														+ error.message);
											});
							$scope.editRow = -1;
						}
					};

					$scope.getAllUsers = function() {

						dataFactory.getAllUsers().success(function(data) {
							$scope.users = data;
						}).error(
								function(error) {
									alert('Unable to load user data: '
											+ error.message);
								});
					};

					$scope.getAllUsers();
				});
