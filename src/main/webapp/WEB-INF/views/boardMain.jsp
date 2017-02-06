<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>Insert title here</title>
<link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <h3>게시판</h3>
            <table class="table table-hover">
                <colgroup>
                    <col class="col-md-2"/>
                    <col class="col-md-4"/>
                    <col class="col-md-2"/>
                    <col class="col-md-2"/>
                    <col class="col-md-2"/>
                </colgroup>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="dto" >
                        <tr>
                            <td>${dto.idx}</td>
                            <td><a href="/boardRead?idx=${dto.idx}&curPage=${paging.curPage}">${dto.title}</a></td>
                            <td>${dto.writer}</td>
                            <td>${dto.regdate}</td>
                            <td>${dto.count}</td>
                        </tr>
                    </c:forEach>
                </tbody>    
            </table>
        </div>
        <div align="center">
            ${paging.pagingDiv}
        </div>
        <div style="float:right">
            <button class="btn btn-defalt" onclick="fnSubmit()"><span class="glyphicon glyphicon-pencil"></span>글쓰기</button>
        </div>
    </div>

<script>

function fnSubmit(){
    location.replace("/boardRegister");
}

var msg = "${msg}";
if(msg!=null && msg!=""){
    alert(msg);
}

</script>
</body>
</html>