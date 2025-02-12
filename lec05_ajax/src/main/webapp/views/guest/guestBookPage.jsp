<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<script src="<%=request.getContextPath()%>/resources/jquery-3.7.1.js"></script>
</head>
<body>
	<h2>방명록</h2>
    <input type="text" id="guest_name" placeholder="이름">
    <textarea id="guest_message" placeholder="메시지">
    
    </textarea>
    <button id="submit_btn">등록</button>

    <h3>📝 남긴 메시지</h3>
    <ul id="guestbook_list">
    
    </ul>
    <script>
    	$(function() {
    		$("#submit_btn").click(function(){
    			let guestName = $('#guest_name').val();
        		let guestMessage = $('#guest_message').val();
        		$.ajax({
        			url : "/guestBook",
					type : "post",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					data : {guestName:guestName, guestMessage:guestMessage},
					dataType : "JSON",
					success : function(data) {						
						let addData = "<li>" + 
                        				"<strong>" + data.guestName + "</strong>" +
                        				"<ul>" + 
                            				"<li>" + data.guestMessage + "</li>" +
                        				"</ul>" +
                    				"</li>";
						$("#guestbook_list").append(addData);
						$("#guest_name").val("");
						$("#guest_message").val("");
					},
					error : function(request,status,error) {
						console.log("서버 요청 중 오류 발생!")
					}
        		})
    		})
    	})
    </script>
</body>
</html>