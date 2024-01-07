+ Number {

	// need to add possibility of processing notes also ... first rename rt_arr

	rtgenerate {

		arg divisions=[2,3], probability=0.5, probability_change_factor=0.8, depth=2, noteon=false, topLevel=true, notenum=0;
		var rt_arr = [], nt_arr = [];

		// nt has Number number of indices in it ... to refer to the possible (undivided) notes in the array.

		this.do({ // this number of times do ... in each recursion, this becomes new number
			if (
				// if true, divide
				(probability.coin) && (depth > 0),
				{
					var recursion_result = divisions.choose.rtgenerate(divisions, probability * probability_change_factor, probability_change_factor, depth-1, noteon, false);

					rt_arr = rt_arr.add(recursion_result[0].rtsanitise);
					nt_arr = nt_arr.add(recursion_result[1]);
					noteon = recursion_result[2];

				},
				{
					//else if 2nd coin toss true, add 1
					if (
						probability.coin,
						{
							rt_arr = rt_arr.add(1);
							noteon = true;
						},
						// else if 3rd coin toss true and noteon is true
						{
							if (
								(probability.coin) && (noteon == true),
								{
									// add continuation -1
									rt_arr = rt_arr.add(-1);
								},
								{
									// else add 0
									rt_arr = rt_arr.add(0);
									noteon = false;
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
				^[rt_arr.rtsanitise, nt_arr]
			},
			{
				^[rt_arr, nt_arr, noteon]
			}
		)
	}

}

/*// what exactly do I need??

[1, 1, 0, 1]
// corresponds to
[0, 1, 2, 3]

// so then
[1, [1, 0], 0, 1]
// is
[0, [1, 1], 2, 3]

// and then
[1, [-1, 0], 0, [1, [-1, 0]]]
// would be
[0, [0, 1], 2, [3, [3, 3]]]



// the numbers represent indices in a list of actual notes, which must be four notes long ... and those notes represent the root notes of whatever division is happening. e.g.
~notes = [60, 64, 67, 72]

// so ...

~getNotes = {
	arg nt, rtn;

	rtn = nt.collect({
		arg item;
		if(
			item.isKindOf(Number),
			{
				~notes[item].postln;
				~notes[item]
			},
			{
				~getNotes.value(item).postln;
				~getNotes.value(item)
			}
		)
})}

// this works great ...


Pbind(
	\midinote, Pseq(~getNotes.value([0, [0, 1], 2, [3, [3, 3]]]).flatten.flatten, inf),
	\dur, Pseq([1, [-1, 0], 0, [1, [-1, 0]]].rtparse(1), inf)
).play

s.boot

~getNotes.value([0, [0, 1], 2, [3, [3, 3]]]) // recursively flatten before outputting... or add to a single nt array in the method ...

// SO therefore I need to keep a sort of platonic array of notes that can be referred to in variations ... which is interesting ... and the indices will therefore always refer to those Platonic notes as the roots of all divisions.*/