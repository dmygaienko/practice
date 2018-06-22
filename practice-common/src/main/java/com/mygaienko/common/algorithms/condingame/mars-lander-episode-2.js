/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
var surface = [];

var surfaceN = parseInt(readline()); // the number of points used to draw the surface of Mars.
printErr('surfaceN' + surfaceN);
for (var i = 0; i < surfaceN; i++) {
    var inputs = readline().split(' ');
    var landX = parseInt(inputs[0]); // X coordinate of a surface point. (0 to 6999)
    var landY = parseInt(inputs[1]);
    surface.push({'x': landX, 'y': landY});// Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
}

var flatGround = getFlatGround();
var lander;

printErr(JSON.stringify(flatGround));
printErr(JSON.stringify(surface));

var moving = true;
var landing = false;
var slowdown = false;
var mitigate = false;

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


    if (!lander) {
        lander = new Lander(flatGround, X, rotate, 4);
    }

    printErr(JSON.stringify(lander));

    lander.execute(hSpeed, vSpeed, X, Y);

    print(lander.rotate + ' ' + lander.power);
    //print('-20 3');
}

function getFlatGround(){
    flatGround = {};

    for(var i = 0; i < surface.length; i++) {
        if (flatGround.y0 == surface[i].y) {

            for (var f = i; f < surface.length; f++) {
                if (flatGround.y0 == surface[f].y) {
                    flatGround.x1 = surface[f].x;
                    flatGround.y1 = surface[f].y;
                }
            }

            if (flatGround.x1 - flatGround.x0 >= 1000) {
                break;
            }
        } else {
            flatGround.x0 = surface[i].x;
            flatGround.y0 = surface[i].y;
        }
    }

    flatGround.x = (flatGround.x1 + flatGround.x0)/2

    return flatGround;
}

function Lander(flatGround, x, rotate, power) {

    self = this;

    this.currentState;
    this.nearestX;
    this.furtherX;
    this.direction = 1;
    this.power = power;
    this.rotate = rotate;

    this.landing = {
        execute: function(hSpeed, vSpeed, x, y) {
            printErr('landing');
            self.rotate = 0;
            if (vSpeed < -35 && self.power < 4) {
                self.power++;
            } else if (vSpeed > -40 && self.power > 3){
                self.power--;
            }
        }
    }

    this.slowdown = {
        execute: function(hSpeed, vSpeed, x, y) {
            printErr('slowdown');
            if (Math.abs(hSpeed) < 5) {
                self.currentState = self.landing;
                self.currentState.execute();
            } else if (between(self.nearestX, x, self.furtherX) && Math.abs(hSpeed) > 30) {
                self.rotate = self.direction * (60);
            } else {
                self.rotate = self.direction * (45);
            }
        }
    }

    this.moving = {
        execute: function(hSpeed, vSpeed, x, y) {
            printErr('moving');
            printErr('to nearest x: ' + Math.abs(self.nearestX - x));
            if (Math.abs(hSpeed) > 70) {
                self.currentState = self.mitigate;
                //self.currentState.execute();
            } else if(Math.abs(self.nearestX - x) > 400) {
                self.rotate = self.direction * (-15);
            } else {
                self.currentState = self.slowdown;
                //self.currentState.execute();
            }
        }
    }

    this.mitigate = {
        execute: function(hSpeed, vSpeed, x, y) {
            printErr('mitigate');
            if (between(self.nearestX, x, self.furtherX)) {
                self.currentState = self.slowdown;
                self.currentState.execute();
            } else if (Math.abs(hSpeed) > 70) {
                self.rotate = self.direction * 30;
            } else {
                self.currentState = self.moving;
                //  self.currentState.execute();
            }
        }
    }

    this.init = function() {
        this.currentState = this.moving;
        printErr(this.moving);
        printErr(this.currentState);
        printErr(this.slowdown);

        //if (x - flatGround.x0 < 0) {
        if (before(x, flatGround.x0)) {
            printErr('before');
            this.nearestX = flatGround.x0;
            this.furtherX = flatGround.x1;
        } else if (before(flatGround.x1, x)) {
            printErr('after');
            this.nearestX = flatGround.x1;
            this.furtherX = flatGround.x0;
            this.direction = -1;
        } else {
            printErr('under');
            this.nearestX = x;
            this.furtherX = x;
            this.currentState = this.landing;
        }
    }

    function before(x1, x2) {
        return x1 < x2;
    }

    function between(x1, x2, x3) {
        printErr('between: ' + x1 + ' ' + x2 + ' ' + x3 );
        printErr(' res: ' + (x1 < x2 && x2 < x3) || (x1 > x2 && x2 > x3));
        return (x1 < x2 && x2 < x3) || (x1 > x2 && x2 > x3);
    }

    this.execute = function(hSpeed, vSpeed, x, y) {
        this.currentState.execute(hSpeed, vSpeed, x, y);
    }

    this.init();
}



