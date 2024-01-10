ProbMap {
	var <div, <div_change, <zero, <zero_change, <one, <one_change, <cont, <cont_change;

	*new {
		arg div, div_change, zero, zero_change, one, one_change, cont, cont_change;

		^super.newCopyArgs(div, div_change, zero, zero_change, one, one_change, cont, cont_change)
	}
}