<%@ page language="java" import="java.util.*, java.text.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<style>
body {
	padding-bottom: 70px;
}
</style>
<body>
	<script>
		function printTime() {
			var d = new Date();
			var yyyy = d.getFullYear(), mm = d.getMonth() + 1, dd = d.getDate(), day = d
					.getDay();
			var h = d.getHours(), m = d.getMinutes(), s = d.getSeconds();

			if (mm < 10)
				mm = "0" + mm;
			if (dd < 10)
				dd = "0" + dd;
			if (h < 10)
				h = "0" + h;
			if (m < 10)
				m = "0" + m;
			if (s < 10)
				s = "0" + s;

			if (day == 1)
				day = "星期一";
			else if (day == 2)
				day = "星期二";
			else if (day == 3)
				day = "星期三";
			else if (day == 4)
				day = "星期四";
			else if (day == 5)
				day = "星期五";
			else if (day == 6)
				day = "星期六";
			else
				day = "星期日";

			document.getElementById("dt").innerHTML = yyyy + "/" + mm + "/"
					+ dd + "<br>" + h + ":" + m + ":" + s + " " + day;
		}
		window.setInterval("printTime();", 1000);
	</script>
	<nav class="navbar navbar-default navbar-fixed-bottom" style="background: #E0EAFC">
	<p id="dt" style="position:absolute; top:5px; right:5%"></p>
	<p align="center" style="margin-top:12px; font-size: 16px">beta1.0
		by : ff && rsl</p>
	</nav>
</body>
</html>