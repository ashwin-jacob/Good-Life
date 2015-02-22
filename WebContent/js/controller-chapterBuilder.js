var curriculum = angular.module('curriculum', []);

/*
	Chapter Builder page controller
*/
curriculum.controller('ChapterBuilder', ['$scope', '$log', '$filter', 'ngTableParams', 'listChapters',
	function($scope, $log, $filter, ngTableParams, listChapters) {

		//Table Information
		var data = [];

		$scope.chapterTable = new ngTableParams({
			//Settings
			page: 1,
			count: 10,
			sorting: {
				name: 'asc'
			}
		}, {
			total: data.length,
			getData: function($defer, params) {
	            $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));			}
		});

		var handleSuccess = function (response) {
			$log.log("Succesful search");
			data = response.data;
			$scope.chapterTable .reload();
		};

		var handleError = function (response) {
			$log.log("Error with search");
			data = [{}];
			$scope.chapterTable .reload();
		};
		
	    $scope.addRow = function() {
	        data.push({chapTitle: 'Empty', orderId: 0, chapDescr:'sd'});
			$scope.chapterTable .reload();
	        modal.open({

		      });
	      };
	      
	    $scope.removeRow = function(chapId){				
	  		var index = -1;		
	  		var comArr = eval( data );
	  		for( var i = 0; i < comArr.length; i++ ) {
	  			if( comArr[i].chapId === chapId ) {
	  				index = i;
	  				break;
	  			}
	  		}

	  		if( index === -1 ) {
	  			alert( "Cannot Delete Chapter" );
	  		}
	  		data.splice( index, 1 );		
			$scope.chapterTable .reload();

	  	};
	  	

		
		var init = function () {
			listChapters.search().then( handleSuccess, handleError );

			};
		init();

}]);