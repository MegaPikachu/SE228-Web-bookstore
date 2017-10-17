$(function() {
	
	$("#pay").click(function(e) {
		var add_date = new Date();
		var month = add_date.getMonth() + 1;
	    var currentdate = add_date.getFullYear() + "-" + month + "-" + add_date.getDate();
		jQuery.ajax({
			url : 'pay',
			processData : true,
			dataType : "text",
			data : {
				date : currentdate
			},
			success : function(data) {
				console.log(data);
				
				bootbox.alert({
					message : 'Pay Successfully!',
					callback : function() {
						location.reload();
					}
				});
			}
		});
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
						url : 'deleteShoppingcart',
						processData : true,
						dataType : "text",
						data : {
							bookid : id
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
	
});
