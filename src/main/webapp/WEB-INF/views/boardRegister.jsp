<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<title>Insert title here</title>
<link href="../../resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../../resources/ckeditor/ckeditor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="../../resources/multifile-master/jquery.MultiFile.js"></script>
<script>
    $(function() {  
        $("#file").MultiFile({
            
        });
    });
    
    function fnSubmit(){
        
        var title = $("#inputTitle");
        var writer = $("#inputWriter");
        var content = CKEDITOR.instances.inputContent.getData();
        
        if(title.val() == "" || title.val() == null){
            alert("제목을 입력해주세요.")
            title.focus();
            return;
        }
        else if(writer.val() == "" || writer.val() == null){
            alert("작성자를 입력해주세요.");
            writer.focus();
            return;
        }
        else if(content == "" || content == null){
            alert("내용을 입력해주세요.");
            content.focus();
            return;
        }
        else{
            $("form").submit();   
        }
        
    }
</script>
</head>
<body>
    <div class="container">
        <div class="row">
            <h3>게시글 작성</h3>
            <form name="fm1" action="/boardRegister.do" method="post" enctype="multipart/form-data">
                <table class="table">
                    <tr>
                        <td>제목</td>
                        <td><input type="text" id="inputTitle" name="title"/></td>
                    </tr>
                    <tr>
                        <td>작성자</td>
                        <td><input type="text" id="inputWriter" name="writer"/></td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td>
                            <textarea rows="10" cols="100" id="inputContent" name="content"></textarea>
                            <script type="text/javascript">
                            window.onload = function(){
                                CKEDITOR.replace('inputContent', {
                                    customConfig : "../../resources/ckeditor/ckeditor.js",
                                    width : '100%',
                                    height: 300,
                                    filebrowserUploadUrl: "/imageUpload"
                                });
                                CKEDITOR.editorConfig = function(config){
                                    config.font_defaultLabel = 'Gulim';
                                    config.font_names = 'Gulim/Gulim;Dotum/Dotum;Batang/Batang;Gungsuh/Gungsuh;Arial/Arial;Tahoma/Tahoma;Verdana/Verdana';
                                    config.fontSize_defaultLabel = '12px';
                                    config.fontSize_sizes = '8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;';
                                    config.language = "ko";
                                    config.resize_enabled = false;
                                    config.enterMode = CKEDITOR.ENTER_BR;
                                    config.shiftEnterMode = CKEDITOR.ENTER_P;
                                    config.startupFocus = true;
                                    config.uiColor = '#EEEEEE';
                                    config.toolbarCanCollapse = false;
                                    config.menu_subMenuDelay = 0;
                                    config.toolbar = [['Bold','Italic','Underline','Strike','-','TextColor','BGColor','-','JustifyLeft','JustifyCenter','JustifyRight','-','Image','Flash'],['Font','FontSize','Undo','Redo']];
                                };
                            }
                            </script>
                        </td>
                    </tr>
                    <tr>
                        <td>파일첨부</td>
                        <td><input type="file" id="file" name="uploadFile" multiple/></td>                    
                    </tr>
                </table>
            </form>
        </div>
        <div align="right">
            <a href="/boardMain" class="btn btn-default"><span class="glyphicon glyphicon-align-justify"></span>목록</a>
            <button class="btn btn-default" onclick="fnSubmit()"><span class="glyphicon glyphicon-pencil"></span>등록</button>
            <a href="/boardMain" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span>취소</a>
        </div>
    </div>
</body>
</html>