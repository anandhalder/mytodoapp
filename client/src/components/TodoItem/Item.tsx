import React from "react";

interface ItemProps {
	key: number;
	value: string;
}

const Item: React.FC<ItemProps> = (item) => {
	return (<li key={item.key}>{item.value}</li>);
}

export default Item;