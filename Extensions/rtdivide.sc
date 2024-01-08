+ Number {

	rtdivide {
		// will return an array of numbers based on a divided structure related to this number as top level]
		arg divisions = [2,3], probability=0.5, probability_change_factor=0.8, depth=2;
		var rt;

		// first generate an rt
		rt = 1.rtgenerate(divisions, probability, probability_change_factor, depth).rtsanitise;

		// then return the parsed rt as output
		^rt.rtparse(this)
	}

}
