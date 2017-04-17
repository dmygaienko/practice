var Game01;
var AltitudeStrategy;

var $log = function() {};

// Game

(function() {
    'use strict';

    Game01 = function(initGameInputs) {
        var game = this;

        $log = initGameInputs.log;
        var strategy;

        (function() {
            var x, y;
            for (var i = 0; i < initGameInputs.land.length; i++) {
                if (y === initGameInputs.land[i].y) {
                    strategy = new AltitudeStrategy({
                        start: x,
                        end: initGameInputs.land[i].x,
                        height: y
                    }, initGameInputs.land);
                    return;
                }
                x = initGameInputs.land[i].x;
                y = initGameInputs.land[i].y;
            }
        })();

        game.playOneTurn = function(inputs) {
            //$log(JSON.stringify(inputs));
            strategy = strategy.next(inputs, initGameInputs.land);
            var move = strategy.move(inputs);
            move = doctorMove(inputs, move);
            return move.angle + ' ' + move.power;
        };

        function doctorMove(inputs, move) {
            var maxAngle = Math.min(90, 90 + inputs.vSpeed);

            if (move.angle < -maxAngle) { move.angle = -maxAngle; }
            if (move.angle > maxAngle) { move.angle = maxAngle; }
            move.angle = Math.round(move.angle);

            var maxPower = (inputs.y < 2800) ? 4 : 3;
            if (move.power > maxPower) { move.power = maxPower; }

            return move;
        }
    };

})();


(function() {
    'use strict';

    function closeToLandingArea(x, landingArea, hSpeed) {
        if (x < landingArea.end && x > landingArea.start) { return true; }

        var goal = Math.abs(x - landingArea.start) < Math.abs(x - landingArea.end)
            ? landingArea.start
            : landingArea.end;

        var turnsToGoal = (goal - x) / hSpeed;
        $log("TTG: " + turnsToGoal);
        if (turnsToGoal < 0) return false;
        return turnsToGoal < 9;
    }

    function intersectPoint(a, b, c, d) {
        var aSlope = (a.y - b.y) / (a.x - b.x);
        var aYIntercept = a.y - a.x*aSlope;
        var cSlope = (c.y - d.y) / (c.x - d.x);
        var cYIntercept = c.y - c.x*cSlope;

        if (a.x == b.x) return { x: a.X, y: aSlope*a.X + aYIntercept};

        var x = (cYIntercept - aYIntercept) / (aSlope - cSlope);
        var y = cSlope*x + cYIntercept;
        return { x: x, y: y };
    }

    function isInRange(a, b, p) {
        return p.x >= Math.min(a.x, b.x) - 0.001 && p.x <= Math.max(a.x, b.x) + 0.001 &&
            p.y >= Math.min(a.y, b.y) - 0.001 && p.y <= Math.max(a.y, b.y) + 0.001;
    }


    function isIntersecting(a, b, c, d) {
        var p = intersectPoint(a, b, c, d);
        $log("intersect at " + JSON.stringify(p));
        return isInRange(a, b, p) && isInRange(c, d, p);
    }

    function intersectsLand(a, b, land) {
        for (var i = 1; i < land.length; i++) {
            var c = land[i-1];
            var d = land[i];
            var aa = { x: a.x, y: a.y };

            if (c.y == d.y) continue;

            $log("check " + JSON.stringify(aa) + " " + JSON.stringify(b) + " " + JSON.stringify(c) + " " + JSON.stringify(d));
            if (isIntersecting(aa, b, c, d)) {
                $log("INTERSECT");
                return true;
            }
        }
        return false;
    }

    function StartingStrategy(landingArea) {
        var strategy = this;

        strategy.shouldGoToNext = function(inputs) {
            if (!landingArea.goal) {
                if (Math.abs(landingArea.start - inputs.x) <
                    Math.abs(landingArea.end - inputs.x)) {
                    landingArea.goal = landingArea.start;
                } else {
                    landingArea.goal = landingArea.end;
                }
            }
            var desiredVector = landingArea.goal - inputs.x;
            if (cruisingInTheRightDirection(inputs.hSpeed, desiredVector)) {
                return true;
            }
            return false;
        };

        strategy.next = function(inputs) {
            if (strategy.shouldGoToNext(inputs)) {
                $log("Seeking");
                delete landingArea.goal;
                return new SeekingStrategy(inputs, landingArea);
            }
            return strategy;
        };

        strategy.move = function(inputs) {
            var desiredVector = landingArea.goal - inputs.x;
            var move = { power: 4 };
            if (needToAccelerateToTheRight(inputs.hSpeed, desiredVector)) {
                move.angle = -30;
            } else {
                move.angle = 30;
            }
            return move;
        };

        function cruisingInTheRightDirection(speed, desiredVector) {
            return cruisingSpeedIsOkay(speed) && (speed * desiredVector) > 0;
        }

        function cruisingSpeedIsOkay(speed) {
            return Math.abs(speed) > 52 && Math.abs(speed) < 65;
        }

        function needToAccelerateToTheRight(speed, desiredVector) {
            if (Math.abs(speed) < 65) { return desiredVector > 0; }
            return speed < 0;
        }
    }

    function SeekingStrategy(inputs, landingArea) {
        var strategy = this;

        var goal = {
            x: landingArea.start + (landingArea.end - landingArea.start) / 2,
            y: landingArea.height
        };

        strategy.next = function(inputs, land) {
            if (intersectsLand(inputs, goal, land)) {
                return strategy;
            }
            $log("Turning");
            return new TurningStrategy(landingArea);
        };

        strategy.move = function(inputs) {
            var move = { angle: 0, power: 3 };
            if (inputs.y < landingArea.height + 612) { move.power = 4; }
            if (inputs.vSpeed < -5) { move.power = 4; }
            return move;
        };
    }

    function TurningStrategy(landingArea) {
        var strategy = this;

        var startStrategy = new StartingStrategy(landingArea);

        strategy.next = function(inputs) {
            if (startStrategy.shouldGoToNext(inputs) ||
                closeToLandingArea(inputs.x, landingArea, inputs.hSpeed)) {
                $log("Cruising");
                return new CruisingStrategy(landingArea);
            }
            return strategy;
        };

        strategy.move = function(inputs) {
            return startStrategy.move(inputs);
        };
    }

    AltitudeStrategy = function(landingArea, land) {
        var strategy = this;

        var peak;

        (function() {
            peak = land[0].y;
            for (var i = 1; i < land.length; i++) {
                if ((land[i].y) > peak) peak = land[i].y;
            }
            peak -= 10;
            $log("peak: " + peak);
        })();

        strategy.next = function(inputs) {
            if (peak > inputs.y) return strategy;

            $log("Starting");
            return new StartingStrategy(landingArea);
        };

        strategy.move = function() {
            return { angle: 10, power: 4 };
        };
    };

    function CruisingStrategy(landingArea) {
        var strategy = this;

        strategy.next = function(inputs) {
            if (closeToLandingArea(inputs.x, landingArea, inputs.hSpeed)) {
                $log("Stabilizing");
                return new StabilizingStrategy(landingArea);
            }
            return strategy;
        };

        strategy.move = function(inputs) {
            var move = { angle: 0, power: 3 };
            if (inputs.y < landingArea.height + 612) { move.power = 4; }
            if (inputs.vSpeed < -24) { move.power = 4; }
            return move;
        };
    }

    function StabilizingStrategy(landingArea) {
        var strategy = this;

        strategy.next = function(inputs) {
            if (Math.abs(inputs.hSpeed) < 10) {
                $log("Landing");
                return new LandingStrategy();
            }
            return strategy;
        };

        strategy.move = function(inputs) {
            return {
                power: 4,
                angle: inputs.hSpeed < 0 ? -55 : 55
            };
        };
    }

    function LandingStrategy() {
        var strategy = this;

        strategy.next = function() {
            return strategy;
        };

        strategy.move = function(inputs) {
            var move = { angle: 0, power: 1};
            if (inputs.vSpeed < -38) { move.power = 4; }
            return move;
        };
    }

})();







/* ***************************************************************** */
// RUN IN CODEINGAME.COM

/*global readline*/
/*global print*/
/*global printErr*/

(function() {
    'use strict';

    var codingameLog = function () {};

    if (typeof readline === 'function') {
        codingameLog = printErr;
        startInCodingameEnv();
    }

    function readIntLine() {
        return readline().split(' ').map(function(el) {
            return parseInt(el, 10);
        });
    }

    function startInCodingameEnv() {
        function parseInitialGameInput() {
            var inputs = readIntLine();
            return {
                width: inputs[0],
                land: readLandData(inputs[0]),
                log: codingameLog
            };
        }

        function readLandData(numPoints) {
            var out = [];
            for (var i = 0; i < numPoints; i++) {
                var inputs = readIntLine();
                out.push({ x: inputs[0], y: inputs[1] });
            }
            return out;
        }

        function parseEachTurnsInput() {
            var inputs = readIntLine();
            return {
                x: inputs[0],
                y: inputs[1],
                hSpeed: inputs[2],
                vSpeed: inputs[3],
                fuel: inputs[4],
                angle: inputs[5],
                power: inputs[6]
            };
        }

        function gameLoop(game) {
            while (true) {
                var move = game.playOneTurn(parseEachTurnsInput());
                print(move);
            }
        }

        function startGame() {
            var gameData = parseInitialGameInput();
            gameLoop(new Game01(gameData));
        }

        startGame();
    }

})();