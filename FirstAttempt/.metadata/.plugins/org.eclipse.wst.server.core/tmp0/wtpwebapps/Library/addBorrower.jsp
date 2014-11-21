<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Borrower</title>
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
<font color="yellow">
<h3><p align="center">Please enter the borrower details</p></h3>
</font>
<font color="Cyan">
<h:form>
<h:panelGrid columns="3"  styleClass="panelGridCenter">
<h:outputLabel value="First Name"></h:outputLabel>
<h:inputText id ="fname" value="#{addBorrowerMB.fname}" required="true" label = "First Name"></h:inputText><h:message errorStyle="color:red; display:block" for="fname"></h:message>
<h:outputLabel value="Last Name: "></h:outputLabel>
<h:inputText id = "lname" value="#{addBorrowerMB.lname}" required="true" label = "Last Name"></h:inputText><h:message errorStyle="color:red; display:block" for="lname"></h:message>
<h:outputLabel value="Address:"></h:outputLabel>
<h:inputText id ="address" value="#{addBorrowerMB.address}" required="true" label = "Address"></h:inputText><h:message errorStyle="color:red; display:block" for="address"></h:message>
<h:outputLabel value="City:"></h:outputLabel>
<h:inputText id ="city" value="#{addBorrowerMB.city}" required="true" label = "City"></h:inputText><h:message errorStyle="color:red; display:block" for="city"></h:message>
<h:outputLabel value="State Code:"></h:outputLabel>
<h:inputText id ="state" value="#{addBorrowerMB.state}" required="true" label = "State"></h:inputText><h:message errorStyle="color:red; display:block" for="state"></h:message>
<h:outputLabel value="Phone Number:"></h:outputLabel>
<h:inputText id ="phoneNumber" value="#{addBorrowerMB.phoneNumber}"  required="true" label = "Phone Number"></h:inputText><h:message errorStyle="color:red; display:block" for="phoneNumber"></h:message>
<h:commandButton value="Submit" action="#{addBorrowerMB.addBorrower}"></h:commandButton>
<h:commandButton value = "Go to home Page" action="#{addBorrowerMB.goBack}"></h:commandButton>
</h:panelGrid>
</h:form>
<h:outputText value="#{addBorrowerMB.message}" style="color: Red; "  styleClass="panelGridCenter"></h:outputText>
</font>
</f:view>
</body>
</html>