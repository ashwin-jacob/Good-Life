angular.module('adminFilter', [])
	.filter('roleType', function() {
		return function(input) {
			if(input.toLowerCase() == "s") {
				return "Student";
			}
			if(input.toLowerCase() == "a") {
				return "Admin";
			}
			if(input.toLowerCase() == "f") {
				return "Facilitator";
			}
			if(input.toLowerCase() == "m") {
				return "Moderator";
			}
			
		};
	});