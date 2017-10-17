$(function() {

	$("#save").click(function(e) {
		var userid = $("#userid").val();
		var date;
	//	var date = $("input[name='date']").val();
		console.log(userid, date);
		//console.log(userid);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateOrderPro',
				processData : true,
				dataType : "text",
				data : {
					id : id,
					userid : userid,
					date : date
				},
				success : function(data) {
					console.log(id);
					bootbox.alert({
						message : 'Modify Successfully! '
							+ 'PS: No change if foreign key does not exist!',
					    callback : function() {
							location.reload();
						}
					});
				}
			});
		} else { // Add
			var add_date = new Date();
			var month = add_date.getMonth() + 1;
		    var currentdate = add_date.getFullYear() + "-" + month + "-" + add_date.getDate();
			jQuery.ajax({
				url : 'addOrderPro',
				processData : true,
				dataType : "text",
				data : {
					userid : userid,
					//date : date
					date : currentdate
				},
				success : function(data) {
					bootbox.alert({
						message : 'Add Successfully! '
							+ 'PS: No change if foreign key does not exist!',
						callback : function() {
							location.reload();
						}
					});
				}
			})
		}

		$('#modal').modal('hide');
	});

	$("#getstastic").click(function(e) {
		var userid = $("input[name='userid']").val();
		var startdate = $("input[name='startdate']").val();
		var enddate = $("input[name='enddate']").val();
		var thebookid = $("#thebookid").val();
		console.log(userid,startdate,enddate);

		jQuery.ajax({
			url : 'getstatistic_get',
			processData : true,
			dataType : "json",
			data : {
				userid : userid,
				startdate : startdate,
				enddate : enddate,
				thebookid: thebookid
			},
			success : function(data) {
				$("#thegotstatistic").html("");
				console.log(data);
				var html;
				
				html= "<table class='table table-striped table-bordered table-hover'>"+
				   "<thead><tr><th>orderitemid</th><th>orderid</th><th>bookid</th><th>amount</th><th>price</th></tr></thead><tbody>";
			var size = data.length;
			//alert(size);
			if(size>0){
				for (var i = 0; i < size; ++i){
					html+="<tr><td>"+data[i][0]+"</td><td>"+data[i][1]+"</td><td>"+data[i][2]+"</td><td>"+data[i][3]+"</td><td>"+data[i][4]/100+"</td></tr>";
				}
				html+="</tbody></table>";
				$('#thegotstatistic').append(html);
			}else
				$('#thegotstatistic').append("<p>No result.</p>");
			}
//				bootbox.alert({
//					message : 'Add Successfully! '
//						+ 'PS: No change if foreign key does not exist!',
//					callback : function() {
//						location.reload();
//					}
//				});
//			}

		})
	});
	
	$(".delete").click(function(e) {
		bootbox.confirm({
			buttons : {
				confirm : {
					label : 'Delete'
				},
				cancel : {
					label : 'Cancel'
				}
			},
			message : 'Sure to delete?',
			callback : function(result) {
				if (result) {

					var dataset = e.currentTarget.dataset;
					var id = dataset.id;
					jQuery.ajax({
						url : 'deleteOrderPro',
						processData : true,
						dataType : "text",
						data : {
							id : id
						},
						success : function(data) {
							console.log(id);
							bootbox.alert({
								message : 'Delete Successfully! '
									+ 'PS: No change if foreign key does not exist!',
							    callback : function() {
							       location.reload();
							    }
							});
						}
					});

				}
			}
		});
	});

	$("#add").click(function(e) {
		$('#modalTitle').html("Add");

		$("#userid").val("");
		$("input[name='date']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("#userid").val(dataset.userid);
		$("input[name='date']").val(dataset.date);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

});
