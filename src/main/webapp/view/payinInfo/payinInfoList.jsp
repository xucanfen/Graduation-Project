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
                            <form class="layui-form layui-col-space5">
                                 <button type="button" class="layui-btn"><i class="layui-icon"></i>请输入想搜索的内容</button>
                                 
                                <div class="layui-inline layui-show-xs-block" style="margin-left:30px">
                                    <input type="text" name="payin_search"  placeholder="请输入账单号" autocomplete="off" class="layui-input" >
                                </div>
                               
                                <div class="layui-inline layui-show-xs-block">
                                
                                 <button class="layui-btn"  lay-submit="" onclick="javascript:this.form.action='<%=basePath%>payinInfoController/searchPayin.do';"  lay-filter="sreach" >
                                 <i class="layui-icon">&#xe615;</i></button>
                                </div>
                            </form>
                        </div>
                        <div class="layui-card-header">
                            <button class="layui-btn layui-btn-danger"  onclick="javascript:this.form.action='<%=basePath%>payinInfoController/batchDelete.do';"><i class="layui-icon"></i>批量删除</button>
                            <button class="layui-btn" onclick="xadmin.open('添加用户','<%=basePath%>payinInfoController/addUI.do',500,650)"><i class="layui-icon"></i>添加</button>
                        </div>
                        <div class="layui-card-body layui-table-body layui-table-main">
                            <table class="layui-table layui-form">
                                <thead>
                                  <tr>
                                    <th>
                                      <input type="checkbox" lay-filter="checkall" name="" lay-skin="primary">
                                    </th>
                                    <th>ID</th>
                                    <th>交易号</th>
                                    <th>交易时间</th>
                                    <th>收入金额</th>
                                    <th>收入来源</th>
                                   
                                    <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${payinInfoList }" var="payinInfo">
                                  <tr>
                                    <td>
                                      <input type="checkbox" name="Id" value="${payinInfo.id }"   lay-skin="primary"> 
                                    </td>
                                    <td>${payinInfo.id }</td>
                                    <td>${payinInfo.accountNumber }</td>
                                   <td><fmt:formatDate value="${payinInfo.time }"
							          pattern="yyyy-MM-dd HH:mm:ss" />
                                    <td>${payinInfo.payinMoney }</td>
                                    <td>${payinInfo.origin }</td>
							    
                                      <td class="td-manage">
                                      <a title="编辑"  onclick="xadmin.open('编辑','<%=basePath%>payinInfoController/updateUI.do?id=${payinInfo.id}',500,650)" href="javascript:;">
                                        <i class="layui-icon layui-icon-form" style="margin-right:40px ;font-size: 20px"></i>
                                      </a>
                                      <a onclick="xadmin.open('待用角色','<%=basePath%>roleController/FPRoleUI.do?userId=${user.userId }',600,400)" title="待用功能" href="javascript:;">
                                        <i class="layui-icon" style="margin-right:40px ;font-size: 20px">&#xe631;</i>
                                      </a>
                                      <a title="删除"  href="<%=basePath%>payinInfoController/deletePayinInfo.do?id=${payinInfo.id}"  onclick="return del(1)">
                                        <i class="layui-icon"  style="font-size: 23px">&#xe640;</i>
                                      </a>
                                      </td>
                                
                                  </tr>
                                 </c:forEach>
                               
                                
                                  
                                </tbody>
                            </table>
                        </div>
                        <div class="layui-card-body ">
                            <div class="page">
                                <div>
                                  <a class="prev" href="">&lt;&lt;</a>
                                  <a class="num" href="">1</a>
                                  <span class="current">2</span>
                                  <a class="num" href="">3</a>
                                  <a class="num" href="">489</a>
                                  <a class="next" href="">&gt;&gt;</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
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
      
     
    		
      
      /*批量删除*/
      function delSelectPayin(id){
  		
  		if(id==undefined)
  		{
  		  var len = 0;
  		  var cks=$("input[name='Id']");
  		  console.log(cks);
  		  for(var i=0;i<cks.length;i++)
  		  {
  		    if(cks[i].checked)
  		    {
  		      ++len;
  		    }
  		  }
  		  console.log(len);
  		  if(len==0)
  		  {
  		    layer.alert("请选择要删除的信息！");
  		    return;
  		  }
  		  var ids=[];
  		  for(var i=0;i<cks.length;i++)
  		  {
  		    if(cks[i].checked)
  		    {
  		      var id=$(cks[i]).val();
  		      ids.push(id);
  		    }
  		    
  		  }
  		  var delId=ids.join(",");
  		  layer.confirm("你确定删除选中的信息吗?", function() 
  		  {
  				window.location.href = "${basePath}/payinInfoController/batchDelete.do?payinId="
  						+ delId;
  			});
  		}
  	}
  	 
  
    </script>
</html>