/**
 * create 2016/1/11 
 * updata 2016/1/12 mckay
 */

/**
 * AJAX
 *  @param {string} url 请求接口地址
 *  @param {string/obj} data 提交数据 get/post
 *  return josn
 */
app.factory('C_api', ['$http','$rootScope', '$q', function ($http,$rootScope,$q) {
	var api = function(url,data){
		return $rootScope.C.base_url;
	}
	return {  
		query : function(action,can) {
			var deferred = $q.defer();
			$http({method: 'GET', url: api(action,can)}).success(function(data, status, headers, config) {  
				deferred.resolve(data);
			}).error(function(data, status, headers, config) {  
				deferred.reject(data);
			});  
			return deferred.promise;
		} 
	};  
}]);


function wxjsServiceCtor($resource) {

}