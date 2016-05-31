/**
 * INSPINIA - Responsive Admin Theme
 * Copyright 2014 Webapplayers.com
 *
 * Main controller.js file
 * Define controllers with data used in Inspinia theme
 *
 *
 * Functions (controllers)
 *  - MainCtrl
 *  - dashboardFlotOne
 *  - nestableCtrl
 *
 */

function mainCtrl($scope, $resource, $modal, ConstService) {
    $scope.currentUser = ConstService.currentUser;

    $scope.dtInstance1 = null;
    $scope.dtInstance2 = null;
    $scope.echarts1 = null;
    $scope.echarts2 = null;
    $scope.echarts3 = null;
    $scope.echarts4 = null;
    $scope.echarts5 = null;
    $scope.echarts6 = null;

    metricCtrl($scope, $resource, ConstService);
    reportCtrl($scope, $resource, ConstService);

    $scope.generateTable = function (dtInstance) {
        var datatable = dtInstance;
        if (!datatable) {
            return;
        }
        datatable.reloadData();
    };

    $scope.generateChart = function (chart, url) {
        if (!chart) {
            return;
        }
        var payload = $scope.where;
        $scope.resetXAxis(chart);
        $resource(url).save(payload, function (option) {
            chart.setOption(option);
        });
    };

    $scope.generateAll = function() {
        $scope.generateTable($scope.dtInstance1);//排队数据
        $scope.generateTable($scope.dtInstance2);//就餐数据
        $scope.generateChart($scope.echarts1, './chart/dinerTable'); //排队基础
        $scope.generateChart($scope.echarts2, './chart/diningRate'); //就餐率同比、环比趋势图
        $scope.generateChart($scope.echarts3, './chart/eatDuration');//就餐时长
        $scope.generateChart($scope.echarts4, './chart/statTermByType');//桌型翻台综合分析
        $scope.generateChart($scope.echarts5, './chart/statTermByZone');//区域翻台综合分析
        $scope.generateChart($scope.echarts6, './chart/missesOfTurn');  //流失时段分布图
    }

    $scope.generateAll();
    $scope.changePassword = function() {
        //var modalInstance = $modal.open({
        //    templateUrl: 'assets/views/modal_changepassword.html',
        //    controller: mainCtrl,
        //    windowClass: "animated fadeIn"
        //});
    }
}

function reportCtrl($scope, $resource, ConstService, $location) {
    $scope.rememberMe = true;
    $scope.echarts = null;
    $scope.echarts2 = null;
    $scope.where = ConstService.defaultWhere();
    $scope.typeOptions = ConstService.tableTypeList;

    console.log($location);

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.search = function (url) {
        var chart = $scope.echarts;
        if (!chart) {
            return;
        }
        var payload = $scope.where;
        $scope.resetXAxis(chart);
        $resource(url).save(payload, function (option) {
            chart.setOption(option);
        });
    };

    $scope.search2 = function (url1, url2) {
        var chart1 = $scope.echarts;
        if (!chart1) {
            return;
        }
        var payload = $scope.where;
        $scope.resetXAxis(chart1);
        $resource(url1).save(payload, function (option) {
            chart1.setOption(option);
        });

        var chart2 = $scope.echarts2;
        if (!chart2) {
            return;
        }
        $scope.resetXAxis(chart2);
        $resource(url2).save(payload, function (option) {
            chart2.setOption(option);
        });
    };

    $scope.resetXAxis = function(chart) {
        var optClone = chart.getOption(), xAxis = optClone.xAxis;
        if (xAxis != null && xAxis[0].type == "category") {
            xAxis[0].data = ["分类1"];
            chart.setOption(optClone, true);
        }
    };
    $scope.dinerCountOptions = './assets/option/dinercount_Options.json';
    $scope.dinerTableOptions = './assets/option/dinertable_Options.json';
    $scope.diningRateOptions = './assets/option/diningrate_Options.json';
    $scope.eatDurationOptions = './assets/option/eatduration_Options.json';
    $scope.typeTermTableOptions = './assets/option/typeTerm_Options.json';
    $scope.zoneTermTableOptions = './assets/option/zoneTerm_Options.json';
}

function metricCtrl($scope, $resource, ConstService, $base64) {
    $scope.where = ConstService.defaultWhere();

    $scope.base64Where = function () {
        var payload = $scope.where;
        return $base64.encode(angular.toJson(payload));
    };

    $scope.dtInstance = null;
    $scope.queue = {};
    $scope.queue.columns = $resource('./assets/option/queue_Columns.json').query().$promise;
    $scope.queue.dtOptions = angular.extend({
        "ajax": {
            "url": "./api/queuingResult",
            "type": "POST",
            "dataSrc": "rows",
            "data": function (data) {
                angular.extend(data, $scope.where);
                data.tableType = {};
            }
        }
    }, ConstService.tableOptions);
    $scope.queue.columnDefs = [
        {
            targets: 6,
            render: function ( data, type, full, meta ) {
                var labels = ["等待", "", "", "就餐", "过号", "预定", "废弃"];
                var styles = ["", "", "", "label-primary", "label-danger", "", ""];
                return "<span class=\"label "+ styles[data] + "\">" + labels[data] + "</span>";
            }
        }
    ];

    $scope.dining = {};
    $scope.dining.columns = $resource('./assets/option/dining_Columns.json').query().$promise;
    $scope.dining.dtOptions = angular.extend({
        "ajax": {
            "url": "./api/diningResult",
            "type": "POST",
            "dataSrc": "rows",
            "data": function (data) {
                angular.extend(data, $scope.where);
                data.tableType = {};
            }
        }
    }, ConstService.tableOptions);
    $scope.search = function () {
        var datatable = $scope.dtInstance;
        if (!datatable) {
            return;
        }
        datatable.reloadData();
    };

    $scope.tableTurn = {};
    $scope.tableTurn.columns = $resource('./assets/option/tableTurn_Columns.json').query().$promise;
    $scope.tableTurn.dtOptions = angular.extend({
        "ajax": {
            "url": "./api/tableTurnResult",
            "type": "POST",
            "dataSrc": "rows",
            "data": function (data) {
                angular.extend(data, $scope.where);
                data.tableType = {};
            }
        }
    }, ConstService.tableOptions);
}

/**
 *
 * Pass all functions into module
 */
angular
    .module('inspinia')
    .controller('mainCtrl', mainCtrl)
    .controller('reportCtrl', reportCtrl)
    .controller('metricCtrl', metricCtrl)
