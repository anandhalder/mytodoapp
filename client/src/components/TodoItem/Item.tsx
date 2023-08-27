import { key } from "localforage";
import React from "react";

interface ItemProps {
	key: number;
	value: string;
	editItem: (index: number, newValue: string) => void;
	deleteItem: (index: number) => void;
}

const Item: React.FC<ItemProps> = (ItemProps) => {
	return (
	<li key={ItemProps.key}>
		{ItemProps.value}
		<button onClick={() => ItemProps.editItem(ItemProps.key, "newValue")}>Edit</button>
		<button onClick={() => ItemProps.deleteItem(ItemProps.key)}>Delete</button>
	</li>);
}

export default Item;