USE [ots]
GO

/****** Object:  StoredProcedure [dbo].[spDailyTrxInfo]    Script Date: 11/21/2014 1:16:29 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--To get the weekly transaction report
CREATE PROCEDURE [dbo].[spDailyTrxInfo]
@trxTime date
AS
BEGIN
SELECT COUNT(*) as noOfTrx, SUM(quantity) as totalTrxQuantity, AVG(quantity) as avgTrxQuantity
FROM [ots].[dbo].[OILTRANSACTION] where isCancelled =1 and datename(DAY,trxtime)=datename(DAY,@trxTime)
END

GO


