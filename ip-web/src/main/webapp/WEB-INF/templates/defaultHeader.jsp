<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#mainMenuDiv"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/myapps/">My Applications</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div id="mainMenuDiv" class="collapse navbar-collapse"
			>
			<ul class="nav navbar-nav">
				<li id="whatIsMyIpMenu"><a href="/myapps/whatismyip">What
						is my ip?</a></li>
				<li class="dropdown active"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Expense Tracker <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li id="expenseListMenu">
							<a	href="/myapps/expensetracker/expense.exp">Expense List</a>
						</li>
						<li role="separator" class="divider"></li>
						<li id="accountListMenu">
							<a	href="/myapps/expensetracker/accounts">Account List</a>
						</li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Link</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Options <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">My Account</a></li>
						<li><a href="#">Preferences</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Logout</a></li>
					</ul>
				</li>
			</ul>
		</div>	
	</div>
</nav>