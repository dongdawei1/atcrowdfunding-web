<%@page pageEncoding="UTF-8"%>
<!-- 登陆页 -->
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">店主科技-让开店更便捷</a></div>
        </div>
      </div>
    </nav>

    <div class="container">
     
       <!--param.errorMsg  获取地址栏中的错误信息  -->
      <!-- action="doLogin"  向后台提交   //$("#loginForm").submit(); -->
      <form id="loginForm" action="doLogin" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="loginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="userpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select class="form-control" >
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="reg.html">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
     <h4 style="color:red">${param.errorMsg}</h4>
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="layer/layer.js"></script><!-- 提示框插件 -->
    <script>
    function dologin() {
        // 非空校验
        var loginacct = $("#loginacct").val();
        // 表单元素的value取值不会为null, 取值是空字符串
        if ( loginacct == "" ) {
        	//alert("用户登录账号不能为空，请输入");
            layer.msg("用户登录账号不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
            	//提示框插件 对下调用 function中可以有其他方法 如alter()方法
            	//time:2000,关闭时间 icon:5图标样式, shift:6 抖动或动画样式
            });
        	return;
        }
        
        var userpswd = $("#userpswd").val();
        if ( userpswd == "" ) {
        	//alert("用户登录密码不能为空，请输入");
            layer.msg("用户登录密码不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
            	
            });
        	return;
        }
        
        // 提交表单
        //alert("提交表单");
      // $("#loginForm").submit();
        // 使用AJAX提交数据
        var loadingIndex = null; //转圈效果，有了返回结果就关闭
        $.ajax({
        	type : "POST",//提交数据方式
        	url  : "doAJAXLogin",//提交的地址
        	data : {
        		"loginacct" : loginacct,
        		"userpswd"  : userpswd
        	},//数据"loginacct"名 : loginacct值,
        	beforeSend : function(){
        		//等待动画
        		loadingIndex = layer.msg('处理中', {icon: 16});
        	},
        	success : function(result) {//result控制器中返回的对象
        		layer.close(loadingIndex);//关闭动画
        		if (result.success) {  //如果是true跳转main页面
        			window.location.href = "main"; //
        		} else {
                    layer.msg("用户登录账号或密码不正确，请重新输入", {time:2000, icon:5, shift:6}, function(){
                    	
                    });
        		}
        	}
        });
    }
    </script>
  </body>
</html>