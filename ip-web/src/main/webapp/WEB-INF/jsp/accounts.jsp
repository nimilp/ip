<script src="/js/internal/accounts.js"></script>
<div class="panel panel-primary" id="primaryData">
	<div class="panel-heading">
		<h3 class="panel-title pull-left">Accounts</h3>
		<i class="fa"></i>
		<!-- Single button -->
		<div class="btn-group">
			<i id="expenseMenuBar" class="fa fa-bars fa-1x dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<!-- Action <span class="caret"></span> -->
			</i>
			<ul class="dropdown-menu">
				<li><a id="newAccount" href="#">New Account</a></li>
				<li role="separator" class="divider"></li>
				<li><a id="deleteAccount" href="#">Delete Account</a></li>
			</ul>
		</div>
		<!-- <button id="newExpense" class="btn brn-default btn-primary pull-right">New
			Expense</button>
		<div class="clearfix"></div> -->

	</div>
	<div class="panel-body">
		<table id="accountsTable" class="table table-striped table-bordered"
			width="100%">
			<thead>
				<tr>
					<th>Select All <input type="checkbox" class="selectAll"></th>
					<th>Account name</th>
					<th>Description</th>
					<th>Budget</th>
					<th>Created on</th>
					<th>Last Updated on</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Select All <input type="checkbox" class="selectAll"></th>
					<th>Account name</th>
					<th>Description</th>
					<th>Budget</th>
					<th>Created on</th>
					<th>Last Updated on</th>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<div id="editAccountsPage" class="panel panel-info"
	style="display: none">
	<div class="panel-heading">
		Edit Account<i class="fa fa-times-circle-o fa-2x" style="float: right"
			aria-hidden="true"></i>
	</div>
	<div class="panel-body">
		<form class="form-horizontal">
			<div id="nameDiv" class="form-group">
				<label for="name" class="col-sm-2 control-label">Name</label>
				<div class="col-sm-2">
					<input type="text" placeholder="Enter Name" class="form-control"
						id="name"> <input type="hidden" id="accountId" /><input
						type="hidden" id="updateDate" /><input type="hidden"
						id="createDate" />
				</div>
			</div>
			<div class="form-group" id="budgetDiv">
				<label for="budget" class="col-sm-2 control-label">Budget</label>
				<div class="col-sm-2">
					<div class="input-group">
						<span class="input-group-addon">$</span> <input type="text"
							class="form-control" id="budget" placeholder="Budget">
					</div>
				</div>
			</div>
			<div class="form-group" id="descDiv">
				<label for="desc" class="col-sm-2 control-label">Description</label>
				<div class="col-sm-7">
					<textarea class="form-control" id="desc" rows="3" cols="10"
						placeholder="Description"></textarea>
				</div>
			</div>
			<button type="button" class="btn brn-default btn-primary" id="save">Save
				Account</button>
			<button type="button" class="btn brn-default" id="cancel">Cancel</button>

		</form>
	</div>
</div>
<script>
	$(document).ready(function() {
		
		var account = new Accounts();
		account.bindAccountsList(${accounts});
		
	});
</script>
