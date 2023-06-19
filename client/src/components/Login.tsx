import React, {useState} from 'react';

function Login() {

	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");

	const changeUsername = (event: React.ChangeEvent<HTMLInputElement>) => {
		setUsername(event.target.value);
	}

	const changePassword = (event: React.ChangeEvent<HTMLInputElement>) => {
		setPassword(event.target.value);
	}

	const submitHandler = (event: React.FormEvent<HTMLFormElement>) => {
		event.preventDefault();

		console.log("Username: " + username);
		console.log("Password: " + password);
	}

	// TODO: We con improve this validation like minimum length.
	const isSubmitDisabled = username.trim() === '' || password.trim() === '';

	return (
		<form onSubmit={submitHandler} className="container">
			<h1>Login</h1>
			<div className="form-group">
				<input type="text"
							 id="username"
							 name="username"
							 placeholder="Enter your username" value={username}
							 onChange={changeUsername}/>
			</div>
			<div className="form-group">
				<input type="password"
							 id="password"
							 name="password"
							 placeholder="Enter your password"
							 value={password}
							 onChange={changePassword}/>
			</div>
			<button disabled={isSubmitDisabled} className="btn" type="submit">Login</button>
		</form>
	);
}

export default Login;