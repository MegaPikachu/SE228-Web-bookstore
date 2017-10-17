$(function() {

	$("#save").click(function(e) {
		var title = $("input[name='title']").val();
		var author = $("input[name='author']").val();
		var price = $("input[name='price']").val();
		var publisher = $("input[name='publisher']").val();
		var date = $("input[name='date']").val();
		var detail = $("input[name='detail']").val();
		var picture = $("input[name='picture']").val();
		console.log(title, author, price, publisher, date,picture);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateBookPro',
				processData : true,
				dataType : "text",
				data : {
					id : id,
					title : title,
					author : author,
					price : price * 100,
					publisher : publisher,
					date : date,
					detail: detail,
					picture: picture
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
			jQuery.ajax({
				url : 'addBookPro',
				processData : true,
				dataType : "text",
				data : {
					id : id,
					title : title,
					author : author,
					price : price * 100,
					publisher : publisher,
					date : date,
					detail: detail,
					picture: picture
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
						url : 'deleteBookPro',
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

		$("input[name='title']").val("");
		$("input[name='author']").val("");
		$("input[name='price']").val("");
		$("input[name='publisher']").val("");
		$("input[name='detail']").val("");
		$("input[name='date']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		jQuery.ajax({
			url : 'bookdetail',
			type:"post",
			dataType:"json",
			data : {
				id : id,
			},
			success : function(data) {
				var d = eval("("+data+")");
				console.log(11,data);
				console.log("get",d["bookdetail"]);
				$("input[name='detail']").val(d["bookdetail"]);
			//	console.log(123,d);
			//	$("input[name='detail']").val(data);
			},
			error: function(data, textStatus){
			//	var d = eval("("+data+")");
				console.log(22,data);
			//	alert(textStatus);
			}
		})
		
		$("input[name='title']").val(dataset.title);
		$("input[name='author']").val(dataset.author);
		$("input[name='price']").val(dataset.price/100);
		$("input[name='publisher']").val(dataset.publisher);
		$("input[name='date']").val(dataset.date);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

});
