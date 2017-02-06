<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>Insert title here</title>
<link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
var msg = "${msg}";
if(msg!=null && msg!=""){
    alert(msg);
}
</script>
</head>
<body>
    <div class="container">
        <div class="row">
            <h3>게시글 상세보기</h3>
                <table class="table">
                    <tr>
                        <td style="width: 25%;" bgcolor="F7F7F7">제목</td>
                        <td style="width: 75%;" colspan="3">${dto.title}</td>
                    </tr>
                    <tr>
                        <td style="width: 25%" bgcolor="F7F7F7">글번호</td>
                        <td style="width: 25%">${dto.idx}</td>
                        <td style="width: 25%" bgcolor="F7F7F7">조회수</td>
                        <td style="width: 25%">${dto.count}</td>
                    </tr>
                    <tr>
                        <td style="width: 25%" bgcolor="F7F7F7">작성자</td>
                        <td style="width: 25%">${dto.writer}</td>
                        <td style="width: 25%" bgcolor="F7F7F7">등록일</td>
                        <td>${dto.regdate}</td>
                    </tr>
                    <tr>
                        <td colspan="4" style="height:300px">${dto.content}</td>
                    </tr>
                    <tr>
                        <td style="width: 25%; vertical-align: middle;" bgcolor="F7F7F7">첨부파일</td>
                        <td colspan="3">
                            <c:forEach items="${fileList}" var="file">
                                <li><a href="fileDownload?file_idx=${file.file_idx}&curPage=${curPage}&idx=${dto.idx}">${file.org_name}</a></li>
                            </c:forEach>
                        </td>
                    </tr>
            </table>
        </div>
        <div align="right">
            <a href="/boardMain" class="btn btn-default"><span class="glyphicon glyphicon-align-justify"></span>목록</a>
            <a href="/boardUpdate?idx=${dto.idx}&curPage=${curPage}" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>수정</a>
            <a href="/boardDelete?idx=${dto.idx}" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span>삭제</a>
        </div>
    </div>
</body>
</html>