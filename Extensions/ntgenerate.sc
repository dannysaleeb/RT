+ Object {

	ntgenerate {
		arg nt_index = 0, topLevel = true;
		var rtn_arr;

		// check if valid class type
		if(
			(this.isKindOf(Number) == false) && (this.isKindOf(SequenceableCollection) == false), {^this}
		);

		// check if number rather than collection
		if (
			this.isKindOf(Number), { ^this },
			{// else
				// if valid type, then get flattened note-tree
				rtn_arr = this.collect({
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
				}).flatBelow(0);

				// filter out nil
				^rtn_arr = rtn_arr.reject({
					arg item;
					item == nil
				})
			}
		)
	}

}