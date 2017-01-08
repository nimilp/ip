<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Expenses</h3>
	</div>
	<div class="panel-body">
		<table id="expenseTable" class="table table-striped table-bordered"
			width="100%">
			<thead>
				<tr>

					<th>Paid Date</th>
					<th>Venue</th>
					<th>Amount</th>

				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Paid Date</th>
					<th>Venue</th>
					<th>Amount</th>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<script>
	$(document).ready(function() {
		var a = ${expenses}
		;//[{id:"956a347c-0da3-497e-b831-31a83626058a", name:"abcabcb", desc:"asdasda", budget:"24.0", lastUpdatedBy:null, _id:"15"}];
		$('#expenseTable').DataTable({
			data : a,
			columns : [ {
				"data" : "paidOn"
			}, {
				"data" : "venue"
			}, {
				"data" : "amount"
			} ]
		});
	});
</script>
