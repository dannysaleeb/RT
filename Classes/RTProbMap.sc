RTProbMap {
	var <probs, <change_factors;

	*new {
		arg div=1, div_change=1, zero=1, zero_change=1, one=1, one_change=1, cont=1, cont_change=1;
		var probs, change_factors;

		probs = Dictionary.newFrom([\div, div, \one, one, \zero, zero, \cont, cont]);
		change_factors = Dictionary.newFrom([\div, div_change, \one, one_change, \zero, zero_change, \cont, cont_change]);

		^super.newCopyArgs(probs, change_factors)
	}

	update {
		probs.keysValuesChange({
			arg k, v;
			if (
				(v * change_factors[k]) < 1,
				{ v * change_factors[k] }, { v }
			)
		})
	}
}