<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html lang="en">
    <head>
        <title>Survey editor</title>

        <meta name="viewport" content="width=device-width"/>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/knockout/3.4.0/knockout-min.js"></script>
        <script src="https://surveyjs.azureedge.net/1.8.13/survey.ko.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.10/ace.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ace/1.4.10/ext-language_tools.js" type="text/javascript" charset="utf-8"></script>
        <!-- Uncomment to enable Select2 <script src="https://unpkg.com/jquery"></script> <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" /> <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script> -->
        <link href="https://surveyjs.azureedge.net/1.8.13/survey-creator.min.css" type="text/css" rel="stylesheet"/>
        <script src="https://surveyjs.azureedge.net/1.8.13/survey-creator.min.js"></script>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@3.3.7/dist/css/bootstrap.min.css">

    </head>
    <body>
		
        <div id="surveyContainer">
            <div id="creatorElement"></div>
            <div style="text-align: right; margin:8px;">
			<button onclick="sendToServer()" class="btn btn-primary" style="width: -webkit-fill-available;">Save</button>
		</div>
        </div>
		
		<script type="text/javascript"><%@include file="./js/editor.js"%></script>
		<script type="text/javascript">
			creator.JSON = ${json};
		</script>
    </body>
    <input id="surveyId" value="${id}" style="display: none;" />
    <input id="userId" value="${userId}" style="display: none;" />
</html>