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
				<div class="col-md-5">
					<div class="panel panel-default" ng-controller="PreviewCtrl">
						<div class="panel-heading">
							<div class="panel-title">
								<div class="pull-right">
									<button id="input-pixel-on" class="btn btn-default btn-xs active" data-toggle="button"><i class="glyphicon glyphicon-pencil"></i></button>
								</div>
								Preview
							</div>
						</div>
						<div class="panel-body">
							<div class="text-center"><canvas id="canvas" height="0" width="0" style="background:#000;" ng-mousedown="setPixel($event)" ng-mousemove="setPixel($event)" ng-mouseup="setBuffer($event)"></canvas></div>
						</div>
						<div class="panel-footer">
							x,y
						</div>
					</div>
				</div>
				<div class="col-md-7">
					<%@include file="panel-display.jsp"%>
					<%@include file="panel-graphics.jsp"%>
				</div>
			</div>
			<%@include file="footer.jsp"%>
		</div>
		<script src="<c:url value='/assets/js/vendor/jquery.min.js'/>"></script>
		<script src="<c:url value='/assets/js/vendor/bootstrap.min.js'/>"></script>
		<script src="<c:url value='/assets/js/vendor/angular.min.js'/>"></script>
		<script src="<c:url value='/assets/js/oled.js'/>"></script>
		<script src="<c:url value='/assets/js/StateService.js'/>"></script>
		<script src="<c:url value='/assets/js/ApiService.js'/>"></script>
		<script src="<c:url value='/assets/js/BufferService.js'/>"></script>
		<script src="<c:url value='/assets/js/OledService.js'/>"></script>
		<script src="<c:url value='/assets/js/PreviewService.js'/>"></script>
		<script src="<c:url value='/assets/js/GraphicsService.js'/>"></script>
		<script src="<c:url value='/assets/js/PreviewCtrl.js'/>"></script>
		<script src="<c:url value='/assets/js/OledCtrl.js'/>"></script>
		<script src="<c:url value='/assets/js/GraphicsCtrl.js'/>"></script>
	</body>
</html>
