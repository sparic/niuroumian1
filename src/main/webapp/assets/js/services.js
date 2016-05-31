/**
 * Created by Martin on 2015/7/29.
 */
function constServiceCtor($resource) {
    this.tableOptions = {
        "info": true,
        "searching": false,
        "lengthChange": false,
        "pagingType": "simple_numbers",
        "pageLength": 20,
        "hasBootstrap": false,
        "serverSide": true,
        "deferLoading": 2,
        "processing": true,
        "language": {
            "sInfo": "显示 _START_ - _END_条，共 _TOTAL_ 条",
            "sInfoEmpty": "显示 0 - 0条，共 0 条",
            "sEmptyTable": "没有查找到数据",
            "sLoadingRecords": "加载中...",
            "sProcessing": "处理中...",
            "sZeroRecords": "没找到匹配的记录",
            "oPaginate": {
                "sFirst": "第一页",
                "sLast": "最后页",
                "sNext": "下一页>",
                "sPrevious": "<上一页"
            },
            "oAria": {
                "sSortAscending": ": 升序",
                "sSortDescending": ": 降序"
            }
        }
    };
    this.currentUser = $resource('./api/queryUserInfo').get();
    this.tableTypeList = $resource('./api/tableTypeResult').query();
    this.yesterday = function () {
        var yesterday = new Date();
        yesterday.setDate(yesterday.getDate() - 1);
        return yesterday;
    };
    this.defaultWhere = function () {
        var yesterday = this.yesterday();
        return {
            queueDate: yesterday,
            beginDate: yesterday,
            endDate: yesterday,
            before1: true,
            before2: true,
            before7: true,
            before30: true
        };
    };
}

angular
    .module('inspinia')
    .service('ConstService', constServiceCtor);