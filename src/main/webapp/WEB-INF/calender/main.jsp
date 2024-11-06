<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="org.zero.ck.smucal.dto.CalenderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="org.zero.ck.smucal.dto.UserDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>캘린더</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table th, table td {
            width: 14.28%;
            text-align: center;
            padding: 10px;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #f2f2f2;
        }

        .calendar-buttons {
            text-align: center;
            margin: 20px 0;
        }

        .calendar-buttons button {
            padding: 10px;
            margin: 0 20px;
            cursor: pointer;
        }
        .editContent {
            color: #008aff;
        }
    </style>
</head>
<body>
<%
     List<CalenderDTO> calenderList = (List<CalenderDTO>) request.getAttribute("calenderList");
    String calenderListJson = new Gson().toJson(calenderList);   // List<CalenderDTO>를 JSON 문자열로 변환

    UserDTO userInfo = (UserDTO) request.getAttribute("userInfo");
    int userId = userInfo.getId();
%>

<div class="wrapper">
    <div class="calendar-buttons">
        <button onclick="changeMonth(-1)">이전 달</button>
        <span id="currentMonth"></span>
        <button onclick="changeMonth(1)">다음 달</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>일</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
            <th>토</th>
        </tr>
        </thead>
        <tbody id="calendarBody">
        </tbody>
    </table>

</div>
<div class="buttons">
    <form action="/register" method="get">
        <button>캘린더 작성</button>
    </form>
    <form action="/logout" method="post">
        <button>LOGOUT</button>
    </form>
</div>
<script type="text/javascript">
    const calenderList =<%=calenderListJson%>;
    const userId = <%=userId%>;
    let currYear = new Date().getFullYear();
    let currMonth = new Date().getMonth();

    function changeMonth(offset) {
        currMonth += offset;

        if (currMonth > 11) {
            currMonth = 0;
            currYear++;
        } else if (currMonth < 0) {
            currMonth = 11;
            currYear--;
        }

        renderCalendar();
    }

    function renderCalendar() {
        // 현재 연도와 월 정보를 갱신
        document.getElementById("currentMonth").textContent = `${'${currYear}'}년 ${'${currMonth + 1}'}월`;

        const calendarBody = document.getElementById("calendarBody");
        calendarBody.innerHTML = "";  // 이전 내용 지우기

        // 이번 달의 첫 날과 마지막 날짜 계산
        const firstDay = new Date(currYear, currMonth, 1).getDay();
        const lastDate = new Date(currYear, currMonth + 1, 0).getDate();

        let html = "<tr>";
        for (let i = 0; i < firstDay; i++) {
            html += "<td></td>";  // 첫째 날 전까지 빈 셀 추가
        }

        // 날짜 채우기
        for (let day = 1; day <= lastDate; day++) {
            const todayEvents = [];
            for (let i = 0; i < calenderList.length; i++) {
                const item = calenderList[i];
                const eventDate = new Date(item.date.year,item.date.month-1,item.date.day)
                const formattedDate = eventDate.toLocaleDateString('en-CA');
                // eventDate가 현재 연도, 월, 일과 일치하는지 확인
                if (eventDate.getFullYear() === currYear &&
                    eventDate.getMonth() === currMonth &&
                    eventDate.getDate() === day) {
                    todayEvents.push(item);  // 조건에 맞는 이벤트 추가
                }

            }

            if (todayEvents.length > 0) {
                todayEvents.forEach(event => {
                    const isEditable = userId === event.author.id
                    if(isEditable) {
                        html += `<td style="background-color: lightblue;">${'${day}'}<br>
                          <a href="/modify?cno=${'${event.id}'}" style="color: black; text-decoration: none;">${'${event.author.id}'} : ${'${event.content}'}</a>
                          </td>`;
                    } else {
                        html += `<td>${'${day}'}<br><span>${'${event.author.id}'} : ${'${event.content}'}</span></td>`;
                    }
                });
            } else {
                html += `<td>${'${day}'}</td>`;
            }
            if ((day + firstDay) % 7 === 0) {
                html += "</tr><tr>";  // 한 주가 끝나면 새로운 행 추가
            }
        }


        html += "</tr>";
        calendarBody.innerHTML = html;
    }
    // 초기 렌더링
    renderCalendar();
</script>
</body>
</html>
