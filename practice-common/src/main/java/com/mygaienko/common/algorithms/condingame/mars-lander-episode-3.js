//var surfaces = ['0 450','300 750','1000 450','1500 650','1800 850','2000 1950','2200 1850','2400 2000','3100 1800','3150 1550','2500 1600','2200 1550','2100 750','2200 150','3200 150','3500 450','4000 950','4500 1450','5000 1550','5500 1500','6000 950','6999 1750'];
var surfaces = ['0 1800', '300 1200', '1000 1550', '2000 1200', '2500 1650', '3700 220', '4700 220', '4750 1000', '4700 1650', '4000 1700', '3700 1600', '3750 1900', '4000 2100', '4900 2050', '5100 1000', '5500 500', '6200 800', '6999 600'];

var surfacePoints = [];
for (var i = 0; i < surfaces.length; i++) {
    var inputs = surfaces[i].split(' ');
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

console.log(JSON.stringify(surfacePoints.length));
console.log(JSON.stringify(surfacePoints));
console.log(JSON.stringify(surfaceSegments.length));
console.log(JSON.stringify(surfaceSegments));
console.log(JSON.stringify(flatGround));

var path = [];
var pathComplete = false;

var currentPoint = {x: 6500, y: 2600};
var targetPoints = [];
var fliedTargetPoints = [];
setFlatCentralPointAsTarget(targetPoints);
path.push(currentPoint);

var roundaboutPointIndex;
var direction = currentPoint.x - flatGround.centralPoint.x > 0 ? 0 : 1;

var targetPoint = targetPoints.shift();
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

var flyTargetIndex = 0;
var bestHSpeed = 40;
var bestVSpeed = 20;
function flyPath(x, y, hSpeed, vSpeed, rotate, power) {
    var desiredSpeed = getDesiredSpeed(x, y, hSpeed, vSpeed);

    if (hSpeed != desiredSpeed.h && vSpeed != desiredSpeed.v) {

    } else if (hSpeed != desiredSpeed.h) {



    } else if (vSpeed != desiredSpeed.v) {



    }
}

function getDesiredSpeed(x, y, hSpeed, vSpeed) {
    var desiredSpeed = {};

    var nextTarget = path[flyTargetIndex];
    var hDistance = nextTarget.x - x;
    var vDistance = nextTarget.y - y;
    if (hDistance < 30 && vDistance < 30) {
        nextTarget = path[++flyTargetIndex];
        hDistance = nextTarget.x - x;
        vDistance = nextTarget.y - y;
    }

    var hTime = hDistance / hSpeed;
    if (hTime < 0) { //need to change H direction
        desiredSpeed.h = bestHSpeed * (hSpeed > 0 ? -1 : 1);
    } else {
        desiredSpeed.h = hSpeed;
    }

    var vTime = vDistance / vSpeed;
    if (vTime < 0) { //need to change V direction
        desiredSpeed.v = bestVSpeed * (vSpeed > 0 ? -1 : 1);
    } else {
        desiredSpeed.v = vSpeed;
    }

    var avgTime;
    if (vTime == hTime) {
        avgTime = vTime;
    } else {
        avgTime = (vTime + hTime) / 2;

        desiredSpeed.h = hDistance / avgTime;
        desiredSpeed.v = vDistance / avgTime;
    }

    return desiredSpeed;
}

console.log('targetPoints: ' + JSON.stringify(targetPoints));
console.log(JSON.stringify(path));

function countIntersectedSegments(currentPoint, targetPoint) {
    var intersectedSegments = [];
    for (var l = 0; l < surfaceSegments.length; l++) {
        var segment = surfaceSegments[l];

        if (intersects(currentPoint, targetPoint, segment.start, segment.end)) {
            intersectedSegments.push(segment);

            console.log('intersects with: currentPoint ' + JSON.stringify(currentPoint) + '; flatGround.centralPoint: ' + JSON.stringify(flatGround.centralPoint)
                + '; segment.start: ' + JSON.stringify(segment.start) + '; segment.end: ' + JSON.stringify(segment.end));
        }
    }
    return intersectedSegments;
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

function getFlatGround(){
    flatGround = {};

    for(var i = 0; i < surfacePoints.length; i++) {
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

    flatGround.centralPoint = {
        x: (flatGround.x1 + flatGround.x0)/2,
        y:  flatGround.y1
    };

    flatGround.contains = function (point) {
        return point.y == flatGround.y0 && point.x >= flatGround.x0 && point.x <= flatGround.x1;
    };

    return flatGround;
}


