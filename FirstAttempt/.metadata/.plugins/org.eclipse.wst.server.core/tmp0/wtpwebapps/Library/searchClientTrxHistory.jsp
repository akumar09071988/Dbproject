<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Client Transaction History</title>
</head>
<body class="Bimage">
<f:view>
<font color="yellow">
<h3><p align="center">Book Search</p></h3>
</font>
<font color="Cyan">
<h:form>
<h3><p align="center">Please enter the search parameters</p></h3>
<h:panelGrid columns="6" styleClass="panelGridCenter">
<h:outputLabel value="Client ID"></h:outputLabel>
<h:inputText value="#{ClientTrxHistoryMB.cid}"></h:inputText>
<h:outputLabel value="Client Name: "></h:outputLabel>
<h:inputText value="#{ClientTrxHistoryMB.fName}"></h:inputText>
<h:outputLabel value="Client City"></h:outputLabel>
<h:inputText value="#{ClientTrxHistoryMB.city}"></h:inputText>
<h:commandButton value="Submit search" action="#{ClientTrxHistoryMB.clientTrxHistorySearch}"></h:commandButton>
</h:panelGrid>
<h:outputText value="#{ClientTrxHistoryMB.message}" style="color: Red; " styleClass="panelGridCenter"></h:outputText>
<c:if test="${ClientTrxHistoryMB.clientTrxList!=null}">
<h:dataTable value="#{ClientTrxHistoryMB.clientTrxList}" var="clientTrxList" border="1" styleClass="panelGridCenter">
		 <h:column>    				
         <f:facet name="header">
         <h:outputText value="Transaction ID"></h:outputText>
         </f:facet>
         <h:outputText value="#{clientTrxList.trxid}"></h:outputText>    				
         </h:column>
	<%-- 	 <h:column>    				
         <f:facet name="header">
         <h:outputText value="Client ID"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.cid}"></h:outputText> 
         </h:column> --%>
         <h:column>
         <f:facet name="header">
         <h:outputText value="Transaction Time"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.trxTime}"></h:outputText>
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="Quantity"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.quantity}"></h:outputText>
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="TransactionType:0=sell 1=buy"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.trxType}"></h:outputText>
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="Transaction Amount"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.trxAmount}"></h:outputText>
         </h:column>      
         <h:column>
         <f:facet name="header">
         <h:outputText value="Fee Amount"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.feeAmount}"></h:outputText>
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="Fee In Oil"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.feeInOil}"></h:outputText>
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="Commission Type:1=oil 2=cash"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.commType}"></h:outputText>
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="Cancellation Status"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.isCancelled}"></h:outputText>
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="UserId"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{clientTrxList.uid}"></h:outputText>
         </h:column>
</h:dataTable>
</c:if>
</h:form>
</font>
</f:view>
</body>
</html>