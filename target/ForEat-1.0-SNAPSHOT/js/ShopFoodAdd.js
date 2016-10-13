//餐品logo
var foodLogo;
//异步提交带有文件的二进制数据
$('#foodLogoUpload').submit(function () {
    var formData = new FormData($("#foodLogoUpload")[0]);
    $.ajax({
        type: 'post',
        url: $('#foodLogoUpload').attr('action'),
        dataType: 'json',
        data: formData,
        //要想用jquery的ajax来提交FormData数据,
        //则必须要把这两项设为false
        processData: false,
        contentType: false,
        //这里是防表单重复提交,可以忽略
        beforeSend: function () {
            $('#foodLogoUpload:submit').attr("disabled","disabled");
        },
        complete: function () {
            $('#foodLogoUpload:submit').removeAttr("disabled");
        },
        success: function (sJson) {
            foodLogo = sJson.filePath;
            $('#imgLogo').attr("src",foodLogo);

            // 存在跨域问题
            // $('#foodLogo').val(sJson.filePath);
        }
    });
    //避免默认结果返回
    return false;
});