var linkage = true;
var timer = null;
var url = '';
function clickF(obj){
	clearTimeout(timer);
	var obj = $(obj);
	if (!obj.hasClass('dd-handle')) {
		var check = obj.parent().parent().find(":checkbox");
		var flag = check.attr("checked") == "checked";
		if (flag) {
			check.removeAttr("checked");
		} else {
			check.attr("checked", "checked");
		}
	}
	timer = setTimeout(function (){
		if (obj.hasClass('dd-handle')) {
			setParents(obj);
		} else {
			setParents(obj.parent().parent());
		}
	},180);
}
function dbClickF(obj){
	clearTimeout(timer);
	var obj = $(obj);
	if(obj.prev().attr("style")=="display: none;"){
		obj.prev().prev().click();
	}else{
		obj.prev().click();
	}
}

$(function() {
	$('#nestable').nestable({group: 1});
    $('.dd').nestable('collapseAll');
	$(".dd").on("click",".dd-handle,:checkbox",function() {
		clickF(this);
	});
	$(".dd").on("dblclick",".dd-handle",function() {
		dbClickF(this);
	});
	$('#nestable-menu button').on('click', function(e){
		var target = $(this),
		action = target.attr('data-action');
        if (action == 'expand-all') {
            $('.dd').nestable('expandAll');
            target.attr("data-action","collapse-all");
            target.html("全部收起");
        }else{
            $('.dd').nestable('collapseAll');
            target.attr("data-action","expand-all");
            target.html("全部展开");
        }
    });
});

function setParents(obj) {
	var check = obj.find(":checkbox");
	var flag = check.attr("checked") == "checked";
	if (flag) {
		check.removeAttr("checked");
	} else {
		check.attr("checked", "checked");
	}
	if(linkage){
		var checkbox = obj.siblings('.dd-list').find(":checkbox");
		if (checkbox != null) {
			if (!flag) {
				checkbox.attr("checked", "checked");
			} else {
				checkbox.removeAttr("checked");
			}
		}
		var parent = obj.parent();
		var fatherF = parent.parents('.dd-list');
		var father = parent.siblings();
		if (father.find(":checked").attr("checked") == "checked") {
			flag = false;
		}
		for (var i = 0; i < fatherF.length; i++) {
			var childs = fatherF.eq(i).children();
			if (childs.find(":checked").attr("checked") == "checked") {
				flag = false;
			}
			if (!flag) {
				fatherF.eq(i).prev().find(":checkbox").attr("checked", "checked");
			} else {
				fatherF.eq(i).prev().find(":checkbox").removeAttr("checked");
			}
		}
	}
}



function childMenu(obj){
	var li = $(obj).parent("li");
	var id = li.attr("data-id").replace("item","");
	if(!li.find("ol").length){
		$.ajax({
			url:url,
			data:{id:id},
			success:function(data){
				var content = '<ol class="dd-list">';
				$.each(data.menuList, function (i, item) {
					var style = "";
					content += '<li class="dd-item" id="item'+item.id+'" data-id="item'+item.id+'">';
					if(item.is_child==1){
						content += '<button data-action="expand" type="button" style="display: block;" onclick="'+
						'childMenu(this)">Expand</button>';
						content += '<button data-action="collapse" type="button" style="display: none;">Collapse</button>';
						style = 'style="margin-left: 15px;"';
					}
					content += '<div class="dd-handle"><div id="'+item.e_name+'" class="checkbox-custom" '+style+'>'+
					  '<input type="checkbox" id="'+item.id+'"/><label for="'+item.e_name+'">'+item.name+'</label></div></div>';
				});
				content += '</ol>';
				li.append(content);
				li.children("ol").show();
			}
		});
	}
}