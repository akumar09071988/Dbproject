USE [ots]
GO

/****** Object:  StoredProcedure [dbo].[spMonthlyTrxInfo]    Script Date: 11/21/2014 1:17:02 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--To get the monthly transaction report
CREATE PROCEDURE [dbo].[spMonthlyTrxInfo]
@trxTime date
AS
BEGIN
SELECT COUNT(*) as noOfTrx, SUM(quantity) as totalTrxQuantity, AVG(quantity) as avgTrxQuantity
FROM [ots].[dbo].[OILTRANSACTION] where isCancelled =1 and datename(MONTH,trxtime)=datename(MONTH,@trxTime)
END

GO


