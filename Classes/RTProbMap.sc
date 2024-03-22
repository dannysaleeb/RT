RTProbMap {
	var <probs, <change_factors, <orig_probs;

	*new {
		// [div, one, cont, zero] in both cases
		arg probs=[1,1,1,1], change_factors=[1,1,1,1];
		var orig_probs;

		orig_probs = probs.copy;

		^super.newCopyArgs(probs, change_factors, orig_probs)
	}

	update {
		probs.do({
			arg val, count;
			probs[count] = probs[count] * change_factors[count]
		})
	}

	reset {
		probs = orig_probs
	}
}