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
				<div class="col-md-5">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Preview</div>
						</div>
						<div class="panel-body">
							<div class="text-center"><canvas id="canvas" height="64" width="128" style="background:#000;"></canvas></div>
						</div>
					</div>
				</div>
				<div class="col-md-7">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Controls</div>
						</div>
						<div class="panel-body">
							<div class="form-group row">
								<div class="col-md-6">
									<button id="btn-init" class="btn btn-primary btn-block" ng-bind="state.initialised?'Shutdown':'Startup'" ng-click="initialise()">Startup</button>
								</div>
								<div class="col-md-6">
									<button id="btn-display-on" class="btn btn-primary btn-block" ng-bind="state.displayOn?'Display Off':'Display On'" ng-click="toggleDisplay()">Display On</button>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-6">
									<button id="btn-invert" class="btn btn-primary btn-block" ng-class="{active:state.inverted}" ng-click="invert()">Invert</button>
								</div>
								<div class="col-md-6">
									<div class="btn-group btn-group-justified">
										<div class="btn-group">
											<button id="btn-flip-h" class="btn btn-primary" ng-class="{active:state.hFlipped}" ng-click="horizontalFlip()">Flip H</button>
										</div>
										<div class="btn-group">
											<button id="btn-flip-v" class="btn btn-primary" ng-class="{active:state.vFlipped}" ng-click="verticalFlip()">Flip V</button>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-md-12">
										<label for="input-contrast">Contrast</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="input-group">
											<input id="input-contrast" name="input-contrast" class="form-control" type="number" min="0" max="255" step="1" ng-model="state.contrast"/>
											<span class="input-group-btn">
												<button class="btn btn-primary" ng-click="setContrast()">Set</button>
											</span>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-md-12">
										<label>Set Pixel</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1">
										<p class="form-control-static text-right"><strong>X</strong></p>
									</div>
									<div class="col-md-3">
										<input id="input-pixel-x" class="form-control" type="number" min="0" max="127" step="1" value="0"/>
									</div>
									<div class="col-md-1">
										<p class="form-control-static text-right"><strong>Y</strong></p>
									</div>
									<div class="col-md-3">
										<input id="input-pixel-y" class="form-control" type="number" min="0" max="63" step="1" value="0"/>
									</div>
									<div class="col-md-4">
										<div class="btn-group btn-group-justified">
											<div class="btn-group">
												<button id="input-pixel-on" class="btn btn-default active" data-toggle="button">On</button>
											</div>
											<div class="btn-group">
												<button class="btn btn-primary" ng-click="setPixel()">Set</button>
											</div>
										</div>
									</div>
								</div>
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
