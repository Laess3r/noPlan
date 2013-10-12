'use strict';
console.log("init main");

angular.module('mytodoApp')
    .controller('TodoCtrl',function ($scope,localStorageService){
        console.log('init TodoCtrl')
        //$scope.todos = ['Item 1','Item2', 'Item3'];
        var todosInStore = localStorageService.get('todos');
        $scope.todos= todosInStore && todosInStore.split('\n') || [];
        $scope.$watch(function(){
            localStorageService.add('todos',$scope.todos.join('\n'));
        })


        $scope.addTodo = function(){
            $scope.todos.push($scope.todo);
            $scope.todo="insert Todo";
        }

        $scope.removeTodo = function($index){
            console.log($index);
            //$scope.todos.splice($index);
        }
    });
