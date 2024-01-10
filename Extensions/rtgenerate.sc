+ Number {

	// so currently probability and change_factor decide whether or not to divide

	rtgenerate {

		arg divisions=[2,3], prob_map=RTProbMap.new(), depth=2, noteon=false, topLevel=true;
		var rtn_arr = [];

		// why doesn't this function do the process for the number given?

		this.do({
			if (
				// if true, divide
				(prob_map.probs[\div].coin) && (depth > 0),
				{
					var recursion_result = divisions.choose.rtgenerate(divisions, prob_map.update, depth-1, noteon, false);

					rtn_arr = rtn_arr.add(recursion_result[0].rtsanitise);
					noteon = recursion_result[1];

				},
				{
					var choice = [1, -1, 0].wchoose([prob_map.probs[\one], prob_map.probs[\cont], prob_map.probs[\zero]].normalizeSum);

					prob_map.probs.postln;
					choice.postln;

					switch(
						choice,
						1, {
							rtn_arr = rtn_arr.add(choice);
							noteon = true;
						},
						-1, {
							if (
								noteon == true,
								{
									rtn_arr = rtn_arr.add(choice)
								},
								{
									var outcome = [1, 0].wchoose([prob_map.probs[\one], prob_map.probs[\zero]].normalizeSum);
									rtn_arr = rtn_arr.add(outcome);
									if (outcome == 1, { noteon = true })
								}
							)
						},
						0,
						{
							rtn_arr = rtn_arr.add(choice);
							noteon = false;
						}
					)

					/*//else if 2nd coin toss true, add 1
					if (
						// same prob for 1
						prob_map.probs[\one].coin,
						{
							rtn_arr = rtn_arr.add(1);
							noteon = true;
						},
						// else if 3rd coin toss true and noteon is true
						{
							if (
								(prob_map.probs[\cont].coin) && (noteon == true),
								{
									// add continuation -1
									rtn_arr = rtn_arr.add(-1)
								},
								{
									// else add 0
									rtn_arr = rtn_arr.add(0);
									noteon = false
								}
							)
						}
					)*/
				}
			)
		});

		if (
			topLevel == true,
			{
				^rtn_arr.rtsanitise
			},
			{
				^[rtn_arr, noteon]
			}
		)
	}

}
