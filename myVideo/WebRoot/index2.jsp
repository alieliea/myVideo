<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>${name }</title>
<meta name="format-detection" content="telephone=yes">
</head>
<body>
	<video id="video" width="60%" height="90%" controls="controls">
		<source src="/myVideo/watch${path }" type="video/mp4">
		Your browser does not support the video tag.
	</video>
	<input type="text" id="show" value="">
	<button onclick="getTime(1);">获取当前时间</button>
	<button onclick="getTime(2);">设置当前时间</button>
	<button onclick="history.go(-1);">返回</button>

	<script type="text/javascript">
		var show = document.getElementById("show");
		//假设"video" 是获取的视频节点对象
		var i = setInterval(function() {
			// 这里注意, 必须判断视频的 readyState。
			// 因为有可能没加载完，则获取到的视频时长信息是不正确的。
			if (video.readyState > 0) {
				// 计算,10进制，以及取模
				var minutes = parseInt(video.duration / 60, 10);
				var seconds = parseInt(video.duration % 60);
				show.value = minutes + ":" + seconds;
				// (此处可以添加代码，将分钟，秒数显示到需要的地方)
				// ...
				// 执行到这里，就将循环定时器清除。
				clearInterval(i);
			}
		}, 200);

		function getTime(type) {
			if (type == 1) {
				show.value = video.currentTime;
			} else {
				video.currentTime = show.value;
			}
		}
	</script>
</body>
</html>