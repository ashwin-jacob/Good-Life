var chapterService = angular.module('chapterServices', []);

chapterService.factory('listChapters', function( $http, $q, $log ) {
	
	return({
		//APIs
		search : search,
		deleteChapter: deleteChapter
	});

	//Function to return serach
	function search(idsForRoles, parameters) {

		var request = $http({
			method: 'GET',
		    dataType: "json",
			url: 'chapterlookup/listcurriculum'
		});

		// return(request.then( handleSuccess, handleError ));
		return request;
	};
	
	function deleteChapter(chapId){
		
		var request = $http({
			method: 'POST',
			params: {chapId: chapId},
		    dataType: "json",
			url: 'chapterlookup/deletechapter'
		});
		
		return request;

	};

});