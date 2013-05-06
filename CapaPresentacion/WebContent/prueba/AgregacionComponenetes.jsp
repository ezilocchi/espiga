<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
          "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:rich="http://richfaces.org/rich"
      xmlns:f="http://java.sun.com/jsf/core">
<head>
  <title>lalas</title> 
</head>

<body>	
	<div id="menu">			
		
	</div>

	<div id="content">
		<rich:panel>
			<f:facet name="header">
				<ui:insert name="title"></ui:insert>
			</f:facet>
			<ui:insert name="content"></ui:insert>
		</rich:panel>
	</div>
	
	<div id="panel">
		<ui:insert name="panel"/>
	</div>
</body>

</html>