+ SequenceableCollection {

	// need to add possibility of processing notes also ... first rename rt_arr

	ntgenerate {
		arg nt_index = 0, topLevel = true;

		^this.collect({
			arg item, count;
			if (topLevel == true, {nt_index = count});
			if (
				item.isKindOf(Number),
				{
					if (
						(item == 1) || (item == 0),
						{
							nt_index
						}
					)
				},
				{
					item.ntgenerate(nt_index, false);
				}
			)
		})
	}
}