<div ng-controller="OledCtrl">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group row">
				<div class="col-xs-6">
					<button id="btn-init" class="btn btn-primary btn-block" ng-click="initialise()">{{state.initialised ? 'Shutdown' : 'Startup'}}</button>
				</div>
				<div class="col-xs-6">
					<button id="btn-display" class="btn btn-primary btn-block" ng-click="toggleDisplay()">Display {{state.displayOn ? 'Off' : 'On'}}</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-6">
					<button id="btn-invert" class="btn btn-primary btn-block" ng-class="{active: state.inverted}" ng-click="invert()">Invert</button>
				</div>
				<div class="col-xs-6">
					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button id="btn-flip-h" class="btn btn-primary" ng-class="{active: state.hFlipped}" ng-click="horizontalFlip()">Flip H</button>
						</div>
						<div class="btn-group">
							<button id="btn-flip-v" class="btn btn-primary" ng-class="{active: state.vFlipped}" ng-click="verticalFlip()">Flip V</button>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-6">
					<button id="btn-clear" class="btn btn-primary btn-block" ng-click="clear()">Clear</button>
				</div>
				<div class="col-xs-6">
					<button id="btn-refresh" class="btn btn-primary btn-block" ng-click="getBuffer()">Refresh</button>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static"><strong>Contrast</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-contrast" name="input-contrast" type="range" min="0" max="255" step="1" ng-disabled="!state.initialised" ng-model="state.contrast"/>
				</div>
				<div class="col-xs-2">
					<p class="form-control-static text-center"><strong>{{state.contrast}}</strong></p>
				</div>
				<div class="col-xs-4">
					<button id="btn-contrast" class="btn btn-primary btn-block" ng-click="setContrast()">Set</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Start</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-scroll-start" class="form-control" type="number" min="0" max="7" step="1" value="0"/>
				</div>
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>End</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-scroll-end" class="form-control" type="number" min="0" max="7" step="1" value="7"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Speed</strong></p>
				</div>
				<div class="col-xs-4">
					<select id="input-scroll-speed" class="form-control">
						<option value="7" selected>2</option>
						<option value="4">3</option>
						<option value="5">4</option>
						<option value="0">5</option>
						<option value="6">25</option>
						<option value="1">64</option>
						<option value="2">128</option>
						<option value="3">256</option>
					</select>
				</div>
				<div class="col-xs-4 col-xs-offset-2">
					<button id="input-scroll-vertical" class="btn btn-default btn-block" data-toggle="button" ng-click="scrollVertical = !scrollVertical">Vertical</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Offset</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-scroll-offset" class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0" ng-disabled="!scrollVertical"/>
				</div>
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Rows</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-scroll-rows" class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="{{state.height}}" ng-disabled="!scrollVertical"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Step</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-scroll-step" class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="1" ng-disabled="!scrollVertical"/>
				</div>
				<div class="col-xs-6">
					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button id="input-scroll-direction" class="btn btn-default btn-block" data-toggle="button" ng-click="scrollDir = !scrollDir">{{scrollDir ? 'Left' : 'Right'}}</button>
						</div>
						<div class="btn-group">
							<button id="btn-scroll-start" class="btn btn-primary btn-block" ng-click="toggleScroll()">{{state.scrolling ? 'Stop' : 'Start'}}</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
