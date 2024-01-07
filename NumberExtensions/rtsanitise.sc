+ Number {

	rtsanitise {

		var processed;

		if (
			this.isKindOf(Array),
			{
				processed = this.collect({ arg item; item.rtsanitise });

				if (
					processed != nil,
					{
						switch(
							processed[0],
							1, { if ( processed[1..].every({arg item; item == -1}), {^1})},
							0, { if ( processed[1..].every({arg item; item == 0}), {^0})},
							-1, { if ( processed[1..].every({arg item; item == -1}), {^-1})},
						);
						^processed
					},
					{
						^processed
					}
				)
			},
			{
				^this
			}
		)
	}

}
