$(function() {

	$("#shoppingcart").click(function(e) {
		var amount = $("input[name='amount']").val();		
		var dataset = e.currentTarget.dataset;
		var bookid = dataset.id;
		var price = dataset.price;
		var title = dataset.title;
		console.log(amount);
	//	bootbox.alert({
	//		message : 'OK! ',
	//		callback : function() {
	//			location.reload();
	//		}
	//	});			
		jQuery.ajax({
			url: 'addtoShoppingcart' ,
			processData: true,
			dataType : "text",
			data: {
			//	bookid: bookid,
			//	price: price,
				amount:amount,
			//	title:title,
			},
			success : function(data) {
			//	console.log(data);
					bootbox.alert({
						message : 'Add to shopping cart success! ',
						callback : function() {
							location.reload();
						}
					});				

				
			}

		});   	
		
	});
	
	$(".edit").click(function(e) {

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		var price = dataset.price;
		var title = dataset.title;
		console.log(id,price/100,title);
		jQuery.ajax({
			url : 'bookdetail',
			type:"post",
			dataType:"json",
			data : {
				id : id,
				price: price,
				title: title
			},
			success : function(data) {				
				var d = eval("("+data+")");
				console.log(11,data);
				console.log("get",d["bookdetail"]);
		//		$("input[name='detail']").val(d["bookdetail"]);
				$('#bookdetailfound').empty();
				var html= "<table><tr><td>Title:</td><td>"+dataset.title+"</td></tr>";
				var real_price = dataset.price/100;
				html+="<tr><td>Author:</td><td>"+dataset.author+"</td></tr>";
				html+="<tr><td>Price:</td><td>"+real_price+"</td></tr>";
				html+="<tr><td>Publisher:</td><td>"+dataset.publisher+"</td></tr>";
				html+="<tr><td>Date:</td><td>"+dataset.date+"</td></tr>";
				html+="<tr><td>Detail:</td><td>"+d["bookdetail"]+"</td></tr>";
				html+="</table>";
				console.log("id:",dataset.id);
				html+="<img alt=\"显示图片\" src= \"getBookPicture?id="+dataset.id+"\"height=\"300px\" width=\"225px\"></img>";
				$('#bookdetailfound').append(html);

				
			},
			error: function(data, textStatus){
			//	var d = eval("("+data+")");
				console.log(22,data);
			//	alert(textStatus);
			}
		})
		

	});

});
