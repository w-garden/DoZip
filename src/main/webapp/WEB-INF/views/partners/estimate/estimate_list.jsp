<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/partners/css/partners_style2.css">


<style>
#estlist_title{
 font-size:32px;
 font-weight:600;
}
#estlist_msg {
    color: darkslateblue;
    font-size: 21px;
    font-family: fantasy;
    margin-bottom: 10px;
}
*{
line-height:2em;
}
#estlist_top_cont {
    border-bottom: 4px solid #7F7F7F;
    padding-bottom: 35px;
}
#est_list_explain {
    width: 94%;
    border: 4px solid #C1C1C1;
    padding: 10px 20px;
    margin: 0 auto;
}
table#est_list_table{
	margin: 20px;
}
table#est_list_table input[type=button] {
	background: none;
	border:none;
	font-size:medium;
}
table#est_list_table input:hover[type=button],#write_contract_btn:hover {
	text-decoration: underline;
	font-weight: bolder;
	color: blue;
}
#write_contract_btn{
	background: none;
	border:none;
	font-size:medium;
}
#cancel_contract_btn{
	background: #FF0000;
	border:none;
	color:white;
	padding:0px 6px;
	border-radius: 3px;
	height: 30px;
}
#cancel_contract_btn:active {
	background: #2C2A29;
}
table#est_list_table {
    margin: 20px auto;
    width: 97%;
}
#est_list_table thead {
    background: cornflowerblue;
}
</style>
	<div id="estlist_top_cont">
	<div id="estlist_title">견적목록</div>
	<div id="estlist_msg">
	계약서를 작성 한 후 계약금 지불이 완료되면 시공 일정에 맞추어 공사를 진행해주시기 바랍니다
	</div>
	<div id='est_list_explain'>
		
		<h3>계약이 파기되는 상황 </h3>
		<p>1. 고객이 계약금을 견적 완료일로부터 3일내로 지급하지 않을경우.<br>
		2. 고객이나 파트너스가 계약을 원치 않을경우 </p>
		<b>이럴 경우에는 제시된 계약금의 전부를 상대방에게 지불해야한다</b>
		</div>
	</div>
		<table id='est_list_table'>
		<thead>
		<tr>
			<th></th>
			<th>견적서번호</th>		
			<th>시공상세정보</th>		
			<th>공사대금(천원)</th>		
			<th>시공일정</th>
			<th>계약진행상황</th>
			<th>계약날짜</th>		
			<th>비고</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td><input type="checkbox"></td>
			<td>10</td>
			<td><input type="button" value="목동 사무실 원상복구 견적 문의" onclick="est_detail()"></td>
			<td>5,000</td>
			<td>2022.09.01 ~ 2022.09.12</td>
			<td>
			<button id="write_contract_btn"> 계약서작성하기</button>
			</td>
			<td>계약서 작성전</td>
			<td><button onclick="est_cancel()" id="cancel_contract_btn">계약해지</button></td>
		</tr>
		<tr>
			<td><input type="checkbox"></td>
			<td>11</td>
			<td><input type="button" value="목동 사무실 원상복구 견적 문의" onclick="est_detail()"></td>
			<td>5,000</td>
			<td>2022.09.01 ~ 2022.09.12</td>
			<td> 계약금 지불전(D-3)</td>
			<td>2022.08.30</td>
			<td><button onclick="est_cancel()" id="cancel_contract_btn">계약해지</button></td>
			
		</tr>
		<tr>
			<td><input type="checkbox"></td>
			<td>12</td>
			<td><input type="button" value="목동 사무실 원상복구 견적 문의" onclick="est_detail()"></td>
			<td>5,000</td>
			<td>2022.09.01 ~ 2022.09.12</td>
			<td>계약금 지불완료</td>
			<td>2022.08.30</td>
			<td><button onclick="est_cancel()" id="cancel_contract_btn">계약해지</button></td>
		</tr>
		
		</tbody>
		<tfoot>
		

		<%--일 단 쪽번호 소스만 불러옴 --%>		
		
		<tr>
		<th colspan="8" id="paging_th">
		<%--검색 전 페이징 --%>
		<c:if test="${(empty find_field ) && (empty find_text)}"><%--검색 필드와 검색어가 없을떄 --%>
		<%--이전 버튼 --%>
		<c:if test="${page <= 1 }">	&lt;이전</c:if>
		<c:if test="${page>1 }"><a href="customer_qna.do?page=${page-1 }"> &lt;이전 </a></c:if>
		<%--현재 쪽번호 출력 --%>
		<c:forEach var="a" begin="${startpage }" end="${endpage }" step="1">
			<c:if test="${a==page }"> <%--현재 쪽번호가 선택된 경우 --%>
				<span style="color:orange;"> &nbsp; ${a } &nbsp;</span>
			</c:if>
			<c:if test="${a!=page }"> <%--현재 페이지가 선택 안된 경우 --%>
				<a href="customer_qna.do?page=${a}">&nbsp; ${a } &nbsp;</a>
			</c:if>
		</c:forEach>
			<%--다음 버튼 --%>
			<c:if test="${page >= maxpage }">
				다음&gt;
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="customer_qna.do?page=${page+1 }"> 다음&gt;</a>
			</c:if>
		</c:if>
		</th>
		</tr>
		</tfoot>
		</table>			
			
		<script>
		function est_detail(){
			var popupWidth = 910;
			var popupHeight = 1000;

			var popupX = (window.screen.width / 2) - (popupWidth / 2);
			// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
			var popupY= (window.screen.height / 2) - (popupHeight / 2);
			// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
			window.open('./estimate/estimate_detail.jsp', 'Child','width='+ popupWidth+ ', height='+popupHeight+ ', top='+ popupY+', left='+ popupX);
			/*
			
			겟 방식으로 받아서 아작스 처리
			$.ajax({
				type:"post",
				url:'loadportfolio_info.do',
				data:{
					business_num:$business_num
				},
				datatype:"text",
				success: function(result){
					window.open( "./portfolio/load_info.jsp", "Child", "width=800, height=300, top=500, left=500");
				}
			});*/
		}
		
		function est_cancel() {
			confirm('해지 하실 경우 계약금이 패널티로 부과됩니다. 해지하시겠습니까?');
		}
	</script>
			
			
		
		
		
		
		
		

<jsp:include page="../include/footer.jsp" />


		