RTProbMap {
	var <probs, <change_factors;

	// I think I need this to take all of these values, but then just hold them in a dictionary, so they can easily be updated -- start_probs, and start_change_factors ... for div, one, zero and continuation

	*new {
		arg div, div_change, zero, zero_change, one, one_change, cont, cont_change;
		var probs, change_factors;

		probs = Dictionary.newFrom([\div, div, \one, one, \zero, zero, \cont, cont]);
		change_factors = Dictionary.newFrom([\div, div_change, \one, one_change, \zero, zero_change, \cont, cont_change]);

		^super.newCopyArgs(probs, change_factors)
	}

	update {
		// implement
	}
}