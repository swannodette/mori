require(["mori"], function(mori) {

    console.log("mori loaded as an AMD module!");

    var x = mori.vector(1,2,3),
        y = mori.conj(x,4);
    
    console.log(mori.into_array(x)); // => [1, 2, 3]
    console.log(mori.into_array(y)); // => [1, 2, 3, 4]

});
