$.fn.pagination = function (options) {
    obj = $(this);
    pagination.options = $.extend({
    	page : 1,					//当前页
    	size : 10,					//每页大小
    	total : 1,					//总页数
    	rows : 10,					//数据总数
    	url : '',					//查询链接
        callback: function () { 	//关闭后回调函数
        }
    }, options || {});
}

window.pagination = {
	options:{},
	init : function (opts){
		var obj = $(".pagination");
		var li = "";
		var start = 1;
		var end = 4;
		if(opts.page == 1){
			li += '<li class="disabled"><a><i class="fa fa-angle-double-left"></i></a></li>';
			li += '<li class="disabled"><a><i class="fa fa-angle-left"></i></a></li>';
		}else{
			li += '<li><a href="'+opts.url+'/1"><i class="fa fa-angle-double-left"></i></a></li>';
			li += '<li><a href="'+opts.url+'/'+(parseInt(opts.page)-1)+'"><i class="fa fa-angle-left"></i></a></li>';
		}
		if(opts.total>5 && opts.page!=1){
			li += '<li><a>...</a></li>';
		}
		if(opts.total>5){
			if((opts.total-opts.page)<4){
				start = opts.total-4;
			}else{
				start = opts.page;
			}
			end = parseInt(start) + 4;
		}else{
			end = opts.total;
		}
		for(var i=start;i<=end;i++){
			if(opts.page==i){
				li += '<li class="disabled active"><a>'+i+'</a></li>';
			}else{
				li += '<li><a href="'+opts.url+'/'+i+'">'+i+'</a></li>';
			}
		}
		if(opts.total>5 && (opts.total-opts.page)>4){
			li += '<li><a>...</a></li>';
		}
		if(opts.page == opts.total){
			li += '<li class="disabled"><a><i class="fa fa-angle-right"></i></a></li>';
			li += '<li class="disabled"><a><i class="fa fa-angle-double-right"></i></a></li>';
		}else{
			li += '<li><a href="'+opts.url+'/'+(parseInt(opts.page)+1)+'"><i class="fa fa-angle-right"></i></a></li>';
			li += '<li><a href="'+opts.url+'/'+opts.total+'"><i class="fa fa-angle-double-right"></i></a></li>';
		}
		obj.append(li);
	}
}