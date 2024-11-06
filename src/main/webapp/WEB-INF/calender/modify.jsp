<%--
  Created by IntelliJ IDEA.
  User: kwakseobang
  Date: 11/6/24
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        /* 폼 컨테이너 중앙 정렬 */
        form[name="registerForm"] {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            width: 300px; /* 너비 지정 */
            margin: 0 auto; /* 중앙 정렬 */
        }

        /* 각 입력 요소 사이에 간격 추가 */
        label, textarea, input[type="submit"] {
            width: 100%;
            margin-bottom: 15px; /* 아래쪽 여백 추가 */
        }

        /* 텍스트 에어리어 스타일 조정 */
        textarea {
            padding: 10px;
            font-family: Arial, sans-serif;
            font-size: 14px;
        }
    </style>
</head>
<body>


<form action="/modify" method="post" name="registerForm">
    <input type="hidden" name="id" value="${dto.id}" />
    <input type="hidden" name="author" value="${dto.author}" />
    <label>일정을 추가할 날짜를 선택하세요.
        <input type="date" name="date" value="${dto.date}" min="2001-01-01" max="2030-12-31"/>
    </label>

    <textarea name="content" id="content"  rows="10"  placeholder="내용을 작성하세요.">${dto.content}</textarea>
    <input type="submit" value="수정"/>
</form>
</body>
</html>
