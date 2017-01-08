<table id="myTable" class="table table-striped table-bordered"  width="100%">
<thead>
            <tr>
            	
                <th>Account name</th>
                <th>Description</th>
                <th>Budget</th>
                <th>Last Update by</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>Account name</th>
                <th>Description</th>
                <th>Budget</th>
                <th>Last Update by</th>
            </tr>
        </tfoot>
</table>
<script>$(document).ready(function(){
	var a = ${accounts};//[{id:"956a347c-0da3-497e-b831-31a83626058a", name:"abcabcb", desc:"asdasda", budget:"24.0", lastUpdatedBy:null, _id:"15"}];
    $('#myTable').DataTable({
    	data:a,
    	columns:[
    	         {"data":"name"},
	    	{"data":"description"},
	    	{"data":"budget"},
	    	{"data":"lastUpdatedBy"}
    	]
    });
});</script>
