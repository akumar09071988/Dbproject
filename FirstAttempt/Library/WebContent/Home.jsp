<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <f:view>
    <h:form>
    <h:panelGrid columns="1" width="100%">
       <h:column>
         <f:verbatim>
           <div style="background-color: blue; height: 60px;">
             
           </div>
         </f:verbatim>
       </h:column>
       <h:column>
        <h:panelGrid columns="6" width="100%" style="padding-left:10px;">
           <h:column>
             <h:outputLabel value="Transaction Type "/>
              <h:selectOneMenu value="#{TransactionMB.transVO.transactionType }">
               <f:selectItem itemLabel="Buy" itemValue="1"/>
               <f:selectItem itemLabel="Sell" itemValue="0"/>
             </h:selectOneMenu> 
           </h:column>
           <h:column>
             <h:outputLabel value="Quantity"/>
             <h:inputText value="#{TransactionMB.transVO.quantity }"/>
           </h:column>
           <h:column>
             <h:outputLabel value="Commission Type" />
              <h:selectOneMenu  value="#{TransactionMB.transVO.commissionType }">
               <f:selectItems value="#{TransactionMB.commTypeList }"/>
             </h:selectOneMenu> 
           </h:column>
           <h:column>
             <h:outputLabel value=""/>            
           </h:column>
        </h:panelGrid>
       </h:column>
       <h:column>
        <h:commandButton value="Submit"  actionListener="#{TransactionMB.transaction }"/>
       </h:column>
       <h:column>
        <h:panelGrid columns="1" width="100%" >
          <h:dataTable value="#{TransactionMB.transList }" width="100%" var="trans">
            <h:column>
             <f:facet name="header">
               <h:outputLabel value="Select"/>
             </f:facet>
             <h:selectBooleanCheckbox value="#{trans.select }"/>
            </h:column>
            <h:column>
             <f:facet name="header">
               <h:outputLabel value="Buy/Sell"/>
             </f:facet>
             <h:outputLabel value="#{trans.trxTypeStr }"/>
            </h:column>
             <h:column>
             <f:facet name="header">
               <h:outputLabel value="Date"/>
             </f:facet>
             <h:outputLabel value="#{trans.trxDate }"/>
            </h:column> 
            <h:column>
             <f:facet name="header">
               <h:outputLabel value="Amount"/>
             </f:facet>
             <h:outputLabel value="#{trans.trxAmount }"/>
            </h:column>
            <h:column>
             <f:facet name="header">
               <h:outputLabel value="Quantity"/>
             </f:facet>
             <h:outputLabel value="#{trans.quantity }"/>
            </h:column>
            <h:column>
             <f:facet name="header">
               <h:outputLabel value="Balance"/>
             </f:facet>
             <h:outputLabel value="#{trans.trxBalance }"/>
            </h:column>
            <h:column>
             <f:facet name="header">
               <h:outputLabel value="Commission Type"/>
             </f:facet>
             <h:outputLabel value="#{trans.commisionTypeStr }"/>
            </h:column>
            <h:column>
             <f:facet name="header">
               <h:outputLabel value="Cancelled"/>
             </f:facet>
             <h:outputLabel value="#{trans.cancelled }"/>
            </h:column>
          </h:dataTable>
        </h:panelGrid>
       </h:column>
       <h:column>
         <h:panelGrid columns="3" width="100%">
           <h:commandButton value="Payment" action="#{TransactionMB.navigateToPayment }"/>
           <h:column>
             <f:verbatim> <div style="width: 30px;"></div></f:verbatim>
           </h:column>
           <h:column>
             <h:commandButton value="Transaction Cancel" actionListener="#{TransactionMB.cancelTransaction }"/>
           </h:column>
         </h:panelGrid>
       </h:column>
    </h:panelGrid>
   </h:form> 
  </f:view>
</body>
</html>