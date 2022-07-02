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
        <title>商品销售信息管理系统</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
        <script src="./lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="./js/xadmin.js"></script>
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">演示</a>
            <a>
              <cite>导航元素</cite></a>
          </span>
          <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
        </div>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <form class="layui-form layui-col-space5" >
                                 <button type="button" class="layui-btn layui-btn-warm"><i class="layui-icon"></i>统计员工考勤记录</button>
                                 
                                <div class="layui-inline layui-show-xs-block" style="margin-left:40px">
                                    <input type="text" name="salesman_id"  placeholder="请输入销售员工号" autocomplete="off" class="layui-input" >
                                </div>
                                
                                
                                <div class="layui-inline layui-show-xs-block" >
                                       <div class="layui-input-inline">
                            <input type="text" id="time1" name="year_month" required="" lay-verify="login_name2" autocomplete="off" class="layui-input" placeholder="请输入月份"></div>    
                                </div>
     
                                <div class="layui-inline layui-show-xs-block" >
                                 <button class="layui-btn layui-btn-warm"  lay-submit="" onclick="javascript:this.form.action='<%=basePath%>statisticsInfoController/statisticsWork.do';"  lay-filter="sreach" id="btn">
                                 <i class="layui-icon">统计</i></button>
                                </div>
                            </form>
                            
                             <form class="layui-form layui-col-space5" style="margin-top: 20px">
                                 <button type="button" class="layui-btn "><i class="layui-icon"></i>请输入搜索的条件</button>
                                 
                                <div class="layui-inline layui-show-xs-block" style="margin-left:40px">
                                    <input type="text" name="salesman_id2"  placeholder="请输入销售员工号" autocomplete="off" class="layui-input" >
                                </div>
                                 
                                
                                <div class="layui-inline layui-show-xs-block" >
                                       <div class="layui-input-inline">
                            <input type="text" id="time2" name="year_time" required="" lay-verify="login_name2" autocomplete="off" class="layui-input" placeholder="请输入年月范围"></div>    
                                </div>
     
                                <div class="layui-inline layui-show-xs-block">
                                 <button class="layui-btn"  lay-submit="" onclick="javascript:this.form.action='<%=basePath%>statisticsInfoController/searchInfo.do';"  lay-filter="sreach" id="btn">
                                 <i class="layui-icon">搜索</i></button>
                                </div>
                            </form>
                            
                             
                        </div>
                       
                        <div class="layui-card-body layui-table-body layui-table-main" id="show" >
                            <table class="layui-table layui-form" lay-data="{page:true,toolbar: '#toolbarDemo',id:'test' ,totalRow: true}" lay-filter="test">
                                <thead>
                                  <tr>
                                   
                                    <th lay-data="{type:'checkbox' }"></th>
                                    <th lay-data="{field:'id2',sort:true ,totalRowText: '合计'}">ID</th>
                                    <th lay-data="{field:'salemanId',sort:true}">销售员工号</th>
                                    <th lay-data="{field:'username',sort:true}">销售员姓名</th>
                                    <th lay-data="{field:'time',sort:true}">统计时间</th>
                                    <th lay-data="{field:'totalWork',sort:true ,totalRow: true}">出勤数</th>
                                    <th lay-data="{field:'totalOver',sort:true ,totalRow: true}">加班数</th>
                                    <th lay-data="{field:'totalOff',sort:true ,totalRow: true}">请假数</th>
                                    <th lay-data="{field:'attendance',sort:true}">出勤率</th>
                                    
                                    
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${statisticsInfoList }" var="statisticsInfo">
                                  <tr>
                                  	<td>
                                      <input type="checkbox" name="id" value="1"   lay-skin="primary"> 
                                    </td>
                                    <td>${statisticsInfo.id}</td>
                                    <td>${statisticsInfo.salesmanId }</td>
                                    <td>${statisticsInfo.salesmanName }</td>
                                   <td><fmt:formatDate value="${statisticsInfo.statisticTime }"
							          pattern="yyyy-MM" />
                                    <td>${statisticsInfo.totalWork }</td>
                                    <td>${statisticsInfo.totalOver }</td>
                                    <td>${statisticsInfo.totalOff }</td>
                                    <td>${statisticsInfo.attendance }</td>   
							      </tr>
                               
                                </c:forEach>
                                
                                  
                                </tbody>
                            </table>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div> 
    </body>
    <script type="text/html" id="toolbarDemo">
        <div class = "layui-btn-container" > 
            <button class = "layui-btn layui-btn-sm" onclick="xadmin.open('修改会员信息','<%=basePath%>memberInfoController/updateUI.do?vipId=${member.vipId}',500,650)" href="javascript:;" > 修改选中行数据 </button>
            <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button > 
            <button class = "layui-btn layui-btn-sm" lay-event = "isAll" > 验证是否全选</button>
        </div > 
    </script>
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

        laydate.render({
        	  elem: '#time1'
        	  ,type: 'month'
        	  ,theme: '#FFB90F'
        	});
        
        laydate.render({
        	  elem: '#time2'
        	  ,type: 'month'
        	  ,range: true
        	});
      });

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要停用吗？',function(index){

              if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

              }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
              }
              
          });
      }

      /*用户-删除*/
      function del(id) {
			if (confirm("您确定要删除吗?")) {
				 return true;
		    }else{
		        return false;

			}
		}

		 
      function delAll (argument) {
        var ids = [];

        // 获取选中的id 
        $('tbody input').each(function(index, el) {
            if($(this).prop('checked')){
               ids.push($(this).val())
            }
        });
  
        layer.confirm('确认要删除吗？'+ids.toString(),function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
    <script>layui.use('table',
        function() {
            var table = layui.table;

            //监听单元格编辑
            table.on('edit(test)',
            function(obj) {
                var value = obj.value //得到修改后的值
                ,
                data = obj.data //得到所在行所有键值
                ,
                field = obj.field; //得到字段
                layer.msg('[ID: ' + data.id + '] ' + field + ' 字段更改为：' + value);
            });

            //头工具栏事件
            table.on('toolbar(test)',
            function(obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选': '未全选');
                    break;
                };
            });
        });</script>
</html>