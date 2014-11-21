<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager HomePage</title>
 <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
 <script>

</script>
</head>
<body class="Bimage">
<f:view>
<font color="yellow">
<h3><p align="center">Book Search</p></h3>
</font>
<font color="Cyan">
<h:form>
<h3><p align="center">Please enter the date</p></h3>
<h:panelGrid columns="2" styleClass="panelGridCenter">
<h:outputLabel value="Date Picker" id="enterText"></h:outputLabel>
<h:inputText value="#{AggregateTrxInfoMB.trxTimeStr}" id="datepicker"></h:inputText>
<h:commandButton value="Get Monthly Report" actionListener="#{AggregateTrxInfoMB.monthlyAggregateTrxInfo}"></h:commandButton>
<h:commandButton value = "Get Weekly Report" actionListener="#{AggregateTrxInfoMB.weeklyAggregateTrxInfo}"></h:commandButton>
<h:commandButton value = "Get Daily Report" actionListener="#{AggregateTrxInfoMB.dailyAggregateTrxInfo}"></h:commandButton>
</h:panelGrid>
<h:dataTable value="#{AggregateTrxInfoMB.aggregateTrxInfoList}" var="aggregateTrxInfoList" border="1" styleClass="panelGridCenter">
		 <h:column>    				
         <f:facet name="header">
         <h:outputText value="Number Transaction"></h:outputText>
         </f:facet>
         <h:outputText value="#{aggregateTrxInfoList.noOfTrx}"></h:outputText>    				
         </h:column>
		 <h:column>    				
         <f:facet name="header">
         <h:outputText value="Total Transaction Quantity"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{aggregateTrxInfoList.totalTrxQuantity}"></h:outputText> 
         </h:column>
         <h:column>
         <f:facet name="header">
         <h:outputText value="Average Quantity"></h:outputText>
         </f:facet>    				
         <h:outputText value="#{aggregateTrxInfoList.avgTrxQuantity}"></h:outputText>
         </h:column>
         
</h:dataTable>

</h:form>
</font>
</f:view>
</body>
</html>