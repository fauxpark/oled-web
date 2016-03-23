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
	<body>
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
				<div class="col-md-7" ng-controller="OledCtrl">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Display</div>
						</div>
						<div class="panel-body">
							<div class="form-group row">
								<div class="col-md-6">
									<button id="btn-init" class="btn btn-primary btn-block" ng-click="initialise()">{{state.initialised?'Shutdown':'Startup'}}</button>
								</div>
								<div class="col-md-6">
									<button id="btn-display" class="btn btn-primary btn-block" ng-click="toggleDisplay()">Display {{state.displayOn?'Off':'On'}}</button>
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
							<div class="form-group row">
								<div class="col-md-6">
									<button id="btn-clear" class="btn btn-primary btn-block" ng-click="clear()">Clear</button>
								</div>
								<div class="col-md-6">
									<button id="btn-refresh" class="btn btn-primary btn-block" ng-click="getBuffer()">Refresh</button>
								</div>
							</div>
							<hr/>
							<div class="form-group row">
								<div class="col-md-2">
									<p class="form-control-static text-right"><strong>Contrast</strong></p>
								</div>
								<div class="col-md-7">
									<input id="input-contrast" name="input-contrast" type="range" min="0" max="255" step="1" ng-model="state.contrast"/>
								</div>
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>{{state.contrast}}</strong></p>
								</div>
								<div class="col-md-2">
									<button id="btn-contrast" class="btn btn-primary btn-block" ng-click="setContrast()">Set</button>
								</div>
							</div>
							<hr/>
							<div class="form-group row">
								<div class="col-md-2">
									<p class="form-control-static text-right"><strong>Set Pixel</strong></p>
								</div>
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>X</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-pixel-x" class="form-control" type="number" min="0" max="127" step="1" value="0"/>
								</div>
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>Y</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-pixel-y" class="form-control" type="number" min="0" max="63" step="1" value="0"/>
								</div>
								<div class="col-md-4">
									<div class="btn-group btn-group-justified">
										<div class="btn-group">
											<button id="input-pixel-on" class="btn btn-default active" data-toggle="button">On</button>
										</div>
										<div class="btn-group">
											<button id="btn-pixel" class="btn btn-primary" ng-click="setPixel()">Set</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Graphics</div>
						</div>
						<div class="panel-body">
							<div class="form-group row">
								<div class="col-md-12">
									<p class="form-control-static"><strong>Text</strong></p>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-4">
									<input id="input-text-text" class="form-control" type="text" placeholder="Enter text here..."/>
								</div>
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>X</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-text-x" class="form-control" type="number" min="0" max="127" step="1" value="0"/>
								</div>
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>Y</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-text-y" class="form-control" type="number" min="0" max="63" step="1" value="0"/>
								</div>
								<div class="col-md-2">
									<button id="btn-text" class="btn btn-primary btn-block" ng-click="drawText()">Draw</button>
								</div>
							</div>
							<hr/>
							<div class="form-group row">
								<div class="col-md-12">
									<p class="form-control-static"><strong>Line</strong></p>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>P1</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-line-x0" class="form-control" type="number" min="0" max="127" step="1" value="0"/>
								</div>
								<div class="col-md-2">
									<input id="input-line-y0" class="form-control" type="number" min="0" max="63" step="1" value="0"/>
								</div>
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>P2</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-line-x1" class="form-control" type="number" min="0" max="127" step="1" value="0"/>
								</div>
								<div class="col-md-2">
									<input id="input-line-y1" class="form-control" type="number" min="0" max="63" step="1" value="0"/>
								</div>
								<div class="col-md-2">
									<button id="btn-line" class="btn btn-primary btn-block" ng-click="drawLine()">Draw</button>
								</div>
							</div>
							<hr/>
							<div class="form-group row">
								<div class="col-md-12">
									<p class="form-control-static"><strong>Circle</strong></p>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>X</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-circle-x" class="form-control" type="number" min="0" max="127" step="1" value="0"/>
								</div>
								<div class="col-md-1">
									<p class="form-control-static text-right"><strong>Y</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-circle-y" class="form-control" type="number" min="0" max="63" step="1" value="0"/>
								</div>
								<div class="col-md-2">
									<p class="form-control-static text-right"><strong>Radius</strong></p>
								</div>
								<div class="col-md-2">
									<input id="input-circle-radius" class="form-control" type="number" min="0" max="64" step="1" value="0"/>
								</div>
								<div class="col-md-2">
									<button id="btn-circle" class="btn btn-primary btn-block" ng-click="drawCircle()">Draw</button>
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
		<script src="<c:url value='/assets/js/ApiService.js'/>"></script>
		<script src="<c:url value='/assets/js/OledService.js'/>"></script>
		<script src="<c:url value='/assets/js/BufferService.js'/>"></script>
		<script src="<c:url value='/assets/js/GraphicsService.js'/>"></script>
		<script src="<c:url value='/assets/js/OledCtrl.js'/>"></script>
	</body>
</html>
