Expense = function(settings) {
	var current = this;
	$("#success-alert").hide();
	$.extend(current,{container: $('#editExpensePage')});
	current.hideEditBox();
	CKEDITOR.replace('venue');
	current.bindButtonEvents();
//	$('.selectpicker').selectpicker();
}
Expense.prototype.bindButtonEvents = function(){
	var current = this;
	$('.fa-times-circle-o').on('click', function(){
		current.hideEditBox()
	});
	current.container.find('#cancel').on('click', function(){current.hideEditBox()});
	$('#save').off().on('click', function(){current.saveExpense()});
}
Expense.prototype.loadAccounts = function(){
	$.ajax({
		url:MyUtil.Server_Context()+'/myapps/accounts/list',
		async:false,
		success: function(data){
			var accounts = $('#account');

			$.each(data,function(i,d){
				//console.log(i,d);
				accounts.append("<option value=\""+d.id+"\">"+d.name+"</option>");
			});
		}
	});
}
Expense.prototype.hideEditBox = function(){
	var current = this;
	current.container.hide();
}
Expense.prototype.bindExpenseList = function(expense){
	console.log(expense);
	var current = this;
	var dataTable = $('#expenseTable').DataTable({
		responsive:true,
		data : expense,
		columnDefs: [
		{
			sortable:false,
			width:"50px",
			render: function(data,type,row,meta){
		    	 
		    	 return "<input type=\"checkbox\" id=\""+row.id+"\"data-autoid=\""+row.autoId+"\" data-account-id=\""+row.accountId+"\">";
		     },"targets":0 
		},
		{
			
			data:'item',"targets":1 
		},
		{
			"data":"venue", "targets":2
		},
		{
			"data":"amount", "targets":3
		},
		{
			"data":"paidOn", "targets":4
		},
		{
			"data":"accountName", "targets":5
		}
		],
		"createdRow": function(row,data,index){
			var current = this;
			$(row).on('click', function(current){
				console.log($(current.target).type);
				if($(current.target) === 'input'){
					console.log('here');
					current.cancel();
					return;
				}
				Expense.prototype.bindEditValues(data);
			});
		}

	});
	current.loadAccounts();
}
Expense.prototype.bindEditValues = function(expense){
//	console.log(expense)
	var editPage = $('#editExpensePage');
	var expenseId = $(editPage).find("#expenseId");
	$(expenseId).val(expense.id);
	$(expenseId).data('autoid',expense.autoId);
	
	$(editPage).find("#item").val(expense.item);
	CKEDITOR.instances['venue'].setData(expense.venue);
//	$(editPage).find("#venue").val(expense.venue);
	$(editPage).find("#amount").val(expense.amount);
	$(editPage).find("#account").val(expense.accountId);
	$(editPage).find("#date").val(expense.paidOn);
	editPage.show(1000);
	$('html, body').animate({
		scrollTop: editPage.offset().top
	},1000);
}
Expense.prototype.saveExpense = function(){
	var current = this;
	var editPage = $('#editExpensePage');
	var expense = {
		'id': $("#expenseId").val(),
		'item': $("#item").val(),
		'venue': CKEDITOR.instances['venue'].getData(),
		'amount': $(editPage).find('#amount').val(),
		'paidOn': $(editPage).find('#date').val(),
		'accountId': $('#account').val(),
		'autoId':$('#expenseId').data("autoid")
	};
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/expense.exp",
		method: "POST",
		data: JSON.stringify(expense),
		contentType:'application/json',
		dataType:"json",
		success: function(response){
			$('html, body').animate({
				scrollTop: $('html body').offset().top
			},1000);
			current.hideEditBox();
			current.successfulSave();
		},
		error: function(response){
			//alert(response.status);
			current.hideEditBox();
		}
	});
}
Expense.prototype.successfulSave = function(){
	var current = this;
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/listexpenses.exp",
		method: "GET",
		dataType:"json",
		success: function(response){
			console.log(response);
			  
        
			var dataTable = $('#expenseTable').DataTable();
			dataTable.clear().draw();
			dataTable.rows.add(response);
			dataTable.columns.adjust().draw();
			$("#success-alert").alert();
            $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
           $("#success-alert").slideUp(500);
            }); 
//			current.bindExpenseList();
		},
		error: function(response){
			alert(response.status);
//			current.hideEditBox();
		}
	});
}
