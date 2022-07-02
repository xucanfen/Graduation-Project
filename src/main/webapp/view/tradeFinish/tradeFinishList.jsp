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
                                <div class="layui-input-inline layui-show-xs-block">
                                    <input type="text" name="goods_search" placeholder="请输入订单号或商品名" autocomplete="off" class="layui-input"></div>
                                <div class="layui-input-inline layui-show-xs-block">
                                    <button class="layui-btn" lay-submit="" lay-filter="sreach" onclick="javascript:this.form.action='<%=basePath%>tradeFinishController/searchInfo.do'">
                                        <i class="layui-icon">&#xe615;</i></button>
                                </div>
                            </form>
                        </div>
                       
                        <div class="layui-card-body layui-table-body layui-table-main">
                             <table class="layui-table layui-form" lay-data="{page:true,toolbar: '#toolbarDemo',id:'test'}" lay-filter="test">
                                <thead>
                                  <tr>
                                    <th lay-data="{type:'checkbox'}"></th>
                                    <th lay-data="{field:'tradeId',sort:true}">交易号</th>
                                    <th lay-data="{field:'saleId',sort:true}">订单号</th>
                                    <th lay-data="{field:'goodsId',sort:true}">商品ID</th>
                                    <th lay-data="{field:'goodsName'}">商品名</th>
                                    <th lay-data="{field:'vipId',sort:true}">会员号</th>
                                    <th lay-data="{field:'vipName'}">会员名</th>
                                    <th lay-data="{field:'unitPrice',sort:true}">单价</th>
                                    <th lay-data="{field:'discountPrice',sort:true}">折扣</th>
                                    <th lay-data="{field:'saleNumber',sort:true}">销售数量</th>
                                    <th lay-data="{field:'consumeMoney',sort:true}">实付</th>
                                    <th lay-data="{field:'time',sort:true}">下单时间</th>
                                    <th lay-data="{field:'deliType'}">配送方式</th>
                                    <th lay-data="{field:'finishType'}">订单状态</th>
                                    
                                    
                                    </tr>
                                </thead>
                                <tbody>
                                
                                <c:forEach items="${tradeFinishlist}" var="tradeFinish">
                               
                                  <tr>
                                    <td>
                                      <input type="checkbox" name="id" value="1"   lay-skin="primary"> 
                                    </td>
                                    <td>${tradeFinish.tradeId}</td>
                                    <td>${tradeFinish.saleId}</td>
                                    <td>${tradeFinish.goodsId}</td>
                                    <td>${tradeFinish.goodsName}</td>
									<td>${tradeFinish.vipId}</td>
                                    <td>${tradeFinish.vipName}</td>
                                    <td>${tradeFinish.unitPrice}</td>
                                    <td>${tradeFinish.discountPrice}</td>
									<td>${tradeFinish.saleNumber}</td>
									<td>${tradeFinish.consumeMoney}</td>
									<td> <fmt:formatDate value="${tradeFinish.time}" pattern='yyyy-MM-dd HH:mm:ss'/></td>
									<td>${tradeFinish.deliType}</td>
									<td>${tradeFinish.finishType}</td>
									 
                                   
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

		$("#checkall").click(function() {
			$("input[name='id[]']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})



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