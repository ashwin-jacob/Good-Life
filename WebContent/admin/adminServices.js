var adminService = angular.module('adminService', []);

adminService.factory('userService', function( $http, $q, $log ) {
	
	return({
		//APIs
		search : search,
		inviteuser: inviteuser,
		changeUserStatus: changeUserStatus,
		activateUser : activateUser
	});

	//Function to return serach
	function search(idsForRoles, parameters) {

		var request = $http({
			method: 'POST',
			url: 'usermanagement/search',
			params: {input:parameters.input, field:parameters.id, sb:parameters.sb, mb:parameters.mb, fb:parameters.fb}
		});

		// return(request.then( handleSuccess, handleError ));
		return request;
	}

	function inviteuser(email) {

		var request = $http({
			method: 'POST',
			url: 'su/inviteuser',
			params: {email:email}
		});
		
		return request;
	}

	/**
	Suspend or delete user
	*/
	function changeUserStatus(action, userId) {
		var request = $http({
			method: 'POST',
			url: 'usermanagement/adduserstatus',
			params: {userId:userId, statusTypeCode:action}
		});

		return request;
	}

	/**
	Activate User
	*/
	function activateUser(userStatusId, date) {
		$log.log("Activate User");
		$log.log(date);
		var request = $http({
			method: 'POST',
			url: 'usermanagement/changeenddate',
			params: {userStatusId:userStatusId, newDate:date}
		});

		return request;
	}

});