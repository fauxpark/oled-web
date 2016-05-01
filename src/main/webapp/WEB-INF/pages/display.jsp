<div ng-controller="OledCtrl">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group row">
				<div class="col-xs-6">
					<button class="btn btn-primary btn-block" ng-click="initialise()">{{state.initialised ? 'Shutdown' : 'Startup'}}</button>
				</div>
				<div class="col-xs-6">
					<button class="btn btn-primary btn-block" ng-click="toggleDisplay()">Display {{state.displayOn ? 'Off' : 'On'}}</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-6">
					<button class="btn btn-primary btn-block" ng-class="{active: state.inverted}" ng-click="invert()">Invert</button>
				</div>
				<div class="col-xs-6">
					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button class="btn btn-primary" ng-class="{active: state.hFlipped}" ng-click="horizontalFlip()">Flip H</button>
						</div>
						<div class="btn-group">
							<button class="btn btn-primary" ng-class="{active: state.vFlipped}" ng-click="verticalFlip()">Flip V</button>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-6">
					<button class="btn btn-primary btn-block" ng-click="clear()">Clear</button>
				</div>
				<div class="col-xs-6">
					<button class="btn btn-primary btn-block" ng-click="getBuffer()">Refresh</button>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static"><strong>Contrast</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-contrast" type="range" min="0" max="255" step="1" ng-disabled="!state.initialised" ng-model="state.contrast"/>
				</div>
				<div class="col-xs-2">
					<p class="form-control-static text-center"><strong>{{state.contrast}}</strong></p>
				</div>
				<div class="col-xs-4">
					<button class="btn btn-primary btn-block" ng-click="setContrast()">Set</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static"><strong>Offset</strong></p>
				</div>
				<div class="col-xs-4">
					<input id="input-offset" class="form-control" type="number" min="0" max="{{state.height - 1}}" ng-disabled="!state.initialised" ng-model="state.offset"/>
				</div>
				<div class="col-xs-4 col-xs-offset-2">
					<button class="btn btn-primary btn-block" ng-click="setOffset()">Set</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Start</strong></p>
				</div>
				<div class="col-xs-4">
					<input class="form-control" type="number" min="0" max="7" step="1" value="0" ng-model="scroll.startPage"/>
				</div>
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>End</strong></p>
				</div>
				<div class="col-xs-4">
					<input class="form-control" type="number" min="0" max="7" step="1" value="7" ng-model="scroll.endPage"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Speed</strong></p>
				</div>
				<div class="col-xs-4">
					<select class="form-control" ng-model="scroll.speed">
						<option value="7">2</option>
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
					<button class="btn btn-default btn-block" data-toggle="button" ng-click="scroll.vertical = !scroll.vertical">Vertical</button>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Offset</strong></p>
				</div>
				<div class="col-xs-4">
					<input class="form-control" type="number" min="0" max="{{state.height - 1}}" step="1" value="0" ng-disabled="!scroll.vertical" ng-model="scroll.offset"/>
				</div>
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Rows</strong></p>
				</div>
				<div class="col-xs-4">
					<input class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="{{state.height}}" ng-disabled="!scroll.vertical" ng-model="scroll.rows"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<p class="form-control-static text-right"><strong>Step</strong></p>
				</div>
				<div class="col-xs-4">
					<input class="form-control" type="number" min="1" max="{{state.height}}" step="1" value="1" ng-disabled="!scroll.vertical" ng-model="scroll.step"/>
				</div>
				<div class="col-xs-6">
					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button class="btn btn-default btn-block" data-toggle="button" ng-click="scroll.left = !scroll.left">{{scroll.left ? 'Left' : 'Right'}}</button>
						</div>
						<div class="btn-group">
							<button class="btn btn-primary btn-block" ng-click="toggleScroll()">{{state.scrolling ? 'Stop' : 'Start'}}</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
