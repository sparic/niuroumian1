/**
 * create 2016/1/11 
 * updata 2016/1/12 mckay
 */
//'use strict';
var app = angular.module('clever', ['ngRoute', 'ngResource'])
	.config(['$routeProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $locationProvider, $httpProvider) {
	    $routeProvider.when('/',{ templateUrl:'weixin/view/shop_list.html',controller:'indexCtrl'});//index
        $routeProvider.when('/shop_list',{ templateUrl:'weixin/view/shop_list.html',controller:'indexCtrl'});//index
        $routeProvider.when('/shop_detail/:id',{ templateUrl:'weixin/view/shop_detail.html',controller:'viewCtrl'});//Collar number
        $routeProvider.when('/account',{ templateUrl:'weixin/view/account.html',controller:'accountCtrl'});//account
        $routeProvider.when('/wait_list',{ templateUrl:'weixin/view/wait_list.html',controller:'orderCtrl'});//my make appointments
        $routeProvider.when('/wait_detail/:tableTypeId/:token/',{ templateUrl:'weixin/view/wait_detail.html',controller:'waitDetailCtrl'});//my make appointments
        $routeProvider.when('/sort',{ templateUrl:'weixin/view/sort.html',controller:'sortCtrl'});//my sort
        $routeProvider.when('/contact',{ templateUrl:'weixin/view/contact.html',controller:'contactCtrl'});//contact us
        $routeProvider.when('/cleverinfo',{ templateUrl:'weixin/view/cleverinfo.html',controller:'cleverinfoCtrl'});//clever info
        $routeProvider.when('/get_userInfo',{ templateUrl:'weixin/view/getUserInfoCtrl.html',controller:'getUserInfoCtrl'});//getUserInfo

	    $routeProvider.otherwise({ redirectTo: '/404/' });
        $locationProvider.html5Mode(false).hashPrefix('');
        //$httpProvider.defaults.useXDomain = true;
        //delete $httpProvider.defaults.headers.common['X-Requested-With'];
	}])
	.run(['$rootScope', '$route', '$http', '$location', '$window', '$timeout', '$templateCache', '$routeParams',function ($rootScope, $route, $http, $location, $window, $timeout, $templateCache, $routeParams) {
        $rootScope.C = {
            base_url:'',
        }
        //报错动画
        $rootScope.error = function(error){
            $("#error").remove();
            $('body').append('<div id="error"><span>'+error+'</span></div>');
            $("#error").addClass('animated fadeInUp');
            setTimeout(function () {
                $("#error").addClass('animated fadeOut');
                setTimeout(function () {
                    $("#error").remove();
                }, 3000);
            }, 3000);
        }
		//链接
        $rootScope.go = function (url) {
            if(url=='back'){
                $window.history.back();
            }else{
                $location.path(url);
            }
        }
        //全页缓存
/*        angular.forEach($route.routes, function (r) {
            if (r.templateUrl) {
                $http.get(r.templateUrl, {cache: $templateCache});
            }
        });*/
	}]);