var agg = (function () {
    var CONSTANT1 = "CONSTANT1";
    console.log("console log: closure");

    function agg() {


        var index = 0,
            data = [1, 2, 3, 4, 5],
            length = data.length;

        console.log("console log: from constructor");

        // API
        return {
            next: function () {
                var element;
                if (!this.hasNext()) {
                    return null;
                }
                element = data[index];
                index = index + 2;
                return element;
            },
            hasNext: function () {
                return index < length;
            }
        };
    }

    return agg;

}());