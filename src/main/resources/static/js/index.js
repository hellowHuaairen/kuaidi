var $testTable = $('#testTable');
$testTable.bootstrapTable({
    url: 'getList',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            userName: $('#queryNameText').val(),
            phone: $('#queryPhoneText').val()
        }
    },
    columns: [{
        field: 'id',
        title: '编号'
    }, {
        field: 'userName',
        title: '收件人姓名'
    }, {
        field: 'phone',
        title: '收件人电话'
    }, {
        field: 'kuaidiNo',
        title: '快递单号',
		formatter: function (value, row, index) {
             return [
                 '<a href="https://q.kuaidi100.cn/auto.php?no='+ row.kuaidiNo +'">'+ row.kuaidiNo + '</a>'
             ].join('');
         },
    }
    // ,
    //     {
    //     formatter: function (value, row, index) {
    //         return [
    //             '<a href="javascript:modifyKuaiDi(' + row.id + ",'" + row.userName + "'," + row.phone + ",'" + row.kuaidiNo + "'" + ')">' +
    //                 '<i class="glyphicon glyphicon-pencil"></i>修改' +
    //             '</a>',
    //             '<a href="javascript:delKuaiDi(' + row.id + ')">' +
    //                 '<i class="glyphicon glyphicon-remove"></i>删除' +
    //             '</a>'
    //         ].join('');
    //     },
    //     title: '操作'
    // }
    ],
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
    // var age = $('#queryAgeText').val();
    // if (verifyAge(age)) {
        // 刷新并跳转到第一页
    $testTable.bootstrapTable('selectPage', 1);
    // } else {
    //     bootbox.alert({
    //        title: titleTip,
    //        message: '年龄输入有误！'
    //     });
    // }
});

// 点击重置按钮，清空查询条件并跳转回第一页
$('#resetBtn').click(function() {
    $('.form-group :text').val('');
    $testTable.bootstrapTable('selectPage', 1);
});

// 用于修改服务器资源
function exchangeData(path, id, userName, phone, kuaiDiNo) {
    $.ajax({
        url: path,
        type: 'post',
        data : {
            id: id,
            userName: userName,
            phone: phone,
            kuaiDiNo: kuaiDiNo
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
    $('#addPhoneText').val('');
    $('#addKuaiDiNoText').val('');
    $('#addModal').modal('show');
});

$('#saveAdd').click(function() {
    $('#addModal').modal('hide');
    bootbox.confirm({
        title: titleTip,
        message: '确认增加？',
        callback: function (flag) {
            if (flag) {
                var userName = $('#addNameText').val();
                var phone = $('#addPhoneText').val();
                var kuaiDiNo = $('#addKuaiDiNoText').val();
                if (verifyNameAndAddress(userName, kuaiDiNo)) {
                    exchangeData('addKuaiDi', null, userName, phone, kuaiDiNo);
                } else {
                    nullAlert();
                }
            }
        }
    });
});

var mid;

// 修改
function modifyKuaiDi(id, name, age, address) {
    mid = id;
    $('#modifyNameText').val(name);
    $('#modifyPhoneText').val(age);
    $('#modifyKuaiDiNoText').val(address);
    $('#modifyModal').modal('show');
}

$('#saveModify').click(function() {
    $('#modifyModal').modal('hide');
    bootbox.confirm({
        title: titleTip,
        message: '确认修改？',
        callback: function (flag) {
            if (flag) {
                var userName = $('#modifyNameText').val();
                var phone = $('#modifyPhoneText').val();
                var kuaiDiNo = $('#modifyKuaiDiNoText').val();
                if (verifyNameAndAddress(userName, phone)) {
                    exchangeData('modifyKuaiDi', mid, userName, phone, kuaiDiNo);
                } else {
                    nullAlert();
                }
            }
        }
    });
});

// 删除
function delKuaiDi(id) {
    bootbox.confirm({
        title: titleTip,
        message: '确认删除？',
        callback: function(flag) {
            if (flag) {
                exchangeData("delKuaiDi", id);
            }
        }
    });
}