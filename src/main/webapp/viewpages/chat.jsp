<%@ include file="header.jsp" %>
<form action="/chat" id="chatForm" name="chatForm" method="post">
	<textarea rows="30" cols="50" id="prevChat" name="prevChat">
	${chatMessages}
	</textarea>
	<br/>
	<input type="text" name="chatContent" id="chatContent">
	<input type="submit" value="Send" name="submitChat" id="submitChat">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<%@ include file="footer.jsp" %>