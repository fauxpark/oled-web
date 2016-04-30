<div class="alert alert-{{alert.type}}" ng-repeat="alert in alerts">
	<i class="glyphicon glyphicon-remove-sign" ng-show="alert.type == 'danger'"></i>
	<i class="glyphicon glyphicon-exclamation-sign" ng-show="alert.type == 'warning'"></i>
	<i class="glyphicon glyphicon-info-sign" ng-show="alert.type == 'info'"></i>
	<i class="glyphicon glyphicon-ok-sign" ng-show="alert.type == 'success'"></i>
	<strong>{{alert.title}}</strong>
	{{alert.message}}
</div>
