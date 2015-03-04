/**
SubChapter view
*/

forceForGood.directive('subchapterView', ['$log', 'student', '$compile', '$http',
	function($log, student, $compile, $http) {

		var getTemplate = function(contentType) {
			$log.log(contentType);
			var templateUrl = '';
			switch(contentType) {
				case 's':
					templateUrl = 'partials/subchapter-s.html';
					break;
				case 'm':
					templateUrl = 'partials/subchapter-m.html'
					break;
				case 'u':
					templateUrl = 'partials/subchapter-u.html'
					break;
			}
			return templateUrl;
		}
		
		var linker = function($scope,element,attrs) {
			$scope.$watch('subchapterType', function(subchapterType) {
				if(subchapterType && subchapterType.length) {
					$scope.dynamicUrl = getTemplate(subchapterType);
				}
			});
			
		};

		var controllerFunc = function($scope) {
			$scope.submitPostShortAns = function() {
				$log.log("Got Here to submitPost for Short Answer");
				angular.forEach($scope.subchapterForm, function(subChapterElement) {
					student.updateShortAns($scope.userId, subChapterElement.saQId, subChapterElement.userAnswer)
				});
			};

			$scope.submitPostMultiChoice = function() {
				$log.log("Got Here to submitPost for Multiple Choice");
				angular.forEach($scope.subchapterForm, function(subChapterElement) {
					student.updateMultiChoice($scope.userId, subChapterElement.multiQuesId, subChapterElement.userAnswer)
				});
			};

			$scope.uploadQues = function() {
				angular.forEach($scope.subchapterForm, function(subChapterElement) {
					$scope.upload($scope.userId, subChapterElement.uploadQuesId, $scope.files);
				});
			}

			$scope.upload = function(user, questId, files) {
				if (files && files.length) {
					for (var i = 0; i < files.length; i++) {
						var file = files[i];
						$upload.upload({
							url: "/student/updateuploadeduseranswer",//URL needed
							fields: { userId:user, uploadQuesId:questId , mediaTypeId: , filePath: }, //chapter id and upload id,
							mpfile:file,
						}).progress(function(evt) {
							var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
							$log.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
						}).success(function (data, status, headers, config) {
							$log.log('file ' + config.file.name + 'uploaded. Response: ' + data);
						});
					}
				}
			};
		};

		return {
			restrict: 'E',
			scope: {
				userId: '=userid',
				subchapter: '=subchapter',
				subchapterType: '=subchapterType',
				subchapterForm: '=subchapterForm'
			},
			link: linker,
			template: '<ng-include src="dynamicUrl"></ng-include>',
			controller: controllerFunc
		};

	}]);