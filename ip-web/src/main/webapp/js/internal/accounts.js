Accounts = function(){
	
	var current = this;
	$.extend(current,{container: $('#editAccountsPage')});
	var menu = $('#mainMenuDiv');
//	menu.find('.active').removeClass('active');
	menu.find('#accountListMenu').addClass('active');
	current.bindSelectAll();
	CKEDITOR.replace('desc');
	current.bindButtonEvents();
	
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
//	$(editPage).find("#account").val(account.accountId);
//	$(editPage).find("#date").val(account.paidOn);
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
		
		'autoId':$('#accountId').data("autoid")
	};
	this.saveAccountCall(account, true);
}
Accounts.prototype.saveAccountCall = function(account, isEdit){
	var current = this;
	var isBad = current.validate(account,isEdit);
	if(!isBad){
	$.ajax({
		url: MyUtil.Server_Context()+"/myapps/expensetracker/account",
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