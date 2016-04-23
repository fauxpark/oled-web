<div class="panel panel-default" ng-controller="GraphicsCtrl">
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
				<input id="input-text-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Y</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-text-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
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
				<input id="input-line-x0" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-2">
				<input id="input-line-y0" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>P2</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-line-x1" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-2">
				<input id="input-line-y1" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-2">
				<button id="btn-line" class="btn btn-primary btn-block" ng-click="drawLine()">Draw</button>
			</div>
		</div>
		<hr/>
		<div class="form-group row">
			<div class="col-md-12">
				<p class="form-control-static"><strong>Rectangle</strong></p>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>X</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-rect-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Y</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-rect-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Width</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-rect-width" class="form-control" type="number" min="1" max="{{state.width}}" step="1" value="1"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Height</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-rect-height" class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="1"/>
			</div>
			<div class="col-md-4 col-md-offset-8">
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<button id="input-rect-filled" class="btn btn-default" data-toggle="button">Filled</button>
					</div>
					<div class="btn-group">
						<button id="btn-rect" class="btn btn-primary" ng-click="drawRectangle()">Draw</button>
					</div>
				</div>
			</div>
		</div>
		<hr/>
		<div class="form-group row">
			<div class="col-md-12">
				<p class="form-control-static"><strong>Arc</strong></p>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>X</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-arc-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Y</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-arc-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Start</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-arc-start" class="form-control" type="number" min="-180" max="180" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>End</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-arc-end" class="form-control" type="number" min="-180" max="180" step="1" value="0"/>
			</div>
			<div class="col-md-2 col-md-offset-5">
				<p class="form-control-static text-right"><strong>Radius</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-arc-radius" class="form-control" type="number" min="0" max="64" step="1" value="0"/>
			</div>
			<div class="col-md-2 col-md-offset-1">
				<button id="btn-arc" class="btn btn-primary btn-block" ng-click="drawArc()">Draw</button>
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
				<input id="input-circle-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Y</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-circle-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
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
		<hr/>
		<div class="form-group row">
			<div class="col-md-12">
				<p class="form-control-static"><strong>Image</strong></p>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-10">
				<div class="well well-sm">
					<input id="input-image-file" type="file" accept=".bmp,.gif,.jpeg,.jpg,.png"/>
				</div>
			</div>
			<div class="col-md-2">
				<button id="btn-image" class="btn btn-primary btn-block" ng-click="drawImage()">Draw</button>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>X</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-image-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Y</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-image-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Width</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-image-width" class="form-control" type="number" min="1" max="{{state.width}}" step="1" value="{{state.width}}"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Height</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-image-height" class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="{{state.height}}"/>
			</div>
		</div>
	</div>
</div>
