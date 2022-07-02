<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
          <base href="<%=basePath%>" />
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.2</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
        <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="./js/xadmin.js"></script>
         <script type="text/javascript" src="./js/jquery.min.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
      
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row">
                <form class="layui-form" action="<%=basePath%>memberInfoController/update.do">
                    
                       
                    <div class="layui-form-item">
                        <label for="login_name1" class="layui-form-label">
                            <span class="x-red"></span>姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" id="login_name1" name="vip_name" value="${member.vipName }" required="" lay-verify="login_name1" autocomplete="off" class="layui-input"></div>
                    </div>
                    
                    
                     <div class="layui-form-item">
                        <label for="login_name2" class="layui-form-label">
                            <span class="x-red"></span>电话号码</label>
                        <div class="layui-input-inline">
                            <input type="text" id="login_name2" name="vip_tel" value="${member.vipTel }" required="" lay-verify="login_name2" autocomplete="off" class="layui-input" placeholder="yyyy-MM-dd HH:mm:ss"></div>
                    </div>
                     
                     <div class="layui-form-item">
                        <label for="name" class="layui-form-label">
                            <span class="x-red">*</span>地址</label>
                        <div class="layui-input-inline">
                            <input type="text" id="name" name="vip_address"  value="${member.vipAddress }" required="" lay-verify="name" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        
                         <div class="layui-inline layui-show-xs-block" style="margin-bottom:15px">
                        <label for="login_name3" class="layui-form-label">
                            <span class="x-red"></span>生日</label>
                            <input class="layui-input"  autocomplete="off" placeholder="请输入生日" name="vip_birthday" value="<fmt:formatDate value='${member.vipBirthday}' type='date' pattern='yyyy-MM-dd'/>" id="start" style="width:190px">
                        </div>
                          
                    
                    
                    <div class="layui-form-item">
                        <label for="repassword1" class="layui-form-label"></label>
                        <button class="layui-btn" lay-filter="add" lay-submit="" >增加</button></div>
                </form>
            </div>
        </div>
        <script>
        layui.use(['laydate','form'], function(){
            var laydate = layui.laydate;
            var  form = layui.form;


            // 监听全选
            form.on('checkbox(checkall)', function(data){

              if(data.elem.checked){
                $('tbody input').prop('checked',true);
              }else{
                $('tbody input').prop('checked',false);
              }
              form.render('checkbox');
            }); 
            
            //执行一个laydate实例
            laydate.render({
              elem: '#start' //指定元素
            });

            //执行一个laydate实例
            laydate.render({
              elem: '#end' //指定元素
            });


          });
        
        layui.use(['form', 'layer','jquery'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;
                var laydate = layui.laydate;
                //自定义验证规则
                form.verify({
                	login_name: function(value) {
                        if (value.length < 0) {
                            return '昵称至少得5个字符啊';
                        }
                    },
                    password: [/(.+){6,12}$/, '密码必须6到12位'],
                    repassword: function(value) {
                        if ($('#password').val() != $('#repassword').val()) {
                            return '两次密码不一致';
                        }
                    }
                });

             
                //监听提交
                form.on('submit(add)',
                function(data) {
                    console.log(data);
                    //发异步，把数据提交给php
                   // layer.alert("增加成功", {
                   //     icon: 6
                   // },
                    //function() {
                      
                   // });
                    parent.parent.location.reload();
                    //关闭当前frame
                    xadmin.close();
                    // 可以对父窗口进行刷新 
                    //xadmin.father_reload();
                    //return false;
                });
                 
            });
        setTimeout(function(){
        	var id = document.getElementById('login_name');
            id.value='';
            var password = document.getElementById('password');
            password.value='';
        },506)
        
        </script>
        <script>var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();</script>
    </body>

</html>