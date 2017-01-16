Expense = function(settings) {
	var current = this;
	current.hideEditBox();
//	$('.selectpicker').selectpicker();
}
Expense.prototype.loadAccounts = function(){
	$.ajax({
		url:'http://localhost:8088/myapps/accounts/list',
		async:false,
		success: function(data){
			var accounts = $('#account');
//			console.log(data);
//			var ops = $.parseJSON(data);
			$.each(data,function(i,d){
				console.log(i,d);
				accounts.append("<option value=\""+d.id+"\">"+d.name+"</option>");
			});
		}
	});
}
Expense.prototype.hideEditBox = function(){
	$('#editExpensePage').hide();
}
Expense.prototype.bindExpenseList = function(expense){
	var current = this;
	var dataTable = $('#expenseTable').DataTable({
		responsive:true,
		data : expense,
		columnDefs: [
		{
			
			data:'item',
		     render: function(data,type,row,meta){
		    	 
		    	 return "<span id=\""+row.id+"\"data-autoid=\""+row.autoId+"\" data-account-id=\""+row.accountId+"\">"+data+"</span>";
		     },"targets":0 
		},
		{
			"data":"venue", "targets":1
		},
		{
			"data":"amount", "targets":2
		},
		{
			"data":"paidOn", "targets":3
		},
		{
			"data":"accountName", "targets":4
		}
		],
		"createdRow": function(row,data,index){
			var current = this;
			$(row).on('click', function(current){
				console.log(current);
				Expense.prototype.bindEditValues(data);
			});
		}

	});
	current.loadAccounts();
}
Expense.prototype.bindEditValues = function(expense){
	console.log(expense)
	var editPage = $('#editExpensePage');
	$(editPage).find("#item").val(expense.item);
	$(editPage).find("#venue").val(expense.venue);
	$(editPage).find("#amount").val(expense.amount);
	$(editPage).find("#account").val(expense.accountId);
	$(editPage).find("#date").val(expense.paidOn);
	editPage.show(1000);
	$('html, body').animate({
		scrollTop: editPage.offset().top
	},1000);
}
