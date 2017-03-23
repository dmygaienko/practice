/**
 * Created by enda1n on 22.03.2017.
 */
this.lines = [];

this.printErr = function(msg) {
    console.log(msg);
};

this.print = function(msg) {
    console.log(msg);
};

this.readline = function() {
    return this.lines.shift();
};

this.robustDoubleGateways = function() {
    this.lines.push('8 13 2');
    //links
    this.lines.push('6 2');
    this.lines.push('7 3');
    this.lines.push('6 3');
    this.lines.push('5 3');
    this.lines.push('3 4');
    this.lines.push('7 1');
    this.lines.push('2 0');
    this.lines.push('0 1');
    this.lines.push('0 3');
    this.lines.push('1 3');
    this.lines.push('2 3');
    this.lines.push('7 4');
    this.lines.push('6 5');
    //gateways
    this.lines.push('4');
    this.lines.push('5');
    //agent turns
    this.lines.push('0');
    this.lines.push('3');
    this.lines.push('4');
    this.lines.push('7');
};

this.complexMesh = function() {
    this.lines.push('37 81 4');
    //links
    this.lines.push('2 5','14 13','16 13','19 21','13 7','16 8','35 5','2 35','10 0','8 3','23 16','0 1','31 17','19 22');
    this.lines.push('12 11','1 2','1 4','14 9','17 16','30 29','32 22','28 26','24 23','20 19','15 13','18 17','6 1','29 28');
    this.lines.push('15 14','9 13','32 18','25 26','1 7','34 35','33 34','27 16','27 26','23 25','33 3','16 30','25 24','3 2');
    this.lines.push('5 4','31 32','27 25','19 3','17 8','4 2','32 17','10 11','29 27','30 27','6 4','24 15','9 10','34 2');
    this.lines.push('11 6','33 2','14 10','12 6','0 6','19 17','20 3','21 20','21 32','15 16','0 9','23 27','11 0');
    this.lines.push('3 1','23 15','18 19','7 0','19 8','21 22','7 36','13 36','8 36','28 27','22 18','9 7');
    //gateways
    this.lines.push('0', '16', '18', '26');
    //agent turns
    //this.lines.push('2', '3', '19', '17', '18');
    this.lines.push('2', '1', '0');
};

this.skynetCoreNetwork = function() {
    this.lines.push('49 62 17');
    //links
    this.lines.push('1 0','1 2','2 3','3 4','4 5','3 6','7 3','9 5','5 8','13 0','14 13','15 14');
    this.lines.push('16 13','17 14','18 15','19 15','19 20','19 18','18 20','22 20','0 23','23 14');
    this.lines.push('23 16','21 20','24 21','24 17','28 27','27 29','27 26','26 25','25 21','21 30');
    this.lines.push('30 34','31 35','32 36','38 33','33 37','33 32','32 31','31 30','33 39','9 40');
    this.lines.push('39 40','41 2','39 42','42 43','43 10','34 46','35 45','37 40','41 46','44 45');
    this.lines.push('44 40','7 41','5 10','47 12','11 47','10 47','43 47','46 48','45 48','48 7');
    //gateways
    this.lines.push('6','7','8','9','11','12','16','17','18','22','28','29','34','35','36','37','38');
    //agent turns
    //this.lines.push('2', '3', '19', '17', '18');
    this.lines.push('0', '13', '14', '15');
};

// this.complexMesh();
this.skynetCoreNetwork();




