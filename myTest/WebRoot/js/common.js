$(document).ready(function(){
	$("body").append('<div class="modal" id="infomodal"><div class="modal-dialog" style="border-radius: 4px;">'+
		        '<div class="modal-content"><div class="modal-header"><h4 class="modal-title"><i></i><span></span></h4>'+
				'</div><div class="modal-body"><p></p></div><div class="modal-footer">'+
				'<button type="button" class="btn btn-default" data-dismiss="modal">确定</button>'+
				'</div></div></div></div>');
	$("body").append('<div class="modal" style="height:100%;width:100%;text-align:center;" id="loading"><div style="margin:10% auto;">'+
	        '<img src="/myTest/img/loading.gif" style="border-radius:97px;"/></div></div>');
	$.ajaxSetup({
		beforeSend: function () {
			//ajax请求之前
			if(window === window.top){
				loadingMessage();
			}else{
				window.parent.loadingMessage();
			}
		},
		complete: function () {
			//ajax请求完成，不管成功失败
			if(window === window.top){
				loadingMessage();
			}else{
				window.parent.loadingMessage();
			}
		},
		error: function () {
			//ajax请求失败
		}
	});
});

function loadingMessage(){
	var loading = $("#loading");
	loading.modal('toggle');
}

function alertMessage(title, message, type, url, obj) {
	var infomodal = $("#infomodal");
	infomodal.find("span").html(title);
	infomodal.find("p").html(message);
	var icon = infomodal.find("i");
	var alert_bt = infomodal.find("button");
	if (type == "info") {
		icon.attr("class", "fa fa-info-circle").attr("style","color:#428bca");
		alert_bt.attr("class", "btn btn-info modal-dismiss");
	}
	if (type == "warning") {
		icon.attr("class", "fa fa-warning").attr("style","color:#ed9c28");
		alert_bt.attr("class", "btn btn-warning modal-dismiss");
	}
	if (type == "success") {
		icon.attr("class", "fa fa-check").attr("style","color:#47a447");
		alert_bt.attr("class", "btn btn-success modal-dismiss");
	}
	if (type == "danger") {
		icon.attr("class", "fa fa-times-circle").attr("style","color:#d2322d");
		alert_bt.attr("class", "btn btn-danger modal-dismiss");
	}
	if(obj != null){
		alert_bt.attr("onclick", obj);
	}else if (url != null && url != ''){
		alert_bt.attr("onclick", "window.location.href='" + url + "'");
	}
	infomodal.modal('show');
}

function refreshIframe(){
	var iframe = $(".tab-content").find(".active").find("iframe");
	iframe.attr('src',iframe.attr('src'));
}