import React = require("react");

const Login: React.FC = () => {

	const [email, setEmail] = React.useState("");
	const [password, setPassword] = React.useState("");

	const handleEmailChange = (event) => {
		setEmail(event.target.value);
	}

	const handlePasswordChange = (event) => {
		setPassword(event.target.value);
	}

	const handleSubmit = (event) => {
		event.preventDefault();
		const data = {email, password};
		console.log(data);
	}


	return (
		<>
			<div>
				<h2>Login</h2>
				<form onSubmit={handleSubmit}>
					<div>
						<label htmlFor="email">Email:</label>
						<input
							type="email"
							id="email"
							value={email}
							onChange={handleEmailChange}
							required
						/>
					</div>
					<div>
						<label htmlFor="password">Password:</label>
						<input
							type="password"
							id="password"
							value={password}
							onChange={handlePasswordChange}
							required
						/>
					</div>
					<button type="submit">Submit</button>
				</form>
			</div>
		</>
	);
}

export default Login;