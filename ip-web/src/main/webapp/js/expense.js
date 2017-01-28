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
	$('#newExpense').off().on('click', function(){
		current.newExpenseForm();
	})
}
Expense.prototype.newExpenseForm = function(){
//	bootbox.alert("Hello world!", function() {
//        console.log("Alert Callback");
//    });
	var box =bootbox.dialog({
		onEscape:true,
		closeButton:false,
//		title:"New Expense",
		message:'<div id="newExpensePage" class="panel panel-info">'+
					'<div class="panel-heading">New Expenses<i id="dialogClose" class="fa fa-times-circle-o fa-2x" style="float: right" aria-hidden="true"></i></div>'+
					'<div class="panel-body">'+
						'<form class="form-horizontal">'+
							'<div class="form-group">'+
								'<label for="newItem" class="col-sm-2 control-label">Item</label>'+
								'<div class="col-sm-5">'+
									'<input type="text" placeholder="Enter item" class="form-control"	id="newItem">'+
								'</div>'+
							'</div>'+
							'<div class="form-group">'+
								'<label for="newAmount" class="col-sm-2 control-label">Amount</label>'+
								'<div class="col-sm-5">'+
									'<div class="input-group">'+
										'<span class="input-group-addon">$</span> <input type="text" class="form-control" id="newAmount" placeholder="Amount">'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<div class="form-group">'+
								'<label for="newVenue" class="col-sm-2 control-label">Place</label>'+
								'<div class="col-sm-7">'+
									'<textarea class="form-control" id="newVenue" rows="3" cols="10" placeholder="Place"></textarea>'+
								'</div>'+
							'</div>'+
							'<div class="form-group">'+
								'<label for="account" class="col-sm-2 control-label">Account</label>'+
								'<div class="col-sm-5">'+
									'<select id="newAccount" class="form-control">'+
										'<option value="-1">Select an account</option>'+
									'</select>'+
								'</div>'+
							'</div>'+
							'<div class="form-group">'+
								'<label for="newDate" class="col-sm-2 control-label">Date</label>'+
								'<div class="col-sm-5">'+
									'<input type="date" class="form-control" id="newDate" placeholder="Date">'+
								'</div>'+
							'</div>'+
						'</form>'+
					'</div>'+
				'</div>',
				buttons:{
					yes :{
						label:'Create',
						className:'btn btn-default btn-primary'
					},
					cancel:{
						label:'Cancel',
						className:'btn btn-default'
					}
				}
	});
	box.on('shown.bs.modal', function(){
		Expense.prototype.loadAccounts('#newAccount');
		$('#dialogClose.fa-times-circle-o').on('click', function(){
//			console.log(this);
			Expense.prototype.hideBox(box)
		});
	});
}
Expense.prototype.hideBox = function(box){
	box.modal('hide');
}
Expense.prototype.loadAccounts = function(selector){
	var mySelector = '#account';
	if(selector){
		mySelector = selector
	}
	$.ajax({
		url:MyUtil.Server_Context()+'/myapps/accounts/list',
		async:false,
		success: function(data){
			var accounts = $(selector);

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
	MyUtil.scrollToTop();
}
Expense.prototype.bindExpenseList = function(expense){
	console.log(expense);
	var current = this;
	var dataTable = $('#expenseTable').DataTable({
		responsive:true,
		data : expense,
		order:[[4,'desc']],
		columnDefs: [
		{
			
			width:"90px",
			render: function(data,type,row,meta){
		    	 
		    	 return "<input type=\"checkbox\" id=\""+row.id+"\"data-autoid=\""+row.autoId+"\" data-account-id=\""+row.accountId+"\">";
		     },"targets":0 ,orderable:false
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
				
				if($(current.target).attr('tabindex') || $(current.target).is('input')){
					Expense.prototype.selectAll();
					current.stopPropagation();
					return;
				} else{
					Expense.prototype.bindEditValues(data);
				}
			});
		}

	});
	current.loadAccounts();
}
Expense.prototype.selectAll = function(){
//	if($(checkbox).prop('checked')){
		var countOfChecked = $('input[data-autoid]:checked').length;
		var countOfAll = $('input[data-autoid]').length;
		if( countOfChecked == countOfAll){
			$('.selectAll').prop('checked',true);
		}else{
			$('.selectAll').prop('checked',false);
		}
//	}
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
	editPage.toggle();
	editPage.show(1000);
	MyUtil.scrollToTop(1000, $('#editExpensePage'))
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
			MyUtil.scrollToTop(500);
			current.successfulSave();
		},
		error: function(response){
			//alert(response.status);
			current.hideEditBox();
			MyUtil.showSuccessOrError(false);
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
			current.hideEditBox();
			var dataTable = $('#expenseTable').DataTable();
			dataTable.clear().draw();
			dataTable.rows.add(response);
			dataTable.columns.adjust().draw();
			MyUtil.showSuccessOrError(true);
//			current.bindExpenseList();
		},
		error: function(response){
//			alert(response.status);
			current.hideEditBox();
			MyUtil.showSuccessOrError(false);
		}
	});
}
