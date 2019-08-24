var $testTable = $('#testTable');
$testTable.bootstrapTable({
    url: 'getPers',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            name: $('#queryNameText').val(),
            age: $('#queryAgeText').val()
        }
    },
    columns: [{
        field: 'id',
        title: '编号'
    }, {
        field: 'name',
        title: '姓名'
    }, {
        field: 'age',
        title: '年龄'
    }, {
        field: 'address',
        title: '地址'
    }, {
        formatter: function (value, row, index) {
            return [
                '<a href="javascript:modifyPer(' + row.id + ",'" + row.name + "'," + row.age + ",'" + row.address + "'" + ')">' +
                    '<i class="glyphicon glyphicon-pencil"></i>修改' +
                '</a>',
                '<a href="javascript:delPer(' + row.id + ')">' +
                    '<i class="glyphicon glyphicon-remove"></i>删除' +
                '</a>'
            ].join('');
        },
        title: '操作'
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25, 50, 100],
    rowStyle: function (row, index) {
        var ageClass = '';
        if (row.age < 18) {
            ageClass = 'text-danger';
        }
        return {classes: ageClass}
    },
});

// 设置bootbox中文
bootbox.setLocale('zh_CN');

var titleTip = '提示';

// 验证输入的年龄是否为数字
function verifyAge(age) {
    var reg = /^[0-9]{0,3}$/;
    if (!reg.test(age)) {
        return false;
    }
    return true;
}

// 验证姓名和地址是否为空
function verifyNameAndAddress(name, address) {
    if (name != '' && address != '') {
        return true;
    }
    return false;
}

function nullAlert() {
    bootbox.alert({
        title: titleTip,
        message: '所有项均为必填！'
    });
}

// 点击查询按钮，年龄符合查询条件方可跳转查询
$('#queryBtn').click(function () {
    var age = $('#queryAgeText').val();
    if (verifyAge(age)) {
        // 刷新并跳转到第一页
        $testTable.bootstrapTable('selectPage', 1);
    } else {
        bootbox.alert({
           title: titleTip,
           message: '年龄输入有误！'
        });
    }
});

// 点击重置按钮，清空查询条件并跳转回第一页
$('#resetBtn').click(function() {
    $('.form-group :text').val('');
    $testTable.bootstrapTable('selectPage', 1);
});

// 用于修改服务器资源
function exchangeData(path, id, name, age, address) {
    $.ajax({
        url: path,
        type: 'post',
        data : {
            id: id,
            name: name,
            age: age,
            address: address
        },
        success: function(res) {
            bootbox.alert({
                title: titleTip,
                message: res.message
            });
            // 在每次提交操作后返回首页
            $testTable.bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function() {
    $('#addNameText').val('');
    $('#addAgeText').val('');
    $('#addAddressText').val('');
    $('#addModal').modal('show');
});

$('#saveAdd').click(function() {
    $('#addModal').modal('hide');
    bootbox.confirm({
        title: titleTip,
        message: '确认增加？',
        callback: function (flag) {
            if (flag) {
                var name = $('#addNameText').val();
                var age = $('#addAgeText').val();
                var address = $('#addAddressText').val();
                if (verifyNameAndAddress(name, address)) {
                    exchangeData('addPer', null, name, age, address);
                } else {
                    nullAlert();
                }
            }
        }
    });
});

var mid;

// 修改
function modifyPer(id, name, age, address) {
    mid = id;
    $('#modifyNameText').val(name);
    $('#modifyAgeText').val(age);
    $('#modifyAddressText').val(address);
    $('#modifyModal').modal('show');
}

$('#saveModify').click(function() {
    $('#modifyModal').modal('hide');
    bootbox.confirm({
        title: titleTip,
        message: '确认修改？',
        callback: function (flag) {
            if (flag) {
                var name = $('#modifyNameText').val();
                var age = $('#modifyAgeText').val();
                var address = $('#modifyAddressText').val();
                if (verifyNameAndAddress(name, address)) {
                    exchangeData('modifyPer', mid, name, age, address);
                } else {
                    nullAlert();
                }
            }
        }
    });
});

// 删除
function delPer(id) {
    bootbox.confirm({
        title: titleTip,
        message: '确认删除？',
        callback: function(flag) {
            if (flag) {
                exchangeData("delPer", id);
            }
        }
    });
}