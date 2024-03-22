+ Number {

	rtgenerate {

		arg divisions=[2,3], prob_map=RTProbMap.new(), depth=2, noteon=false, topLevel=true;
		var rtn_arr = [];

		this.do({

			// make sure probability map is set to correct values from top level
			if(
				topLevel == true,
				{ prob_map.reset }
			);

			if (
				// check whether to divide this beat if depth limit not reached
				(prob_map.probs[0].coin) && (depth > 0),
				{
					var recursion_result = divisions.choose.rtgenerate(divisions, prob_map.update, depth-1, noteon, false);

					rtn_arr = rtn_arr.add(recursion_result[0].rtsanitise);
					noteon = recursion_result[1];

				},
				{
					var choice = [1, -1, 0].wchoose([prob_map.probs[1], prob_map.probs[2], prob_map.probs[3]].normalizeSum);

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
									var outcome = [1, 0].wchoose([prob_map.probs[1], prob_map.probs[3]].normalizeSum);
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
				}
			)
		});

		if (
			topLevel == true,
			{
				^rtn_arr.rtsanitise
			},
			{
				^[rtn_arr.rtsanitise, noteon]
			}
		)
	}

}

// I'm trying to avoid having a beat eliminated ... so basically, if it evaluates to zero, return this...
