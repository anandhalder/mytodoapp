import React, {useState} from 'react';

function Login() {

	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");

	return (
		<div className="Login">
			<form>
				<input type="text" placeholder="Username" value={username} />
				<input type="password" placeholder="Password" value={password} />
				<button type="submit">Login</button>
			</form>
		</div>
	);
}

export default Login;