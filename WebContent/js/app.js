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
				templateUrl:'admin/search.html',
				controller: 'AdminSearch'
			}).
			when('/adminConsole', {
				templateUrl: 'admin/adminConsole.html',
				controller: 'AdminConsole'
			}).
			when('/userinvite', {
				templateUrl: 'admin/userinvite.html',
				controller: 'UserInvite'
			}). 
			when('/chapterBuilder', {
				templateUrl: 'partials/chapterBuilder.html',
				controller: 'ChapterBuilder'
			}).
			when('/curriculum/:userId', {
				templateUrl: 'partials/curriculum.html',
				controller: 'StudentCurriculumView'
			}).
			when('/studentHome/:userId', {
				templateUrl: 'partials/studentHome.html',
				controller: 'StudentHome'
			}).
			when('/home', {
				templateUrl: 'partials/selectFromMany.html',
				controller: 'RedirectController'
			}). 
			otherwise({
				redirectTo: '/home'
			});
	}]);