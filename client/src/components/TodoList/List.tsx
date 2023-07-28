import Item from "../TodoItem/Item";

function List() {

	let todoList: string[] = ["Todo 1", "Todo 2", "Todo 3", "Todo 4"];

	return (<div>
		<h1>Todo List</h1>
		<ul>
			{todoList.map((todo, index) => (<Item key={index} value={todo}/>))}
		</ul>
	</div>);
}

export default List;