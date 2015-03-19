var chapterService = angular.module('chapterServices', []);

chapterService.factory('listChapters', function( $http, $q, $log ) {
	
	return({
		//APIs
		search : search,
		deleteChapter: deleteChapter,
		addChapter: addChapter,
		addSubChapter: addSubChapter,
		viewPage: viewPage,
		deleteExercise: deleteExercise
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
	
	function addChapter(chapTitle, chapDesc, orderId){
		
		var request = $http({
			method: 'POST',
			params: {chapTitle: chapTitle, chapDescr: chapDesc, orderId: orderId},
		    dataType: "json",
			url: 'chapterlookup/addchapter'
		});
		
		return request;

	};
	
	function addSubChapter(chapId, title, chapDesc, orderId){
		
		var request = $http({
			method: 'POST',
			params: {chapId: chapId, subChapTitle: title, subChapDescr: chapDesc, orderId: orderId },
		    dataType: "json",
			url: 'subchapterlookup/addsubchapter'
		});
		
		return request;

	};
	
	function deleteExercise(chapId){
		
		var request = $http({
			method: 'POST',
			params: {subchapId: chapId},
		    dataType: "json",
			url: 'subchapterlookup/deletesubchapter'
		});
		
		return request;

	};
	
	
	function viewPage(url){
		
		var request = $http({
			method: 'GET',
		    dataType: "json",
		    //responseType: 'arraybuffer',
			url: url
		});
		
		return request;

	};



});
