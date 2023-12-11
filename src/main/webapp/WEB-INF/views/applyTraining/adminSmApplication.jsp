<%--
  Created by IntelliJ IDEA.
  User: jang-yongho
  Date: 12/4/23
  Time: 7:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>세미나 신청서</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <h1>정석코딩 세미나 신청서 관리</h1>
    <hr>

    <form id="smApplicationForm" method="post" >
            신청 번호 :
            <input type="text" name="stfmNo" value="${smApplicationDto.stfmNo}" readonly>
            <br>
            신청자 계정 :
            <input type="text" name="id" value="${smApplicationDto.id}" readonly>
            <input type="hidden" name="registCode" value="${smApplicationDto.registCode}">
            <br>
            신청한 강의 이름 :
            <input type="text" name="title" value="${smApplicationDto.title}" readonly>
            <input type="hidden" name="lastPrice" value="${smApplicationDto.lastPrice}">
            <hr>
        <div>
            <div>Q.본인의 프로그래밍 지식이나 경험 수준에 대해 선택해주세요. (선택1)</div>
            <input type="radio" name="level" value="낮음" onclick="return(false);"
            <c:if test="${smApplicationDto.level eq '낮음'}"> checked="checked"</c:if>>낮음
            <input type="radio" name="level" value="중간" onclick="return(false);"
            <c:if test="${smApplicationDto.level eq '중간'}"> checked="checked"</c:if>>중간
            <input type="radio" name="level" value="높은" onclick="return(false);"
            <c:if test="${smApplicationDto.level eq '높은'}"> checked="checked"</c:if>>높은
        </div>
        <br>
        <div>
            <div>Q.본인의 코딩 경험이나 수준에 대해서 자세히 적어주세요. (300자 내외)</div>
            <textarea name="levelDetail" maxlength="300" readonly>${smApplicationDto.levelDetail}</textarea>
        </div>
        <br>
        <div>
            <div>Q.정석코딩 세미나에 참여하려는 목적을 선택해주세요. (선택1)</div>
            <input type="radio" name="attdPps" value="실무에서 필요" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPps eq '실무에서 필요'}"> checked="checked"</c:if>>실무에서 필요<br>
            <input type="radio" name="attdPps" value="취업 준비 or 이직 준비" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPps eq '취업 준비 or 이직 준비'}"> checked="checked"</c:if>>취업 준비 or 이직 준비<br>
            <input type="radio" name="attdPps" value="개인적인 관심" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPps eq '개인적인 관심'}"> checked="checked"</c:if>>개인적인 관심<br>
            <input type="radio" name="attdPps" value="기타" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPps eq '기타'}"> checked="checked"</c:if>>기타
        </div>
        <br>
        <div>
            <div>Q.정석코딩 세미나에 대한 정보를 얻으신 경로를 선택해주세요. (선택1)</div>
            <input type="radio" name="attdPath" value="페이스북, 인스타 등 SNS" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPath eq '페이스북, 인스타 등 SNS'}"> checked="checked"</c:if>>페이스북, 인스타 등 SNS<br>
            <input type="radio" name="attdPath" value="정석코딩 네이버 카페" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPath eq '정석코딩 네이버 카페'}"> checked="checked"</c:if>>정석코딩 네이버 카페<br>
            <input type="radio" name="attdPath" value="지인 추천" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPath eq '지인 추천'}"> checked="checked"</c:if>>지인 추천<br>
            <input type="radio" name="attdPath" value="기타" onclick="return(false);"
            <c:if test="${smApplicationDto.attdPath eq '기타'}"> checked="checked"</c:if>>기타
        </div>
        <br>
        <div>
            <div>Q.간단한 자기소개를 해주세요.(300자 내외)</div>
            <textarea name="intro" maxlength="300" readonly>${smApplicationDto.intro}</textarea>
        </div>
        <br>
        <div>
            <div>Q.정석코딩 세미나에서 기대하는 것은 무엇입니까? (300자 내외)</div>
            <textarea name="expect" maxlength="300" readonly>${smApplicationDto.expect}</textarea>
        </div>
        <hr>
        <br>
        <button type="button" id="listBtn">목록</button>
    </form>

    <script>
        $(document).ready(()=>{
            // 목록
            $('#listBtn').on("click", ()=>{
                location.href="<c:url value='/adminSmTraining/list?page=${page}&pageSize=${pageSize}'/>";
            });

        });
    </script>
</body>
</html>