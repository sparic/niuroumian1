/**
 * clever Controllers
 * create 2016/1/11
 * updata 2016/1/12 mckay
 */
app.controller('indexCtrl', ['$scope', '$resource', 'C_api','$location', function ($scope, $resource, C_api,$location) {
    $resource('./wxitf/shop_list').get(
        {
            secret: 'xxx',
            orgId : '104',
            ifPaged : '1',
            pageNo : '3',
            pageSize : '7'
        },
        function (resp) {
        $scope.shops = resp.rows;
    });
}])
    .controller('viewCtrl', ['$scope', '$rootScope', '$resource', '$routeParams', function ($scope, $rootScope, $resource, $routeParams) {
        $resource('./wxitf/shop_detail').get({secret: 'xxx', storeId: $routeParams.id}, function (resp) {
            $scope.shop = resp.result;
        });
        
        var view = $scope.view = {
            num: '',
            token: null,
            get_num: function (id) {
                $('#dialog1').show();//此方法需要封directive
            },
            close: function () {
                $('#dialog1').hide();
            },
            subnum: function () {
                var me = this;
                if (me.num !== 0 && me.num !== '' && me.num !== null) {
                    $resource('./wxitf/enqueue').get({secret: "xxx", shopId: $routeParams.id, dinerCount: me.num}, function (resp) {
                        $('#dialog1').hide().next().show();
                        me.token = resp.result;
                    }, function(err) {
                        console.log(err);
                    });
                } else {
                    $rootScope.error('请输入准确的就餐人数')
                }
            },
            ok: function () {
                var me = this;
                $('#dialog2').hide();
                //+ "/" + me.token.waitTables + "/" + me.token.waitMinutes
                $rootScope.go('wait_detail/' + me.token.tableTypeId + "/" + me.token.token);
            }
        };
    }])
    .controller('waitDetailCtrl', ['$scope', '$resource', '$routeParams', function ($scope, $resource, $routeParams) {
        var payload = {
            secret: "xxx",
            tableTypeId: $routeParams.tableTypeId,
            token: $routeParams.token
        };
        $resource('./wxitf/view_progress').get(payload, function (resp) {
            $scope.token = resp.result;
        });

        var view = $scope.view = {
            cancelQueue: function() {
                //var payload = {
                //    waitQueueId: $routeParams.waitQueueId
                //};
                var me = this;
                if (me.num !== 0 && me.num !== '' && me.num !== null) {
                    $resource('./wxitf/cancel_Queue').get({waitQueueId: $routeParams.waitQueueId},function(resp) {
                        alert(waitQueueId);
                    },
                        function(err) {
                        console.log(err);
                    });
                }
            }
        }
    }])//
    .controller('accountCtrl', ['$scope', function ($scope) {
        console.log('accountCtrl');
    }])
    .controller('dazhongCtrl', ['$scope', '$routeParams', function ($scope, $routeParams) {
        //jump大众点评
        // $routeParams.id 大众点评id
        console.log('accountCtrl');
    }])
    .controller('orderCtrl', ['$scope', function ($scope) {

        $resource('./wxitf/wait_list').get(
            {
                secret: 'xxx',
                orgId : '104',
                ifPaged : '1',
                pageNo : '3',
                pageSize : '7'
            },
            function (resp) {
                $scope.orders = resp.rows;
            });
    }])
    .controller('sortCtrl', ['$scope', function ($scope) {
        console.log('sortCtrl');
    }])
    .controller('contactCtrl', ['$scope', function ($scope) {
        console.log('contactCtrl');
    }])
    .controller('cleverinfoCtrl', ['$scope', function ($scope) {
        console.log('cleverinfoCtrl');
    }])
    .controller('getUserInfoCtrl', ['$scope', '$resource', 'C_api', function ($scope, $resource, C_api) {
        $resource('./wxitf/get_userInfo').get({secret: 'xxx'}, function (resp) {
            $scope.shop = resp.result;
        });
    }])