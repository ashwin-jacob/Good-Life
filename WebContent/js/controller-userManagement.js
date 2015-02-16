var userManagement = angular.module('userManagement', []);

/*
	Admin Seach page controller
*/
userManagement.controller('AdminSearch', ['$scope', '$http', '$log', '$filter', 'ngTableParams',
	function($scope, $http, $log, $filter, ngTableParams) {

		//Sample Data
		$scope.userData = [
  {
    fname: "Ashwin",
    lname: "Jacob",
    uname: "shwin23",
    email: "ashwin.jacob@tsgforce.com",
    role: "student",
    status: "active"
  },
  {
    fname: "Carlo",
    lname: "Vendiola",
    uname: "cviola",
    email: "carlo.ven@tsgforce.com",
    role: "student",
    status: "active"
  }
];
		$scope.example1model = [];

		//Search Options for the Text Field
		$scope.searchOptions = [
			{ label:'User Name', id:1},
			{ label:'First Name', id:2},
			{ label:'Last Name', id:3},
			{ label:'E-mail', id:4},
			{ label:'Roser ID', id:5} ];
		//User needs to select one search value
		$scope.typeSelected = $scope.searchOptions[1];

		//Role options
		$scope.roleOptions = [
			{ name:'Student', id:'student', ticked: true},
			{ name:'Facilitator', id:'facilitator', ticked: false},
			{ name:'Moderator', id:'moderator', ticked: false}];

		$scope.roleSelected = {};

		//Function that gets the user data for table
		$scope.getUserData = function() {
			if($scope.userData.length > 0) {
				$scope.seachisOpen = false;
			}
			return $scope.userData;
		}

		//Creation of the Table using 3rd party api
	    $scope.userTable = new ngTableParams({
	    	page:1,
	    	count:10,
	    	sorting: {
	    		fname: 'asc'
	    	}
	    }, {
    		total: function() { return $scope.getUserData().length; },
    		getData: function($defer, params) {
    			var filteredData = $scope.getUserData();
    			var orderedData = params.sorting() ?
    				$filter('orderBy')(filteredData, params.orderBy()): filteredData;

    			$defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
    		}
	    });

	    $http.get('localhost:8080/good-life/chapterlookup/listpublishedchapters').
		  success(function(data, status, headers, config) {
		    // this callback will be called asynchronously
		    // when the response is available
		    $log.log('Got to success');
		  }).
		  error(function(data, status, headers, config) {
		    // called asynchronously if an error occurs
		    // or server returns response with an error status.
		    $log.log('Got to error');
		});
	}]);