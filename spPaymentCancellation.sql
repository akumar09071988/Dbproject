USE [ots]
GO

/****** Object:  StoredProcedure [dbo].[spPaymentCancellation]    Script Date: 11/21/2014 1:17:42 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[spPaymentCancellation]
--should we keep track of the history of who (traderid) cancelled the payment). If yes then payment column must have 1 extra col
@pid int
AS
BEGIN
DECLARE @paymentamount float
SET @paymentamount =(select paymentamt from dbo.payment where pid =@pid)
--UPDATE trxbalance in the oiltransaction table
UPDATE [dbo].[OILTRANSACTION] SET trxbalance = trxbalance+@paymentamount
UPDATE [dbo].[PAYMENT] SET isCancelled = 1 where pid=@pid;

END
GO


