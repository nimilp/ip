Accounts = function(){
	
	var current = this;
	$.extend(current,{container: $('#editAccountsPage')});
	var menu = $('#mainMenuDiv');
//	menu.find('.active').removeClass('active');
	menu.find('#accountListMenu').addClass('active');
	current.bindSelectAll();
	CKEDITOR.replace('desc');
	current.bindButtonEvents();
	$('#newAccount').off().on('click', current.newAccountForm);
	$('#deleteAccount').off().on('click', function(){
		var ids = [];
		var checked = $('input[data-autoid]:checked');
		if(checked.length>0){
			checked.each(function(index,checkbox){
				
				ids.push($(checkbox).attr('id'))
			});
			
			current.deleteAccounts(ids);
		}else{
			var dialog =bootbox.dialog({
				message:"<p class='alert alert-danger' role='alert'><i class='fa fa-exclamation-circle fa-2x'/> You must select an account to delete!</p>",
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
Accounts.prototype.newAccountForm = function(){
	var box = bootbox.dialog({
		closeButton:false,
		onEscape:true,
		message:'<div id="createAccountsPage" class="panel panel-info">'+
	'<div class="panel-heading">'+
	'	New Account<i id="dialogClose" class="fa fa-times-circle-o fa-2x" style="float: right"'+
	'		aria-hidden="true"></i>'+
	'</div>'+
	'<div class="panel-body">'+
	'	<form class="form-horizontal">'+
	'		<div id="newNameDiv" class="form-group">'+
	'			<label for="newName" class="col-sm-2 control-label">Name</label>'+
	'			<div class="col-sm-5">'+
	'				<input type="text" placeholder="Enter Name" class="form-control" id="newName">'+ 
	'			</div>'+
	'		</div>'+
	'		<div class="form-group" id="newBudgetDiv">'+
	'			<label for="newBudget" class="col-sm-2 control-label">Budget</label>'+
	'			<div class="col-sm-5">'+
	'				<div class="input-group">'+
	'					<span class="input-group-addon">$</span> <input type="text"'+
	'						class="form-control" id="newBudget" placeholder="Budget">'+
	'				</div>'+
	'			</div>'+
	'		</div>'+
	'		<div class="form-group" id="newDescDiv">'+
	'			<label for="newDesc" class="col-sm-2 control-label">Description</label>'+
	'			<div class="col-sm-7">'+
	'				<textarea class="form-control" id="newDesc" rows="3" cols="10"'+
	'					placeholder="Description"></textarea>'+
	'			</div>'+
	'		</div>'+
	'	</form>'+
	'</div>'+
'</div>',
		buttons:{
			yes :{
				label:'Create',
				className:'btn btn-default btn-primary',
				callback: function(){ 
					var returnValue= Accounts.prototype.createAccount();
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
		
		$('#dialogClose.fa-times-circle-o').on('click', function(){

			Accounts.prototype.hideBox(box)
		});
	});
}
Accounts.prototype.hideBox = function(box){
	box.modal('hide');
}
Accounts.prototype.deleteAccounts=function(ids){
	var current = this;
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/deleteaccounts",
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
Accounts.prototype.bindButtonEvents = function(){
	var current = this;
	$('.fa-times-circle-o').on('click', function(){
		current.hideEditBox()
	});
	current.container.find('#cancel').on('click', function(){current.hideEditBox()});
	$('#save').off().on('click', function(){current.saveExpense()});
}
Accounts.prototype.bindAccountsList = function(accounts){
	var current = this;
//	console.log(accounts);
	var dataTable = $('#accountsTable').DataTable({
		responsive:true,
		data : accounts,
		order:[[5,'desc']],
		columnDefs: [
		{
			
			width:"90px",
			render: function(data,type,row,meta){
		    	 
		    	 return "<input type=\"checkbox\" id=\""+row.id+"\"data-autoid=\""+row.autoId+"\">";
		     },"targets":0 ,orderable:false
		},
		{
			
			data:'name',"targets":1 
		},
		{
			"data":"budget", "targets":2
		},
		{
			"data":"description", "targets":3
		},
		{
			"data":"creationDate", "targets":4
		},
		{
			"data":"updateDate", "targets":5
		}
		],
		
		"createdRow": function(row,data,index){
			var current = this;
			$(row).on('click', function(current){
				
				if($(current.target).attr('tabindex') || $(current.target).is('input')){
					Accounts.prototype.selectAll();
					current.stopPropagation();
					return;
				} else{
					Accounts.prototype.bindEditValues(data);
				}
			});
		}

	});
}
Accounts.prototype.selectAll = function(){

	var countOfChecked = $('input[data-autoid]:checked').length;
	var countOfAll = $('input[data-autoid]').length;
	if( countOfChecked == countOfAll){
		$('.selectAll').prop('checked',true);
	}else{
		$('.selectAll').prop('checked',false);
	}

}
Accounts.prototype.bindSelectAll = function(){

$('.selectAll').off().on('change', function(e){
	var allCheckbox = $('input[type="checkbox"][data-autoid]');
	var isChecked = $(e.currentTarget).prop('checked');
	allCheckbox.prop('checked',isChecked);
	$('.selectAll').prop('checked',isChecked);
});
}
Accounts.prototype.bindEditValues = function(account){

	var editPage = $('#editAccountsPage');
	var accountId = $(editPage).find("#accountId");
	$(accountId).val(account.id);
	$(accountId).data('autoid',account.autoId);
	
	$(editPage).find("#name").val(account.name);
	CKEDITOR.instances['desc'].setData(account.description);
	$(editPage).find("#budget").val(account.budget);
	$(editPage).find("#updateDate").val(account.updateDate);
	$(editPage).find("#createDate").val(account.creationDate);
	editPage.toggle();
	editPage.show(1000);
	MyUtil.scrollToTop(1000, $('#editAccountsPage'))
}
Accounts.prototype.validate = function(account, isEdit){
	var markedError = false;
	var nameSelector = '#nameDiv';
	var budgetSelector ='#budgetDiv';
	var descSelector = '#descDiv';
	
	if(!isEdit){
		nameSelector = '#newNameDiv';
		budgetSelector = '#newBudgetDiv';
		descSelector = '#newDescDiv';
		
			
	}
	if(!account.name){
		$(nameSelector).addClass('has-error');
		markedError = true;
	}else{
		$(nameSelector).removeClass('has-error');
	}
	if(!account.budget){
		$(budgetSelector).addClass('has-error');
		markedError = true;
	}else{
		$(budgetSelector).removeClass('has-error');
	}
	if(!account.description){
		$(descSelector).addClass('has-error');
		markedError = true;
	}else{
		$(descSelector).removeClass('has-error');
	}
	
	return markedError;
}
Accounts.prototype.hideEditBox = function(){
	var current = this;
	if(current.container){
		current.container.hide();
	}
	MyUtil.scrollToTop();
}
Accounts.prototype.saveExpense = function(){
	var current = this;
	var editPage = $('#editAccountsPage');
	var account = {
		'id': $("#accountId").val(),
		'name': $("#name").val(),
		'description': CKEDITOR.instances['desc'].getData(),
		'budget': $(editPage).find('#budget').val(),
		'updateDate': new Date().toISOString().slice(0,10),
		'creationDate':$(editPage).find('#createDate').val(),
		'autoId':$('#accountId').data("autoid")
	};
	this.saveAccountCall(account, true);
}
Accounts.prototype.createAccount = function(){
	var current = this;
	var editPage = $('#newAccountsPage');
	var account = {
		'name': $("#newName").val(),
		'description': $('#newDesc').val(),
		'budget': $('#newBudget').val(),
		'updateDate': new Date().toISOString().slice(0,10),
		'creationDate':new Date().toISOString().slice(0,10)
	};
	return this.saveAccountCall(account, false);
}
Accounts.prototype.saveAccountCall = function(account, isEdit){
	var current = this;
	var isBad = current.validate(account,isEdit);
	if(!isBad){
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/accounts",
		method: "POST",
		data: JSON.stringify(account),
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
Accounts.prototype.successfulSave = function(){
	var current = this;
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/accounts/list",
		method: "GET",
		dataType:"json",
		success: function(response){
			current.hideEditBox();
			if(response){
				var dataTable = $('#accountsTable').DataTable();
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
