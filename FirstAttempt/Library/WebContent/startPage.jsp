<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Management System</title>
<style>               
        .Bimage{
        background-image:url("resources/images/library.jpg"); 
        } 
        .panelGridCenter {
    		margin: 0 auto;
		}       
</style>  
</head>
<body class="Bimage">
<f:view>
<h:form>
<font color="yellow">
<h1><p align="center">Welcome to the Library Management System</p></h1>
</font>
<h:panelGrid columns="1"  border="0" styleClass="panelGridCenter">
<h:commandLink action ="bookSearch.jsp" value="Book Search" disabled="false" style='line-height: normal; color: Cyan; font-style: normal; font-size: 16px; font-family: "Comic Sans MS", Sans-Serif'></h:commandLink>
<h:commandLink action ="addBorrower.jsp" value="Register borrower" disabled="false" style='line-height: normal; color: Cyan; font-style: normal; font-size: 16px; font-family: "Comic Sans MS", Sans-Serif'></h:commandLink>
<h:commandLink action ="bookLoans.jsp" value="Book Loans" disabled="false" style='line-height: normal; color: Cyan; font-style: normal; font-size: 16px; font-family: "Comic Sans MS", Sans-Serif'></h:commandLink>
<h:commandLink action ="fines.jsp" value="Fines" disabled="false" style='line-height: normal; color: Cyan; font-style: normal; font-size: 16px; font-family: "Comic Sans MS", Sans-Serif'></h:commandLink>
</h:panelGrid>
</h:form>
</f:view>
</body>
</html>