'use strict';

var curriculumMakerApp = angular.module('curriculumMakerApp', [
	'ngRoute',
	'ui.bootstrap',
	'userManagement',
	'ngTable',
	'adminCenter',
	'multi-select']);

curriculumMakerApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/searchUsers', {
				templateUrl:'partials/search.html',
				controller: 'AdminSearch'
			}).
			when('/adminConsole', {
				templateUrl: 'partials/adminConsole.html',
				controller: 'AdminConsole'
			}). 
			otherwise({
				redirectTo: '/searchUsers'
			});
	}]);