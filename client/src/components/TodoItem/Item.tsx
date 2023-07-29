import React from "react";

interface ItemProps {
	key: number;
	value: string;
}

const Item: React.FC<ItemProps> = (item) => {
	return (<li key={item.key}>{item.value}
		<button>Add</button>
		<button>Delete</button>
	</li>);
}

export default Item;