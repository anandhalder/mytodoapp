import { useState } from "react";
import Item from "../TodoItem/Item";

function List() {

	const [list, setList] = useState(["Todo 1", "Todo 2", "Todo 3", "Todo 4"]);

	const deleteItem = (index: number) => {
		list.splice(index, 1);
		setList([...list]);
	}

	const editItem = (index: number, newValue: string) => {
		list[index] = newValue;
		setList([...list]);
	}


	return (<div>
		<h1>Todo List</h1>
		<ul>
			{list.map((todo, index) => (<Item deleteItem={deleteItem} editItem={editItem} key={index} value={todo}/>))}
		</ul>
	</div>);
}

export default List;