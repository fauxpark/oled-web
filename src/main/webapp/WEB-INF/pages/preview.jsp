<div class="panel panel-default" ng-controller="PreviewCtrl">
  <div class="panel-heading">
    <div class="panel-title">
      <div class="pull-right">
        <button id="input-pixel-on" class="btn btn-default btn-xs active" data-toggle="button"><i class="glyphicon glyphicon-pencil"></i></button>
      </div>
      Preview
    </div>
  </div>
  <div class="panel-body">
    <div class="text-center"><canvas id="canvas" height="0" width="0" style="background:#000;" ng-mousedown="setPixel($event)" ng-mousemove="setPixel($event)" ng-mouseup="setBuffer($event)"></canvas></div>
  </div>
</div>
