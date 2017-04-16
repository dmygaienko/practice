var surfacePoints = [];
var surfaceN = parseInt(readline()); // the number of points used to draw the surface of Mars.
for (var i = 0; i < surfaceN; i++) {
    var inputs = readline().split(' ');
    surfacePoints.push({
        x: parseInt(inputs[0]),
        y: parseInt(inputs[1])
    });
}

var surfaceSegments = [];
for (var j = 0; j + 1 < surfacePoints.length; j++) {
    surfaceSegments.push({
        start: surfacePoints[j],
        end: surfacePoints[j+1]
    });
}

var flatGround = getFlatGround();


var pathComplete = false;

var currentPoint = {x: 6500, y: 2600};
var targetPoints = [];
var fliedTargetPoints = [];
setFlatCentralPointAsTarget(targetPoints);
var targetPoint = targetPoints.shift();

var flyTargetIndex = 0;
var bestHSpeed = 27;
var bestVSpeed = 22;

var roundaboutPointIndex;
var path = [];
var direction
function completePath(x, y) {
    path.push({x: x, y: y});
    direction = x - flatGround.centralPoint.x > 0 ? 0 : 1;

    while (!pathComplete) {

        var intersectedSegments = countIntersectedSegments(currentPoint, targetPoint);

        if (intersectedSegments.length == 1) {
            path.push(targetPoint);
            pathComplete = true;
        } else if (intersectedSegments.length == 2 && fliedTargetPoints.indexOf(targetPoint) < 0) {
            var shiftedTargetPoint = shiftTargetPoint(currentPoint, targetPoint);
            fliedTargetPoints.push(targetPoint);
            path.push(shiftedTargetPoint);
            currentPoint = shiftedTargetPoint;
            setFlatCentralPointAsTarget(targetPoints);
            if (flatGround.contains(targetPoint)) {
                pathComplete = true;
                path.push(targetPoint);
            }
        } else {
            if (!roundaboutPointIndex) {
                roundaboutPointIndex = findRoundaboutPointIndex(currentPoint, targetPoint, intersectedSegments);
            }
            targetPoint = surfacePoints[roundaboutPointIndex];
            roundaboutPointIndex = direction == 0 ? --roundaboutPointIndex : ++roundaboutPointIndex;
        }
    }


    var lastPoint = path[path.length-1];
    path.splice(path.length-2, 0, {x:lastPoint.x, y:lastPoint.y+500});
    printErr('path: ' + JSON.stringify(path));
}
printErr('surfacePoints: ' + JSON.stringify(surfacePoints));

function Point(x, y)  {
    self = this;

    this.x = x;
    this.y = y;

    this.distanceTo = function(that){
        return Math.sqrt(Math.pow(self.x - that.x, 2) + Math.pow(self.y - that.y, 2));
    };

    this.equals = function(that) {
        return that.x == self.x && that.y == self.y;
    }

}

function vectorFromPoints(currentPoint, targetPoint) {
    return new Vector(targetPoint.x - currentPoint.x, targetPoint.y - currentPoint.y);
}

function Vector(x, y) {
    self = this;

    this.x = x;
    this.y = y;

    this.toDegrees = function (radians) {
        return radians * (180 / Math.PI);
    };

    this.length = Math.sqrt(self.x * self.x + self.y * self.y);

    this.angle = this.toDegrees(Math.atan2(self.x, self.y));

    this.plus = function (that) {
        return new Vector(this.x + that.x, this.y + that.y);
    };

    this.minus = function (that) {
        return new Vector(this.x - that.x, this.y - that.y);
    };

    this.multiply = function (scalar) {
        return new Vector(x * scalar, y * scalar);
    };

    this.isOpposite = function (that) {
        return self.angle/that.angle < 0;
    };

    this.inBounds = function (that) {
        return Math.abs(self.x) <= Math.abs(that.x) && Math.abs(self.y) <= Math.abs(that.y);
    };

    this.slowDown = function (that) {
        var newX = self.x;
        if (Math.abs(newX) > Math.abs(that.x)) {
            newX = newX > 0 ? that.x : - that.x;
        }
        var newY = self.y;
        if (Math.abs(newY) > Math.abs(that.y)) {
            newY = newY > 0 ? that.y : - that.y;
        }
        return new Vector(newX, newY);
    }

}

var G = -3.711;
var gravityVector = new Vector(0, G);
var levelingVector = new Vector(0, -35);
var extremeLandingVector = new Vector(20, -40).plus(gravityVector);
var maxSpeedVector = new Vector(20, 20);

// game loop
while (true) {
    var inputs = readline().split(' ');
    var X = parseInt(inputs[0]);
    var Y = parseInt(inputs[1]);
    var hSpeed = parseInt(inputs[2]); // the horizontal speed (in m/s), can be negative.
    var vSpeed = parseInt(inputs[3]); // the vertical speed (in m/s), can be negative.
    var fuel = parseInt(inputs[4]); // the quantity of remaining fuel in liters.
    var rotate = parseInt(inputs[5]); // the rotation angle in degrees (-90 to 90).
    var power = parseInt(inputs[6]); // the thrust power (0 to 4).

    if (!pathComplete) {
        completePath(X, Y);
    }

    var currentPoint = new Point(X, Y);

    var verticalVector = new Vector(0, vSpeed).plus(gravityVector);
    printErr('verticalVector ' + JSON.stringify(verticalVector));
    var horizontalVector = new Vector(hSpeed, 0);
    printErr('horizontalVector ' + JSON.stringify(horizontalVector));
    var currentVector = verticalVector.plus(horizontalVector);
    printErr('currentVector ' + JSON.stringify(currentVector));
    printErr('currentVector ' + JSON.stringify(verticalVector.plus(horizontalVector)));

    var deltaVector = getDeltaVector(currentPoint, currentVector, flatGround);

    var controls = getNextControls(verticalVector, currentVector, deltaVector, isLanding(currentPoint, flatGround));
    printErr('controls ' + JSON.stringify(controls));
    print(-parseInt(controls.angle) + " " + controls.power);

    // Write an action using print()
    // To debug: printErr('Debug messages...');

    //flyPath(X, Y, hSpeed, vSpeed, rotate, power);

    // rotate power. rotate is the desired rotation angle. power is the desired thrust power.
    //print('-20 3');
}

function getDeltaVector(currentPoint, currentVector, flatGround) {
    var deltaVector;

    if (isLanding(currentPoint, flatGround)) {
        deltaVector = levelingVector.minus(currentVector);
    } else {
        deltaVector = getNonLevelingDeltaVector(currentPoint, currentVector, getNextTarget(currentPoint));

        printErr('currentVector' + JSON.stringify(currentVector));
        if (!currentVector.inBounds(maxSpeedVector)) {
            var slowDownVector = currentVector.slowDown(maxSpeedVector).minus(currentVector);
            deltaVector = slowDownVector.plus(deltaVector);
            printErr('deltaVector slowDownVector' + JSON.stringify(deltaVector));
        }
    }
    return deltaVector;
}

function getNextTarget(currentPoint) {

    var nextTarget = path[flyTargetIndex];
    var hDistance = nextTarget.x - currentPoint.x;
    var vDistance = nextTarget.y - currentPoint.y;
    printErr('nextTarget: ' + JSON.stringify(nextTarget) + ';hDistance ' + hDistance + ';vDistance ' + vDistance);
    if (Math.abs(hDistance) < 90 && Math.abs(vDistance) < 90) {
        nextTarget = path[++flyTargetIndex];
        hDistance = nextTarget.x - currentPoint.x;
        vDistance = nextTarget.y - currentPoint.y;
    }

    return new Point(nextTarget.x, nextTarget.y);
}

function getNonLevelingDeltaVector(currentPoint, currentVector, targetPoint) {
    var desiredVector = vectorFromPoints(currentPoint, targetPoint);

    while (desiredVector.length > currentVector.length * 1.05){
        desiredVector = desiredVector.multiply(0.95);
    }
    return desiredVector.minus(currentVector);
}

function getNextControls(verticalVector, currentVector, deltaVector, isLanding) {
    var controls;
    if (isLanding && currentVector.inBounds(extremeLandingVector)) {
        controls = getNextLandingControls(verticalVector);
    } else {
        controls = getNextNonLandingControls(currentVector, deltaVector);
    }
    return controls;
}

function getNextLandingControls(verticalVector) {
    var controls = {};
    controls.angle = 0;
    controls.power = verticalVector.y < -39 ? 4 : 3;
    return controls;
}

function getNextNonLandingControls(currentVector, deltaVector) {
    var angle = deltaVector.angle;
    var isOpposite = deltaVector.isOpposite(currentVector);

    var controls = {};
    if (angle == 0) {
        controls.angle = 0;
        controls.power = 4;
    } else if (angle == 180) {
        controls.angle = 0;
        controls.power = 3;
    } else if (angle > 0 && angle < 90) {
        controls.angle = getSafeAngle(angle);
        controls.power = 4;
    } else if (angle > 90 && angle < 180) {
        controls.angle = getSafeAngle(isOpposite ? angle :(angle - 90));
        controls.power = isOpposite ? 4 : 3;
    } else if (angle > -90 && angle < 0) {
        controls.angle = getSafeAngle(angle);
        controls.power = 4;
    } else if (angle > -180 && angle < -90) {
        controls.angle = getSafeAngle(isOpposite ? angle :(angle + 90));
        controls.power = isOpposite ? 4 : 3;
    }

    printErr('controls ' + JSON.stringify(controls));
    return controls;
}

function getSafeAngle(angle) {
    return angle * 22/90;
}

function isLanding(currentPoint, flatGround) {
    var near = flatGround.startLandingPoint.distanceTo(currentPoint) < 300;
    if (near) {
        flatGround.nextTargetPoint(flatGround.centralPoint);
    }
    return near || flatGround.nextTargetPoint.equals(flatGround.centralPoint);
}

function countIntersectedSegments(currentPoint, targetPoint) {
    var intersectedSegments = [];
    for (var l = 0; l < surfaceSegments.length; l++) {
        var segment = surfaceSegments[l];

        if (intersects(currentPoint, targetPoint, segment.start, segment.end)) {
            intersectedSegments.push(segment);

            //  console.log('intersects with: currentPoint ' + JSON.stringify(currentPoint) + '; flatGround.centralPoint: ' + JSON.stringify(flatGround.centralPoint)
            //      + '; segment.start: ' + JSON.stringify(segment.start) + '; segment.end: ' + JSON.stringify(segment.end));
        }
    }
    return intersectedSegments;
}

function flyPath(x, y, hSpeed, vSpeed, rotate, power) {
    hSpeed = hSpeed == 0 ? 1 : hSpeed;
    vSpeed = vSpeed == 0 ? 1 : vSpeed;

    var desiredSpeed = getDesiredSpeed(x, y, hSpeed, vSpeed);
    printErr('desiredSpeed: ' + JSON.stringify(desiredSpeed));

    if (hSpeed != desiredSpeed.h && Math.abs(vSpeed - desiredSpeed.v) < 1) {
        power = 4;
        if (Math.abs(hSpeed) < Math.abs(desiredSpeed.h)) { //speedup
            rotate = desiredSpeed.h > 0 ? -22 : 22;
        } else { //slowdown
            rotate = desiredSpeed.h < 0 ? -22 : 22;
        }
    } else if (vSpeed != desiredSpeed.v && Math.abs(hSpeed - desiredSpeed.h) < 1) {
        rotate = 0;
        if (Math.abs(vSpeed) < Math.abs(desiredSpeed.v)) {
            power = desiredSpeed.v > 0 ? 4 : 2;
        } else {
            power = desiredSpeed.v > 0 ? 2 : 4;
        }
    } else if (hSpeed != desiredSpeed.h && vSpeed != desiredSpeed.v) {
        var hDiff = Math.abs((desiredSpeed.h - hSpeed)/hSpeed);
        var vDiff = Math.abs((desiredSpeed.v - vSpeed)/vSpeed);
        printErr('hDiff: ' + hDiff + ' ;vDiff: ' + vDiff);

        var hvDiff = Math.abs((hDiff - vDiff)/vDiff) > 0.2;
        var vhDiff = Math.abs((vDiff - hDiff)/hDiff) > 0.2;

        var hPrior = hvDiff && !vhDiff;
        var vPrior = vhDiff && !hvDiff;
        printErr('hPrior: ' + hPrior + ' ;vPrior: ' + vPrior);

        if (hSpeed < desiredSpeed.h) { // ----->
            if (hPrior) {
                rotate = -22;
                power = 4;
            }
            else if (vSpeed < desiredSpeed.v) {
                if (vPrior) {
                    rotate = 0;
                    power = 4;
                } else {
                    rotate = -14;
                    power = 4;
                }

            } else {
                rotate = -22;
                power = 3;
            }
        } else if (hSpeed > desiredSpeed.h) { //<-----
            if (hPrior) {
                rotate = 0;
                power = 2;
            } else if (vSpeed < desiredSpeed.v) {
                if (vPrior) {
                    rotate = 0;
                    power = 4;
                } else {
                    rotate = 14;
                    power = 4;
                }
            } else {
                rotate = 22;
                power = 3;
            }
        }
    }

    print(rotate + ' ' + power);
}

function getDesiredSpeed(x, y, hSpeed, vSpeed) {
    var desiredSpeed = {};
    hSpeed = hSpeed == 0 ? 1 : hSpeed;
    vSpeed = vSpeed == 0 ? 1 : vSpeed;

    var nextTarget = path[flyTargetIndex];
    var hDistance = nextTarget.x - x;
    var vDistance = nextTarget.y - y;
    printErr('nextTarget: ' + JSON.stringify(nextTarget) + ';hDistance ' + hDistance + ';vDistance ' + vDistance);
    if (Math.abs(hDistance) < 90 && Math.abs(vDistance) < 90) {
        nextTarget = path[++flyTargetIndex];
        hDistance = nextTarget.x - x;
        vDistance = nextTarget.y - y;
    }

    var hTime = hDistance / hSpeed;
    printErr('hTime: ' + hTime);
    if (hTime < 0) { //need to change H direction
        desiredSpeed.h = bestHSpeed * (hSpeed > 0 ? -1 : 1);
    } else if (hTime >90 && Math.abs(hSpeed * 2) < bestHSpeed) {
        printErr('entered H')
        desiredSpeed.h = (hSpeed == 0 ? 1 : hSpeed) * 2;
    } else {
        desiredSpeed.h = hSpeed;
    }

    var vTime = vDistance / vSpeed;
    printErr('vTime: ' + vTime);
    if (vTime < 0) { //need to change V direction
        desiredSpeed.v = bestVSpeed * (vSpeed > 0 ? -1 : 1);
    } else if (vTime > 20 && Math.abs(vSpeed * 2) < bestVSpeed) {
        printErr('entered V')
        desiredSpeed.v = vSpeed * 2;
    } else {
        desiredSpeed.v = vSpeed;
    }
    printErr('vTime > 50' + (vTime > 50 && Math.abs(vSpeed * 1.2) < bestVSpeed));
    printErr('desired speed before ' + desiredSpeed.h + ' ' + desiredSpeed.v);

    var avgTime;
    if (vTime == hTime) {
        avgTime = vTime;
    } else {
        printErr(hDistance+' '+desiredSpeed.h+' '+vDistance+' '+desiredSpeed.v);
        avgTime = (hDistance/desiredSpeed.h + vDistance/desiredSpeed.v) / 2;

        var avgHDesiredSpeed = hDistance / avgTime;
        var avgVDesiredSpeed = vDistance / avgTime;

        if (Math.abs(avgHDesiredSpeed) <= bestHSpeed) {
            desiredSpeed.h = avgHDesiredSpeed
        }

        if (Math.abs(avgVDesiredSpeed) <= bestVSpeed) {
            desiredSpeed.v = avgVDesiredSpeed
        }

    }

    printErr('avgTime: ' + avgTime);
    return desiredSpeed;
}

function findRoundaboutPointIndex(currentPoint, targetPoint, intersectedSegments) {
    var uniquePoints = [];
    intersectedSegments.forEach(function(value) {
        addUniquePoint(uniquePoints, value.start);
        addUniquePoint(uniquePoints, value.end);
    });

    var distances = uniquePoints.map(function(value, index) {
        var result = {};
        result.point = value;
        result.distance = Math.sqrt(Math.pow(currentPoint.x - value.x,2) + Math.pow(currentPoint.y - value.y ,2));
        return result;
    });

    distances.sort(function (a, b) {
        return a.distance - b.distance;
    });

    return surfacePoints.indexOf(distances[0].point);
}

function shiftTargetPoint(currentPoint, targetPoint, changeMark) {
    var shifterTargetPoint = {};

    if (!changeMark) {
        if (targetPoint.x - currentPoint.x > 0) {
            //right
            shifterTargetPoint.x = targetPoint.x + 100;
            if (targetPoint.y - currentPoint.y > 0) {
                shifterTargetPoint.y = targetPoint.y + 100;
                changeMark = {rightTop: true};
                //top
            } else {
                shifterTargetPoint.y = targetPoint.y - 100;
                changeMark = {rightBottom: true};
            }
        } else {
            //left
            shifterTargetPoint.x = targetPoint.x - 100;
            if (targetPoint.y - currentPoint.y > 0) {
                //top
                shifterTargetPoint.y = targetPoint.y + 100;
                changeMark = {leftTop: true};
            } else {
                shifterTargetPoint.y = targetPoint.y - 100;
                changeMark = {leftBottom: true};
            }
        }
    } else if (changeMark.rightBottom && !changeMark.rightTop || (changeMark.leftTop && changeMark.leftBottom)) {
        changeMark.rightTop = true;
        shifterTargetPoint.x = targetPoint.x + 100;
        shifterTargetPoint.y = targetPoint.y + 100;
    } else if (changeMark.leftBottom && !changeMark.leftTop || (changeMark.rightBottom && changeMark.rightTop)) {
        changeMark.leftTop = true;
        shifterTargetPoint.x = targetPoint.x - 100;
        shifterTargetPoint.y = targetPoint.y + 100;
    } else if (changeMark.leftTop && !changeMark.leftBottom || (changeMark.rightBottom && changeMark.rightTop)) {
        changeMark.leftBottom = true;
        shifterTargetPoint.x = targetPoint.x - 100;
        shifterTargetPoint.y = targetPoint.y - 100;
    } else if (changeMark.rightTop && !changeMark.rightBottom || (changeMark.leftTop && changeMark.leftBottom)) {
        changeMark.rightBottom = true;
        shifterTargetPoint.x = targetPoint.x + 100;
        shifterTargetPoint.y = targetPoint.y - 100;
    }

    var intersectedSegments = countIntersectedSegments(currentPoint, shifterTargetPoint);
    if (intersectedSegments.length > 0) {
        return shiftTargetPoint(currentPoint, targetPoint, changeMark);
    }
    return shifterTargetPoint;
}

function addUniquePoint(targetPoints, point) {
    if (targetPoints.indexOf(point) < 0) targetPoints.push(point);
}

function setFlatCentralPointAsTarget(targetPoints) {
    targetPoints.length = 0;
    targetPoints.push(flatGround.centralPoint);
}

function intersects(s1, e1, s2, e2) {

    var o1 = orientation(s1, e1, s2);
    var o2 = orientation(s1, e1, e2);
    var o3 = orientation(s2, e2, s1);
    var o4 = orientation(s2, e2, e1);

    if (o1 != o2 && o3 != o4)
        return true;

    // Special Cases
    // p1, q1 and p2 are collinear and p2 lies on segment p1q1
    if (o1 == 0 && onSegment(s1, s2, e1)) return true;
    // p1, q1 and p2 are collinear and q2 lies on segment p1q1
    if (o2 == 0 && onSegment(s1, e2, e1)) return true;
    // p2, q2 and p1 are collinear and p1 lies on segment p2q2
    if (o3 == 0 && onSegment(s2, s1, e2)) return true;
    // p2, q2 and q1 are collinear and q1 lies on segment p2q2
    if (o4 == 0 && onSegment(s2, e1, e2)) return true;

    return false; // Doesn’t fall in any of the above cases
}

function orientation(p, q, r) {
    // See 10th slides from following link for derivation of the formula
    // http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf

    var val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    if (val == 0) return 0;  // collinear
    return (val > 0) ? 1 : 2; // clock or counterclock wise
}

function onSegment(p, q, r){
    return (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
    q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y));

}

function getFlatGround() {
    flatGround = {};

    for (var i = 0; i < surfacePoints.length; i++) {
        if (flatGround.y0 == surfacePoints[i].y) {

            for (var f = i; f < surfacePoints.length; f++) {
                if (flatGround.y0 == surfacePoints[f].y) {
                    flatGround.x1 = surfacePoints[f].x;
                    flatGround.y1 = surfacePoints[f].y;
                }
            }

            if (flatGround.x1 - flatGround.x0 >= 1000) {
                break;
            }
        } else {
            flatGround.x0 = surfacePoints[i].x;
            flatGround.y0 = surfacePoints[i].y;
        }
    }

    flatGround.centralPoint = new Point((flatGround.x1 + flatGround.x0)/2, flatGround.y1);

    flatGround.startLandingPoint = new Point(flatGround.centralPoint.x, flatGround.centralPoint.y + 300);

    flatGround.nextTargetPoint = flatGround.startLandingPoint;

    flatGround.contains = function (point) {
        return point.y == flatGround.y0 && point.x >= flatGround.x0 && point.x <= flatGround.x1;
    };

    return flatGround;
}
