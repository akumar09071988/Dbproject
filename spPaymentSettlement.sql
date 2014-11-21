USE [ots]
GO

/****** Object:  StoredProcedure [dbo].[spPaymentSettlement]    Script Date: 11/21/2014 1:18:00 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[spPaymentSettlement]
@cid int,
@trxid int,
@paymentamt float, --0 = sell and 1 = buy
@traderid int --this is same as uid OR different from uid as this trader accepts payment. coz trader who trades on behalf of 
--of a client is also eligible for commission
AS
DECLARE @trxbalance float
SET @trxbalance =(select trxbalance from dbo.oiltransaction where trxid =@trxid)
--if there is pending transaction balance and payment amt less than equal to trxbalance then only insert payment 
begin
IF @trxbalance>0 and @paymentamt<=@trxbalance --so that balance does not become negative
	begin
		INSERT INTO [dbo].[PAYMENT]
           ([paymenttime]
           ,[paymentamt]
           ,[isCancelled]
           ,[cid]
           ,[traderid]
           ,[trxid])
		VALUES
           (GETDATE()
           ,@paymentamt
           ,0 --  default is 0. cancelled = 1
           ,@cid
           ,@traderid
           ,@trxid)
		UPDATE [dbo].[OILTRANSACTION] SET trxbalance=trxbalance-@paymentamt where trxid=@trxid
	end
end





GO


