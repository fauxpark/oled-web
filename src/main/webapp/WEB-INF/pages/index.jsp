<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" ng-app="oled">
	<head>
		<title>OLED</title>
		<meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width,initial-scale=1"/>
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>"/>
	</head>
	<body ng-controller="OledCtrl">
		<%@include file="nav.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Preview</div>
						</div>
						<div class="panel-body">
							<div class="text-center"><canvas id="canvas" height="64" width="128" style="background:#000;"></canvas></div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Controls</div>
						</div>
						<div class="panel-body">
							<div class="form-group"><button class="btn btn-primary btn-block" ng-click="startup()">Startup</button></div>
							<div class="form-group"><button class="btn btn-primary btn-block" ng-click="shutdown()">Shutdown</button></div>
							<div class="form-group"><button class="btn btn-primary btn-block" ng-click="displayOn()">Display On</button></div>
							<div class="form-group"><button class="btn btn-primary btn-block" ng-click="displayOff()">Display Off</button></div>
							<div class="form-group"><button class="btn btn-primary btn-block" ng-click="invert()">Invert</button></div>
							<div class="form-group">
								<input class="form-control" type="number" min="0" max="255" step="1"/>
								<button class="btn btn-primary btn-block" ng-click="setContrast(state.contrast)">Set Contrast</button>
							</div>
							<div class="form-group">
								<input class="form-control" type="number" min="0" max="127" step="1"/>
								<input class="form-control" type="number" min="0" max="63" step="1"/>
								<input class="form-control" type="checkbox" checked/>
								<button class="btn btn-primary btn-block" ng-click="setPixel(state.x, state.y, state.on)">Set Pixel</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.jsp"%>
		</div>
		<script src="<c:url value='/assets/js/vendor/jquery.min.js'/>"></script>
		<script src="<c:url value='/assets/js/vendor/bootstrap.min.js'/>"></script>
		<script src="<c:url value='/assets/js/vendor/angular.min.js'/>"></script>
		<script src="<c:url value='/assets/js/oled.js'/>"></script>
	</body>
</html>
