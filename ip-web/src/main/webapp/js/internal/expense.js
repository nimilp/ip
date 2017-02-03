Expense = function(settings) {
	var current = this;
	$('#expenseListMenu').addClass('active');
	$("#success-alert").hide();
	$.extend(current,{container: $('#editExpensePage')});
	current.hideEditBox();
	CKEDITOR.replace('venue');
	current.bindButtonEvents();
	current.bindSelectAll();
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
	});
	$('#deleteExpense').off().on('click', function(){
		var ids = [];
		var checked = $('input[data-autoid]:checked');
		if(checked.length>0){
			checked.each(function(index,checkbox){
				
				ids.push($(checkbox).attr('id'))
			});
			
			current.deleteExpenses(ids);
		}else{
			var dialog =bootbox.dialog({
				message:"<p class='alert alert-danger' role='alert'><i class='fa fa-exclamation-circle fa-2x'/> You must select an expense to delete!</p>",
				onEscape:true,
				closeButton:false, 
				backdrop:true,
				size:'small'
				});
			setTimeout(function(){
				dialog.modal('hide');
			},2000)
		}
	});
}
Expense.prototype.deleteExpenses=function(ids){
	var current = this;
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/delete.exp",
		method: "POST",
		dataType:'json',
		data:{'id':ids},
		success: function(response){
			MyUtil.scrollToTop(500);
			if(response.success){
				current.successfulSave();
			}else{
//				current.hideEditBox();
				MyUtil.showSuccessOrError(false);
			}
		}
	});
}

Expense.prototype.newExpenseForm = function(){

	var box =bootbox.dialog({
		onEscape:true,
		closeButton:false,
//		title:"New Expense",
		message:'<div id="newExpensePage" class="panel panel-info">'+
					'<div class="panel-heading">New Expenses<i id="dialogClose" class="fa fa-times-circle-o fa-2x" style="float: right" aria-hidden="true"></i></div>'+
					'<div class="panel-body">'+
						'<form class="form-horizontal">'+
							'<div class="form-group" id="newItemDiv">'+
								'<label for="newItem" class="col-sm-2 control-label">Item</label>'+
								'<div class="col-sm-5">'+
									'<input type="text" placeholder="Enter item" class="form-control"	id="newItem">'+
								'</div>'+
							'</div>'+
							'<div class="form-group" id="newAmountDiv">'+
								'<label for="newAmount" class="col-sm-2 control-label">Amount</label>'+
								'<div class="col-sm-5">'+
									'<div class="input-group">'+
										'<span class="input-group-addon">$</span> <input type="text" class="form-control" id="newAmount" placeholder="Amount">'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<div class="form-group" id="newVenueDiv">'+
								'<label for="newVenue" class="col-sm-2 control-label">Place</label>'+
								'<div class="col-sm-7">'+
									'<textarea class="form-control" id="newVenue" rows="3" cols="10" placeholder="Place"></textarea>'+
								'</div>'+
							'</div>'+
							'<div class="form-group" id="newAccountDiv">'+
								'<label for="account" class="col-sm-2 control-label">Account</label>'+
								'<div class="col-sm-5">'+
									'<select id="newAccount" class="form-control">'+
										'<option value="-1">Select an account</option>'+
									'</select>'+
								'</div>'+
							'</div>'+
							'<div class="form-group" id="newDateDiv">'+
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
						className:'btn btn-default btn-primary',
						callback: function(){ 
							var returnValue= Expense.prototype.createExpense();
							return returnValue;
						}
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
			var accounts = $(mySelector);

			$.each(data,function(i,d){
				//console.log(i,d);
				accounts.append("<option value=\""+d.id+"\">"+d.name+"</option>");
			});
		}
	});
}
Expense.prototype.hideEditBox = function(){
	var current = this;
	if(current.container){
		current.container.hide();
	}
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

		var countOfChecked = $('input[data-autoid]:checked').length;
		var countOfAll = $('input[data-autoid]').length;
		if( countOfChecked == countOfAll){
			$('.selectAll').prop('checked',true);
		}else{
			$('.selectAll').prop('checked',false);
		}

}
Expense.prototype.bindSelectAll = function(){
	
	$('.selectAll').off().on('change', function(e){
		var allCheckbox = $('input[type="checkbox"][data-autoid]');
		var isChecked = $(e.currentTarget).prop('checked');
		allCheckbox.prop('checked',isChecked);
		$('.selectAll').prop('checked',isChecked);
	});
}
Expense.prototype.bindEditValues = function(expense){

	var editPage = $('#editExpensePage');
	var expenseId = $(editPage).find("#expenseId");
	$(expenseId).val(expense.id);
	$(expenseId).data('autoid',expense.autoId);
	
	$(editPage).find("#item").val(expense.item);
	CKEDITOR.instances['venue'].setData(expense.venue);
	$(editPage).find("#amount").val(expense.amount);
	$(editPage).find("#account").val(expense.accountId);
	$(editPage).find("#date").val(expense.paidOn);
	editPage.toggle();
	editPage.show(1000);
	MyUtil.scrollToTop(1000, $('#editExpensePage'))
}
Expense.prototype.validate = function(expense, isEdit){
	var markedError = false;
	var itemSelector = '#itemDiv';
	var amountSelector ='#amountDiv';
	var venueSelector = '#venueDiv';
	var accountSelector = '#accountDiv';
	var dateSelector = '#dateDiv';
	if(!isEdit){
		itemSelector = '#newItemDiv';
		amountSelector = '#newAmountDiv';
		venueSelector = '#newVenueDiv';
		accountSelector = '#newAccountDiv';
		dateSelector = '#newDateDiv';
			
	}
	if(!expense.item){
		$(itemSelector).addClass('has-error');
		markedError = true;
	}else{
		$(itemSelector).removeClass('has-error');
	}
	if(!expense.amount){
		$(amountSelector).addClass('has-error');
		markedError = true;
	}else{
		$(amountSelector).removeClass('has-error');
	}
	if(!expense.venue){
		$(venueSelector).addClass('has-error');
		markedError = true;
	}else{
		$(venueSelector).removeClass('has-error');
	}
	if(+(expense.accountId) === -1){
		$(accountSelector).addClass('has-error');
		markedError = true;
	}else{
		$(accountSelector).removeClass('has-error');
	}
	if(!expense.paidOn){
		$(dateSelector).addClass('has-error');
		markedError = true;
	}else{
		$(dateSelector).removeClass('has-error');
	}
	return markedError;
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
	this.saveExpenseCall(expense, true);
}
Expense.prototype.createExpense = function(){
	var current = this;
	var editPage = $('#newExpensePage');
	var expense = {
		'item': $("#newItem").val(),
		'venue': $('#newVenue').val(),
		'amount': $(editPage).find('#newAmount').val(),
		'paidOn': $(editPage).find('#newDate').val(),
		'accountId': $('#newAccount').val()
	};
	return Expense.prototype.saveExpenseCall(expense,false);
}
Expense.prototype.saveExpenseCall = function(expense, isEdit){
	var current = this;
	var isBad = current.validate(expense,isEdit);
	if(!isBad){
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/expense.exp",
		method: "POST",
		data: JSON.stringify(expense),
		contentType:'application/json',
		dataType:"json",
		success: function(response){
			MyUtil.scrollToTop(500);
			if(response.success){
				current.successfulSave();
			}else{
				current.hideEditBox();
				MyUtil.showSuccessOrError(false);
			}
		},
		error: function(response){
			
			current.hideEditBox();
			MyUtil.showSuccessOrError(false);
		}
	});
	}else{
		return false;
	}
}
Expense.prototype.successfulSave = function(){
	var current = this;
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/listexpenses.exp",
		method: "GET",
		dataType:"json",
		success: function(response){
			current.hideEditBox();
			if(response){
				var dataTable = $('#expenseTable').DataTable();
				dataTable.clear().draw();
				dataTable.rows.add(response);
				dataTable.columns.adjust().draw();
			}
			MyUtil.showSuccessOrError(true);

		},
		error: function(response){

			current.hideEditBox();
			MyUtil.showSuccessOrError(false);
		}
	});
}

