+ Number {

	// so currently probability and change_factor decide whether or not to divide

	rtgenerate {

		arg divisions=[2,3], probability=0.5, probability_change_factor=0.8, depth=2, noteon=false, topLevel=true;
		var rtn_arr = [];

		// why doesn't this function do the process for the number given?

		this.do({ // this number of times do ... in each recursion, this becomes new number
			if (
				// if true, divide
				(probability.coin) && (depth > 0),
				{
					var recursion_result = divisions.choose.rtgenerate(divisions, probability * probability_change_factor, probability_change_factor, depth-1, noteon, false);

					rtn_arr = rtn_arr.add(recursion_result[0].rtsanitise);
					noteon = recursion_result[1];

				},
				{
					//else if 2nd coin toss true, add 1
					if (
						// same prob for 1
						probability.coin,
						{
							rtn_arr = rtn_arr.add(1);
							noteon = true;
						},
						// else if 3rd coin toss true and noteon is true
						{
							if (
								(probability.coin) && (noteon == true),
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
				^[rtn_arr, noteon]
			}
		)
	}

}
