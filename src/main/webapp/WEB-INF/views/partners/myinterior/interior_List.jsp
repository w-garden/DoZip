<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="../include/header.jsp"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    thead#interiorList_table_thead th {
        font-weight: lighter;
		line-height: 1.2em;
    }

    a#go_scheduleList {
        font-size: 16px;
        margin-left: 40px;
        color: saddlebrown;
    }

    #interiorList_title {
        font-size: 26px;
        font-weight: 530;
		border-bottom: 4px solid #000;
    }

    #interiorList_msg {
        color: darkslateblue;
        font-size: 16px;
        font-family: fantasy;
        margin-bottom: 10px;
		margin: 20px 0px 10px 13px;

	}

    #interiorList_explain {
        width: 94%;
        padding: 10px 20px;
        margin: 0 auto;
    }

    #interiorList_top_cont {
		border-bottom: 4px double #555;
        padding-bottom: 15px;
        margin-bottom: 40px;
    }

    * {
        line-height: 2em;
    }

    table#interiorList_table {
        margin: 20px auto;
        width: 100%;
        border-collapse: collapse;
    }

    thead#interiorList_table_thead {
        background: cornflowerblue;
    }

    div#interior_list_searchBar label {
        margin: 0px 12px;
    }

    #interior_list_searchBar {
        display: flex;
        float: right;
        margin-bottom: 10px;
    }
</style>
<p> | 내공사 > 내공사내역</p>
<div id='interiorList_top_cont'>
    <div id='interiorList_title'>내 공사내역<a id="go_scheduleList" href="schedule_list">일정관리</a>
    </div>
    <div id='interiorList_msg'>
        현재 시공중인 공사, 완료된 공사등 모든 공사내역을 확인할 수 있는 페이지
    </div>
    <div id='interiorList_explain'>
		<img src="/images/dozip/blt_check_red.jpg">&nbsp;공사 진행상황에 따라 고객에게 중도금, 잔금을 청구할 수 있습니다
    </div>
</div>
<div id='interior_list_searchBar'>
    <label for="interior_status">시공상태</label> <select id="interior_status">
    <option value="예정">예정</option>
    <option value="진행중">진행중</option>
    <option value="완료">완료</option>
</select> <label for="balance_status">정산상태</label> <select id="balance_status">
    <option value="계약금완납">계약금완납</option>
    <option value="중도금신청">중도금신청</option>
    <option value="중도금완납">중도금완납</option>
    <option value="잔금신청">잔금신청</option>
    <option value="잔금완납">잔금완납</option>
</select>
</div>
<script>
    //payT 로 시공 상태 조회 (확실하지 않음)

    $('#interior_status').change(function (){
        let est_check = $('#interior_status option:selected').val();
        location.href ='/partners/interior_list?est_check='+est_check;
    });
    //payT 로 정산 상태 조회
    $('#balance_status').change(function(){
        let pay_state = $('#balance_status option:selected').val();
        location.href ='/partners/interior_list?pay_state='+pay_state;

    })
</script>
<table id="interiorList_table">
    <thead id="interiorList_table_thead">
    <tr>
        <th>계약번호</th>
        <th>시공상태<br>(예정, 진행중, 완료)</th>
        <th>공사시작(예정)일</th>
        <th>공사마감(예정)일</th>
        <th>정산하기<br>(계약금,중도금,완납)
        </th>
        <th>계약서</th>
        <th>시공 상세정보</th>
    </tr>

    </thead>
    <tbody>

        <c:if test="${empty clist}">

            <th colspan="7"> 조회된 내역이 없습니다</th>
        </tr>
        </c:if>
        <c:if test="${!empty clist}">
            <c:forEach var="c" items="${clist}">
            <tr>
                <td>${c.cont_no}</td>
                <td>예정 ${c.customer_name}</td>
                <td>${fn:substring(c.cont_start,0,10)}</td>
                <td>${fn:substring(c.cont_end,0,10)}</td>
                <td>${c.pay_state}</td>

                <%--
                계약금요청, 계약금 결제완료, 중도금요청, 중도금결제완료, 잔금요청, 잔금 결제완료=공사완료
                --%>
                <td>보기</td>
                <td>보기</td>
            </tr>
            </c:forEach>
        </c:if>

    </tbody>

</table>


<jsp:include page="../include/footer.jsp"/>

