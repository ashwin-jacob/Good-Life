var curriculum = angular.module('curriculum', []);

/*
	Chapter Builder page controller
*/
curriculum.controller('ChapterBuilder', ['$scope', '$log', '$filter', 'ngTableParams', 'listChapters','$modal',
	function($scope, $log, $filter, ngTableParams, listChapters, modal) {

		//Table Information
		var data = [];

		$scope.chapterTable = new ngTableParams({
			//Settings
			page: 1,
			count: 10,
	        filter: {

	            chapTitle: ''       // initial filter

	        }
		}, {
			total: data.length,
	        //groupBy: 'data.curriculumTreeList.objR.chapId',
		       getData: function($defer, params) {

		            var page = data.slice((params.page() - 1) * params.count(), params.page() * params.count());
		            $scope.data=page;
                    $defer.resolve(page);
		            
		        }
		});
		

		var handleSuccess = function (response) {
			$log.log("Successful");
			data = response.data;
		//	alert(JSON.stringify(data));
			$scope.structureData(data);
			//alert(JSON.stringify($scope.chapterData[0].exer[0]));
			data = $scope.chapterData;
			$scope.chapterTable .reload();
		};

		var handleError = function (response) {
			$log.log("Error");
			data = [{}];
			$scope.chapterTable .reload();
		};
		
		// Structure Table Data	
		$scope.structureData  = function(data){
			$scope.chapterData = [];
			var count = 0;
			angular.forEach(data, function(root) {
				angular.forEach(root, function(item) {
					$scope.chapterData.push( {"chap": item.data, "exer": null});
					$scope.exerciseData = [];

					count++;
					if (item.numberOfChildren > 0){

						angular.forEach(item.children, function(exercise) {

							$scope.exerciseData.push(exercise.data);
							//alert(JSON.stringify($scope.exerciseData));
							$scope.chapterData[count-1].exer = $scope.exerciseData;

						});
						
						
					}
					//alert(JSON.stringify($scope.chapterData[count-1]));

				});
			});
		}
		
		//Add Chapter
		$scope.addRow = function(title, desc, addChapterFm) {
			
			var id = addChapterFm.chapId.$viewValue;
			var title = addChapterFm.chapTitle.$viewValue;
			var desc = addChapterFm.chapDescr.$viewValue;


			var confirmAdd = function (response) {
				$scope.chapterData.push({"chap":{"chapId":response.data,"chapDescr":desc,"chapTitle":title,"orderId":id,"published":false},"exer":[]});
				$scope.showConfirmation("success", "Chapter titled " + "'"+title +"' was added!");

			} 

			var failAdd = function (response) {
				$scope.showConfirmation("fail", "Chapter titled " + "'"+title +"' was not added! Please try again" );
			} 

			if (addChapterFm.$valid){
				listChapters.addChapter(title, desc, id).then(confirmAdd, failAdd);
			}

		};

		//Delete Chapter
	    $scope.removeRow = function(){		
	    	var chapId = $scope.chapIdNum;
	  		var index = -1;		
	  		var comArr = eval( data );
		//	alert(JSON.stringify(comArr[0].objR));
	  		for( var i = 0; i < comArr.length; i++ ) {
	  			if( comArr[i].chap.chapId === chapId ) {
	  				index = i;
	  				break;
	  			}
	  		}

	  		if( index === -1 ) {
				$scope.showConfirmation("fail", "Chapter" + "#"+chapId +" was not deleted!");
	  		}
	  		data.splice( index, 1 );		
			listChapters.deleteChapter(chapId).then( handleSuccess, handleError );
			$scope.showConfirmation("success", "Chapter" + " #"+chapId +" has been deleted!");
			$scope.chapterTable .reload();
			init();

	  	};
	  	
	    var show = false;
	    //Expand Collapse -WIP
	    $scope.showButton = function(val){
	    if(angular.isUndefined(val)){
	 	    	 return false;
	 	     }
	    else{
	      return show;
	    }
	    }
	  	
	    $scope.hideRows = function(){
	    	show = false;
	    };
	  	
	    $scope.showRows = false;
  	
	    $scope.showRow = function(val){

	     if(typeof val == 'undefined'){
	    	// alert('false');
	    	 return false;

	     }
	     else{
	    	 return true;
	     }
	    };
	    
	    // Dialog Modal
	    var myModal;
	    $scope.addExerciseData = {};
	    
	    $scope.openDialog = function(url, formData){
	    	$scope.addExerciseData = formData;
	    	myModal = modal.open({templateUrl: url, scope: $scope
	    		
	    	
	    	});
	    }
	    
	    $scope.closeDialog = function(){
	    	myModal.close();
	    }
	    
	    $scope.listClick = function(event, rowInfo){
	    	
	    	if(event.target.className.indexOf("exerciseTitle") > 0){
	    		$scope.exerciseClick(rowInfo, event);

	    	}
	    	else{
	    		$scope.chapterClick(rowInfo);
	    	}
	    	//alert("check"+JSON.stringify(rowInfo));

	    }
	    
	    // View Row Details from Table
	    $scope.chapterClick = function(rowData){
	    	
	    	var chapTitle = rowData.chap.chapTitle;
	    	var chapDesc = rowData.chap.chapDescr;
	    	$scope.chapIdNum = rowData.chap.chapId;
	    	$scope.showPane("chapter", chapTitle, "", chapDesc);

	    }
	    
	    $scope.exerciseClick = function(rowData, event){

	 	   var title = event.target.textContent;
	 	   var chapId = rowData.chap.chapId;
	       $scope.chapIdNum = chapId;
	 	   var chapTitle = "";
	    	
	  		var comArr = eval( data );
	  		

		  		for( var i = 0; i < comArr.length; i++ ) {
		  			if( comArr[i].chap.chapId === chapId ) {
		  				chapTitle = comArr[i].chap.chapTitle;
		  			}
		  		}
	    	$scope.showPane("subchapter", chapTitle, title, "");

	    }
	    
	    //Show Chapter and Exercise (Subchapter) View
	    $scope.showChapterPane = false;
	    $scope.showSubChapPane = false;
	    $scope.chapIdNum = "";
	    $scope.chapTitle = "";
	    $scope.chapDescr = "";
	    $scope.exTitle = "";
	    $scope.exDescr = "";

	    $scope.resetPaneValues = function(){
		    $scope.chapIdNum = "";
		    $scope.chapTitle = "";
		    $scope.chapDescr = "";
		    $scope.exTitle = "";
		    $scope.exDescr = "";
	    }
	    
	    //Get Row Data
	    $scope.getRowByID = function(chapID){
	  		var comArr = eval( data );
	  		
	  		var row;
	  		
	  		for( var i = 0; i < comArr.length; i++ ) {
	  			if( comArr[i].chap.chapId === chapID ) {
	  				row = comArr[i];
	  				break;
	  			}
	  		}
	  		
	  		return row;
	  		
			
	    }
	    
	    $scope.showPane = function(paneType, chap, subChap, chapDesc){
	    	//  $scope.resetPaneValues();
	    	  
	    	if (paneType == "subchapter"){
	    		
		    	  $scope.showChapterPane = false;
		          $scope.showSubChapPane = true;
		          $scope.chapTitle = chap;
		          $scope.exTitle = subChap;
	    	}
	    	else if (paneType == "chapter"){
	    		
	    		
		    	  $scope.showChapterPane = true;
		          $scope.showSubChapPane = false;
		          $scope.chapTitle = chap;
		          $scope.chapDescr = chapDesc;

	    	}

	    };

	    $scope.showSuccess = false;
	    $scope.showFailure = false;
	    $scope.confirmMsg = "";
	    
	    $scope.showConfirmation = function(flag, message){
	    	if (flag == "success"){
		    	  $scope.showSuccess = true;
		          $scope.showFailure = false;
	    	}
	    	else if (flag == "fail"){
		    	  $scope.showFailure = true;
		          $scope.showSuccess = false;
	    	}
	    	
	          $scope.confirmMsg = message;


	    };
	    //Add Exercise
	    $scope.submitExercise = function(exerciseFm){
	    	
	    	var chapId = $scope.chapIdNum;
	    	var title = exerciseFm.exTitle.$viewValue;
	    	var exDesc = exerciseFm.exDescr.$viewValue;
	    	var orderId = exerciseFm.exOrder.$viewValue;
	    	var published = exerciseFm.chapPub.$viewValue;
	    	
	    	var confirmAdd = function (response) {
	    		
		  	var position = 0;
	    		var comArr = eval( data );
		  		for( var i = 0; i < comArr.length; i++ ) {
		  			if( comArr[i].chap.chapId === chapId ) {
		  				position = i+1;
		  			}
		  		}
	    		
		  		//alert(position + comArr[position-1].chap.chapTitle);
				$scope.chapterData.splice(position, 0, { "chap ":{"exer":[{"subChapId":response.data,"chapId":chapId,"subChapDescr":exDesc,"subChapTitle":title,"orderId":orderId,"published":published}]}});
				$scope.showConfirmation("success", "Exercise titled " + "'"+title +"' was added!");

			} 

   		    var failAdd = function (response) {
				$scope.showConfirmation("fail", "Exercise titled " + "'"+title +"' was not added! Please try again" );
			} 

			if (exerciseFm.$valid){
				
				listChapters.addSubChapter(chapId, title, exDesc, orderId).then( confirmAdd, failAdd );
				$scope.chapterTable .reload();
				init();

			}

			
			
	    }

	    // Add Questions to Exercise
	    var questions = {};
	    questions.data = [{
	        id: "1",
	        text: "How has the Good Life impacted you?"
	    },
	    {
	        id: "2",
	        text: "Where do you plan to be in 5 years?"
	    }];
	    
	    $scope.questions = questions;
	    
	    $scope.deleteItem = function (index) {
	    	questions.data.splice(index, 1);
	    }
	    $scope.addItem = function (question) {
	    	questions.data.push({
	            id: questions.data.length + 1,
	            text: question
	        });
	    }
	    //Delete Exercise
	    $scope.deleteExercise = function(exTitle){
	    	var row = $scope.getRowByID($scope.chapIdNum);	    	
	    	
	  		var chapindex = -1;		
	  		var comArr = eval( data );
	  		for( var i = 0; i < comArr.length; i++ ) {
	  			if( comArr[i].chap.chapId === row.chap.chapId ) {
	  				chapindex = i;
	  				break;
	  			}
	  		}
	  		
	  		
	  		var exindex = -1;		

	  		for( var i = 0; i < row.exer.length; i++ ) {
	  			if( row.exer[i].subChapTitle === exTitle) {
	  				exindex = i;
	  				break;
	  			}
	  		}
	  		
	  		if( chapindex === -1 || exindex === -1) {
				$scope.showConfirmation("fail", "Chapter" + "#"+row.exer[0].subChapId +" was not deleted!");
	  		}
	  		//only removing first exercise for now
	  		var itemToDelete = row.exer[exindex];
	  		//alert(JSON.stringify(data[chapindex].exer[exindex]));
	  		data[chapindex].exer.splice(exindex, 1);		
			listChapters.deleteExercise(itemToDelete.subChapId).then( handleSuccess, handleError );
			$scope.showConfirmation("success", "Exercise" + " titled '"+ itemToDelete.subChapTitle +"' has been deleted!");
			$scope.showChapterPane = false;
			$scope.chapterTable .reload();
			init();
	    }
	    
	    //Carousel
	    $scope.myInterval = 5000;
	    var slides = $scope.slides = [];
	    $scope.addSlide = function() {
	      var newWidth = 600 + slides.length + 1;
	      slides.push({
	        image: 'http://placekitten.com/' + newWidth + '/300',
	        text: ['More','Extra','Lots of','Surplus'][slides.length % 4] + ' ' +
	          ['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
	      });
	    };
	    for (var i=0; i<4; i++) {
	      $scope.addSlide();
	    }
	    
	    $scope.fileURL = "";
	    $scope.openCarousel = function(url, pdf){
	    	//alert(pdf);
		    $scope.fileURL = pdf;
		    $scope.viewPage(url, pdf);
	    }
	    //View Page Request
	    
		$scope.viewPage = function(url, pdf){
			
			var confirmAdd = function (response) {
				/*var file = new Blob([response], {type: 'application/pdf'});
			    var fileURL = URL.createObjectURL(file);
			    $scope.content = $sce.trustAsResourceUrl(fileURL);*/

		    	myModal = modal.open({templateUrl: url, scope: $scope
		    		
		    	});
			} 

			var failAdd = function (response) {
				$scope.showConfirmation("fail", "Please try again" );
			} 
			
			listChapters.viewPage(pdf).then( confirmAdd, failAdd );
		}

	    //Initial Home Search
		var init = function () {
			listChapters.search().then( handleSuccess, handleError );

			};
		init();

}]);