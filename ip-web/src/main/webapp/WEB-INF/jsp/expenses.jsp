<script src="/js/internal/expense.js"></script>

<div class="panel panel-primary" id="primaryData">
	<div class="panel-heading">
		<h3 class="panel-title pull-left">Expenses</h3>
		<button id="newExpense" class="btn brn-default btn-primary pull-right">New
			Expense</button>
		<div class="clearfix"></div>

	</div>
	<div class="panel-body">
		<table id="expenseTable" class="table table-striped table-bordered"
			width="100%">
			<thead>
				<tr>
					<th>Select All <input type="checkbox" class="selectAll"></th>
					<th>Item</th>
					<th>Venue</th>
					<th>Amount</th>
					<th>Paid Date</th>
					<th>Account Name</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Select All <input type="checkbox" class="selectAll"></th>
					<th>Item</th>
					<th>Venue</th>
					<th>Amount</th>
					<th>Paid Date</th>
					<th>Account Name</th>
				</tr>
			</tfoot>
		</table>
	</div>

</div>
<div id="editExpensePage" class="panel panel-info" style="display:none">
	<div class="panel-heading">
		Edit Expenses<i class="fa fa-times-circle-o fa-2x"
			style="float: right" aria-hidden="true"></i>
	</div>
	<div class="panel-body">
		<form class="form-horizontal">
			<div id="itemDiv" class="form-group">
				<label for="item" class="col-sm-2 control-label">Item</label>
				<div class="col-sm-2">
					<input type="text" placeholder="Enter item" class="form-control"
						id="item"> <input type="hidden" id="expenseId" />
				</div>
			</div>
			<div class="form-group" id="amountDiv">
				<label for="amount" class="col-sm-2 control-label">Amount</label>
				<div class="col-sm-2">
					<div class="input-group">
						<span class="input-group-addon">$</span> <input type="text"
							class="form-control" id="amount" placeholder="Amount">
					</div>
				</div>
			</div>
			<div class="form-group" id="venueDiv">
				<label for="venue" class="col-sm-2 control-label">Place</label>
				<div class="col-sm-7">
					<textarea class="form-control" id="venue" rows="3" cols="10"
						placeholder="Place"></textarea>
				</div>
			</div>
			<div class="form-group" id="accountDiv">
				<label for="account" class="col-sm-2 control-label">Account</label>
				<div class="col-sm-2">
					<select id="account" class="form-control">
						<option value="-1">Select an account</option>
					</select>
				</div>
			</div>
			<div class="form-group" id="dateDiv">
				<label for="date" class="col-sm-2 control-label">Date</label>
				<div class="col-sm-2">
					<input type="date" class="form-control" id="date"
						placeholder="Date">
				</div>
			</div>
			<!-- <div class="form-group">

					<div class="col-sm-2"> -->
			<button type="button" class="btn brn-default btn-primary" id="save">Save
				Expense</button>
			<button type="button" class="btn brn-default" id="cancel">Cancel</button>
			<!-- </div>
				</div> -->
		</form>
	</div>
</div>
<script>
	
	var expense = new Expense();
	expense.bindExpenseList(${expenses});
</script>
