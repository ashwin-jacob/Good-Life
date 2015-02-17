var userManagement = angular.module('userManagement', []);
var prefix = "http://localhost:8080/good-life"; //For Dev Purposes

/*
	Admin Seach page controller
*/
userManagement.controller('AdminSearch', ['$scope', '$http', '$log', '$filter', 'ngTableParams',
	function($scope, $http, $log, $filter, ngTableParams) {

		$scope.submitSearch = function(searchForm) {
			$log.log( 'Submitting values' );
			var idsForRoles = getIdsForRoles($scope.roleOptions);
			var urlSearch = prefix + "/userlookup/";
			urlSearch += searchForm.textInput + "/" +searchForm.typeSelected.id+"/";
			urlSearch += "sb/" + idsForRoles.student + "/mb/" + idsForRoles.moderator + "/fb/" + idsForRoles.facilitator;
			$log.log("URL Value: "+ urlSearch);

			$http.post(urlSearch).
				success(function(data, status, headers, config) {
					console.log("Got to success for submit");
					console.log(data);
				}).
				error(function(data, status, headers, config) {
					console.log("Got to error for submit");
					console.log(data);
				});
		};

		//Search Options for the Text Field
		$scope.searchOptions = [
			{ label:'User Name', id:"usr_id"},
			{ label:'First Name', id:"frst_nm"},
			{ label:'Last Name', id:"lst_nm"},
			{ label:'E-mail', id:"email"}];

		//Role options
		$scope.roleOptions = [
			{ name:'Student', id:'student', ticked: true},
			{ name:'Facilitator', id:'facilitator', ticked: false},
			{ name:'Moderator', id:'moderator', ticked: false}];

		//Table Information
		var data = [{name: "Ashwin", person: "Jacob"},
						{name: "Shane", person:"Jacob"},
						{name: "Carlo", person:"Vendiola"}];

		$scope.userTable = new ngTableParams({
			//Settings
			page: 1,
			count: 10,
			sorting: {
				name: 'asc'
			}
		}, {
			total: data.length,
			getData: function($defer, params) {
				var orderedData = params.sorting() ? $filter('orderBy')(data, params.orderBy()) : data;
				$defer.resolve(orderedData.slice((params.page()-1)*params.count(),params.page()*params.count()));
			}
		});

		var getIdsForRoles = function(roleOptions) {
			var rolechoosen = {student:0, moderator:0, facilitator:0};
			angular.forEach(roleOptions, function(value, key) {
				if( value.ticked === true ) {
					if(value.id == "student") rolechoosen.student = 1;
					if(value.id == "facilitator") rolechoosen.facilitator = 1;
					if(value.id == "moderator") rolechoosen.moderator = 1;
						
				}
			});
			return rolechoosen;
		};

}]);