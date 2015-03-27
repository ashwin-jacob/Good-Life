forceForGood.controller('UserInvite', ['$log', '$scope', 
	function($log, $scope) {
		
		$scope.invite = function(user) {
			$log.log(user);
		};


	}]);