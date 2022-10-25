<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" ng-app="oled">
	<head>
		<title>OLED</title>
		<meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width,initial-scale=1"/>
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>"/>
		<script>var path = '<%=request.getContextPath()%>/';</script>
	</head>
	<body>
		<%@include file="nav.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<%@include file="alerts.jsp"%>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<%@include file="preview.jsp"%>
				</div>
				<div class="col-md-8">
					<%@include file="display.jsp"%>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<%@include file="graphics.jsp"%>
				</div>
			</div>
			<%@include file="footer.jsp"%>
		</div>
		<script src="<c:url value='/assets/js/vendor/jquery.min.js'/>"></script>
		<script src="<c:url value='/assets/js/vendor/bootstrap.min.js'/>"></script>
		<script src="<c:url value='/assets/js/vendor/angular.min.js'/>"></script>
		<script src="<c:url value='/assets/js/oled.js'/>"></script>
		<script src="<c:url value='/assets/js/service/AlertService.js'/>"></script>
		<script src="<c:url value='/assets/js/service/StateService.js'/>"></script>
		<script src="<c:url value='/assets/js/service/ApiV1Service.js'/>"></script>
		<script src="<c:url value='/assets/js/service/BufferService.js'/>"></script>
		<script src="<c:url value='/assets/js/service/OledService.js'/>"></script>
		<script src="<c:url value='/assets/js/service/PreviewService.js'/>"></script>
		<script src="<c:url value='/assets/js/service/GraphicsService.js'/>"></script>
		<script src="<c:url value='/assets/js/controller/PreviewCtrl.js'/>"></script>
		<script src="<c:url value='/assets/js/controller/OledCtrl.js'/>"></script>
		<script src="<c:url value='/assets/js/controller/GraphicsCtrl.js'/>"></script>
	</body>
</html>
