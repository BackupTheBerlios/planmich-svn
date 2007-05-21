<%@ page isErrorPage="true" import="java.io.*"%>
<html>
	<head>
		<title>Ausnahme</title>
		<style>
	body, p { font-family:Tahoma; font-size:10pt; padding-left:30; }
	pre { font-size:8pt; }
	</style>
	</head>
	<body>
		<h3>
			Ausnahme
		</h3>

		<font color="red"> <%=exception.toString()%>
			<br> </font>

		<%
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	exception.printStackTrace(pw);
	String str = sw.toString();
	if (str.indexOf("LazyInitializationException") != -1) {
		out.print("<p>");
		out.print("Es gab eine LazyInitializationException.<br/>");
		out.print("Eine mögliche Ursache ist das Fehlen des OpenSessionInViewFilters (siehe Kapitel 9.3.1).<br/>");
		out.print("Der OpenSessionInViewFilter wird in der <code>web.xml</code> aktiviert.");
		out.print("<br/>");
		out.print("Eine andere Ursache kann.<br/>");
		out.print("Dazu muss in der <code>CustomerAction</code> in der Methode <code>getCurrentCustomer</code>");
		out.print(" der Kunde aus der HttpSession mit <code>attach</code> der aktuellen Hibernate-Session hinzugefügt werden (siehe Kapitel 9.3.2.)");
		out.print("</p>");
	} else if (str.indexOf("associate a collection with two open sessions") != -1) {
		out.print("<p>");
		out.print("Es gab ein Problem mit einem gleichzeitigen Zugriff auf ein persistentes Objekt.<br/>");
		out.print("Es wurde versucht ein Objekt mit zwei verschiedenen Hibernate-Sessions zu verbinden.<br/>");
		out.print("Abhilfe schafft hier die Serialisierung der Requests über die HttpSession (siehe Kapitel 9.3.6)</code> aktiviert.");
		out.print("</p>");
	}
	out.print(str);
	sw.close();
	pw.close();
%>

	</body>
</html>
