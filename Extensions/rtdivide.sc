+ Number {

	rtdivide {
		// will return an array of numbers based on a divided structure related to this number as top level]
		arg divisions = [2,3], prob_map=RTProbMap(), depth=2;
		var rt;

		// first generate an rt
		rt = 1.rtgenerate(divisions, prob_map, depth).rtsanitise

		// then return the parsed rt as output
		^rt.rtparse(this)
	}

}
