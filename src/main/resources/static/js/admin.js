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
    },  {
        field: 'company',
        title: '快递公司'
    },{
        field: 'kuaidiNo',
        title: '快递单号',
        formatter: function (value, row, index) {
            return [
				'<a onclick="kuaidiRecordInfo(' + "'" + row.kuaidiNo + "','" + row.company + "')" + '">' + row.kuaidiNo +'</a>',
            ].join('');
        },
    }, {
        formatter: function (value, row, index) {
            return [
                '<a href="javascript:modifyKuaiDi(' + row.id + ",'" + row.userName + "'," + row.phone + ",'" + row.kuaidiNo + "'" + ')">' +
                    '<i class="glyphicon glyphicon-pencil"></i>修改' +
                '</a>',
                '<a href="javascript:delKuaiDi(' + row.id + ')">' +
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

function kuaidiRecordInfo(no, company) {
    $('#viewModal').modal('show');
    $.ajax({
        type:'get',
        url:'getKuaiDiInfoByJson?com='+ company +'&no=' + no,
        cache:false,
        dataType:'json',
        success:function(result){
            // 显示详细信息 发送请求通过单号
			$("#viewDataList").empty();
            console.log(result.data);
            var dataList = result.data;
            if(null != dataList){
				$("#viewDataList").append('<li class="accordion-navigation"><a href="#kuaidi'+ '">快递单号：'+ result.nu +'</a><div id="kuaidi'+ '" class="content"></div></li>');
				$("#kuaidi").append('<section class="result-box"><div id="resultTop" class="flex result-top"><time class="up">时间</time><span>地点和跟踪进度</span></div><ul id="reordList'+'" class="result-list sortup"></ul></section>');  
                for(var i=0;i<dataList.length; i++){
                    var kuaiRecodList = dataList[i];
                    if( i == 0){
                        $("#reordList").append('<li class="last finish"><div class="time"> '+ kuaiRecodList.ftime + '</div><div class="dot"></div><div class="text"> '+ kuaiRecodList.context +'</div></li>');
                    }else{
                        $("#reordList").append('<li class=""><div class="time"> '+ kuaiRecodList.ftime + '</div><div class="dot"></div><div class="text"> '+ kuaiRecodList.context +'</div></li>');
                    }
                }
            }

        }
    });

}
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
function exchangeData(path, id, userName, phone, kuaiDiNo, company) {
    $.ajax({
        url: path,
        type: 'post',
        data : {
            id: id,
            userName: userName,
            phone: phone,
            kuaiDiNo: kuaiDiNo,
            company: company
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
	$('#addCompanyText').val('');
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
                var company = $('#addCompanyText').val();
                if (verifyNameAndAddress(userName, kuaiDiNo)) {
                    exchangeData('addKuaiDi', null, userName, phone, kuaiDiNo, company);
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
	$('#modifyCompanyText').val(address);
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
                var company = $('#modifyCompanyText').val();
                if (verifyNameAndAddress(userName, phone)) {
                    exchangeData('modifyKuaiDi', mid, userName, phone, kuaiDiNo, company);
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