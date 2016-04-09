<div class="panel panel-default" ng-controller="OledCtrl">
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
				<input id="input-pixel-x" class="form-control" type="number" min="0" max="{{state.width - 1}}" step="1" value="0"/>
			</div>
			<div class="col-md-1">
				<p class="form-control-static text-right"><strong>Y</strong></p>
			</div>
			<div class="col-md-2">
				<input id="input-pixel-y" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0"/>
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
