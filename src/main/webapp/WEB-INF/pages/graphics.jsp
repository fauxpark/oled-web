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
							<input id="input-text-text" class="form-control" type="text" placeholder="Enter text here..."/>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>X</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-text-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-text-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button id="btn-text" class="btn btn-primary btn-block" ng-click="drawText()">Draw</button>
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
							<input id="input-line-x0" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
						</div>
						<div class="col-xs-5">
							<input id="input-line-y0" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>To</strong></p>
						</div>
						<div class="col-xs-5">
							<input id="input-line-x1" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
						</div>
						<div class="col-xs-5">
							<input id="input-line-y1" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button id="btn-line" class="btn btn-primary btn-block" ng-click="drawLine()">Draw</button>
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
							<input id="input-rect-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-rect-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Width</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-rect-width" class="form-control" type="number" min="1" max="{{state.width}}" step="1" value="1"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Height</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-rect-height" class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="1"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button id="input-rect-filled" class="btn btn-default btn-block" data-toggle="button">Filled</button>
						</div>
					</div>
				</div>
				<div class="col-sm-2 col-sm-offset-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button id="btn-rect" class="btn btn-primary btn-block" ng-click="drawRectangle()">Draw</button>
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
							<input id="input-arc-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-arc-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Start</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-arc-start" class="form-control" type="number" min="-180" max="180" step="1" value="0"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>End</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-arc-end" class="form-control" type="number" min="-180" max="180" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group row">
						<div class="col-xs-4">
							<p class="form-control-static text-right"><strong>Radius</strong></p>
						</div>
						<div class="col-xs-8">
							<input id="input-arc-radius" class="form-control" type="number" min="0" max="64" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2 col-sm-offset-1">
					<div class="form-group row">
						<div class="col-xs-12">
							<button id="btn-arc" class="btn btn-primary btn-block" ng-click="drawArc()">Draw</button>
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
							<input id="input-circle-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-circle-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group row">
						<div class="col-xs-4">
							<p class="form-control-static text-right"><strong>Radius</strong></p>
						</div>
						<div class="col-xs-8">
							<input id="input-circle-radius" class="form-control" type="number" min="0" max="64" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2 col-sm-offset-1">
					<div class="form-group row">
						<div class="col-xs-12">
							<button id="btn-circle" class="btn btn-primary btn-block" ng-click="drawCircle()">Draw</button>
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
							<input id="input-image-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Y</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-image-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
						</div>
					</div>
				</div>
				<div class="col-sm-5">
					<div class="form-group row">
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Width</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-image-width" class="form-control" type="number" min="1" max="{{state.width}}" step="1" value="{{state.width}}"/>
						</div>
						<div class="col-xs-2">
							<p class="form-control-static text-right"><strong>Height</strong></p>
						</div>
						<div class="col-xs-4">
							<input id="input-image-height" class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="{{state.height}}"/>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="form-group row">
						<div class="col-xs-12">
							<button id="btn-image" class="btn btn-primary btn-block" ng-click="drawImage()">Draw</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
