var app = angular.module('app', []);
app.controller('postcontroller', function($scope, $http, $location) {
  $scope.submitForm = function(){
    var url = $location.absUrl() + "/save";
    
    var config = {
                headers : {
                    'Accept': 'text/plain'
                }
        }
    var data = {
    		id:$scope.id,
            name: $scope.name,
            language: $scope.language,
            framework:$scope.framework
        };
    
    $http.post(url, data, config).then(function (response) {
      $scope.success = response.data;
    }, function error(response) {
      $scope.errorsave = "Error with status: " +  response.statusText;
    });
    $scope.id="";
    $scope.name = "";
    $scope.language = "";
    $scope.framework="";
  }
});
 
app.controller('requestcontroller', function($scope, $http, $location) {
  $scope.getfunction = function(){
    var url = $location.absUrl() + "findAll";
    $http.get(url).then(function (response) {
    	$scope.myVar=false;
      $scope.response1 = response.data
    }, function error(response) {
      $scope.errorfindall = "Error with status: " +  response.statusText;
    });
  }

  $scope.getdetail = function() {
	  var url = $location.absUrl() + "/findByName/"+$scope.name;
	    $http.get(url).then(function (response) {
	    	$scope.myVar1=false;
	    	$scope.name="";
	      $scope.response2 = response.data
	    }, function error(response) {
	    	$scope.myVar1=false;
	    	$scope.name="";
	    	$scope.response2.length=0;
	    	
	      $scope.errorfindbyname = "Error with status: " +  response.statusText;
	    });
	    
	 
	   
	}
  $scope.delete = function() {
	    var url = $location.absUrl() + "/delete/"+$scope.id;
	    var config = {
                headers : {
                    'Accept': 'text/plain'
                }
        }
	    $http.delete(url,config).then(function (response){
	    	$scope.id="";
	      $scope.response3 = response.data
	    }, function error(response) {
	    	$scope.id="";
	      $scope.response3 = "No Record Found !" +response.statusText;
	    });
	}
  
});


