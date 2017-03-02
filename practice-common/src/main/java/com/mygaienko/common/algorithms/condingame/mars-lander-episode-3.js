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
        end: surfacePoints[j]
    });
}

var flatGround = getFlatGround();
var currentPoint = {x: 6500, y: 2600};
console.log(JSON.stringify(surfacePoints.length));
console.log(JSON.stringify(surfacePoints));
console.log(JSON.stringify(surfaceSegments.length));
console.log(JSON.stringify(surfaceSegments));
console.log(JSON.stringify(flatGround));

var intersections = 0
for (var l = 0; l < surfaceSegments.length; l++) {
    var segment = surfaceSegments[l];

    if (intersects(currentPoint, flatGround.centralPoint, segment.start, segment.end)) {
        intersections++;
        console.log('intersects with: currentPoint ' + JSON.stringify(currentPoint) +
            +'; flatGround.centralPoint: ' + JSON.stringify(flatGround.centralPoint) +
            +'; segment.start: ' + JSON.stringify(segment.start), +
            +'; segment.end: ' + JSON.stringify(segment.end));
    }
}

console.log(JSON.stringify(intersections));

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

    return flatGround;
}


