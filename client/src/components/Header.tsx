import React from 'react';

function Header() {	// This is a component
	return (
		<header>
			<div className="header-container">
				<div className="logo">Your Logo</div>
				<nav>
					<ul className="navigation">
						<li><a href="#">Home</a></li>
						<li><a href="#">About</a></li>
						<li><a href="#">Services</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
				</nav>
			</div>
		</header>
	);
}

export default Header;