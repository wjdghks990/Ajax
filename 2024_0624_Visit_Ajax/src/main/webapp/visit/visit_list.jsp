<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL Library  -->
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<%--현재 컨텍스트 경로 : ${ pageContext.request.contextPath  }--%>
<link rel="stylesheet" href="../css/visit.css">

<script type="text/javascript">
	
	function del(f){
		
		let c_pwd = f.c_pwd.value.trim(); // 내가 입력한 비밀번호
		let idx	  = f.idx.value;
		
		let no	  = f.no.value;
		
		if(c_pwd=='') {
			alert('삭제할 게시물의 비밀번호를 입력하세요!!');
			f.c_pwd.value="";
			f.c_pwd.focus();
			return;
		}
		
		// jQuery Ajax: 비밀번호 체크
		$.ajax({
			// 요청옵션
			url			:	"check_pwd.do",				// VisitCheckPwdAction
			data		:	{"idx":idx, "c_pwd":c_pwd},	// parameter : check_pwd.do?idx5&c_pwd=1234
			
			// 응답옵션		
			dataType	:	"json",
			success		:	function(res_data){
				//				비번일치				비번불일치
				//res_data = {"result":true} or {"result":false}
				if(res_data.result==false) {
					alert('삭제할 게시물의 비밀번호가 틀립니다.');
					f.c_pwd.value="";
					f.c_pwd.focus();
					return;
				}
				
				//삭제확인
				if(confirm("정말 삭제하시겠습니까?")==false)return;
				
				//삭제처리         JSP EL 와   JS 변수처리 충돌 : 해결방법 '\${ }'
				//location.href=`delete.do?idx=\${ idx }&no=\${ no }`;
				
				location.href="delete.do?idx=" + idx + "&no=" + no;
				
			},
			error		:	function(err){
				alert(err.responseText);
			}
					
		});
		
		
			
	}// end - del()
	
	// 수정폼 띄우기
	function modify_form(f) {
		
		let c_pwd = f.c_pwd.value.trim(); // 내가 입력한 비밀번호
		let idx	  = f.idx.value;
		
		let no	  = f.no.value;
		
		if(c_pwd=='') {
			alert('수정할 게시물의 비밀번호를 입력하세요!!');
			f.c_pwd.value="";
			f.c_pwd.focus();
			return;
		}
		
		// jQuery Ajax: 비밀번호 체크
		$.ajax({
			// 요청옵션
			url			:	"check_pwd.do",				// VisitCheckPwdAction
			data		:	{"idx":idx, "c_pwd":c_pwd},	// parameter : check_pwd.do?idx5&c_pwd=1234
			
			// 응답옵션		
			dataType	:	"json",
			success		:	function(res_data){
				//				비번일치				비번불일치
				//res_data = {"result":true} or {"result":false}
				if(res_data.result==false) {
					alert('수정할 게시물의 비밀번호가 틀립니다.');
					f.c_pwd.value="";
					f.c_pwd.focus();
					return;
				}
				
				//수정확인
				if(confirm("정말 수정하시겠습니까?")==false){
					location.href=""; // 현재 자신의 페이지를 호출
					return;
				}
				
				//수정처리         JSP EL 와   JS 변수처리 충돌 : 해결방법 '\${ }'
				//location.href=`delete.do?idx=\${ idx }&no=\${ no }`;
				
				location.href="modify_form.do?idx=" + idx + "&no=" + no;
				
			},
			error		:	function(err){
				alert(err.responseText);
			}
					
		});
	}
</script>

</head>
<body>
<div id="box">
	<h1 id="title">::::: 방명록 :::::</h1>
			
	<div style="margin-bottom: 10px">
		<input class="btn btn-primary"  type="button" value="글쓰기"
				onclick="location.href='insert_form.do'">
	</div>
	
	<!-- 내용이 없을 경우 -->
	<c:if test="${ empty requestScope.list }">
		<div id="empty_msg">등록된 게시물이 없습니다</div>
	</c:if>
	
	<!-- 내용 -->
	<!-- for(VisitVo vo : list) 동일 -->
	<c:forEach var="vo" items="${ requestScope.list }">
	<!-- 코드 정렬 : ctrl + shift + f -->
		<form class="form-inline">
			<input type="hidden" name="idx" value="${ vo.idx }">
			<input type="hidden" name="no"  value="${ vo.no }">
			
			<div class="panel panel-primary" id="p_${ vo.no }">
				<div class="panel-heading">
					<h4><b>${ vo.name }</b>님의 글: ${ vo.ip }</h4>
				</div>
				<div class="panel-body">
					<div class="mycommon content">${ vo.content } </div>
					<div class="mycommon regdate">작성일자 : ${ fn:substring(vo.regdate,0,16) }</div>
					<div class="mycommon pwd">
						비밀번호(${ vo.pwd }) : <input class="form-control" type="password" name="c_pwd">
								<input class="btn btn-success" type="button" value="수정"
									   onclick="modify_form(this.form);">
								<input class="btn btn-danger" type="button" value="삭제"
									   onclick="del(this.form);">
					</div>
				</div>
			</div>
		</form>	
	</c:forEach>
	
</div>

</body>
</html>