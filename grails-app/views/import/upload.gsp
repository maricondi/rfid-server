<html>
    <head>
        <title>Import .csv</title>
		<meta name="layout" content="main" />
    </head>
    <body>

		<g:form controller="import" method="post" action="save" enctype="multipart/form-data">
    		<input type="file" name="file"/>
    		<input type="submit"/>
		</g:form>
	</body>
</html>