+ SequenceableCollection {

	rtparse {
		arg beat_dur, stored=0, rtn_arr=[], topLevel=true;

		if (topLevel == true && this.isKindOf(Number), {
			if (
				this == 1, { ^beat_dur }, { ^Rest(beat_dur) }
			)
		});

		this.do({
			arg item;

			switch(
				item.isKindOf(Array),
				true,
				{
					var result;

					result = item.rtparse(beat_dur / item.size, stored, rtn_arr, false);

					rtn_arr = result[0];
					stored = result[1];

				},
				false,
				{
					switch(
						item,
						1, {
							if (
								stored != 0,
								{
									rtn_arr = rtn_arr.add(stored);
									// add note_arr =
									stored = beat_dur;
								},
								{
									stored = beat_dur
								}
							)
						},
						0, {
							if (
								stored != 0,
								{
									rtn_arr = rtn_arr.add(stored);
									stored = 0;

									rtn_arr = rtn_arr.add(Rest(beat_dur))
								},
								{
									rtn_arr = rtn_arr.add(Rest(beat_dur))
								}
							)
						},
						-1, {
							stored = stored + beat_dur;
						}
					)
				}
			)
		});
		if (
			topLevel == true,
			{
				// return this if array is empty...
				if (
					stored > 0,
					{
						rtn_arr = rtn_arr.add(stored);
						^rtn_arr
					},
					{
						if (
							rtn_arr.size > 0,
							{
								// Weed out case where all members of array are rests
								var all_rests = true;
								rtn_arr.do({
									arg item;
									if (item.isKindOf(Rest) == false,
										{
											^rtn_arr
										}
									);
								});
								// must be all rests, so return rest (not sure if this is the behaviour I want, might be better to return itself in this case)
								^Rest(beat_dur)
							},
							{
								// returns itself if array is empty
								^this
							}
						)
					}
				)
			},
			{
				^[rtn_arr, stored]
			}
		)
	}
}