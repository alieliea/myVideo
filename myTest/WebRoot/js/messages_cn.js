jQuery.extend(jQuery.validator.messages, {  
	required: "&nbsp;必填字段",  
	remote: "&nbsp;请修正该字段",  
	email: "&nbsp;请输入正确格式的电子邮件",  
	url: "&nbsp;请输入合法的网址",  
	date: "&nbsp;请输入合法的日期",  
	dateISO: "&nbsp;请输入合法的日期 (ISO).",  
	number: "&nbsp;请输入合法的数字",  
	digits: "&nbsp;只能输入整数",  
	creditcard: "&nbsp;请输入合法的信用卡号",  
	equalTo: "&nbsp;请再次输入相同的值",  
	accept: "&nbsp;请输入拥有合法后缀名的字符串",  
	maxlength: jQuery.validator.format("&nbsp;请输入一个长度最多是 {0} 的字符串"),  
	minlength: jQuery.validator.format("&nbsp;请输入一个长度最少是 {0} 的字符串"),  
	rangelength: jQuery.validator.format("&nbsp;请输入一个长度介于 {0} 和 {1} 之间的字符串"),  
	range: jQuery.validator.format("&nbsp;请输入一个介于 {0} 和 {1} 之间的值"),  
	max: jQuery.validator.format("&nbsp;请输入一个最大为 {0} 的值"),  
	min: jQuery.validator.format("&nbsp;请输入一个最小为 {0} 的值")  
});