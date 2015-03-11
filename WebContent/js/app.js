'use strict';

var forceForGood = angular.module('forceForGood', [
	'ngRoute',
	'ui.bootstrap',
	'userManagement',
	'curriculum',
	'ngTable',
	'adminCenter',
	'multi-select',
	'adminFilter',
	'adminService',
	'chapterServices',
	'studentServices',
	'angularFileUpload',
	'ngAnimate']);

forceForGood.config(['$routeProvider',
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
			when('/chapterBuilder', {
				templateUrl: 'partials/chapterBuilder.html',
				controller: 'ChapterBuilder'
			}).
			when('/curriculum/:userId', {
				templateUrl: 'partials/curriculum.html',
				controller: 'studentCurriculumView'
			}).
			when('/studentHome', {
				templateUrl: 'partials/studentHome.html',
				controller: 'StudentHome'
			}).
			otherwise({
				redirectTo: '/curriculum'
			});
	}]);