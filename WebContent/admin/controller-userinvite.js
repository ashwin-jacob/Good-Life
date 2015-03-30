forceForGood.controller('UserInvite', ['$log', '$scope', 'userService',
	function($log, $scope, userService) {
		
		$scope.invite = function(user) {
			$log.log(user);
			userService.inviteuser(user).then( handleSuccess, handleError );
		};

		var handleSuccess = function( response ) {

		};

		var handleError = function( response ) {

		};


	}]);