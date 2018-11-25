var $_GET = (function(){
    var url = window.document.location.href.toString();
    var u = url.split("?");
    if(typeof(u[1]) == "string"){
        u = u[1].split("&");
        var get = {};
        for(var i in u){
            var j = u[i].split("=");
            get[j[0]] = j[1];
        }
        return get;
    } else {
        return {};
    }
})();
layui.use(['element', 'form'], function(){
  var $ = layui.jquery
  ,element = layui.element
  ,form = layui.form
  ,layer = layui.layer;

  form.on('select(type)', function(data){
    if(data.value == 1 || data.value == 3){
		$("#toAlipay").show();
		$("#toQQ").hide();
		$("#toWeixin").hide();
	}else if(data.value == 2 || data.value == 6){
		$("#toAlipay").hide();
		$("#toQQ").show();
		$("#toWeixin").hide();
	}else if(data.value == 4 || data.value == 5){
		$("#toAlipay").hide();
		$("#toQQ").hide();
		$("#toWeixin").show();
	}
  });
  $("#money").blur(function () {
    var money = parseFloat($(this).val());
    if(money<0 || isNaN(money)){
		$(this).val('');
		return;
	}
	var rate = parseFloat($('#rate').val());
	var fee = money * (100-rate) / 100;
	var realmoney = money + fee;
	$("#realmoney").html(realmoney.toFixed(2));
	$("#fee").html(fee.toFixed(2));
  });
  form.on('submit(submit1)', function(data){
    console.log(data.field);
	var type = data.field.type;
	if(type == "1" || type == "3"){
		var account = data.field.account.trim();
		var name = data.field.name.trim();
		mailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
		if(account=="" || name==""){
			layer.msg('请确保各项不能为空', {icon: 2,anim: 6,time: 1000});
			return false;
		}else if(!mailReg.test(account) && account.length!=11){
			layer.msg('支付宝账号不正确', {icon: 2,anim: 6,time: 1000});
			$("input[name='account']").focus();
			return false;
		}else if(name.length<3){
			layer.msg('支付宝姓名不正确', {icon: 2,anim: 6,time: 1000});
			$("input[name='name']").focus();
			return false;
		}
		var confirm = '支付宝：'+account+'\r\n姓名：'+name;
	}else if(type == "2" || type == "6"){
		var account = data.field.uin1.trim();
		var account2 = data.field.uin2.trim();
		if(account=="" || account2==""){
			layer.msg('请确保各项不能为空', {icon: 2,anim: 6,time: 1000});
			return false;
		}else if(isNaN(account) || account.length<5){
			layer.msg('QQ号码不正确', {icon: 2,anim: 6,time: 1000});
			$("input[name='uin1']").focus();
			return false;
		}else if(account != account2){
			layer.msg('两次输入QQ号码不一致', {icon: 2,anim: 6,time: 1000});
			$("input[name='uin2']").focus();
			return false;
		}
		var confirm = 'QQ：'+account;
	}else if(type == "4" || type == "5"){
		var account = data.field.openid.trim();
		if(account==""){
			layer.msg('请确保各项不能为空', {icon: 2,anim: 6,time: 1000});
			return false;
		}
		var confirm = '微信Openid：'+account;
	}else{
		layer.msg('请先选择换钱方式', {icon: 2,anim: 6,time: 1000});
		return false;
	}
	if(data.field.money == "" || data.field.money == 0 || isNaN(data.field.money)){
		layer.msg('换钱金额不能为空', {icon: 2,anim: 6,time: 1000});
		$("input[name='money']").focus();
		return false;
	}
	layer.confirm(confirm, {icon: 3, title:'请再次确认收款账号是否正确'}, function(index){
	  layer.close(index);
	  var ii = layer.load(2, {shade:[0.1,'#fff']});
	  $.ajax({
		type : "POST",
		url : "ajax.php?act=pay",
		data : {type:type, money:data.field.money, account:account, name:name},
		dataType : 'json',
		success : function(data) {
			layer.close(ii);
			if(data.code == 0){
				window.location.href='./submit.php?trade_no='+data.trade_no;
			}else{
				layer.alert(data.msg, {icon: 0});
			}
		},
		error:function(data){
			layer.msg('服务器错误', {icon: 2});
			return false;
		}
	});
	});
    return false;
  });
  form.on('submit(submit_query)', function(data){
    console.log(data.field);
	var content = data.field.content;
	$('#submit_query').val('Loading');
	$('#result').hide();
	$('#list').html('');
	var paytype=new Array()
	paytype["alipay"]="支付宝"
	paytype["wxpay"]="微信"
	paytype["qqpay"]="QQ钱包"
	$.ajax({
		type : "POST",
		url : "ajax.php?act=query",
		data : {account:content},
		dataType : 'json',
		success : function(data) {
			if(data.code == 0){
				if(data.data.length>0){
				var status;
				$.each(data.data, function(i, item){
					if(item.transfer_status==1)
						status='<font color="green">转账成功！</font>完成时间：'+item.transfer_date;
					else if(item.transfer_status==2)
						status='<font color="red">'+item.transfer_result+'</font>';
					else
						status='<font color="blue">支付成功但未完成转账</font>';
					$('#list').append('<div class="layui-card"><b>订单号：</b>'+item.trade_no+'&nbsp;<font color="blue">￥'+item.tmoney+'</font>&nbsp;'+paytype[item.type]+'=>'+paytype[item.totype]+'<hr/><b>结果：</b>'+status+'</div>');
				});
				$("#result").slideDown();
				}
			}else{
				layer.alert(data.msg);
			}
			$('#submit_query').val('立即查询');
		} 
	});
    return false;
  });
  form.on('submit(getopenid)', function(data){
	  $.ajax({
		type : "GET",
		url : "ajax.php?act=qrcode",
		dataType : 'json',
		success : function(data) {
			if(data.code == 0){
				$.iopen = layer.open({
				  type: 1,
				  title: '请使用微信扫描以下二维码',
				  skin: 'layui-layer-demo',
				  anim: 2,
				  shadeClose: true,
				  content: '<center><img src="http://qr.liantu.com/api.php?w=260&text='+encodeURIComponent(data.url)+'"></center>',
				  success: function(){
					$.ostart = true;
					setTimeout('layui.jquery("#openid2").click();', 2000);
				  },
				  end: function(){
					$.ostart = false;
				  }
				});
			}else{
				layer.alert(data.msg, {icon: 0});
			}
		},
		error:function(data){
			layer.msg('服务器错误', {icon: 2});
			return false;
		}
	});
    return false;
  });
  form.on('submit(openid2)', function(data){
	  $.ajax({
		type: "GET",
		dataType: "json",
		url: "ajax.php?act=getopenid",
		success: function (data, textStatus) {
			if (data.code == 0) {
				layer.msg('Openid获取成功');
				layer.close($.iopen);
				$("input[name='openid']").val(data.openid);
			}else if($.ostart==true){
				setTimeout('layui.jquery("#openid2").click();', 2000);
			}else{
				return false;
			}
		},
		error: function (data) {
			layer.msg('服务器错误', {icon: 2});
			return false;
		}
	});
    return false;
  });
  $("#myRecord").click(function () {
    $("#submit_query").click();
  });
  if($_GET['buyok']){
	$("#content").val($_GET['trade_no']);
	$("#myRecord").click();
  }
});