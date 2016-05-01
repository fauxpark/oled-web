<div ng-controller="GraphicsCtrl">
	<div class="form-group">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab-text" data-toggle="tab">Text</a></li>
			<li><a href="#tab-line" data-toggle="tab">Line</a></li>
			<li><a href="#tab-rect" data-toggle="tab">Rectangle</a></li>
			<li><a href="#tab-arc" data-toggle="tab">Arc</a></li>
			<li><a href="#tab-circle" data-toggle="tab">Circle</a></li>
			<li><a href="#tab-image" data-toggle="tab">Image</a></li>
		</ul>
	</div>
	<div class="tab-content">
		<div id="tab-text" class="tab-pane fade in active">
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group row">
						<div class="col-xs-12">
							<input class="form-control" type="text" placeholder="Enter text here..." ng-model="text.text"/>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>X</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" ng-model="text.x"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" ng-model="text.y"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button class="btn btn-primary btn-block" ng-click="drawText()">Draw</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tab-line" class="tab-pane fade">
			<div class="row">
				<div class="col-sm-5">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>From</strong></p>
						</div>
						<div class="col-xs-5">
							<input class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" ng-model="line.x0"/>
						</div>
						<div class="col-xs-5">
							<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" ng-model="line.y0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>To</strong></p>
						</div>
						<div class="col-xs-5">
							<input class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" ng-model="line.x1"/>
						</div>
						<div class="col-xs-5">
							<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" ng-model="line.y1"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button class="btn btn-primary btn-block" ng-click="drawLine()">Draw</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tab-rect" class="tab-pane fade">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>X</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" ng-model="rect.x"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" ng-model="rect.y"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Width</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="1" max="{{state.width}}" step="1" ng-model="rect.width"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Height</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="1" max="{{state.height}}" step="1" ng-model="rect.height"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button class="btn btn-default btn-block" data-toggle="button" ng-click="rect.filled = !rect.filled">Filled</button>
						</div>
					</div>
				</div>
				<div class="col-sm-2 col-sm-offset-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button class="btn btn-primary btn-block" ng-click="drawRectangle()">Draw</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tab-arc" class="tab-pane fade">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>X</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" ng-model="arc.x"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" ng-model="arc.y"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Start</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="-180" max="180" step="1" ng-model="arc.startAngle"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>End</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="-180" max="180" step="1" ng-model="arc.endAngle"/>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group row">
						<div class="col-xs-4">
							<p class="form-control-static text-right"><strong>Radius</strong></p>
						</div>
						<div class="col-xs-8">
							<input class="form-control" type="number" min="0" max="64" step="1" ng-model="arc.radius"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2 col-sm-offset-1">
					<div class="form-group row">
						<div class="col-xs-12">
							<button class="btn btn-primary btn-block" ng-click="drawArc()">Draw</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tab-circle" class="tab-pane fade">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>X</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" ng-model="circle.x"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" ng-model="circle.y"/>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group row">
						<div class="col-xs-4">
							<p class="form-control-static text-right"><strong>Radius</strong></p>
						</div>
						<div class="col-xs-8">
							<input class="form-control" type="number" min="0" max="64" step="1" ng-model="circle.radius"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2 col-sm-offset-1">
					<div class="form-group row">
						<div class="col-xs-12">
							<button class="btn btn-primary btn-block" ng-click="drawCircle()">Draw</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tab-image" class="tab-pane fade">
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group row">
						<div class="col-xs-12 col-sm-6 col-sm-offset-3">
							<div class="well well-sm">
								<input id="input-image-file" type="file" accept=".bmp,.gif,.jpeg,.jpg,.png"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-5">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>X</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" ng-model="image.x"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" ng-model="image.y"/>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Width</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="1" max="{{state.width}}" step="1" ng-model="image.width"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Height</strong></p>
						</div>
						<div class="col-xs-4">
							<input class="form-control" type="number" min="1" max="{{state.height}}" step="1" ng-model="image.height"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button class="btn btn-primary btn-block" ng-click="drawImage()">Draw</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
