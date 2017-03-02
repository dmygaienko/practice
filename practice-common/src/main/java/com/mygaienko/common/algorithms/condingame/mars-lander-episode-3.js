var surfaces = ['0 450','300 750','1000 450','1500 650','1800 850','2000 1950','2200 1850','2400 2000','3100 1800','3150 1550','2500 1600','2200 1550','2100 750','2200 150','3200 150','3500 450','4000 950','4500 1450','5000 1550','5500 1500','6000 950','6999 1750'];

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

console.log(JSON.stringify(surfacePoints.length));
console.log(JSON.stringify(surfacePoints));
console.log(JSON.stringify(surfaceSegments.length));
console.log(JSON.stringify(surfaceSegments));
console.log(JSON.stringify(flatGround));




























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

    flatGround.x = (flatGround.x1 + flatGround.x0)/2;
    flatGround.y = flatGround.y1;

    return flatGround;
}


