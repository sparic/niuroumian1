/**
 * INSPINIA - Responsive Admin Theme
 * Copyright 2014 Webapplayers.com
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written stat for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/dashboards/dashboard");

    $stateProvider
        .state('dashboards', {
            abstract: true,
            url: "/dashboards",
            templateUrl: "assets/views/common/content.html",
        })
        .state('dashboards.dashboard', {
            url: "/dashboard",
            templateUrl: "assets/views/dashboard.html",
            data: { pageTitle: '我的主页' }
        })
        .state('dashboards.daily', {
            url: "/dashboard_daily",
            templateUrl: "assets/views/dashboards_daily.html",
            data: { pageTitle: '我的日报' }
        })
        .state('dashboards.authority', {
            url: "/dashboards_authority",
            templateUrl: "assets/views/dashboards_authority.html",
            data: { pageTitle: '权限管理' }
        })
        .state('dashboards.schedule', {
            url: "/dashboards_schedule",
            templateUrl: "assets/views/dashboards_schedule.html",
            data: { pageTitle: '调度管理' }
        })
        .state('reports', {
            abstract: true,
            url: "/reports",
            templateUrl: "assets/views/common/content.html",
        })
        .state('reports.diningrate', {
            url: "/report_diningrate",
            templateUrl: "assets/views/report_diningrate.html",
            data: { pageTitle: '就餐率趋势图' }
        })
        .state('reports.dinertable', {
            url: "/report_dinertable",
            templateUrl: "assets/views/report_dinertable.html",
            data: { pageTitle: '就餐桌数统计' }
        })
        .state('reports.dinercount', {
            url: "/report_dinercount",
            templateUrl: "assets/views/report_dinercount.html",
            data: { pageTitle: '就餐人数统计' }
        })
        .state('reports.eatduration', {
            url: "/report_eatduration",
            templateUrl: "assets/views/report_eatduration.html",
            data: { pageTitle: '就餐时长分析' }
        })
        .state('reports.termtable', {
            url: "/report_termtable",
            templateUrl: "assets/views/report_termtable.html",
            data: { pageTitle: '翻台综合分析' }
        })
        .state('metrics', {
            abstract: true,
            url: "/metrics",
            templateUrl: "assets/views/common/content.html",
        })
        .state('metrics.queuing', {
            url: "/metrics_queuing",
            templateUrl: "assets/views/metrics_queuing.html",
            data: { pageTitle: '排队数据' }
        })
        .state('metrics.dining', {
            url: "/metrics_dining",
            templateUrl: "assets/views/metrics_dining.html",
            data: { pageTitle: '翻台数据' }
        })
        .state('metrics.tableturn', {
            url: "/metrics_tableturn",
            templateUrl: "assets/views/metrics_tableturn.html",
            data: { pageTitle: '翻台统计' }
        })
        .state('strategies', {
            abstract: true,
            url: "/strategies",
            templateUrl: "assets/views/common/content.html",
        })
        .state('strategies.storeplan', {
            url: "/strategies_storeplan",
            templateUrl: "assets/views/strategies_storeplan.html",
            data: { pageTitle: '门店规划' }
        })
        .state('strategies.tableplan', {
            url: "/strategies_storeplan",
            templateUrl: "assets/views/strategies_tableplan.html",
            data: { pageTitle: '餐桌规划' }
        })
        .state('profiles', {
            abstract: true,
            url: "/profiles",
            templateUrl: "assets/views/common/content.html",
        })
        .state('profiles.tabletype', {
            url: "/profiles_tabletype",
            templateUrl: "assets/views/profiles_tabletype.html",
            data: { pageTitle: '餐桌类型' }
        })
        .state('profiles.table', {
            url: "/profiles_table",
            templateUrl: "assets/views/profiles_table.html",
            data: { pageTitle: '餐桌设置' }
        })
        .state('topics', {
            abstract: true,
            url: "/topics",
            templateUrl: "assets/views/common/content.html",
        })
        .state('topics.cookstyle', {
            url: "/topics_cookstyle",
            templateUrl: "assets/views/topics_cookstyle.html",
            data: { pageTitle: '菜系主题' }
        })
        .state('topics.district', {
            url: "/topics_district",
            templateUrl: "assets/views/topics_district.html",
            data: { pageTitle: '商圈主题' },
        })
        .state('cockpits', {
            abstract: true,
            url: "/cockpits",
            templateUrl: "assets/views/common/content.html",
        })
        .state('cockpits.guest', {
            url: "/cockpits_guest",
            templateUrl: "assets/views/cockpits_guest.html",
            data: { pageTitle: '菜系主题' }
        })
        .state('cockpits.turnover', {
            url: "/cockpits_turnover",
            templateUrl: "assets/views/cockpits_turnover.html",
            data: { pageTitle: '商圈主题' }
        });
}
angular
    .module('inspinia')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });