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
			}
			$scope.submitPostMultiChoice = function() {
				$log.log("Got Here to submitPost for Multiple Choice");
				angular.forEach($scope.subchapterForm, function(subChapterElement) {
					student.updateMultiChoice($scope.userId, subChapterElement.multiQuesId, subChapterElement.userAnswer)
				});
			}
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