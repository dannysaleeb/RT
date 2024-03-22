RTProbMap {
	var <probs, <change_factors;

	*new {
		// [div, one, cont, zero] in both cases
		arg probs=[1,1,1,1], change_factors=[1,1,1,1];

		^super.newCopyArgs(probs, change_factors)
	}

	update {
		probs.do({
			arg val, count;
			probs[count] = probs[count] * change_factors[count]
		})
	}
}